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

import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectAssert#isInstanceOfAny(Class...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectAssert_isInstanceOfAny_Test {

  @Test
  public void should_pass_if_actual_is_instance_of_any_of_expected_types() {
    new ObjectAssert(6).isInstanceOfAny(Float.class, String.class, Integer.class);
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("The given array of types should not be null").on(new CodeToTest() {
      public void run() {
        new ObjectAssert(6).isInstanceOfAny((Class<?>[]) null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] The given array of types should not be null").on(new CodeToTest() {
      public void run() {
        new ObjectAssert(6).as("A Test")
                           .isInstanceOfAny((Class<?>[]) null);
      }
    });
  }

  @Test
  public void should_throw_error_if_an_element_in_expected_is_null() {
    expectErrorIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert(6).isInstanceOfAny(new Class<?>[] { null });
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_an_element_in_expected_is_null() {
    expectErrorWithDescriptionIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert(6).as("A Test")
                           .isInstanceOfAny(new Class<?>[] { null });
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_not_instance_of_none_of_expected_types() {
    String message = "expected instance of any:<[java.lang.String, java.lang.Float]> but was instance of:<java.lang.Integer>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ObjectAssert(6).isInstanceOfAny(String.class, Float.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_instance_of_none_of_expected_types() {
    String message = "[A Test] expected instance of any:<[java.lang.String, java.lang.Float]> but was instance of:<java.lang.Integer>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ObjectAssert(6).as("A Test").isInstanceOfAny(String.class, Float.class);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert(null).isInstanceOfAny(String.class, Integer.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ObjectAssert(null).as("A Test")
                              .isInstanceOfAny(String.class, Integer.class);
      }
    });
  }

}
