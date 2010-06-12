/*
 * Created on 2010-4-29
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
 * Base class for testing {@link org.fest.assertions.GenericAssert#isNotSameAs(Object)}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>isNotSameAs</code> as template methods and
 * uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */

public abstract class GenericAssert_isNotSameAs_TestBase<VALUE_TYPE> implements GenericAssert_isNotSameAs_TestCase {

  protected abstract VALUE_TYPE createEight();

  protected abstract String eightAsString();

  protected abstract GenericAssert<VALUE_TYPE> assertionFor(VALUE_TYPE actual);

  @Test
  public void should_pass_if_actual_and_expected_are_not_same() {
    assertionFor(createEight()).isNotSameAs(createEight());
  }

  @Test
  public void should_fail_if_actual_and_expected_are_same() {
    expectAssertionError("given objects are same:<" + eightAsString() + ">").on(new CodeToTest() {
      public void run() {
        final VALUE_TYPE myEight = createEight();
        assertionFor(myEight).isNotSameAs(myEight);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_same() {
    expectAssertionError("[A Test] given objects are same:<" + eightAsString() + ">").on(new CodeToTest() {
      public void run() {
        final VALUE_TYPE myEight = createEight();
        assertionFor(myEight).as("A Test").isNotSameAs(myEight);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        final VALUE_TYPE myEight = createEight();
        assertionFor(myEight).overridingErrorMessage("My custom message").isNotSameAs(myEight);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        final VALUE_TYPE myEight = createEight();
        assertionFor(myEight).as("A Test").overridingErrorMessage("My custom message").isNotSameAs(myEight);
      }
    });
  }

}

