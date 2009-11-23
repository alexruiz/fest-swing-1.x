/*
 * Created on Jan 24, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.MapAssert.entry;
import static org.fest.assertions.MapFactory.map;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import java.util.Map;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link MapAssert#isEqualTo(Map)}</code>.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssert_isEqualTo_Test implements GenericAssert_isEqualTo_TestCase {

  private static Map<Object, Object> map;

  @BeforeClass
  public static void setUpOnce() {
    map = map(entry("key1", 1), entry("key2", 2));
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    Map<Object, Object> e = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map).isEqualTo(e);
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    new MapAssert(null).isEqualTo(null);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError("expected:<{'key[6'=6, 'key8'=8]}> but was:<{'key[1'=1, 'key2'=2]}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> e = map(entry("key6", 6), entry("key8", 8));
        new MapAssert(map).isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("[A Test] expected:<{'key[6'=6, 'key8'=8]}> but was:<{'key[1'=1, 'key2'=2]}>").on(
      new CodeToTest() {
        public void run() {
          Map<Object, Object> e = map(entry("key6", 6), entry("key8", 8));
          new MapAssert(map).as("A Test")
                            .isEqualTo(e);
        }
      });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> e = map(entry("key6", 6), entry("key8", 8));
        new MapAssert(map).overridingErrorMessage("My custom message")
                          .isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> e = map(entry("key6", 6), entry("key8", 8));
        new MapAssert(map).as("A Test")
                          .overridingErrorMessage("My custom message")
                          .isEqualTo(e);
      }
    });
  }
}
