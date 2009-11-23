/*
 * Created on Mar 29, 2009
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
 * Tests for <code>{@link ListAssert#containsExactly(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_containsExactly_Test {

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = list("Luke", "Leia");
  }
  
  @Test
  public void should_pass_if_actual_contains_exactly_the_expected_Objects() {
    new ListAssert(list).containsExactly("Luke", "Leia");
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(list).containsExactly(objects);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(list).as("A Test")
                            .containsExactly(objects);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).containsExactly("Anakin");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .containsExactly("Anakin");
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_exactly_the_expected_Objects() {
    expectAssertionError("expected:<['[Anakin]']> but was:<['[Luke', 'Leia]']>").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).containsExactly("Anakin");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_exactly_the_expected_Objects() {
    expectAssertionError("[A Test] expected:<['[Anakin]']> but was:<['[Luke', 'Leia]']>").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .containsExactly("Anakin");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_exactly_the_expected_Objects() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .containsExactly("Anakin");
      }
    });
  }
  
  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_exactly_the_expected_Objects() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .containsExactly("Anakin");
      }
    });
  }
}
