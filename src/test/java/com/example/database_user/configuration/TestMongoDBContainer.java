package com.example.database_user.configuration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;


@Testcontainers
public class TestMongoDBContainer {

  //protected static final GenericContainer mongoDBContainer;
  private static final String IMAGE_VERSION = "mongo:6.0.7";

  @Container
  public static final GenericContainer<?> mongoDBContainer = new GenericContainer<>(
      DockerImageName.parse(IMAGE_VERSION))
      .withCopyFileToContainer(
          MountableFile.forClasspathResource("init-script.js"),
          "docker-entrypoint-initdb.d/init-mongo.js")
      .withExposedPorts(27017);

  static {
    mongoDBContainer.start();
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    String uri =
        "mongodb://" + mongoDBContainer.getHost() + ":" + mongoDBContainer.getMappedPort(27017)
            + "/testContainer";
    registry.add("spring.data.mongodb.uri", () -> uri);
  }


}
