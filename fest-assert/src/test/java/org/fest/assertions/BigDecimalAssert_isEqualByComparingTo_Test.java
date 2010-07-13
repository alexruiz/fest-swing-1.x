/*
 * Created on Jan 10, 2007
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

import static java.math.BigDecimal.ZERO;
import static org.fest.assertions.BigDecimals.eight;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.assertions.FailureMessages.unexpectedNotEqual;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import java.math.BigDecimal;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Tests for <code>{@link BigDecimalAssert#isEqualByComparingTo(BigDecimal)}</code>.
 *
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert_isEqualByComparingTo_Test {

  private static BigDecimal actual;
  private static BigDecimal other;

  @BeforeClass
  public static void setUpOnce() {
    actual = eight();
    other = ZERO;
  }

  @Test
  public void should_pass_if_values_are_equal() {
    new BigDecimalAssert(actual).isEqualByComparingTo(actual);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(null).isEqualByComparingTo(actual);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(null).as("A Test")
                                  .isEqualByComparingTo(actual);
      }
    });
  }

  @Test
  public void should_fail_if_values_are_not_equal() {
    expectAssertionError(unexpectedNotEqual(actual, other)).on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(actual).isEqualByComparingTo(other);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_values_are_not_equal() {
    expectAssertionError(unexpectedNotEqual("A Test", actual, other)).on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(actual).as("A Test")
                                    .isEqualByComparingTo(other);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_values_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(actual).overridingErrorMessage("My custom message")
                                    .isEqualByComparingTo(other);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_zero() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(actual).as("A Test")
                                    .overridingErrorMessage("My custom message")
                                    .isEqualByComparingTo(other);
      }
    });
  }

}
