/*
 * Created on Oct 10, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Fail.failWithMessage;
import static org.fest.assertions.Formatting.format;
import static org.fest.assertions.Formatting.valueOf;

/**
 * Understands the base class for all assertion methods for objects and primitives.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class Assert {

  private Description description;
  private String errorMessage;

  /**
   * Returns the description of the actual value in this assertion.
   * @return the description of the actual value in this assertion.
   */
  public final String description() {
    return valueOf(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails.
   * @param d the new description.
   */
  protected final void description(String d) {
    description(new BasicDescription(d));
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails.
   * @param d the new description.
   */
  protected final void description(Description d) {
    this.description = d;
  }

  /**
   * Returns the description of the actual value in this assertion.
   * @return the description of the actual value in this assertion.
   * @since 1.2
   */
  protected final Description rawDescription() {
    return description;
  }

  /**
   * Returns the given message formatted as follows:
   * <pre>
   * [assertion description] given message.
   * </pre>
   * @param message the message to format.
   * @return the formatted message.
   */
  protected final String formattedErrorMessage(String message) {
    return format(description, message);
  }

  /**
   * Specifies the message to use in case of a failure, replacing the default one.
   * @param message the new error message.
   */
  protected final void replaceDefaultErrorMessagesWith(String message) {
    errorMessage = message;
  }

  /**
   * Returns the message to use when a failure occurs, if one has been specified.
   * @return the message to use when a failure occurs, or <code>null</code> if none has been specified.
   */
  protected final String customErrorMessage() {
    return errorMessage;
  }

  /**
   * Throws an <code>{@link AssertionError}</code> only if the the custom message in this assertion object is not
   * <code>null</code>.
   * @throws AssertionError only if the custom error message in this assertion object is not <code>null</code>.
   * @since 1.2
   */
  protected final void failIfCustomMessageIsSet() {
    failWithMessage(customErrorMessage());
  }

  /**
   * Throws an <code>{@link AssertionError}</code> only if the the custom message in this assertion object is not
   * <code>null</code>.
   * @param realCause cause of the error.
   * @throws AssertionError only if the custom error message in this assertion object is not <code>null</code>.
   * @since 1.2
   */
  protected final void failIfCustomMessageIsSet(Throwable realCause) {
    failWithMessage(customErrorMessage(), realCause);
  }

  /**
   * Fails by throwing an <code>{@link AssertionError}</code>.
   * <p>
   * <strong>Note:</strong> This method appears to return <code>{@link AssertionError}</code>, but it is really not the
   * case, since the exception is thrown and not returned. In version 2.0 the return type of this method will change
   * to <code>void</code>. Since we cannot create an overloaded version with return type <code>void</code>, we cannot
   * deprecate this method. Please pretend this method does not return anything :)
   * </p>
   * @param reason the reason for the failure, used as the message for the thrown exception.
   * @return the thrown <code>AssertionError</code>.
   * @throws AssertionError using the given reason as the message.
   * @see #failure(String)
   */
  protected final AssertionError fail(String reason) {
    // TODO in 2.0: change return type to 'void'
    throw failure(reason);
  }

  /**
   * Creates an <code>{@link AssertionError}</code>, adding the description of the actual value to the given message.
   * @param reason the reason for the failure, used as the message for the thrown exception.
   * @return the created exception.
   */
  protected final AssertionError failure(String reason) {
    return Fail.failure(formattedErrorMessage(reason));
  }

  /**
   * Fails by throwing an <code>{@link AssertionError}</code>.
   * @param reason the reason for the failure, used as the message for the thrown exception.
   * @param cause the root cause of the failure, included in the thrown exception.
   */
  protected final void fail(String reason, Throwable cause) {
    Fail.fail(formattedErrorMessage(reason), cause);
  }

  /**
   * Throws <code>{@link UnsupportedOperationException}</code> if called. It is easy to accidentally call
   * <code>{@link #equals(Object)}</code> instead of <code>isEqualTo</code>.
   * @throws UnsupportedOperationException if this method is called.
   */
  @Override public final boolean equals(Object obj) {
    throw new UnsupportedOperationException("'equals' is not supported...maybe you intended to call 'isEqualTo'");
  }

  /**
   * Always returns 1.
   * @return 1.
   */
  @Override public final int hashCode() { return 1; }
}
