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
 * Test for <code>{@link DoubleAssert#isLessThan(double)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class DoubleAssert_isLessThan_Test implements Assert_isLessThan_TestCase {

  @Test
  public void should_pass_if_actual_is_less_than_expected() {
    new DoubleAssert(-0.0).isLessThan(0.0);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    expectAssertionError("actual value:<6.68> should be less than:<6.68>").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.68).isLessThan(6.68);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError("[A Test] actual value:<6.68> should be less than:<6.68>").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.68).as("A Test")
                              .isLessThan(6.68);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.68).overridingErrorMessage("My custom message")
                              .isLessThan(6.68);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.68).as("A Test")
                              .overridingErrorMessage("My custom message")
                              .isLessThan(6.68);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_greater_than_expected() {
    expectAssertionError("actual value:<6.88> should be less than:<6.68>").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.88).isLessThan(6.68);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_greater_than_expected() {
    expectAssertionError("[A Test] actual value:<6.88> should be less than:<6.68>").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.88).as("A Test")
                              .isLessThan(6.68);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_greater_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.88).overridingErrorMessage("My custom message")
                              .isLessThan(6.68);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_greater_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleAssert(6.88).as("A Test")
                              .overridingErrorMessage("My custom message")
                              .isLessThan(6.68);
      }
    });
  }


}
