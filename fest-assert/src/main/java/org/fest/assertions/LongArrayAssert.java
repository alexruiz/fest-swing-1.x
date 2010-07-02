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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayInspection.copy;
import static org.fest.assertions.ErrorMessages.*;

import java.util.Arrays;

/**
 * Understands assertion methods for <code>long</code> arrays. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(long[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class LongArrayAssert extends ArrayAssert<long[]> {

  /**
   * Creates a new </code>{@link LongArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected LongArrayAssert(long... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public LongArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public LongArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public LongArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public LongArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>long</code> array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array does not contain the given values.
   */
  public LongArrayAssert contains(long...values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array does not contain the given objects, or if the actual
   * <code>long</code> array contains elements other than the ones specified.
   */
  public LongArrayAssert containsOnly(long...values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array contains any of the given values.
   */
  public LongArrayAssert excludes(long...values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array does not satisfy the given condition.
   * @see #is(Condition)
   */
  public LongArrayAssert satisfies(Condition<long[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array satisfies the given condition.
   * @see #isNot(Condition)
   */
  public LongArrayAssert doesNotSatisfy(Condition<long[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array does not satisfy the given condition.
   * @since 1.2
   */
  public LongArrayAssert is(Condition<long[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array satisfies the given condition.
   * @since 1.2
   */
  public LongArrayAssert isNot(Condition<long[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   */
  public LongArrayAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array is empty.
   */
  public LongArrayAssert isNotEmpty() {
    assertIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(long[], long[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is not equal to the given one.
   */
  public LongArrayAssert isEqualTo(long[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>long</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(long[], long[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is equal to the given one.
   */
  public LongArrayAssert isNotEqualTo(long[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual <code>long</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>long</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>long</code> array is not equal to the given
   * one.
   */
  public LongArrayAssert hasSize(int expected) {
    assertHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is not the same as the given one.
   */
  public LongArrayAssert isSameAs(long[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is the same as the given one.
   */
  public LongArrayAssert isNotSameAs(long[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  public LongArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
