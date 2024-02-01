package com.example.database_user.controllers.impl;

import com.example.database_user.configs.security.auth.AuthenticationRequest;
import com.example.database_user.configs.security.auth.AuthenticationResponse;
import com.example.database_user.configs.security.auth.RegisterRequest;
import com.example.database_user.controllers.api.UserAPI;
import com.example.database_user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class UserController implements UserAPI {

  UserService userService;

  @Override
  public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
    return ResponseEntity.ok(userService.register(registerRequest));
  }

  @Override
  public ResponseEntity<AuthenticationResponse> register(AuthenticationRequest registerRequest) {
    return null;
  }
}
