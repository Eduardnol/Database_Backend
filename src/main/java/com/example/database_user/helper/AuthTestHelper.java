package com.example.database_user.helper;

import com.example.database_user.model.dto.AuthUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthTestHelper implements UserDetailsService {

  public static final String FAKE_JWT = "eyJhbGciOiJIUzUxMiJ9"
      + ".eyJpc3MiOiJnZXN0b3AiLCJzdWIiOiJ3cmV3ciIsInVzZXJuYW1lIjoid3Jld3IiLCJhdXRob3JpdGllcyI6IlJPTEVfQURNIiwiem9u"
      + "ZUNvZGUiOiJDQVRCQUwiLCJ6b25lIjoiQ0FUQkFMIiwidXNlcklkIjoiMSIsImZ1bGxOYW1lIjoiRGVidWcgVXNlciIsImVtYWlsIjoi"
      + "ZGVidWdAc2Vuc2VkaS5jb20iLCJ1c3VhcmlvQ29uZmlndXJhZG9yIjp0cnVlLCJpYXQiOjE2MTc3OTI5ODUsImV4cCI6MTYxNzc5NDQ4NX0"
      + ".m4XMdA1AQ9Y1q4s_tWwbrxVeSh9NQazP33ZfybdRtlKbHbWIpf43L3vJjwDyrWaoiAcw1NNimtF8unDU3bEy2Q";

  @Override
  public AuthUserDTO loadUserByUsername(final String username) throws UsernameNotFoundException {
    return AuthUserDTO.builder()
        .email("test")
        .build();
  }
}
