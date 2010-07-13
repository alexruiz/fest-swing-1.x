/*
 * Created on Dec 27, 2006
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
 * Copyright @2006-2009 the original author or authors.
 */
package org.fest.assertions;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

/**
 * Understands assertion methods for <code>{@link BigDecimal}</code>. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(BigDecimal)}</code>.
 *
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert extends ComparableAssert<BigDecimal> implements NumberAssert {

  /**
   * Creates a new </code>{@link BigDecimalAssert}</code>.
   * @param actual the target to verify.
   */
  protected BigDecimalAssert(BigDecimal actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public BigDecimalAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public BigDecimalAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public BigDecimalAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public BigDecimalAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> does not satisfy the given condition.
   * @see #is(Condition)
   */
  public BigDecimalAssert satisfies(Condition<BigDecimal> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   */
  public BigDecimalAssert doesNotSatisfy(Condition<BigDecimal> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> does not satisfy the given condition.
   * @since 1.2
   */
  public BigDecimalAssert is(Condition<BigDecimal> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @since 1.2
   */
  public BigDecimalAssert isNot(Condition<BigDecimal> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not positive.
   */
  public BigDecimalAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not negative.
   */
  public BigDecimalAssert isNegative() {
    return isLessThan(ZERO);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is equal to zero, regardless of precision.
   * Essentially, this is the same as
   * <code>{@link #isEqualByComparingTo(BigDecimal) isEqualByComparingTo}</code>(<code>{@link BigDecimal#ZERO BigDecimal.ZERO}</code>).
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not equal to zero.
   */
  public BigDecimalAssert isZero() {
    return isEqualByComparingTo(ZERO);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   */
  public BigDecimalAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is the same as the given one.
   * @param expected the given <code>BigDecimal</code> to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not the same as the given one.
   */
  public BigDecimalAssert isSameAs(BigDecimal expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is not the same as the given one.
   * @param other the given <code>BigDecimal</code> to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is the same as the given one.
   */
  public BigDecimalAssert isNotSameAs(BigDecimal other) {
    assertNotSameAs(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is equal to the given one. Unlike
   * <code>{@link #isEqualByComparingTo(BigDecimal)}</code>, this method considers two
   * <code>{@link BigDecimal}</code> objects equal only if they are equal in value and scale (thus 2.0 is not equal to
   * 2.00 when compared by this method).
   * @param expected the given <code>BigDecimal</code> to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not equal to the given one.
   * @see BigDecimal#equals(Object)
   * @see #isEqualByComparingTo(BigDecimal)
   */
  public BigDecimalAssert isEqualTo(BigDecimal expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is not equal to the given one.
   * @param other the given <code>BigDecimal</code> to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is equal to the given one.
   */
  public BigDecimalAssert isNotEqualTo(BigDecimal other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is equal to the given one. Two
   * <code>{@link BigDecimal}</code> objects that are equal in value but have a different scale (like 2.0 and 2.00)
   * are considered equal by this method.
   * @param expected the given <code>BigDecimal</code> to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not equal to the given one.
   * @see BigDecimal#compareTo(BigDecimal)
   */
  public BigDecimalAssert isEqualByComparingTo(BigDecimal expected) {
    assertIsEqualByComparingTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is <b>not</b> equal to the given one. Two
   * <code>{@link BigDecimal}</code> objects that are equal in value but have a different scale (like 2.0 and 2.00)
   * are considered equal by this method.
   * @param expected the given <code>BigDecimal</code> to use to compare to the actual <code>BigDecimal</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is equal to the given one.
   * @see BigDecimal#compareTo(BigDecimal)
   */
  public BigDecimalAssert isNotEqualByComparingTo(BigDecimal expected) {
    assertIsNotEqualByComparingTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> value is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not less than the given one.
   */
  public BigDecimalAssert isLessThan(BigDecimal other) {
    assertIsLessThan(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> value is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not greater than the given one.
   */
  public BigDecimalAssert isGreaterThan(BigDecimal other) {
    assertIsGreaterThan(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> value is less than or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not less than or equal to the given one.
   */
  public BigDecimalAssert isLessThanOrEqualTo(BigDecimal other) {
    assertIsLessThanOrEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> value is greater than or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not greater than or equal to the given one.
   */
  public BigDecimalAssert isGreaterThanOrEqualTo(BigDecimal other) {
    assertIsGreaterThanOrEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is not equal to zero, regardless of precision.
   * Essentially, this is the same as
   * <code>{@link #isEqualByComparingTo(BigDecimal) isNotEqualByComparingTo}</code>(<code>{@link BigDecimal#ZERO BigDecimal.ZERO}</code>).
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>BigDecimal</code> is equal to zero.
   */
  public BigDecimalAssert isNotZero() {
    return isNotEqualByComparingTo(ZERO);
  }

  /** {@inheritDoc} */
  public BigDecimalAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

}
