/*
 * Created on Apr 1, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.lock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.timing.Pause.pause;

import org.fest.swing.exception.ScreenLockException;
import org.fest.swing.timing.Condition;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ScreenLock#acquire(Object)}</code>, <code>{@link ScreenLock#acquiredBy(Object)}</code> and
 * <code>{@link ScreenLock#release(Object)}</code>.
 *
 * @author Alex Ruiz
 */
public class ScreenLock_acquire_acquiredBy_release_Test {

  private Object owner;
  private ScreenLock lock;

  @Before
  public void setUp() {
    owner = new Object();
    lock = new ScreenLock();
  }

  @Test
  public void should_acquire_lock_and_queue_others_wanting_lock() {
    lock.acquire(owner);
    assertThat(lock.owner()).isSameAs(owner);
    assertThat(lock.acquiredBy(owner)).isTrue();
    final Object o = new Object();
    new Thread() {
      @Override public void run() {
        lock.acquire(o);
      }
    }.start();
    lock.release(owner);
    pause(new Condition("ScreenLock to be obtained by waiting thread") {
      @Override
      public boolean test() {
        return lock.owner() == o;
      }
    }, 1000);
    assertThat(lock.acquiredBy(o)).isTrue();
    assertThat(lock.acquiredBy(owner)).isFalse();
    lock.release(o);
    assertThat(lock.acquiredBy(o)).isFalse();
  }

  @Test(expected = ScreenLockException.class)
  public void should_throw_error_when_releasing_lock_without_owner() {
    lock.release(owner);
  }

  @Test
  public void should_throw_error_if_releasing_lock_with_wrong_owner() {
    lock.acquire(owner);
    try {
      lock.release(new Object());
      failWhenExpectingException();
    } catch (ScreenLockException expected) {}
    lock.release(owner);
  }
}
