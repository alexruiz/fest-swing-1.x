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
 * Copyright @2010 the original author or authors.
 */

package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.junit.Test;
import org.fest.test.CodeToTest;

/**
 * Base class for testing {@link org.fest.assertions.GenericAssert#isSameAs(Object)}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>isSameAs</code> as template methods and
 * uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */

public abstract class GenericAssert_isSameAs_TestBase<VALUE_TYPE> implements GenericAssert_isSameAs_TestCase {

  protected abstract VALUE_TYPE createEight();

  protected abstract String eightAsString();

  protected abstract GenericAssert<VALUE_TYPE> assertionFor(VALUE_TYPE actual);


  @Test
  public void should_pass_if_actual_and_expected_are_same() {
    final VALUE_TYPE myEight = createEight();
    assertionFor(myEight).isSameAs(myEight);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_same() {
    expectAssertionError("expected same instance but found:<" + eightAsString() + "> and:<" + eightAsString() + ">")
      .on(new CodeToTest() {
        public void run() {
          assertionFor(createEight()).isSameAs(createEight());
        }
      });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_same() {
    expectAssertionError("[A Test] expected same instance but found:<" + eightAsString() + "> and:<" + eightAsString() + ">")
      .on(new CodeToTest() {
        public void run() {
          assertionFor(createEight()).as("A Test").isSameAs(createEight());
        }
      });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(createEight()).overridingErrorMessage("My custom message").isSameAs(createEight());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(createEight()).as("A Test").overridingErrorMessage("My custom message").isSameAs(createEight());
      }
    });
  }
}
