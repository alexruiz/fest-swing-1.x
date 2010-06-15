/*
 * Created on Jun 18, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ComparisonFailureMessages.unexpectedNotEqual;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link IntAssert#isEqualTo(int)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class IntAssert_isEqualTo_Test implements Assert_isEqualTo_TestCase {

  private static int actual;
  private static int notEqualValue;

  @BeforeClass
  public static void setUpOnce() {
    actual = 6;
    notEqualValue = 8;
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new IntAssert(actual).isEqualTo(6);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError(unexpectedNotEqual(actual, notEqualValue)).on(new CodeToTest() {
      public void run() {
        new IntAssert(actual).isEqualTo(notEqualValue);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError(unexpectedNotEqual("A Test", actual, notEqualValue)).on(new CodeToTest() {
      public void run() {
        new IntAssert(actual).as("A Test")
                             .isEqualTo(notEqualValue);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntAssert(actual).overridingErrorMessage("My custom message")
                             .isEqualTo(notEqualValue);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntAssert(actual).as("A Test")
                             .overridingErrorMessage("My custom message")
                             .isEqualTo(notEqualValue);
      }
    });
  }
}
