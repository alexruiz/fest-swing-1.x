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

import static java.util.Collections.emptyList;
import static org.fest.assertions.CommonFailures.expectErrorIfActualListIsNull;
import static org.fest.assertions.CommonFailures.expectErrorWithDescriptionIfActualListIsNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Collections.list;

import java.util.List;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ListAssert#doesNotHaveDuplicates()}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_doesNotHaveDuplicates_Test implements GroupAssert_doesNotHaveDuplicates_TestCase {

  private static List<String> withDuplicates;

  @BeforeClass
  public static void setUpOnce() {
    withDuplicates = list("Luke", "Yoda", "Luke");
  }

  @Test
  public void should_pass_if_actual_does_not_contain_duplicates() {
    new ListAssert(list("Luke", "Yoda")).doesNotHaveDuplicates();
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    new ListAssert(emptyList()).doesNotHaveDuplicates();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_if_actual_has_duplicates() {
    expectAssertionError("list:<['Luke', 'Yoda', 'Luke']> contains duplicate(s):<['Luke']>").on(new CodeToTest() {
      public void run() {
        new ListAssert(withDuplicates).doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_has_duplicates() {
    String message = "[A Test] list:<['Luke', 'Yoda', 'Luke']> contains duplicate(s):<['Luke']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(withDuplicates).as("A Test")
                                      .doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_has_duplicates() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(withDuplicates).overridingErrorMessage("My custom message")
                                      .doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_has_duplicates() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(withDuplicates).as("A Test")
                                      .overridingErrorMessage("My custom message")
                                      .doesNotHaveDuplicates();
      }
    });
  }
}
