/*
 * Created on Dec 23, 2007
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
 * Tests for <code>{@link ThrowableAssert#isExactlyInstanceOf(Class)}</code>.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_isExactlyInstanceOf_Test {

  @Test
  public void should_pass_if_actual_is_exactly_instance_of_expected() {
    new ThrowableAssert(new IllegalStateException()).isExactlyInstanceOf(IllegalStateException.class);
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectErrorIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).isExactlyInstanceOf(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectErrorWithDescriptionIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).as("A Test")
                                            .isExactlyInstanceOf(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_instance_of_expected_but_not_exactly() {
    String message = "expected exactly the same type:<java.lang.Exception> but was:<java.lang.NullPointerException>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new NullPointerException()).isExactlyInstanceOf(Exception.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_instance_of_expected_but_not_exactly() {
    String message = "[A Test] expected exactly the same type:<java.lang.Exception> but was:<java.lang.NullPointerException>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new NullPointerException()).as("A Test")
                                                       .isExactlyInstanceOf(Exception.class);
      }
    });
  }
  
  @Test
  public void should_fail_with_custom_message_if_actual_is_instance_of_expected_but_not_exactly() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new NullPointerException()).overridingErrorMessage("My custom message")
                                                       .isExactlyInstanceOf(Exception.class);
      }
    });
  }
  
  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_instance_of_expected_but_not_exactly() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new NullPointerException()).as("A Test")
                                                       .overridingErrorMessage("My custom message")
                                                       .isExactlyInstanceOf(Exception.class);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_not_exactly_instance_of_expected() {
    String message = "expected exactly the same type:<java.lang.NullPointerException> but was:<java.lang.Exception>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).isExactlyInstanceOf(NullPointerException.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_exactly_instance_of_expected() {
    String message = "[A Test] expected exactly the same type:<java.lang.NullPointerException> but was:<java.lang.Exception>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).as("A Test")
                                            .isExactlyInstanceOf(NullPointerException.class);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_exactly_instance_of_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).overridingErrorMessage("My custom message")
                                            .isExactlyInstanceOf(NullPointerException.class);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_exactly_instance_of_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(new Exception()).as("A Test")
                                            .overridingErrorMessage("My custom message")
                                            .isExactlyInstanceOf(NullPointerException.class);
      }
    });
  }
  
  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ThrowableAssert(null).isExactlyInstanceOf(NullPointerException.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ThrowableAssert(null).as("A Test")
                                 .isExactlyInstanceOf(NullPointerException.class);
      }
    });
  }
}
