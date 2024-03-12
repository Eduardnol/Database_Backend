package com.example.database_user.configuration;

import com.example.database_user.clock.ClockProvider;
import com.example.database_user.configs.security.JWTService;
import com.example.database_user.model.dto.AuthUserDTO;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTest {

  protected String token;
  @Autowired
  private JWTService jwtService; // Autowired instance with active profile
  @Autowired
  private ClockProvider clockProvider;

  @BeforeEach
  public void generateToken() {
    AuthUserDTO userDetailsDTO = AuthUserDTO.builder()
        .email("test@example.com")
        .password("testPassword")
        .build();
    token = jwtService.generateToken(userDetailsDTO);
  }

  @AfterEach
  public void tearDown() {
    this.clockProvider.setClock(Clock.systemDefaultZone());
  }

  protected void setCurrentTime(final LocalDateTime date) {
    this.clockProvider.setClock(
        Clock.fixed(date.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault()));
  }


  protected Resource jsonResource(final String name) {
    return new ClassPathResource("/json/" + name + ".json");
  }


  @Test
  public void test_ok() {
    Assertions.assertTrue(true);
  }
}
