package com.example.database_user.domain.service;

import com.example.database_user.configs.security.auth_messages.AuthenticationRequest;
import com.example.database_user.configs.security.auth_messages.AuthenticationReset;
import com.example.database_user.configs.security.auth_messages.AuthenticationResponse;
import com.example.database_user.configs.security.auth_messages.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserService {

  AuthenticationResponse register(RegisterRequest registerRequest);

  AuthenticationResponse authenticate(AuthenticationRequest authenticaitonRequest);

  AuthenticationResponse changePassword(AuthenticationReset authenticationReset);
}
