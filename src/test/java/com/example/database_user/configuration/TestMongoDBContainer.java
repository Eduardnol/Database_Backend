package com.example.database_user.configuration;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.MountableFile;

public class TestMongoDBContainer extends MongoDBContainer {

  private static final Integer CONTAINER_PORT = 27017;
  private static TestMongoDBContainer container;

  public TestMongoDBContainer() {
    super("mongo:4.4.6");
    withExposedPorts(CONTAINER_PORT);
    withEnv("MONGO_INITDB_ROOT_USERNAME", "test");
    withEnv("MONGO_INITDB_ROOT_PASSWORD", "test");
    withEnv("MONGO_INITDB_DATABASE", "management");
    withCopyFileToContainer(MountableFile.forClasspathResource("init-script.js"),
        "/docker-entrypoint-initdb.d/init-mongo.js");
  }

  public static TestMongoDBContainer getInstance() {
    if (container == null) {
      container = new TestMongoDBContainer();
      container.start();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("DB_URL", container.getReplicaSetUrl());
    System.setProperty("DB_USERNAME", "test");
    System.setProperty("DB_PASSWORD", "test");
  }
}
