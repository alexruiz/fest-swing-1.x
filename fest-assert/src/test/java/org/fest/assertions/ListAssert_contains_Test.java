/*
 * Created on Mar 29, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Collections.list;

import java.util.List;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ListAssert#contains(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_contains_Test implements GroupAssert_contains_TestCase {

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = list("Leia", "Luke");
  }

  @Test
  public void should_pass_if_actual_contains_given_value() {
    new ListAssert(list).contains("Leia");
  }

  @Test
  public void should_pass_if_actual_contains_given_values() {
    new ListAssert(list).contains("Leia", "Luke");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).contains("Leia", "Luke");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .contains("Leia", "Luke");
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] expected = null;
        new ListAssert(list).contains(expected);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] expected = null;
        new ListAssert(list).as("A Test")
                            .contains(expected);
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values() {
    expectAssertionError("list:<['Leia', 'Luke']> does not contain element(s):<['Han']>").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).contains("Han");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("[A Test] list:<['Leia', 'Luke']> does not contain element(s):<['Han']>").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .contains("Han");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .contains("Han");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .contains("Han");
      }
    });
  }


}
