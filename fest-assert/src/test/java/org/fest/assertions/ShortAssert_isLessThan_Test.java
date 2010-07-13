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
import org.junit.*;

/**
 * Tests for <code>{@link ShortAssert#isLessThan(short)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ShortAssert_isLessThan_Test implements Assert_isLessThan_TestCase {

  private static short actual;
  private static short lessThanActual;

  @BeforeClass
  public static void setUpOnce() {
    actual = 8;
    lessThanActual = 6;
  }

  @Test
  public void should_pass_if_actual_is_less_than_expected() {
    new ShortAssert(actual).isLessThan((short)10);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    expectAssertionError("actual value:<8> should be less than:<8>").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).isLessThan(actual);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError("[A Test] actual value:<8> should be less than:<8>").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).as("A Test")
                               .isLessThan(actual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).overridingErrorMessage("My custom message")
                               .isLessThan(actual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).as("A Test")
                               .overridingErrorMessage("My custom message")
                               .isLessThan(actual);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_greater_than_expected() {
    expectAssertionError("actual value:<8> should be less than:<6>").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).isLessThan(lessThanActual);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_greater_than_expected() {
    expectAssertionError("[A Test] actual value:<8> should be less than:<6>").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).as("A Test")
                               .isLessThan(lessThanActual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_greater_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).overridingErrorMessage("My custom message")
                               .isLessThan(lessThanActual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_greater_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortAssert(actual).as("A Test")
                               .overridingErrorMessage("My custom message")
                               .isLessThan(lessThanActual);
      }
    });
  }


}
