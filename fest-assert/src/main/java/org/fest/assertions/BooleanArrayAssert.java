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
 * Understands assertion methods for <code>boolean</code> arrays. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(boolean[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BooleanArrayAssert extends ArrayAssert<boolean[]> {

  /**
   * Creates a new </code>{@link BooleanArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected BooleanArrayAssert(boolean... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public BooleanArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public BooleanArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public BooleanArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public BooleanArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>boolean</code> array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>boolean</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array does not contain the given values.
   */
  public BooleanArrayAssert contains(boolean...values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>boolean</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array does not contain the given objects, or if the
   * actual <code>boolean</code> array contains elements other than the ones specified.
   */
  public BooleanArrayAssert containsOnly(boolean...values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>boolean</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array contains any of the given values.
   */
  public BooleanArrayAssert excludes(boolean...values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array does not satisfy the given condition.
   * @see #is(Condition)
   */
  public BooleanArrayAssert satisfies(Condition<boolean[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array satisfies the given condition.
   * @see #isNot(Condition)
   */
  public BooleanArrayAssert doesNotSatisfy(Condition<boolean[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array does not satisfy the given condition.
   * @since 1.2
   */
  public BooleanArrayAssert is(Condition<boolean[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array satisfies the given condition.
   * @since 1.2
   */
  public BooleanArrayAssert isNot(Condition<boolean[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is <code>null</code>.
   */
  public BooleanArrayAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>boolean</code> array is empty.
   */
  public BooleanArrayAssert isNotEmpty() {
    assertIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(boolean[], boolean[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is not equal to the given one.
   */
  public BooleanArrayAssert isEqualTo(boolean[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>boolean</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(boolean[], boolean[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is equal to the given one.
   */
  public BooleanArrayAssert isNotEqualTo(boolean[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual <code>boolean</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>boolean</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>boolean</code> array is not equal to the given
   * one.
   */
  public BooleanArrayAssert hasSize(int expected) {
    assertHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is not the same as the given one.
   */
  public BooleanArrayAssert isSameAs(boolean[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>boolean</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>boolean</code> array is the same as the given one.
   */
  public BooleanArrayAssert isNotSameAs(boolean[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  public BooleanArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
