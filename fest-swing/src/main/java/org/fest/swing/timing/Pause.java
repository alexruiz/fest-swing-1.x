/*
 * Created on Dec 19, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.timing;

import static org.fest.swing.util.TimeoutWatch.startWatchWithTimeoutOf;
import static org.fest.util.Arrays.format;
import static org.fest.util.Arrays.isEmpty;
import static org.fest.util.Strings.concat;

import java.util.concurrent.TimeUnit;

import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.util.TimeoutWatch;

/**
 * Understands waiting for period of time or for a particular condition to be satisfied.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class Pause {

  private static final int DEFAULT_DELAY = 30000;
  private static final int SLEEP_INTERVAL = 10;

  /**
   * Waits until the given condition is satisfied.
   * @param condition the condition to verify.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws WaitTimedOutError if the wait times out (more than 30 seconds).
   */
  public static void pause(Condition condition) {
    pause(condition, DEFAULT_DELAY);
  }

  /**
   * Waits until the given condition is satisfied.
   * @param condition the condition to verify.
   * @param timeout the timeout.
   * @throws NullPointerException if the given timeout is <code>null</code>.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws WaitTimedOutError if the wait times out.
   */
  public static void pause(Condition condition, Timeout timeout) {
    if (timeout == null) throw new NullPointerException("The given timeout should not be null");
    pause(condition, timeout.duration());
  }

  /**
   * Waits until the given condition is satisfied.
   * @param condition the condition to verify.
   * @param timeout the timeout (in milliseconds.)
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws WaitTimedOutError if the wait times out.
   */
  public static void pause(Condition condition, long timeout) {
    if (condition == null) throw new NullPointerException("The condition to verify should not be null");
    TimeoutWatch watch = startWatchWithTimeoutOf(timeout);
    while (!condition.test()) {
      if (watch.isTimeOut() && !condition.test()) {
        condition.done();
        throw timeoutExpired(condition);
      }
      pause(SLEEP_INTERVAL);
    }
    condition.done();
  }

  private static WaitTimedOutError timeoutExpired(Condition condition) {
    return new WaitTimedOutError((concat("Timed out waiting for ", condition)));
  }

  /**
   * Waits until the given conditions are satisfied.
   * @param conditions the conditions to verify.
   * @throws NullPointerException if the array of conditions is <code>null</code>.
   * @throws IllegalArgumentException if the array of conditions is empty.
   * @throws NullPointerException if the array of conditions has one or more <code>null</code> values.
   * @throws WaitTimedOutError if the wait times out (more than 30 seconds).
   */
  public static void pause(Condition[] conditions) {
    pause(conditions, DEFAULT_DELAY);
  }

  /**
   * Waits until the given conditions are satisfied.
   * @param conditions the conditions to verify.
   * @param timeout the timeout.
   * @throws NullPointerException if the given timeout is <code>null</code>.
   * @throws NullPointerException if the array of conditions is <code>null</code>.
   * @throws IllegalArgumentException if the array of conditions is empty.
   * @throws NullPointerException if the array of conditions has one or more <code>null</code> values.
   * @throws WaitTimedOutError if the wait times out.
   */
  public static void pause(Condition[] conditions, Timeout timeout) {
    pause(conditions, timeout.duration());
  }

  /**
   * Waits until the given conditions are satisfied.
   * @param conditions the conditions to verify.
   * @param timeout the timeout (in milliseconds.)
   * @throws NullPointerException if the array of conditions is <code>null</code>.
   * @throws IllegalArgumentException if the array of conditions is empty.
   * @throws NullPointerException if the array of conditions has one or more <code>null</code> values.
   * @throws WaitTimedOutError if the wait times out.
   */
  public static void pause(Condition[] conditions, long timeout) {
    validate(conditions);
    TimeoutWatch watch = startWatchWithTimeoutOf(timeout);
    while (!areSatisfied(conditions)) {
      if (watch.isTimeOut()) {
        done(conditions);
        throw timeoutExpired(conditions);
      }
      pause(SLEEP_INTERVAL);
    }
    done(conditions);
  }

  private static void validate(Condition[] conditions) {
    if (conditions == null) throw new NullPointerException("The array of conditions to verify should not be null");
    if (isEmpty(conditions))
      throw new IllegalArgumentException("The array of conditions to verify should not be empty");
    for (Condition condition : conditions) {
      if (condition != null) continue;
      throw new NullPointerException(concat(
          "The array of conditions <", format(conditions), "> contains one or more null values"));
    }
  }

  private static boolean areSatisfied(Condition[] conditions) {
    for (Condition condition : conditions) if (!condition.test()) return false;
    return true;
  }

  private static void done(Condition[] conditions) {
    for (Condition condition : conditions) condition.done();
  }

  private static WaitTimedOutError timeoutExpired(Condition[] conditions) {
    return new WaitTimedOutError((concat("Timed out waiting for ", format(conditions))));
  }

  /**
   * Sleeps for the specified time.
   * @param timeout the quantity of time units to sleep.
   * @param unit the time units.
   * @see #pause(long)
   * @throws NullPointerException if <code>unit</code> is <code>null</code>.
   */
  public static void pause(long timeout, TimeUnit unit) {
    if (unit == null) throw new NullPointerException("Time unit cannot be null");
    pause(unit.toMillis(timeout));
  }

  /**
   * Sleeps for the specified time.
   * <p>
   * To catch any <code>InterruptedException</code>s that occur, <code>{@link Thread#sleep(long)}()</code> may be used
   * instead.
   * </p>
   * @param ms the time to sleep in milliseconds.
   */
  public static void pause(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Sleeps for 10 milliseconds.
   */
  public static void pause() { pause(SLEEP_INTERVAL); }

  private Pause() {}
}
