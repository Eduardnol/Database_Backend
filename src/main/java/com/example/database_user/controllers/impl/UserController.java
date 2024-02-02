package com.example.database_user.controllers.impl;

import com.example.database_user.configs.security.auth.AuthenticationRequest;
import com.example.database_user.configs.security.auth.AuthenticationResponse;
import com.example.database_user.configs.security.auth.RegisterRequest;
import com.example.database_user.controllers.api.UserAPI;
import com.example.database_user.domain.service.UserService;
import com.example.database_user.domain.service.implementation.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Log4j2
public class UserController implements UserAPI {

  private final UserServiceImplementation userService;

  @Override
  public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
    log.info("Registering user {}", registerRequest.getEmail());
    return ResponseEntity.ok(userService.register(registerRequest));
  }

  @Override
  public ResponseEntity<AuthenticationResponse> authenticate(
      AuthenticationRequest authenticateRequest) {
    log.info("Authenticating user {}", authenticateRequest.getEmail());
    return ResponseEntity.ok(userService.authenticate(authenticateRequest));

  }
}
