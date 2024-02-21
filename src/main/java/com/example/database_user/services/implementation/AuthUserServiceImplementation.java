package com.example.database_user.services.implementation;

import com.example.database_user.configs.security.JWTService;
import com.example.database_user.configs.security.auth_messages.AuthenticationRequest;
import com.example.database_user.configs.security.auth_messages.AuthenticationReset;
import com.example.database_user.configs.security.auth_messages.AuthenticationResponse;
import com.example.database_user.configs.security.auth_messages.RegisterRequest;
import com.example.database_user.model.dto.AuthUserDTO;
import com.example.database_user.model.enums.Role;
import com.example.database_user.model.mapper.AuthUserMapper;
import com.example.database_user.services.AuthUserService;
import com.example.database_user.services.exception.PasswordMismatchException;
import com.example.database_user.services.exception.PasswordReusedException;
import com.example.database_user.services.exception.UserAlreadyExistsException;
import com.example.database_user.services.exception.WrongEmailOrPasswordException;
import com.example.database_user.repositories.AuthUserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthUserServiceImplementation implements AuthUserService, UserDetailsService {

  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthUserMapper authUserMapper;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    AuthUserDTO userDTO = authUserRepository.findByEmail(email).map(authUserMapper::toDTO)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(userDTO.getRole().name()));

    return new User(userDTO.getUsername(), userDTO.getPassword(), authorities);
  }

  @Override
  public AuthenticationResponse register(RegisterRequest registerRequest) {
    String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
    var user = AuthUserDTO.builder()
        .name(registerRequest.getName())
        .surname(registerRequest.getSurname())
        .email(registerRequest.getEmail())
        .role(Role.USER)
        .password(encodedPassword)
        .passwordHistory(encodedPassword)
        .passwordUpdatedAt(LocalDateTime.now())
        .createdAt(LocalDateTime.now())
        .loginHistory(LocalDateTime.now())
        .isLocked(false)
        .build();
    authUserRepository.findByEmail(registerRequest.getEmail()).ifPresent(u -> {
      throw new UserAlreadyExistsException();
    });
    authUserRepository.save(authUserMapper.toEntity(user));

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getEmail(),
              request.getPassword()
          )
      );
    } catch (Exception e) {
      throw new WrongEmailOrPasswordException();
    }
    //They are both username and password correct at this point
    AuthUserDTO user = authUserMapper.toDTO(
        authUserRepository.findByEmail(request.getEmail()).orElseThrow());
    if (user.getIsLocked()) {
      throw new WrongEmailOrPasswordException();
    } else {
      user.setIsLocked(false);
    }

    List<LocalDateTime> loginHistory = new ArrayList<>(user.getLoginHistory());
    loginHistory.add(LocalDateTime.now());
    user.setLoginHistory(loginHistory);

    authUserRepository.save(authUserMapper.toEntity(user));

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  @Override
  public AuthenticationResponse changePassword(AuthenticationReset authenticationReset) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationReset.getEmail(),
              authenticationReset.getOldPassword()
          )
      );
      if (authenticationReset.getOldPassword().equals(authenticationReset.getNewPassword())) {
        throw new PasswordReusedException();
      }
      if (authenticationReset.getNewPassword()
          .equals(authenticationReset.getConfirmNewPassword())) {
        String encodedPassword = passwordEncoder.encode(authenticationReset.getNewPassword());
        AuthUserDTO user = authUserMapper.toDTO(
            authUserRepository.findByEmail(authenticationReset.getEmail()).orElseThrow());

        user.setPassword(encodedPassword);
        List<String> passHistory = new ArrayList<>(user.getPasswordHistory());
        passHistory.add(encodedPassword);
        user.setPasswordHistory(passHistory);
        user.setPasswordUpdatedAt(LocalDateTime.now());
        authUserRepository.save(authUserMapper.toEntity(user));
      } else {
        throw new PasswordMismatchException();
      }

    } catch (Exception e) {
      throw new WrongEmailOrPasswordException();
    }
    return null;
  }
}
