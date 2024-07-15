package com.example.database_user.configuration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@SpringBootTest
@Testcontainers
public class TestMongoDBContainer {

  private static final String IMAGE_VERSION = "mongo:4.4.6";

  @Container
  @ServiceConnection
  static MongoDBContainer mongoContainer = new MongoDBContainer(
      DockerImageName.parse(IMAGE_VERSION))
      .withCopyFileToContainer(
          MountableFile.forClasspathResource("init-script.js", 777),
          "./data/mongo-init:/docker-entrypoint-initdb.d/init-mongo.js");

}
