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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.Byte.valueOf;
import static org.fest.assertions.ErrorMessages.*;

import org.fest.util.VisibleForTesting;

/**
 * Understands assertion methods for {@code Byte}s and {@code byte}s. To create a new instance of this class call
 * <code>{@link Assertions#assertThat(Byte)}</code> or <code>{@link Assertions#assertThat(byte)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class ByteAssert extends GenericAssert<Byte> implements NumberAssert {

  private static final byte ZERO = (byte) 0;

  @VisibleForTesting
  ByteAssert(int actual) {
    this((byte)actual);
  }

  /**
   * Creates a new <code>{@link ByteAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected ByteAssert(byte actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link ByteAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected ByteAssert(Byte actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  @Override
  public ByteAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ByteAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public ByteAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ByteAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code Byte} value is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not equal to the given one.
   */
  public ByteAssert isEqualTo(byte expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Byte} value is equal to the given one.
   * @param expected the given {@code Byte} value to compare the actual {@code Byte} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not equal to the given one.
   * @since 1.3
   */
  @Override
  public ByteAssert isEqualTo(Byte expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Byte} value is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is equal to the given one.
   */
  public ByteAssert isNotEqualTo(byte other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Byte} is not equal to the given one.
   * @param other the given {@code Byte} to compare the actual {@code Byte} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is equal to the given one.
   * @since 1.3
   */
  @Override
  public ByteAssert isNotEqualTo(Byte other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual {@code Byte} value is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not greater than the given one.
   */
  public ByteAssert isGreaterThan(byte other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not less than the given one.
   */
  public ByteAssert isLessThan(byte other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not greater than or equal to the given one.
   */
  public ByteAssert isGreaterThanOrEqualTo(byte other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not less than or equal to the given one.
   */
  public ByteAssert isLessThanOrEqualTo(byte other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not equal to zero.
   */
  public ByteAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Byte} value is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not positive.
   */
  public ByteAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Byte} value is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not negative.
   */
  public ByteAssert isNegative() {
    return isLessThan(ZERO);
  }

  /** {@inheritDoc} */
  @Override
  public ByteAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Verifies that the actual {@code Byte} satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Byte} does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  @Override
  public ByteAssert satisfies(Condition<Byte> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Byte} does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Byte} does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  @Override
  public ByteAssert doesNotSatisfy(Condition<Byte> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Byte} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public ByteAssert is(Condition<Byte> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Byte} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public ByteAssert isNot(Condition<Byte> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Byte} is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is <code>null</code>.
   * @since 1.3
   */
  @Override
  public ByteAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual {@code Byte} is the same object as the given one.
   * @param expected the given {@code Byte} to compare the actual {@code Byte} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not the same as the given one.
   * @since 1.3
   */
  @Override
  public ByteAssert isSameAs(Byte expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Byte} is not the same object as the given one.
   * @param other the given {@code Byte} to compare the actual {@code Byte} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is the same as the given one.
   * @since 1.3
   */
  @Override
  public ByteAssert isNotSameAs(Byte other) {
    assertNotSameAs(other);
    return this;
  }
}
