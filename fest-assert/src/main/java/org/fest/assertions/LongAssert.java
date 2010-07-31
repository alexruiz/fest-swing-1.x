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

import static java.lang.Long.valueOf;
import static org.fest.assertions.ErrorMessages.*;

/**
 * Understands assertion methods for {@code Long}s and {@code long}s. To create a new instance of this class call
 * <code>{@link Assertions#assertThat(Long)}</code> or <code>{@link Assertions#assertThat(long)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class LongAssert extends GenericAssert<Long> implements NumberAssert {

  private static final long ZERO = 0L;

  /**
   * Creates a new <code>{@link LongAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected LongAssert(long actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link LongAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected LongAssert(Long actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  @Override
  public LongAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public LongAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public LongAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public LongAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code Long} is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not equal to the given one.
   */
  public LongAssert isEqualTo(long expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Long} is equal to the given one.
   * @param expected the value to compare the actual {@code Long} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not equal to the given one.
   * @since 1.3
   */
  @Override
  public LongAssert isEqualTo(Long expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Long} is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is equal to the given one.
   */
  public LongAssert isNotEqualTo(long other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Long} is not equal to the given one.
   * @param other the value to compare the actual {@code Long} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is equal to the given one.
   * @since 1.3
   */
  @Override
  public LongAssert isNotEqualTo(Long other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual {@code Long} is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not greater than the given one.
   */
  public LongAssert isGreaterThan(long other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not less than the given one.
   */
  public LongAssert isLessThan(long other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not greater than or equal to the given one.
   */
  public LongAssert isGreaterThanOrEqualTo(long other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not less than or equal to the given one.
   */
  public LongAssert isLessThanOrEqualTo(long other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not equal to zero.
   */
  public LongAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Long} is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not positive.
   */
  public LongAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Long} is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not negative.
   */
  public LongAssert isNegative() {
    return isLessThan(ZERO);
  }

  /** {@inheritDoc} */
  @Override
  public LongAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Verifies that the actual {@code Long} satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Long} does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  @Override
  public LongAssert satisfies(Condition<Long> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Long} does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  @Override
  public LongAssert doesNotSatisfy(Condition<Long> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Long} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public LongAssert is(Condition<Long> condition) {
    assertIs(condition);
    return this;
  }


  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Long} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public LongAssert isNot(Condition<Long> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Long} is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is <code>null</code>.
   * @since 1.3
   */
  @Override
  public LongAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual {@code Long} is the same object as the given one.
   * @param expected the value to compare the actual {@code Long} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not the same as the given one.
   * @since 1.3
   */
  @Override
  public LongAssert isSameAs(Long expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Long} is not the same object as the given one.
   * @param other the value to compare the actual {@code Long} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is the same as the given one.
   * @since 1.3
   */
  @Override
  public LongAssert isNotSameAs(Long other) {
    assertNotSameAs(other);
    return this;
  }
}
