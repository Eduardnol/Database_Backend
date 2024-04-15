package com.example.database_user.configuration;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.MountableFile;

public class TestMongoDBContainer extends MongoDBContainer {

  private static final String IMAGE_VERSION = "mongo:4.4.6";
  //private static final Integer CONTAINER_PORT = 27017;
  private static TestMongoDBContainer container;

  private TestMongoDBContainer() {
    super(IMAGE_VERSION);
  }

  public static TestMongoDBContainer getInstance() {
    if (container == null) {
      container = new TestMongoDBContainer();
      container.withEnv("MONGO_INITDB_ROOT_USERNAME", "test");
      container.withEnv("MONGO_INITDB_ROOT_PASSWORD", "test");
      container.withEnv("MONGO_INITDB_DATABASE", "management");
      container.withCopyFileToContainer(MountableFile.forClasspathResource("init-script.js"),
          "/docker-entrypoint-initdb.d/init-mongo.js");
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
