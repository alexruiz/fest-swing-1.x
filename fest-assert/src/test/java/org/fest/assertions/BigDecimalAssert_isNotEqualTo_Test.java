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
import static org.fest.test.ExpectedFailure.expectAssertionError;

import java.math.BigDecimal;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link BigDecimalAssert#isNotEqualTo(BigDecimal)}</code>.
 *
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert_isNotEqualTo_Test implements Assert_isNotEqualTo_TestCase {

  private static BigDecimal eight;

  @BeforeClass
  public static void setUpOnce() {
    eight = eight();
  }

  @Test
  public void should_pass_if_actual_and_expected_are_not_equal() {
    new BigDecimalAssert(eight).isNotEqualTo(ZERO);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_equal() {
    expectAssertionError("actual value:<8.0> should not be equal to:<8.0>").on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(eight).isNotEqualTo(eight);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("[A Test] actual value:<8.0> should not be equal to:<8.0>").on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(eight).as("A Test")
                                   .isNotEqualTo(eight);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(eight).overridingErrorMessage("My custom message")
                                   .isNotEqualTo(eight);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BigDecimalAssert(eight).as("A Test")
                                   .overridingErrorMessage("My custom message")
                                   .isNotEqualTo(eight);
      }
    });
  }
}
