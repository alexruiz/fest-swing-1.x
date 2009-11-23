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
import static org.fest.assertions.ErrorMessages.unexpectedEqual;
import static org.fest.assertions.ErrorMessages.unexpectedNotEqual;

import java.util.Arrays;

/**
 * Understands assertion methods for <code>byte</code> arrays. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(byte[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ByteArrayAssert extends ArrayAssert<byte[]> {

  /**
   * Creates a new </code>{@link ByteArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected ByteArrayAssert(byte... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public ByteArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ByteArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public ByteArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ByteArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>byte</code> array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>byte</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array does not contain the given values.
   */
  public ByteArrayAssert contains(byte...values) {
    isNotNull();
    validateIsNotNull(values);
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>byte</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array does not contain the given objects, or if the actual
   * <code>byte</code> array contains elements other than the ones specified.
   */
  public ByteArrayAssert containsOnly(byte...values) {
    isNotNull();
    validateIsNotNull(values);
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>byte</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array contains any of the given values.
   */
  public ByteArrayAssert excludes(byte...values) {
    isNotNull();
    validateIsNotNull(values);
    assertExcludes(copy(values));
    return this;
  }

  private void validateIsNotNull(byte[] values) {
    if (values == null)
      throw new NullPointerException(formattedErrorMessage("the given array of bytes should not be null"));
  }

  /**
   * Verifies that the actual <code>byte</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array does not satisfy the given condition.
   * @see #is(Condition)
   */
  public ByteArrayAssert satisfies(Condition<byte[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array satisfies the given condition.
   * @see #isNot(Condition)
   */
  public ByteArrayAssert doesNotSatisfy(Condition<byte[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array does not satisfy the given condition.
   * @since 1.2
   */
  public ByteArrayAssert is(Condition<byte[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array satisfies the given condition.
   * @since 1.2
   */
  public ByteArrayAssert isNot(Condition<byte[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is <code>null</code>.
   */
  public ByteArrayAssert isNotNull() {
    assertThatActualIsNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>byte</code> array is empty.
   */
  public ByteArrayAssert isNotEmpty() {
    assertThatActualIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(byte[], byte[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is not equal to the given one.
   */
  public ByteArrayAssert isEqualTo(byte[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>byte</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(byte[], byte[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is equal to the given one.
   */
  public ByteArrayAssert isNotEqualTo(byte[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual <code>byte</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>byte</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>byte</code> array is not equal to the given
   * one.
   */
  public ByteArrayAssert hasSize(int expected) {
    assertThatActualHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is not the same as the given one.
   */
  public ByteArrayAssert isSameAs(byte[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>byte</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>byte</code> array is the same as the given one.
   */
  public ByteArrayAssert isNotSameAs(byte[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  public ByteArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
