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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Test for <code>{@link FloatAssert#isPositive()}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class FloatAssert_isPositive_Test implements NumberAssert_isPositive_TestCase {

  @Test
  public void should_pass_if_actual_is_positive() {
    new FloatAssert(6.68f).isPositive();
  }

  @Test
  public void should_fail_if_actual_is_negative() {
    expectAssertionError("actual value:<-6.68> should be greater than:<0.0>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(-6.68f).isPositive();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_negative() {
    expectAssertionError("[A Test] actual value:<-6.68> should be greater than:<0.0>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(-6.68f).as("A Test")
                               .isPositive();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_negative() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(-6.68f).overridingErrorMessage("My custom message")
                               .isPositive();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_negative() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(-6.68f).as("A Test")
                               .overridingErrorMessage("My custom message")
                               .isPositive();
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_zero() {
    expectAssertionError("actual value:<0.0> should be greater than:<0.0>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(0.0f).isPositive();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_zero() {
    expectAssertionError("[A Test] actual value:<0.0> should be greater than:<0.0>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(0.0f).as("A Test")
                             .isPositive();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_zero() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(0.0f).overridingErrorMessage("My custom message")
                             .isPositive();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_zero() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(0.0f).as("A Test")
                             .overridingErrorMessage("My custom message")
                             .isPositive();
      }
    });
  }


}
