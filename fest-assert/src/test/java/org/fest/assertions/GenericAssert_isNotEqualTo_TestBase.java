/*
 * Created on 2010-4-26
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010 the original author or authors.
 */

package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.junit.Test;
import org.fest.test.CodeToTest;

/**
 * Base class for testing {@link org.fest.assertions.GenericAssert#isNotEqualTo(Object)}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>isNotEqualTo</code> as template methods
 * and uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */
public abstract class GenericAssert_isNotEqualTo_TestBase<VALUE_TYPE> implements Assert_isNotEqualTo_TestCase {

  protected abstract VALUE_TYPE zero();

  protected abstract VALUE_TYPE eight();

  protected abstract String eightAsString();

  protected abstract GenericAssert<VALUE_TYPE> assertionFor(VALUE_TYPE actual);

  @Test
  public void should_pass_if_actual_and_expected_are_not_equal() {
    assertionFor(eight()).isNotEqualTo(zero());
  }

  @Test
  public void should_fail_if_actual_and_expected_are_equal() {
    expectAssertionError("actual value:<" + eightAsString() + "> should not be equal to:<" + eightAsString() + ">")
      .on(new CodeToTest() {
        public void run() {
          assertionFor(eight()).isNotEqualTo(eight());
        }
      });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("[A Test] actual value:<" + eightAsString() + "> should not be equal to:<" + eightAsString() + ">")
      .on(new CodeToTest() {
        public void run() {
          assertionFor(eight()).as("A Test").isNotEqualTo(eight());
        }
      });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(eight()).overridingErrorMessage("My custom message").isNotEqualTo(eight());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(eight()).as("A Test").overridingErrorMessage("My custom message").isNotEqualTo(eight());
      }
    });
  }

}
