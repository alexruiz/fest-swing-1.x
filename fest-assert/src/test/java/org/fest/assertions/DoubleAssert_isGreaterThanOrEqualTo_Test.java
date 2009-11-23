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

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Test for <code>{@link DoubleAssert#isGreaterThanOrEqualTo(double)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class DoubleAssert_isGreaterThanOrEqualTo_Test implements Assert_isGreaterThanOrEqualTo_TestCase {

  @Test
  public void should_pass_if_actual_is_greater_than_expected() {
    new DoubleAssert(8.8).isGreaterThanOrEqualTo(6.6);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    new DoubleAssert(8.8).isGreaterThanOrEqualTo(8.8);
  }

  @Test
  public void should_fail_if_actual_is_less_than_expected() {
    expectAssertionError("actual value:<6.6> should be greater than or equal to:<8.8>").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.6).isGreaterThanOrEqualTo(8.8);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_less_than_expected() {
    expectAssertionError("[A Test] actual value:<6.6> should be greater than or equal to:<8.8>").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.6).as("A Test")
                             .isGreaterThanOrEqualTo(8.8);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_less_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.6).overridingErrorMessage("My custom message")
                             .isGreaterThanOrEqualTo(8.8);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_less_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.6).as("A Test")
                             .overridingErrorMessage("My custom message")
                             .isGreaterThanOrEqualTo(8.8);
      }
    });
  }
}
