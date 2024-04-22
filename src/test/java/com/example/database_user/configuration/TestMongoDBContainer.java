package com.example.database_user.configuration;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;


@Testcontainers
public class TestMongoDBContainer {

  private static final String IMAGE_VERSION = "mongo:4.4.6";

  @ServiceConnection
  @Container
  static MongoDBContainer mongoContainer = new MongoDBContainer(IMAGE_VERSION);
  private static TestMongoDBContainer instance;

  public static TestMongoDBContainer getInstance() {
    if (instance == null) {
      instance = new TestMongoDBContainer();
      mongoContainer.withEnv("MONGO_INITDB_ROOT_USERNAME", "test");
      mongoContainer.withEnv("MONGO_INITDB_ROOT_PASSWORD", "test");
      mongoContainer.withEnv("MONGO_INITDB_DATABASE", "management");
      mongoContainer.withCopyFileToContainer(
          MountableFile.forClasspathResource("init-script.js"),
          "/docker-entrypoint-initdb.d/init-mongo.js");
      instance.start();
    }
    return instance;
  }

  public void start() {
    mongoContainer.start();
    System.setProperty("DB_URL", mongoContainer.getReplicaSetUrl());
    System.setProperty("DB_USERNAME", "test");
    System.setProperty("DB_PASSWORD", "test");
  }
}
