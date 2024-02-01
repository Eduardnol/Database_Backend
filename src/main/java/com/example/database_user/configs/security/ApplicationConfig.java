package com.example.database_user.configs.security;

import com.example.database_user.domain.model.mapper.UserMapper;
import com.example.database_user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username).map(userMapper::toDTO)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
