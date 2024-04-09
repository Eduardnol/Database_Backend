package com.example.database_user.configuration;

import java.util.Arrays;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class TestMongoDBContainer extends GenericContainer<TestMongoDBContainer> {

  private static final Integer CONTAINER_PORT = 27017;
  private static final Integer HOST_PORT = 27017;
  private static TestMongoDBContainer container;

  public TestMongoDBContainer() {
    super(DockerImageName.parse("mongo:4.4.6"));
    withExposedPorts(CONTAINER_PORT);
    setPortBindings(Arrays.asList(HOST_PORT + ":" + CONTAINER_PORT));
    withEnv("MONGO_INITDB_ROOT_USERNAME", "test");
    withEnv("MONGO_INITDB_ROOT_PASSWORD", "test");
    withEnv("MONGO_INITDB_DATABASE", "management");
    withCopyFileToContainer(MountableFile.forClasspathResource("init-script.js"),
        "/docker-entrypoint-initdb.d/init-mongo.js");
  }

  public static TestMongoDBContainer getInstance() {
    if (container == null) {
      container = new TestMongoDBContainer();
    }
    return container;
  }


}
