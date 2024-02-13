package com.example.database_user.controllers.api;

import com.example.database_user.configs.security.auth_messages.AuthenticationRequest;
import com.example.database_user.configs.security.auth_messages.AuthenticationReset;
import com.example.database_user.configs.security.auth_messages.AuthenticationResponse;
import com.example.database_user.configs.security.auth_messages.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AuthUser", description = "Register and authenticate users")
@RestController
@RequestMapping("/api/v1/auth")
public interface AuthUserAPI {

  @Operation(summary = "Register a new user")
  @PostMapping("/register")
  ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest registerRequest);

  @Operation(summary = "Authenticate a user")
  @PostMapping("/authenticate")
  ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody @Valid AuthenticationRequest authenticateRequest);

  @Operation(summary = "Change password")
  @PostMapping("/change-password")
  ResponseEntity<AuthenticationResponse> changePassword(
      @RequestBody @Valid AuthenticationReset authenticationReset);

}
