package com.example.database_user.domain.service;

import com.example.database_user.configs.security.auth.AuthenticationRequest;
import com.example.database_user.configs.security.auth.AuthenticationResponse;
import com.example.database_user.configs.security.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  AuthenticationResponse register(RegisterRequest registerRequest);

  AuthenticationResponse authenticate(AuthenticationRequest authenticaitonRequest);

}
