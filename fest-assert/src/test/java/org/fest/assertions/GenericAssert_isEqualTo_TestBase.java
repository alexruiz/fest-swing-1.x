/*
 * Created on 2010-4-23
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
 * Base class for testing {@link org.fest.assertions.GenericAssert#isEqualTo(Object)}.
 *
 * This class implements the algorithms which must be performed to test <code>isEqualTo</code> as template methods
 * and uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */

public abstract class GenericAssert_isEqualTo_TestBase<VALUE_TYPE, ASSERT_TYPE extends GenericAssert<VALUE_TYPE>> implements GenericAssert_isEqualTo_TestCase {

  protected abstract VALUE_TYPE actualValueX();
  protected abstract VALUE_TYPE actualValueY();

  /**
   * Make sure string representation encloses delta in <code>[</code> and <code>]</code>, as generated
   * by {@link junit.framework.ComparisonCompactor}.
   */
  protected abstract String messageStringRepresentingX();
  
  /**
   * Make sure string representation encloses delta in <code>[</code> and <code>]</code>, as generated
   * by {@link junit.framework.ComparisonCompactor}.
   */  protected abstract String messageStringRepresentingY();

  protected abstract ASSERT_TYPE createAssertForActual(VALUE_TYPE actual);

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    createAssertForActual(actualValueX()).isEqualTo(actualValueX());
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    createAssertForActual(null).isEqualTo(null);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError("expected:<" + messageStringRepresentingY() + "> but was:<" + messageStringRepresentingX() + ">").on(new CodeToTest() {
      public void run() {
        createAssertForActual(actualValueX()).isEqualTo(actualValueY());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("[A Test] expected:<" + messageStringRepresentingY() + "> but was:<" + messageStringRepresentingX() + ">").on(new CodeToTest() {
      public void run() {
        createAssertForActual(actualValueX()).as("A Test").isEqualTo(actualValueY());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createAssertForActual(actualValueX()).overridingErrorMessage("My custom message").isEqualTo(actualValueY());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createAssertForActual(actualValueX()).as("A Test").overridingErrorMessage("My custom message").isEqualTo(actualValueY());
      }
    });
  }
}
