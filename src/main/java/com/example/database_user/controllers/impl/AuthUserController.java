package com.example.database_user.controllers.impl;

import com.example.database_user.configs.security.auth_messages.AuthenticationRequest;
import com.example.database_user.configs.security.auth_messages.AuthenticationReset;
import com.example.database_user.configs.security.auth_messages.AuthenticationResponse;
import com.example.database_user.configs.security.auth_messages.RegisterRequest;
import com.example.database_user.controllers.api.AuthUserAPI;
import com.example.database_user.services.implementation.AuthUserServiceImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Log4j2
public class AuthUserController implements AuthUserAPI {

  private final AuthUserServiceImplementation authService;

  @Override
  public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
    log.info("Registering user {}", registerRequest.getEmail());
    return ResponseEntity.ok(authService.register(registerRequest));
  }

  @Override
  public ResponseEntity<AuthenticationResponse> authenticate(
      AuthenticationRequest authenticateRequest) {
    log.info("Authenticating user {}", authenticateRequest.getEmail());
    return ResponseEntity.ok(authService.authenticate(authenticateRequest));

  }

  @Override
  public ResponseEntity<AuthenticationResponse> changePassword(
      AuthenticationReset authenticationReset) {
    return ResponseEntity.ok(authService.changePassword(authenticationReset));
  }
}
