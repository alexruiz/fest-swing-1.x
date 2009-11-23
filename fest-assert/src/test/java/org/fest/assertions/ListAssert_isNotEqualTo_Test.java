/*
 * Created on Mar 29, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Collections.list;

import java.util.List;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ListAssert#isNotEqualTo(List)}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_isNotEqualTo_Test implements Assert_isNotEqualTo_TestCase {

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = list("Luke", "Leia");
  }

  @Test
  public void should_pass_if_actual_and_expected_are_not_equal() {
    new ListAssert(list).isNotEqualTo(list("Yoda"));
  }

  @Test
  public void should_fail_if_actual_and_expected_are_equal() {
    String message = "actual value:<['Luke', 'Leia']> should not be equal to:<['Luke', 'Leia']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).isNotEqualTo(list("Luke", "Leia"));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_equal() {
    String message = "[A Test] actual value:<['Luke', 'Leia']> should not be equal to:<['Luke', 'Leia']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .isNotEqualTo(list("Luke", "Leia"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .isNotEqualTo(list("Luke", "Leia"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .isNotEqualTo(list("Luke", "Leia"));
      }
    });
  }
}
