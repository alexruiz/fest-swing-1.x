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
 * Understands assertion methods for {@code Character}s. To create a new instance of this class use the methods
 * <code>{@link Assertions#assertThat(char)}</code> or <code>{@link Assertions#assertThat(Character)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 */
public class CharAssert extends GenericAssert<Character> {

  /**
   * Creates a new <code>{@link CharAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected CharAssert(char actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link CharAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected CharAssert(Character actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public CharAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public CharAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public CharAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public CharAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the {@code Character} value is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not equal to the given one.
   */
  public CharAssert isEqualTo(char expected) {
    if (actual == expected) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the {@code Character} value is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is equal to the given one.
   */
  public CharAssert isNotEqualTo(char other) {
    if (actual != other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not greater than the given one.
   */
  public CharAssert isGreaterThan(char other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not less than the given one.
   */
  public CharAssert isLessThan(char other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not greater than or equal to the given one.
   */
  public CharAssert isGreaterThanOrEqualTo(char other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not less than or equal to the given one.
   */
  public CharAssert isLessThanOrEqualTo(char other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is an upper-case value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not an upper-case value.
   */
  public CharAssert isUpperCase() {
    if (Character.isUpperCase(actual)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(inBrackets(actual), " should be an upper-case character"));
  }

  /**
   * Verifies that the {@code Character} value is an lower-case value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not an lower-case value.
   */
  public CharAssert isLowerCase() {
    if (Character.isLowerCase(actual)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(inBrackets(actual), " should be a lower-case character"));
  }

  /** {@inheritDoc} */
  public CharAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Character} does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  public CharAssert satisfies(Condition<Character> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Character} does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  public CharAssert doesNotSatisfy(Condition<Character> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Character} does not satisfy the given condition.
   * @since 1.3
   */
  public CharAssert is(Condition<Character> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Character} does not satisfy the given condition.
   * @since 1.3
   */
  public CharAssert isNot(Condition<Character> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> value is equal to the given one.
   * @param expected the given {@code Character} value to compare the actual {@code Character} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Character} value is not equal to the given one.
   * @since 1.3
   */
  public CharAssert isEqualTo(Character expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is not equal to the given one.
   * @param other the given {@code Character} to compare the actual {@code Character} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Character} value is equal to the given one.
   * @since 1.3
   */
  public CharAssert isNotEqualTo(Character other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Character} value is <code>null</code>.
   * @since 1.3
   */
  public CharAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is the same object as the given one.
   * @param expected the given {@code Character} to compare the actual {@code Character} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Character} value is not the same as the given one.
   * @since 1.3
   */
  public CharAssert isSameAs(Character expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Character}</code> is not the same object as the given one.
   * @param other the given {@code Character} to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Character} value is the same as the given one.
   * @since 1.3
   */
  public CharAssert isNotSameAs(Character other) {
    assertNotSameAs(other);
    return this;
  }
}
