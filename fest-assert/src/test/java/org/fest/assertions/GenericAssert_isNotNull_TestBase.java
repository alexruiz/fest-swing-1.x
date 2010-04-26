/*
 * Created on 2010-4-26
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

import static org.fest.assertions.CommonFailures.expectErrorIfActualIsNull;
import static org.fest.assertions.CommonFailures.expectErrorWithDescriptionIfActualIsNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;

/**
 * Base class for testing {@link GenericAssert#isNotNull()}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>isNotNull</code> as template methods
 * and uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */

public abstract class GenericAssert_isNotNull_TestBase<VALUE_CLASS> implements GenericAssert_isNotNull_TestCase {

  protected abstract GenericAssert<VALUE_CLASS> createAssertInstanceForActual(VALUE_CLASS actual);

  protected abstract VALUE_CLASS createNonNullValue();

  private GenericAssert<VALUE_CLASS> assertionForNonNullValue() {
    return createAssertInstanceForActual(createNonNullValue());
  }

  private GenericAssert<VALUE_CLASS> assertionForNullValue() {
    return createAssertInstanceForActual(null);
  }


  @Test
  public void should_pass_if_actual_is_not_null() {
    assertionForNonNullValue().isNotNull();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        assertionForNullValue().isNotNull();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        assertionForNullValue().as("A Test").isNotNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionForNullValue().overridingErrorMessage("My custom message").isNotNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionForNullValue().as("A Test").overridingErrorMessage("My custom message").isNotNull();
      }
    });
  }
}
