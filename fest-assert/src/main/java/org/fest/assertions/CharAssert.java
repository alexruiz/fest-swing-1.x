/*
 * Created on Jun 18, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

/**
 * Understands assertion methods for <code>char</code>s. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(char)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class CharAssert extends GenericAssert<Character> {

  /**
   * Creates a new <code>{@link CharAssert}</code>.
   *
   * @param actual the actual value to verify.
   */
  protected CharAssert(char actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link CharAssert}</code>.
   *
   * @param actual the actual value to verify.
   */
  protected CharAssert(Character actual) {
    super(actual);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description. <p> For example:
   * <pre>
   * assertThat(value).<strong>as</strong>(&quot;Some value&quot;).isEqualTo(otherValue);
   * </pre>
   * </p>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public CharAssert as(String description) {
    description(description);
    return this;
  }

  /**
   * Alias for <code>{@link #as(String)}</code>, since "as" is a keyword in <a href="http://groovy.codehaus.org/"
   * target="_blank">Groovy</a>. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description. <p> For example:
   * <pre>
   * assertThat(value).<strong>describedAs</strong>(&quot;Some value&quot;).isEqualTo(otherValue);
   * </pre>
   * </p>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public CharAssert describedAs(String description) {
    return as(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description. <p> For example:
   * <pre>
   * assertThat(value).<strong>as</strong>(new BasicDescription(&quot;Some value&quot;)).isEqualTo(otherValue);
   * </pre>
   * </p>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public CharAssert as(Description description) {
    description(description);
    return this;
  }

  /**
   * Alias for <code>{@link #as(Description)}</code>, since "as" is a keyword in <a href="http://groovy.codehaus.org/"
   * target="_blank">Groovy</a>. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description. <p> For example:
   * <pre>
   * assertThat(value).<strong>describedAs</strong>(new BasicDescription(&quot;Some value&quot;))
   *   .isEqualTo(otherValue);
   * </pre>
   * </p>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public CharAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>char</code> value is equal to the given one.
   *
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is not equal to the given one.
   */
  public CharAssert isEqualTo(char expected) {
    if (actual == expected) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>char</code> value is not equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is equal to the given one.
   */
  public CharAssert isNotEqualTo(char other) {
    if (actual != other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, other));
  }

  /**
   * Verifies that the actual <code>char</code> value is greater than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is not greater than the given one.
   */
  public CharAssert isGreaterThan(char other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual <code>char</code> value is less than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is not less than the given one.
   */
  public CharAssert isLessThan(char other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual <code>char</code> value is greater or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is not greater than or equal to the given one.
   */
  public CharAssert isGreaterThanOrEqualTo(char other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual <code>char</code> value is less or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is not less than or equal to the given one.
   */
  public CharAssert isLessThanOrEqualTo(char other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual <code>char</code> value is an uppercase value.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is not an uppercase value.
   */
  public CharAssert isUpperCase() {
    if (Character.isUpperCase(actual)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(inBrackets(actual), " should be an uppercase character"));
  }

  /**
   * Verifies that the actual <code>char</code> value is an lowercase value.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual <code>char</code> value is not an lowercase value.
   */
  public CharAssert isLowerCase() {
    if (Character.isLowerCase(actual)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(inBrackets(actual), " should be a lowercase character"));
  }

  /** {@inheritDoc} */
  public CharAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> satisfies the given condition.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual <code>Char</code> does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  @Override
  public CharAssert satisfies(Condition<Character> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> does not satisfy the given condition.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  @Override
  public CharAssert doesNotSatisfy(Condition<Character> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual <code>Character</code> does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public CharAssert is(Condition<Character> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual <code>Character</code> does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public CharAssert isNot(Condition<Character> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> value is equal to the given one.
   *
   * @param expected the given <code>Character</code> value to compare the actual <code>Character</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Character</code> value is not equal to the given one.
   * @since 1.3
   */
  @Override
  public CharAssert isEqualTo(Character expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is not equal to the given one.
   *
   * @param other the given <code>Character</code> to compare the actual <code>Character</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Character</code> value is equal to the given one.
   * @since 1.3
   */
  @Override
  public CharAssert isNotEqualTo(Character other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Character</code> value is <code>null</code>.
   * @since 1.3
   */
  @Override
  public CharAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is the same object as the given one.
   * @param expected the given <code>Character</code> to compare the actual <code>Character</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Character</code> value is not the same as the given one.
   * @since 1.3
   */
  @Override
  public CharAssert isSameAs(Character expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is not the same object as the given one.
   * @param other the given <code>Character</code> to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Character</code> value is the same as the given one.
   * @since 1.3
   */
  @Override
  public CharAssert isNotSameAs(Character other) {
    assertNotSameAs(other);
    return this;
  }
}
