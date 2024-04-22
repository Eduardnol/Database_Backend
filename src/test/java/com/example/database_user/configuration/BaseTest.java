package com.example.database_user.configuration;

import com.example.database_user.configs.security.JWTService;
import com.example.database_user.model.dto.AuthUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BaseTest {

  static {
    TestMongoDBContainer.getInstance();
  }

  protected String token;
  @Autowired
  private JWTService jwtService;

  @BeforeEach
  public void generateToken() {
    AuthUserDTO userDetailsDTO = AuthUserDTO.builder()
        .email("test@example.com")
        .password("testPassword")
        .build();
    token = jwtService.generateToken(userDetailsDTO);
  }

  @Test
  public void test_ok() {
    Assertions.assertTrue(true);
  }
}
