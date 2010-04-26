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
 * Copyright @2007-2010 the original author or authors.
 */

package org.fest.assertions;

import org.fest.test.CodeToTest;
import org.junit.Test;

import static org.fest.test.ExpectedFailure.expectAssertionError;

/**
 * Base class for testing {@link org.fest.assertions.GenericAssert#isNotEqualTo(Object)}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>isNotEqualTo</code> as template methods
 * and uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */
public abstract class GenericAssert_isNotEqualTo_TestBase<VALUE_CLASS> implements Assert_isNotEqualTo_TestCase {

  protected abstract VALUE_CLASS createActualValueEight();

  protected abstract VALUE_CLASS createActualValueZero();

  protected abstract GenericAssert<VALUE_CLASS> createAssertionForActualValue(VALUE_CLASS actual);

  protected abstract String messageStringRepresentingEight();

  private VALUE_CLASS eight() {
    return createActualValueEight();
  }

  private VALUE_CLASS zero() {
    return createActualValueZero();
  }

  private String EIGHT_STRING() {
    return messageStringRepresentingEight();
  }

  private GenericAssert<VALUE_CLASS> createAssertion(VALUE_CLASS actual) {
    return createAssertionForActualValue(actual);
  }


  @Test
  public void should_pass_if_actual_and_expected_are_not_equal() {
    createAssertion(eight()).isNotEqualTo(zero());
  }

  @Test
  public void should_fail_if_actual_and_expected_are_equal() {
    expectAssertionError("actual value:<" + EIGHT_STRING() + "> should not be equal to:<" + EIGHT_STRING() + ">").on(new CodeToTest() {
        public void run() {
          createAssertion(eight()).isNotEqualTo(eight());
        }
      });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("[A Test] actual value:<"+EIGHT_STRING()+"> should not be equal to:<"+EIGHT_STRING()+">").on(new CodeToTest() {
      public void run() {
        createAssertion(eight()).as("A Test").isNotEqualTo(eight());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createAssertion(eight()).overridingErrorMessage("My custom message").isNotEqualTo(eight());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createAssertion(eight()).as("A Test").overridingErrorMessage("My custom message").isNotEqualTo(eight());
      }
    });
  }

}
