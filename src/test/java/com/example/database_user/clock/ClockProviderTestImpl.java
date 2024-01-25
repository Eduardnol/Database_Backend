package com.example.database_user.clock;

import java.time.Clock;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class ClockProviderTestImpl implements ClockProvider {

  private Clock clock;

  public ClockProviderTestImpl() {
    this.clock = Clock.systemDefaultZone();
  }

  @Override
  public void setClock(final Clock clockParam) {
    this.clock = clockParam;
  }

  @Override
  public Clock getUTCClock() {
    return this.clock;
  }

  @Override
  public Clock getClock() {
    return clock;
  }
}
