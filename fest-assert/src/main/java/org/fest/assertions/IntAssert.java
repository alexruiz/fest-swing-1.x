/*
 * Created on Jun 14, 2007
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

import static java.lang.Integer.valueOf;
import static org.fest.assertions.ErrorMessages.*;

/**
 * Understands assert method for {@code Integer}s and {@code int}s. To create a new instance of this class call
 * <code>{@link Assertions#assertThat(Integer)}</code> or <code>{@link Assertions#assertThat(int)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class IntAssert extends GenericAssert<Integer> implements NumberAssert {

  private static final int ZERO = 0;

  /**
   * Creates a new </code>{@link IntAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected IntAssert(int actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link IntAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected IntAssert(Integer actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  @Override
  public IntAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IntAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public IntAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IntAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code Integer} is equal to the given one.
   *
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not equal to the given one.
   */
  public IntAssert isEqualTo(int expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Integer} is equal to the given one.
   * @param expected the given {@code Integer} to compare the actual <code>Integer</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not equal to the given one.
   * @since 1.3
   */
  @Override
  public IntAssert isEqualTo(Integer expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Integer} is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is equal to the given one.
   */
  public IntAssert isNotEqualTo(int other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual <code>{@link Integer}</code> is not equal to the given one.
   * @param other the given <code>Integer</code> to compare the actual <code>Integer</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is equal to the given one.
   * @since 1.3
   */
  @Override
  public IntAssert isNotEqualTo(Integer other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual {@code Integer} is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not greater than the given one.
   */
  public IntAssert isGreaterThan(int other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Integer} is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not less than the given one.
   */
  public IntAssert isLessThan(int other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Integer} is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not greater than or equal to the given one.
   */
  public IntAssert isGreaterThanOrEqualTo(int other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Integer} is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not less than or equal to the given one.
   */
  public IntAssert isLessThanOrEqualTo(int other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Integer} is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not equal to zero.
   */
  public IntAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Integer} is positive.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not positive.
   */
  public IntAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Integer} is negative.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not negative.
   */
  public IntAssert isNegative() {
    return isLessThan(ZERO);
  }

  /** {@inheritDoc} */
  @Override
  public IntAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Integer}</code> satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Integer</code> does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  @Override
  public IntAssert satisfies(Condition<Integer> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Integer}</code> does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  @Override
  public IntAssert doesNotSatisfy(Condition<Integer> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Integer</code> does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public IntAssert is(Condition<Integer> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Integer</code> does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public IntAssert isNot(Condition<Integer> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Integer}</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is <code>null</code>.
   * @since 1.3
   */
  @Override
  public IntAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Integer}</code> is the same object as the given one.
   * @param expected the given <code>Integer</code> to compare the actual <code>Integer</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is not the same as the given one.
   * @since 1.3
   */
  @Override
  public IntAssert isSameAs(Integer expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Integer}</code> is not the same object as the given one.
   * @param other the given <code>Integer</code> to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Integer} is the same as the given one.
   * @since 1.3
   */
  @Override
  public IntAssert isNotSameAs(Integer other) {
    assertNotSameAs(other);
    return this;
  }
}
