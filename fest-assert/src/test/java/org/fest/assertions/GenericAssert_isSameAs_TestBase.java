/*
 * Created on 2010-4-27
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
 * Base class for testing {@link org.fest.assertions.GenericAssert#isSameAs(Object)}.
 *
 * This class implements the algorithms which must be performed to test <code>isSameAs</code> as template methods
 * and uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */

public abstract class GenericAssert_isSameAs_TestBase<VALUE_CLASS> implements GenericAssert_isSameAs_TestCase {

  protected abstract VALUE_CLASS createNewEight();

  protected abstract String eightAsString();

  protected abstract GenericAssert<VALUE_CLASS> createAssertInstance(VALUE_CLASS actual);


  @Test
  public void should_pass_if_actual_and_expected_are_same() {
    final VALUE_CLASS myEight = createNewEight();
    createAssertInstance(myEight).isSameAs(myEight);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_same() {
    expectAssertionError("expected same instance but found:<"+eightAsString()+"> and:<"+eightAsString()+">")
      .on(new CodeToTest() {
      public void run() {
        createAssertInstance(createNewEight()).isSameAs(createNewEight());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_same() {
    expectAssertionError("[A Test] expected same instance but found:<"+eightAsString()+"> and:<"+eightAsString()+">")
      .on(new CodeToTest() {
      public void run() {
        createAssertInstance(createNewEight()).as("A Test").isSameAs(createNewEight());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createAssertInstance(createNewEight()).overridingErrorMessage("My custom message").isSameAs(createNewEight());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createAssertInstance(createNewEight()).as("A Test").overridingErrorMessage("My custom message").isSameAs(createNewEight());
      }
    });
  }
}
