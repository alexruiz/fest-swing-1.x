/*
 * Created on Oct 31, 2007
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

import java.util.concurrent.TimeUnit;

/**
 * Understands a timeout.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class Timeout {

  private final long duration;

  /**
   * Creates a new <code>{@link Timeout}</code>.
   * @param duration the duration of the timeout in milliseconds.
   * @return the created <code>Timeout</code>.
   */
  public static Timeout timeout(long duration) {
    return new Timeout(duration);
  }

  /**
   * Creates a new <code>{@link Timeout}</code>.
   * @param duration the duration of the timeout.
   * @param timeUnit the unit of time of the timeout.
   * @return the created <code>Timeout</code>.
   * @throws NullPointerException if the given time unit is <code>null</code>.
   */
  public static Timeout timeout(long duration, TimeUnit timeUnit) {
    if (timeUnit == null) throw new NullPointerException("Time unit should not be null");
    return new Timeout(timeUnit.toMillis(duration));
  }

  private Timeout(long duration) {
    this.duration = duration;
  }

  /**
   * Returns the duration of the timeout in milliseconds.
   * @return the duration of the timeout in milliseconds.
   */
  public long duration() { return duration; }
}
