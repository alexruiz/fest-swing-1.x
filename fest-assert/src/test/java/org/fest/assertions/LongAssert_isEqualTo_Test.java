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

import static org.fest.assertions.ComparisonFailureMessages.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link LongAssert#isEqualTo(long)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class LongAssert_isEqualTo_Test implements Assert_isEqualTo_TestCase {

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new LongAssert(6).isEqualTo(6);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError(comparisonFailureMessage(expected("8"), actual("6"))).on(new CodeToTest() {
      public void run() {
        new LongAssert(6).isEqualTo(8);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    String msg = comparisonFailureMessage(description("A Test"), expected("8"), actual("6"));
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        new LongAssert(6).as("A Test")
                         .isEqualTo(8);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new LongAssert(6).overridingErrorMessage("My custom message")
                         .isEqualTo(8);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new LongAssert(6).as("A Test")
                         .overridingErrorMessage("My custom message")
                         .isEqualTo(8);
      }
    });
  }
}
