package com.example.database_user.domain.service.implementation;

import com.example.database_user.configs.security.JWTService;
import com.example.database_user.configs.security.auth_messages.AuthenticationRequest;
import com.example.database_user.configs.security.auth_messages.AuthenticationResponse;
import com.example.database_user.configs.security.auth_messages.RegisterRequest;
import com.example.database_user.controllers.dto.AuthUserDTO;
import com.example.database_user.controllers.enums.Role;
import com.example.database_user.domain.model.mapper.AuthUserMapper;
import com.example.database_user.domain.service.AuthUserService;
import com.example.database_user.exception.UserAlreadyExistsException;
import com.example.database_user.repositories.AuthUserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthUserServiceImplementation implements AuthUserService {

  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthUserMapper authUserMapper;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;


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

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    //They are both username and password correct at this point
    AuthUserDTO user = authUserMapper.toDTO(
        authUserRepository.findByEmail(request.getEmail()).orElseThrow());

    user.getLoginHistory().add(LocalDateTime.now());

    authUserRepository.save(authUserMapper.toEntity(user));

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
