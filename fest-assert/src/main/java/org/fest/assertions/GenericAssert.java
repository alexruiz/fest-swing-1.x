/*
 * Created on May 21, 2007
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

import static org.fest.assertions.Fail.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

/**
 * Understands a template for assertion methods.
 * @param <T> the type of object implementations of this template can verify.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class GenericAssert<T> extends Assert {

  protected final T actual;

  /**
   * Creates a new <code>{@link GenericAssert}</code>.
   * @param actual the actual target to verify.
   */
  protected GenericAssert(T actual) {
    this.actual = actual;
  }

  /**
   * Asserts that the actual value (specified in the constructor of this class) is <code>null</code>.
   * @throws AssertionError if the actual value is not <code>null</code>.
   */
  public final void isNull() {
    failIfNotNull(customErrorMessage(), rawDescription(), actual);
  }

  /**
   * Verifies that the actual value satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   * @see #is(Condition)
   */
  protected abstract GenericAssert<T> satisfies(Condition<T> condition);

  /**
   * Verifies that the actual value does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   */
  protected abstract GenericAssert<T> doesNotSatisfy(Condition<T> condition);

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   * @since 1.2
   */
  protected abstract GenericAssert<T> is(Condition<T> condition);

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @since 1.2
   */
  protected abstract GenericAssert<T> isNot(Condition<T> condition);

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> as(String description);

  /**
   * Alias for <code>{@link #as(String)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> describedAs(String description);

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> as(Description description);

  /**
   * Alias for <code>{@link #as(Description)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> describedAs(Description description);

  /**
   * Verifies that the actual value is equal to the given one.
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  protected abstract GenericAssert<T> isEqualTo(T expected);

  /**
   * Verifies that the actual value is not equal to the given one.
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  protected abstract GenericAssert<T> isNotEqualTo(T other);

  /**
   * Verifies that the actual value is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual value is <code>null</code>.
   */
  protected abstract GenericAssert<T> isNotNull();

  /**
   * Verifies that the actual value is the same as the given one.
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not the same as the given one.
   */
  protected abstract GenericAssert<T> isSameAs(T expected);

  /**
   * Verifies that the actual value is not the same as the given one.
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is the same as the given one.
   */
  protected abstract GenericAssert<T> isNotSameAs(T other);

  /**
   * Verifies that the actual value satisfies the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   */
  protected final void assertSatisfies(Condition<T> condition) {
    if (matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfConditionNotSatisfied(condition));
  }

  private String errorMessageIfConditionNotSatisfied(Condition<T> condition) {
    String message = concat("actual value:", inBrackets(actual), " should satisfy condition");
    return condition.addDescriptionTo(message);
  }

  /**
   * Verifies that the actual value satisfies the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   */
  protected final void assertIs(Condition<T> condition) {
    if (matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfIsNot(condition));
  }

  private String errorMessageIfIsNot(Condition<T> condition) {
    String message = concat("actual value:", inBrackets(actual), " should be");
    return condition.addDescriptionTo(message);
  }

  /**
   * Verifies that the actual value does not satisfy the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value satisfies the given condition.
   */
  protected final void assertDoesNotSatisfy(Condition<T> condition) {
    if (!matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfConditionSatisfied(condition));
  }

  private String errorMessageIfConditionSatisfied(Condition<T> condition) {
    String message = concat("actual value:", inBrackets(actual), " should not satisfy condition");
    return condition.addDescriptionTo(message);
  }

  /**
   * Verifies that the actual value does not satisfy the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value satisfies the given condition.
   */
  protected final void assertIsNot(Condition<T> condition) {
    if (!matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfIs(condition));
  }

  private boolean matches(Condition<T> condition) {
    validateIsNotNull(condition);
    return condition.matches(actual);
  }

  private void validateIsNotNull(Condition<T> condition) {
    if (condition == null) throw new NullPointerException("Condition to check should not be null");
  }

  private String errorMessageIfIs(Condition<T> condition) {
    String message = concat("actual value:", inBrackets(actual), " should not be");
    return condition.addDescriptionTo(message);
  }

  /**
   * Verifies that the actual value is equal to the given one.
   * @param expected the value to compare the actual value to.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  protected final void assertEqualTo(T expected) {
    failIfNotEqual(customErrorMessage(), rawDescription(), actual, expected);
  }

  /**
   * Verifies that the actual value is not equal to the given one.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  protected final void assertNotEqualTo(T other) {
    failIfEqual(customErrorMessage(), rawDescription(), actual, other);
  }

  /**
   * Verifies that the actual value is not <code>null</code>.
   * @throws AssertionError if the actual value is <code>null</code>.
   */
  protected final void assertNotNull() {
    failIfNull(customErrorMessage(), rawDescription(), actual);
  }

  /**
   * Verifies that the actual value is the same as the given one.
   * @param expected the value to compare the actual value to.
   * @throws AssertionError if the actual value is not the same as the given one.
   */
  protected final void assertSameAs(T expected) {
    failIfNotSame(customErrorMessage(), rawDescription(), actual, expected);
  }

  /**
   * Verifies that the actual value is not the same as the given one.
   * @param expected the value to compare the actual value to.
   * @throws AssertionError if the actual value is the same as the given one.
   */
  protected final void assertNotSameAs(T expected) {
    failIfSame(customErrorMessage(), rawDescription(), actual, expected);
  }

  /**
   * Replaces the default message displayed in case of a failure with the given one.
   * <p>
   * For example, the following assertion:
   * <pre>
   * assertThat("Hello").isEqualTo("Bye");
   * </pre>
   * will fail with the default message "<em>expected:<'[Bye]'> but was:<'[Hello]'></em>."
   * </p>
   * <p>
   * We can replace this message with our own:
   * <pre>
   * assertThat("Hello").overridingErrorMessage("'Hello' should be equal to 'Bye'").isEqualTo("Bye");
   * </pre>
   * in this case, the assertion will fail showing the message "<em>'Hello' should be equal to 'Bye'</em>".
   * </p>
   * @param message the given error message, which will replace the default one.
   * @return this assertion.
   * @since 1.2
   */
  protected abstract GenericAssert<T> overridingErrorMessage(String message);
}
