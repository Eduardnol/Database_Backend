package com.example.database_user.configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

// https://medium.com/@p.giannone333/populating-mongodb-for-integration-testing-made-easy-leveraging-the-power-of-initialization-86c9422ad619
@Testcontainers
@Configuration
public class TestMongoDBContainer {

  private static final String USERNAME = "test";
  private static final String PASSWORD = "test";
  private static final String TEST_DATABASE = "management";
  private static final String URI = "mongodb://test:test@localhost:27017/user_management";
  private static final Integer PORT = 27017;

  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer(
      DockerImageName.parse("mongo:4.0.10")).withExposedPorts(PORT)
      .withCopyFileToContainer(MountableFile.forClasspathResource("./init-script.js"),
          "/docker-entrypoint-initdb.d/init.js").withPrivilegedMode(true)
      .withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
      .withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD)
      .withEnv("MONGO_INITDB_DATABASE", TEST_DATABASE)
      //.withEnv("MONGO_INITDB_URI", URI)
      .waitingFor(Wait.forLogMessage(".*waiting for connections on port.*\\n", 1));

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.databaseName", () -> TEST_DATABASE);
    registry.add("spring.data.mongodb.username", () -> USERNAME);
    registry.add("spring.data.mongodb.password", () -> PASSWORD);
  }

  @BeforeAll
  static void beforeAll() {
    mongoDBContainer.start();
  }

  @AfterAll
  static void afterAll() {
    mongoDBContainer.stop();
  }

}
