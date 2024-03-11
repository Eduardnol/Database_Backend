package com.example.database_user.configuration;

import com.example.database_user.clock.ClockProvider;
import com.example.database_user.configs.properties.JwtProperties;
import com.example.database_user.configs.security.JWTService;
import com.example.database_user.model.dto.AuthUserDTO;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(properties = "spring.config.name=application-test")
@TestPropertySource(locations = "classpath:application-test.yaml")
@ContextConfiguration(classes = {JwtProperties.class, JWTService.class, ClockProvider.class})
@ActiveProfiles("test")
public class BaseTest {

  protected static String token;
  @Autowired
  private ClockProvider clockProvider;
/*
  @ClassRule
  public static MongoDBContainer mongoDBContainer = TestMongoDBContainer.getInstance();*/

  @BeforeAll
  static void arrancaContenedor() {
    JWTService jwtService = new JWTService();
    // mongoDBContainer.start();
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
