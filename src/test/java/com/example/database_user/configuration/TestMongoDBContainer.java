package com.example.database_user.configuration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

// https://medium.com/@p.giannone333/populating-mongodb-for-integration-testing-made-easy-leveraging-the-power-of-initialization-86c9422ad619
@Testcontainers
public class TestMongoDBContainer {

  @Container
  public static MongoDBContainer mongoDBContainer =
      new MongoDBContainer("mongo:4.0.10")
          .withExposedPorts(27017)
          .withCopyFileToContainer(MountableFile.forClasspathResource("./init-schema.js")
              , "/docker-entrypoint-initdb.d/init-script.js");

  static {
    mongoDBContainer.start();
    var mappedPort = mongoDBContainer.getMappedPort(27017);
    System.setProperty("spring.data.mongodb.uri", "mongodb://localhost:" + mappedPort);
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri",
        () -> "mongodb://localhost:" + mongoDBContainer.getMappedPort(27017));
    registry.add("spring.data.mongodb.databaseName", () -> "user_management");
    registry.add("spring.data.mongodb.username", () -> "test_container");
    registry.add("spring.data.mongodb.password", () -> "test_container");

  }
}
