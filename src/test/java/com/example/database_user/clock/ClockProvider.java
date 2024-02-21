package com.example.database_user.clock;

import java.time.Clock;


/**
 * To be able to change clock on tests.
 */
public interface ClockProvider {

  /**
   * Gets a clock.
   *
   * @return current system clock.
   */
  Clock getClock();

  /**
   * Gets the UTC clock.
   *
   * @return UTC clock.
   */
  Clock getUTCClock();

  /**
   * Set clock.
   *
   * @param clock clock to set (throws {@link UnsupportedOperationException})
   */
  void setClock(Clock clock);

}
