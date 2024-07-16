package com.example.database_user.configuration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
public abstract class TestMongoDBContainer {

  protected static final GenericContainer mongoDBContainer;
  private static final String IMAGE_VERSION = "mongo:6.0.7";

  static {
    mongoDBContainer = new GenericContainer(
        DockerImageName.parse(IMAGE_VERSION))
/*        .withCopyFileToContainer(
            MountableFile.forClasspathResource("init-script.js", 777),
            "docker-entrypoint-initdb.d/init-mongo.js");*/
        .withExposedPorts(27017);
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
