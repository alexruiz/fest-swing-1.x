/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayInspection.copy;
import static org.fest.assertions.ErrorMessages.*;

import java.util.Arrays;

/**
 * Understands assertion methods for <code>int</code> arrays. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(int[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class IntArrayAssert extends ArrayAssert<int[]> {

  /**
   * Creates a new </code>{@link IntArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected IntArrayAssert(int... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  @Override
  public IntArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IntArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public IntArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IntArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>int</code> array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>int</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array does not contain the given values.
   */
  public IntArrayAssert contains(int...values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>int</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array does not contain the given objects, or if the actual
   * <code>int</code> array contains elements other than the ones specified.
   */
  public IntArrayAssert containsOnly(int...values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>int</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array contains any of the given values.
   */
  public IntArrayAssert excludes(int...values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array does not satisfy the given condition.
   * @see #is(Condition)
   */
  @Override
  public IntArrayAssert satisfies(Condition<int[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array satisfies the given condition.
   * @see #isNot(Condition)
   */
  @Override
  public IntArrayAssert doesNotSatisfy(Condition<int[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array does not satisfy the given condition.
   * @since 1.2
   */
  @Override
  public IntArrayAssert is(Condition<int[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array satisfies the given condition.
   * @since 1.2
   */
  @Override
  public IntArrayAssert isNot(Condition<int[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is <code>null</code>.
   */
  @Override
  public IntArrayAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>int</code> array is empty.
   */
  @Override
  public IntArrayAssert isNotEmpty() {
    assertIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(int[], int[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is not equal to the given one.
   */
  @Override
  public IntArrayAssert isEqualTo(int[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>int</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(int[], int[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is equal to the given one.
   */
  @Override
  public IntArrayAssert isNotEqualTo(int[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual <code>int</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>int</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>int</code> array is not equal to the given
   * one.
   */
  @Override
  public IntArrayAssert hasSize(int expected) {
    assertHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is not the same as the given one.
   */
  @Override
  public IntArrayAssert isSameAs(int[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>int</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>int</code> array is the same as the given one.
   */
  @Override
  public IntArrayAssert isNotSameAs(int[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IntArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
