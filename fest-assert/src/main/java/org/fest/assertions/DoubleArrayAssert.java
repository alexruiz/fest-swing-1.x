/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.Math.abs;
import static org.fest.assertions.ArrayInspection.copy;
import static org.fest.assertions.ErrorMessages.unexpectedEqual;
import static org.fest.assertions.ErrorMessages.unexpectedNotEqual;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

import java.util.Arrays;

/**
 * Understands assertion methods for <code>double</code> arrays. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(double[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class DoubleArrayAssert extends ArrayAssert<double[]> {

  /**
   * Creates a new </code>{@link DoubleArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected DoubleArrayAssert(double... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public DoubleArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public DoubleArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public DoubleArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public DoubleArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>double</code> array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>double</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array does not contain the given values.
   */
  public DoubleArrayAssert contains(double... values) {
    isNotNull();
    validateIsNotNull(values);
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>double</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array does not contain the given objects, or if the actual
   * <code>double</code> array contains elements other than the ones specified.
   */
  public DoubleArrayAssert containsOnly(double... values) {
    isNotNull();
    validateIsNotNull(values);
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>double</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array contains any of the given values.
   */
  public DoubleArrayAssert excludes(double... values) {
    isNotNull();
    validateIsNotNull(values);
    assertExcludes(copy(values));
    return this;
  }

  private void validateIsNotNull(double[] values) {
    if (values == null)
      throw new NullPointerException(formattedErrorMessage("the given array of doubles should not be null"));
  }

  /**
   * Verifies that the actual <code>double</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array does not satisfy the given condition.
   * @see #is(Condition)
   */
  public DoubleArrayAssert satisfies(Condition<double[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array satisfies the given condition.
   * @see #isNot(Condition)
   */
  public DoubleArrayAssert doesNotSatisfy(Condition<double[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array does not satisfy the given condition.
   * @since 1.2
   */
  public DoubleArrayAssert is(Condition<double[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array satisfies the given condition.
   * @since 1.2
   */
  public DoubleArrayAssert isNot(Condition<double[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is <code>null</code>.
   */
  public DoubleArrayAssert isNotNull() {
    assertThatActualIsNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>double</code> array is empty.
   */
  public DoubleArrayAssert isNotEmpty() {
    assertThatActualIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(double[], double[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is not equal to the given one.
   */
  public DoubleArrayAssert isEqualTo(double[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>double</code> array is equal to the given array, within a positive delta.
   * @param expected the given array to compare the actual array to.
   * @param delta the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is not equal to the given one.
   * @since 1.1
   */
  public DoubleArrayAssert isEqualTo(double[] expected, Delta delta) {
    if (actual == expected) return this;
    if (actual == null || expected == null) throw failureWhenNotEqual(expected, delta);
    int length = expected.length;
    if (actual.length != length) failureWhenNotEqual(expected, delta);
    for (int i = 0; i < length; i++)
      if (!equals(expected[i], actual[i], delta)) failureWhenNotEqual(expected, delta);
    return this;
  }

  private AssertionError failureWhenNotEqual(double[] expected, Delta delta) {
    failIfCustomMessageIsSet();
    throw failure(concat(unexpectedNotEqual(actual, expected), " using delta:", inBrackets(delta.doubleValue())));
  }

  private boolean equals(double e, double a, Delta delta) {
    if (Double.compare(e, a) == 0) return true;
    return abs(e - a) <= delta.doubleValue();
  }

  /**
   * Verifies that the actual <code>double</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(double[], double[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is equal to the given one.
   */
  public DoubleArrayAssert isNotEqualTo(double[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual <code>double</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>double</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>double</code> array is not equal to the given
   * one.
   */
  public DoubleArrayAssert hasSize(int expected) {
    assertThatActualHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is not the same as the given one.
   */
  public DoubleArrayAssert isSameAs(double[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>double</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> array is the same as the given one.
   */
  public DoubleArrayAssert isNotSameAs(double[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  public DoubleArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
