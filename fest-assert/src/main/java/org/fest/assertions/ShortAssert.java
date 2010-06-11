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

import static java.lang.Short.valueOf;
import static org.fest.assertions.ErrorMessages.*;

/**
 * Understands assertion methods for {@code Short}s and {@code short}s. To create a new instance of this class call
 * <code>{@link Assertions#assertThat(Short)}</code> <code>{@link Assertions#assertThat(short)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class ShortAssert extends GenericAssert<Short> implements NumberAssert {

  private static final short ZERO = (short) 0;

  /**
   * Creates a new <code>{@link ShortAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected ShortAssert(short actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link ShortAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected ShortAssert(Short actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public ShortAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ShortAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public ShortAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ShortAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code Short} is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not equal to the given one.
   */
  public ShortAssert isEqualTo(short expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Short} is equal to the given one.
   * @param expected the given value to compare the actual {@code Short} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not equal to the given one.
   * @since 1.3
   */
  public ShortAssert isEqualTo(Short expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Short} is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is equal to the given one.
   */
  public ShortAssert isNotEqualTo(short other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Short} is not equal to the given one.
   * @param other the given value to compare the actual {@code Short} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is equal to the given one.
   * @since 1.3
   */
  public ShortAssert isNotEqualTo(Short other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual {@code Short} is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not greater than the given one.
   */
  public ShortAssert isGreaterThan(short other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not less than the given one.
   */
  public ShortAssert isLessThan(short other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not greater than or equal to the given one.
   */
  public ShortAssert isGreaterThanOrEqualTo(short other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not less than or equal to the given one.
   */
  public ShortAssert isLessThanOrEqualTo(short other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not equal to zero.
   */
  public ShortAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Short} is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not positive.
   */
  public ShortAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Short} is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not negative.
   */
  public ShortAssert isNegative() {
    return isLessThan(ZERO);
  }

  /** {@inheritDoc} */
  public ShortAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Verifies that the actual {@code Short} satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Short} does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  public ShortAssert satisfies(Condition<Short> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Short} does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  public ShortAssert doesNotSatisfy(Condition<Short> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Short} does not satisfy the given condition.
   * @since 1.3
   */
  public ShortAssert is(Condition<Short> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Short} does not satisfy the given condition.
   * @since 1.3
   */
  public ShortAssert isNot(Condition<Short> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Short} is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is <code>null</code>.
   * @since 1.3
   */
  public ShortAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual {@code Short} is the same object as the given one.
   * @param expected the given value to compare the actual {@code Short} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not the same as the given one.
   * @since 1.3
   */
  public ShortAssert isSameAs(Short expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Short} is not the same object as the given one.
   * @param other the given value to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is the same as the given one.
   * @since 1.3
   */
  public ShortAssert isNotSameAs(Short other) {
    assertNotSameAs(other);
    return this;
  }
}
