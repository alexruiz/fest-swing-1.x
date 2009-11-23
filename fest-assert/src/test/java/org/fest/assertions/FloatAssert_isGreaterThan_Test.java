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
 * Test for <code>{@link FloatAssert#isGreaterThan(float)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class FloatAssert_isGreaterThan_Test implements Assert_isGreaterThan_TestCase {

  @Test
  public void should_pass_if_actual_is_greater_than_expected() {
    new FloatAssert(0.00f).isGreaterThan(-0.00f);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    expectAssertionError("actual value:<8.68> should be greater than:<8.68>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).isGreaterThan(8.68f);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError("[A Test] actual value:<8.68> should be greater than:<8.68>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).as("A Test")
                              .isGreaterThan(8.68f);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).overridingErrorMessage("My custom message")
                              .isGreaterThan(8.68f);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).as("A Test")
                              .overridingErrorMessage("My custom message")
                              .isGreaterThan(8.68f);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_less_than_expected() {
    expectAssertionError("actual value:<8.68> should be greater than:<8.88>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).isGreaterThan(8.88f);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_less_than_expected() {
    expectAssertionError("[A Test] actual value:<8.68> should be greater than:<8.88>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).as("A Test")
                              .isGreaterThan(8.88f);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_less_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).overridingErrorMessage("My custom message")
                              .isGreaterThan(8.88f);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_less_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.68f).as("A Test")
                              .overridingErrorMessage("My custom message")
                              .isGreaterThan(8.88f);
      }
    });
  }


}
