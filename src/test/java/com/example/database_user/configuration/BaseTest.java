package com.example.database_user.configuration;

import com.example.database_user.clock.ClockProvider;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.testcontainers.containers.MongoDBContainer;

/*
@ExtendWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@WithUserDetails(value = "test", userDetailsServiceBeanName = "authHelper")
public class BaseTest {

  @Autowired
  private ClockProvider clockProvider;

  @ClassRule
  public static MongoDBContainer mongoDBContainer = TestMongoDBContainer.getInstance();

  @BeforeAll
  static void arrancaContenedor() {
    mongoDBContainer.start();
  }

  @AfterEach
  public void tearDown() {
    this.clockProvider.setClock(Clock.systemDefaultZone());
  }

  protected void setCurrentTime(final LocalDateTime date) {
    this.clockProvider.setClock(Clock.fixed(date.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault()));
  }


  protected Resource jsonResource(final String name) {
    return new ClassPathResource("/json/" + name + ".json");
  }


  @Test
  public void test_ok() {
    Assertions.assertTrue(true);
  }
}*/
