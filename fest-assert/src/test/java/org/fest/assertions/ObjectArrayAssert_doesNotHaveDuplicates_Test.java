/*
 * Created on Oct 27, 2009
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

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectArrayAssert#doesNotHaveDuplicates()}</code>.
 *
 * @author Alex Ruiz
 */
public class ObjectArrayAssert_doesNotHaveDuplicates_Test {

  @Test
  public void should_pass_if_actual_does_not_have_duplicates() {
    new ObjectArrayAssert(6, 8).doesNotHaveDuplicates();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        Object[] actual = null;
        new ObjectArrayAssert(actual).doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        Object[] actual = null;
        new ObjectArrayAssert(actual).as("A Test")
                                     .doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_if_actual_has_duplicates() {
    String message = "<['A', 'A']> contains duplicate(s):<['A']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert("A", "A").doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_has_duplicates() {
    String message = "[A Test] <['A', 'A']> contains duplicate(s):<['A']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert("A", "A").as("A Test")
                                       .doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_has_duplicates() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert("A", "A").overridingErrorMessage("My custom message")
                                       .doesNotHaveDuplicates();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_has_duplicates() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert("A", "A").as("A Test")
                                       .overridingErrorMessage("My custom message")
                                       .doesNotHaveDuplicates();
      }
    });
  }
}
