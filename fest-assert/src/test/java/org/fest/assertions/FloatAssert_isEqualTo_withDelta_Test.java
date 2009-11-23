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

import static org.fest.assertions.Delta.delta;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Test for <code>{@link FloatAssert#isEqualTo(float, Delta)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class FloatAssert_isEqualTo_withDelta_Test {

  @Test
  public void should_pass_if_actual_and_expected_are_equal_and_using_delta_of_zero() {
    new FloatAssert(8.0f).isEqualTo(8.0f, delta(0.0f));
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal_and_using_delta() {
    new FloatAssert(8.688f).isEqualTo(8.68f, delta(0.009f));
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError("expected:<8.888> but was:<8.688> using delta:<0.0090>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.688f).isEqualTo(8.888f, delta(0.009f));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("[A Test] expected:<8.888> but was:<8.688> using delta:<0.0090>").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.688f).as("A Test")
                               .isEqualTo(8.888f, delta(0.009f));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.688f).overridingErrorMessage("My custom message")
                               .isEqualTo(8.888f, delta(0.009f));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FloatAssert(8.688f).as("A Test")
                               .overridingErrorMessage("My custom message")
                               .isEqualTo(8.888f, delta(0.009f));
      }
    });
  }
}
