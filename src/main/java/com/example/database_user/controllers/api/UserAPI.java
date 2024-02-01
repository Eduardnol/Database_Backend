package com.example.database_user.controllers.api;

import com.example.database_user.configs.security.auth.AuthenticationRequest;
import com.example.database_user.configs.security.auth.AuthenticationResponse;
import com.example.database_user.configs.security.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public interface UserAPI {

  @PostMapping("/register")
  ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest);

  @PostMapping("/authenticate")
  ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest authenticateRequest);


}
