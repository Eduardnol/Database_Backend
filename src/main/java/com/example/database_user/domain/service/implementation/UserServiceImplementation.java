package com.example.database_user.domain.service.implementation;

import com.example.database_user.configs.security.JWTService;
import com.example.database_user.configs.security.auth.AuthenticationRequest;
import com.example.database_user.configs.security.auth.AuthenticationResponse;
import com.example.database_user.configs.security.auth.RegisterRequest;
import com.example.database_user.controllers.dto.UserDTO;
import com.example.database_user.controllers.enums.Role;
import com.example.database_user.domain.model.mapper.UserMapper;
import com.example.database_user.domain.service.UserService;
import com.example.database_user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;


  @Override
  public AuthenticationResponse register(RegisterRequest registerRequest) {
    var user = UserDTO.builder()
        .name(registerRequest.getName())
        .surname(registerRequest.getSurname())
        .email(registerRequest.getEmail())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .role(Role.USER)
        .build();
    userRepository.save(userMapper.toEntity(user));

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
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

    var jwtToken = jwtService.generateToken(userMapper.toDTO(user));
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
