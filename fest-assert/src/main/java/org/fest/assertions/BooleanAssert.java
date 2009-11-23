/*
 * Created on Mar 19, 2007
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

import static org.fest.assertions.ErrorMessages.unexpectedNotEqual;
import static org.fest.assertions.Fail.failIfEqual;

/**
 * Understands assertion methods for <code>boolean</code> values. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(boolean)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class BooleanAssert extends PrimitiveAssert {

  private final boolean actual;

  /**
   * Creates a new </code>{@link BooleanAssert}</code>.
   * @param actual the target to verify.
   */
  protected BooleanAssert(boolean actual) {
    this.actual = actual;
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>as</strong>(&quot;Some value&quot;).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public BooleanAssert as(String description) {
    description(description);
    return this;
  }

  /**
   * Alias for <code>{@link #as(String)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>describedAs</strong>(&quot;Some value&quot;).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public BooleanAssert describedAs(String description) {
    return as(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>as</strong>(new BasicDescription(&quot;Some value&quot;)).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public BooleanAssert as(Description description) {
    description(description);
    return this;
  }

  /**
   * Alias for <code>{@link #as(Description)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>describedAs</strong>(new BasicDescription(&quot;Some value&quot;)).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public BooleanAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>boolean</code> value is <code>true</code>.
   * @throws AssertionError if the actual <code>boolean</code> value is <code>false</code>.
   */
  public void isTrue() {
    isEqualTo(true);
  }

  /**
   * Verifies that the actual <code>boolean</code> value is <code>false</code>.
   * @throws AssertionError if the actual <code>boolean</code> value is <code>true</code>.
   */
  public void isFalse() {
    isEqualTo(false);
  }

  /**
   * Verifies that the actual <code>boolean</code> is equal to the given one.
   * @param expected the given <code>boolean</code> to compare the actual <code>boolean</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> is not equal to the given one.
   */
  public BooleanAssert isEqualTo(boolean expected) {
    if (actual == expected) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>boolean</code> is not equal to the given one.
   * @param other the given <code>boolean</code> to compare the actual <code>boolean</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> is equal to the given one.
   */
  public BooleanAssert isNotEqualTo(boolean other) {
    failIfEqual(customErrorMessage(), rawDescription(), actual, other);
    return this;
  }

  /** {@inheritDoc} */
  public BooleanAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
