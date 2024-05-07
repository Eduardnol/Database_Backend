package com.example.database_user.configuration;

import com.example.database_user.configs.security.JWTService;
import com.example.database_user.model.dto.AuthUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@SpringBootTest
@Testcontainers
public class BaseTest {

  private static final String IMAGE_VERSION = "mongo:4.4.6";

  @Container
  @ServiceConnection
  static MongoDBContainer mongoContainer = new MongoDBContainer(
      DockerImageName.parse(IMAGE_VERSION))
      .withCopyFileToContainer(
          MountableFile.forClasspathResource("init-script.js"),
          "/docker-entrypoint-initdb.d/init-mongo.js");

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
    System.out.println("Test ok");
    int six = 6;
    Assertions.assertTrue(true);
  }
}
