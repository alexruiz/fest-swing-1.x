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
 * Understands assertion methods for <code>short</code> arrays. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(short[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ShortArrayAssert extends ArrayAssert<short[]> {

  /**
   * Creates a new </code>{@link ShortArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected ShortArrayAssert(short... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public ShortArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ShortArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public ShortArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ShortArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>short</code> array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>short</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>short</code> array does not contain the given values.
   */
  public ShortArrayAssert contains(short...values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>short</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>short</code> array does not contain the given objects, or if the actual
   * <code>short</code> array contains elements other than the ones specified.
   */
  public ShortArrayAssert containsOnly(short...values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>short</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array contains any of the given values.
   */
  public ShortArrayAssert excludes(short...values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>short</code> array does not satisfy the given condition.
   * @see #is(Condition)
   */
  public ShortArrayAssert satisfies(Condition<short[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>short</code> array satisfies the given condition.
   * @see #isNot(Condition)
   */
  public ShortArrayAssert doesNotSatisfy(Condition<short[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>short</code> array does not satisfy the given condition.
   * @since 1.2
   */
  public ShortArrayAssert is(Condition<short[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>short</code> array satisfies the given condition.
   * @since 1.2
   */
  public ShortArrayAssert isNot(Condition<short[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is <code>null</code>.
   */
  public ShortArrayAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>short</code> array is empty.
   */
  public ShortArrayAssert isNotEmpty() {
    assertIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(short[], short[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is not equal to the given one.
   */
  public ShortArrayAssert isEqualTo(short[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>short</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(short[], short[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is equal to the given one.
   */
  public ShortArrayAssert isNotEqualTo(short[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual <code>short</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>short</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>short</code> array is not equal to the given
   * one.
   */
  public ShortArrayAssert hasSize(int expected) {
    assertHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is not the same as the given one.
   */
  public ShortArrayAssert isSameAs(short[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>short</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>short</code> array is the same as the given one.
   */
  public ShortArrayAssert isNotSameAs(short[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  public ShortArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
