package com.example.database_user.configuration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@SpringBootTest
@Testcontainers
public abstract class TestMongoDBContainer {

  protected static final MongoDBContainer mongoDBContainer;
  private static final String IMAGE_VERSION = "mongo:4.4.6";

  static {
    mongoDBContainer = new MongoDBContainer(
        DockerImageName.parse(IMAGE_VERSION))
        .withCopyFileToContainer(
            MountableFile.forClasspathResource("init-script.js", 777),
            "./data/mongo-init:/docker-entrypoint-initdb.d/init-mongo.js");
    mongoDBContainer.start();

  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }


}
