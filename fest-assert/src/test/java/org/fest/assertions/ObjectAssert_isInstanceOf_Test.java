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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectAssert#isInstanceOf(Class)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectAssert_isInstanceOf_Test implements Assert_isInstanceOf_TestCase {

  @Test
  public void should_pass_if_actual_is_instance_of_expected() {
    new ObjectAssert(6).isInstanceOf(Integer.class);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert(null).isInstanceOf(String.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert(null).as("A Test")
                              .isInstanceOf(String.class);
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectErrorIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert("Yoda").isInstanceOf(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectErrorWithDescriptionIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert("Yoda").as("A Test")
                                .isInstanceOf(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_not_instance_of_expected() {
    final String message = "expected instance of:<java.lang.String> but was instance of:<java.lang.Integer>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ObjectAssert(2).isInstanceOf(String.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_instance_of_expected() {
    final String message = "[A Test] expected instance of:<java.lang.String> but was instance of:<java.lang.Integer>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ObjectAssert(2).as("A Test")
                           .isInstanceOf(String.class);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_instance_of_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ObjectAssert(2).overridingErrorMessage("My custom message")
                           .isInstanceOf(String.class);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_instance_of_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ObjectAssert(2).as("A Test")
                           .overridingErrorMessage("My custom message")
                           .isInstanceOf(String.class);
      }
    });
  }
}
