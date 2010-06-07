/*
 * Created on Apr 22, 2010
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

import static java.lang.Boolean.TRUE;
import static org.fest.assertions.CommonFailures.expectErrorIfConditionIsNull;
import static org.fest.assertions.NotNull.notNullBoolean;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link BooleanAssert#isNot(Condition)}</code>
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class BooleanAssert_isNot_Test implements GenericAssert_doesNotSatisfy_TestCase {

  private static Boolean value;

  @BeforeClass
  public static void setUpOnce() {
    value = TRUE;
  }

  @Test
  public void should_pass_if_condition_is_not_satisfied() {
    new BooleanAssert(null).isNot(notNullBoolean());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).isNot(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_satisfied() {
    expectAssertionError("actual value:<true> should not be:<NotNull>").on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).isNot(notNullBoolean());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<true> should not be:<NotNull>").on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).as("A Test")
                                .isNot(notNullBoolean());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("actual value:<true> should not be:<Not Null>").on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).isNot(notNullBoolean().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<true> should not be:<Not Null>").on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).as("A Test")
                                .isNot(notNullBoolean().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).overridingErrorMessage("My custom message")
                                .isNot(notNullBoolean());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).as("A Test")
                                .overridingErrorMessage("My custom message")
                                .isNot(notNullBoolean());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanAssert(value).overridingErrorMessage("My custom message")
                                .isNot(notNullBoolean().as("Not Null"));
      }
    });
  }

}