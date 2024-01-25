package com.example.database_user.configuration;

import es.mdp.led.clock.ClockProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@WithUserDetails(value = "test", userDetailsServiceBeanName = "authHelper")
public class BaseTest {

  @Autowired
  private ClockProvider clockProvider;

  @ClassRule
  public static MySQLContainer<?> mySQLContainer = TestMySqlContainer.getInstance();

  @BeforeAll
  static void arrancaContenedor() {
    mySQLContainer.start();
  }

  @After
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
    Assert.assertTrue(true);
  }
}
