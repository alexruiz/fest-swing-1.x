/*
 * Created on Oct 6, 2009
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
import static org.fest.assertions.FileStub.newFile;
import static org.fest.assertions.NotNull.notNullFile;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Tests for <code>{@link FileAssert#is(Condition)}</code>.
 *
 * @author Alex Ruiz
 */
public class FileAssert_is_Test implements GenericAssert_satisfies_TestCase {

  private static FileStub file;

  @BeforeClass
  public static void setUpOnce() {
    file = newFile("c:\\f.txt");
  }

  @Test
  public void should_pass_if_condition_is_satisfied() {
    new FileAssert(file).is(notNullFile());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new FileAssert(file).is(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should be:<NotNull>").on(new CodeToTest() {
      public void run() {
        new FileAssert(null).is(notNullFile());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should be:<NotNull>").on(new CodeToTest() {
      public void run() {
        new FileAssert(null).as("A Test")
                            .is(notNullFile());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should be:<Not Null>").on(new CodeToTest() {
      public void run() {
        new FileAssert(null).is(notNullFile().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should be:<Not Null>").on(new CodeToTest() {
      public void run() {
        new FileAssert(null).as("A Test")
                            .is(notNullFile().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FileAssert(null).overridingErrorMessage("My custom message")
                            .is(notNullFile());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FileAssert(null).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .is(notNullFile());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new FileAssert(null).overridingErrorMessage("My custom message")
                            .is(notNullFile().as("Not Null"));
      }
    });
  }
}
