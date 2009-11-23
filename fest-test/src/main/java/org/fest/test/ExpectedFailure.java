/*
 * Created on Feb 7, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.test;

import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

/**
 * Understands executing test code that is expected to fail.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class ExpectedFailure {

  private final Class<? extends Throwable> errorType;

  /**
   * Specifies the expected failure message type.
   * @param errorType the expected failure message type.
   * @return a holder for the expected failure message type.
   */
  public static ExpectedFailure expect(Class<? extends Throwable> errorType) {
    return new ExpectedFailure(errorType);
  }

  /**
   * Specifies the expected message of an expected <code>{@link AssertionError}</code>.
   * @param message the expected failure message.
   * @return a holder of the expected failure message.
   */
  public static Message expectAssertionError(String message) {
    return new ExpectedFailure(AssertionError.class).withMessage(message);
  }

  /**
   * Specifies the expected failure message.
   * @param message the expected failure message.
   * @return a holder of the expected failure message.
   */
  public Message withMessage(String message) {
    return new Message(errorType, message);
  }

  /**
   * Understands executing test code that is expected to fail.
   *
   * @author Alex Ruiz
   * @author Yvonne Wang
   */
  public static class Message {
    private final Class<? extends Throwable> errorType;
    private final String message;

    Message(Class<? extends Throwable> errorType, String message) {
      this.errorType = errorType;
      this.message = message;
    }

    /**
     * Executes the given code to test.
     * @param codeToTest the code to test.
     * @throws AssertionError if an exception of the expected type is never thrown by the code to test.
     * @throws AssertionError if the type of the thrown exception is different than the expected type.
     * @throws AssertionError if the message of the thrown exception is different than the expected message.
     */
    public void on(CodeToTest codeToTest) {
      try {
        codeToTest.run();
        fail(concat("Expecting a thrown exception of type:<", errorTypeName(), ">"));
      } catch (Throwable t) {
        if (!errorType.isInstance(t))
          fail(concat("Expecting exception of type:<", errorTypeName(), "> but was:<", t.getClass().getName(), ">"));
        if (!areEqual(message, t.getMessage()))
          fail(concat("Expecting message:<", quote(message), "> but was:<", quote(t.getMessage()), ">"));
      }
    }

    private String errorTypeName() {
      return errorType.getName();
    }

    private void fail(String failureMessage) {
      throw new AssertionError(failureMessage);
    }
  }

  private ExpectedFailure(Class<? extends Throwable> error) {
    this.errorType = error;
  }
}
