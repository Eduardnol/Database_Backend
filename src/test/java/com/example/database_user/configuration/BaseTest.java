package com.example.database_user.configuration;

import com.example.database_user.clock.ClockProvider;
import com.example.database_user.configs.security.JWTService;
import com.example.database_user.model.dto.AuthUserDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTest extends TestMongoDBContainer {

  protected String token;
  @Autowired
  private JWTService jwtService;
  @Autowired
  private ClockProvider clockProvider;

  @BeforeAll
  static void startContainer() {
    TestMongoDBContainer.getInstance();
  }

  @AfterAll
  static void stopContainer() {
    TestMongoDBContainer.getInstance().stop();
  }

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
