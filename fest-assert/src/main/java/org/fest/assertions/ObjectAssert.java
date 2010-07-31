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
 * Copyright @2006-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ErrorMessages.unexpectedNullType;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Objects.namesOf;
import static org.fest.util.Strings.concat;

import java.util.Arrays;

/**
 * Understands assertion methods for objects. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(Object)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectAssert extends GenericAssert<Object> {

  /**
   * Creates a new </code>{@link ObjectAssert}</code>.
   * @param actual the target to verify.
   */
  protected ObjectAssert(Object actual) {
    super(actual);
  }

  /**
   * Verifies that the actual <code>Object</code> is an instance of the given type.
   * @param type the type to check the actual <code>Object</code> against.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> is not an instance of the given type.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  public ObjectAssert isInstanceOf(Class<?> type) {
    isNotNull();
    validateNotNull(type);
    Class<?> current = actual.getClass();
    if (type.isAssignableFrom(current)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("expected instance of:", inBrackets(type), " but was instance of:", inBrackets(current)));
  }

  /**
   * Verifies that the actual <code>Object</code> is an instance of any of the given types.
   * @param types the types to check the actual <code>Object</code> against.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> is not an instance of any of the given types.
   * @throws NullPointerException if the given array of types is <code>null</code>.
   * @throws NullPointerException if the given array of types contains <code>null</code>s.
   */
  public ObjectAssert isInstanceOfAny(Class<?>...types) {
    isNotNull();
    if (types == null)
      throw new NullPointerException(formattedErrorMessage("The given array of types should not be null"));
    if (!foundInstanceOfAny(types))
      fail(concat("expected instance of any:<", typeNames(types), "> but was instance of:", inBrackets(actual.getClass())));
    return this;
  }

  private boolean foundInstanceOfAny(Class<?>...types) {
    Class<?> current = actual.getClass();
    for (Class<?> type : types) {
      validateNotNull(type);
      if (type.isAssignableFrom(current)) return true;
    }
    return false;
  }

  void validateNotNull(Class<?> type) {
    if (type == null)
      throw new NullPointerException(unexpectedNullType(rawDescription()));
  }

  private String typeNames(Class<?>... types) {
    return Arrays.toString(namesOf(types));
  }

  /** {@inheritDoc} */
  @Override
  public ObjectAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ObjectAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public ObjectAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ObjectAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>Object</code> satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> does not satisfy the given condition.
   * @see #is(Condition)
   */
  @Override
  public ObjectAssert satisfies(Condition<Object> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> satisfies the given condition.
   * @see #isNot(Condition)
   */
  @Override
  public ObjectAssert doesNotSatisfy(Condition<Object> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> does not satisfy the given condition.
   * @since 1.2
   */
  @Override
  public ObjectAssert is(Condition<Object> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> satisfies the given condition.
   * @since 1.2
   */
  @Override
  public ObjectAssert isNot(Condition<Object> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> is <code>null</code>.
   */
  @Override
  public ObjectAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> is the same as the given one.
   * @param expected the given <code>Object</code> to compare the actual <code>Object</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> is not the same as the given one.
   */
  @Override
  public ObjectAssert isSameAs(Object expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> is not the same as the given one.
   * @param other the given <code>Object</code> to compare the actual <code>Object</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> is the same as the given one.
   */
  @Override
  public ObjectAssert isNotSameAs(Object other) {
    assertNotSameAs(other);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> is equal to the given one.
   * @param expected the given <code>Object</code> to compare the actual <code>Object</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> is not equal to the given one.
   */
  @Override
  public ObjectAssert isEqualTo(Object expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> is not equal to the given one.
   * @param other the given <code>Object</code> to compare the actual <code>Object</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> is equal to the given one.
   */
  @Override
  public ObjectAssert isNotEqualTo(Object other) {
    assertNotEqualTo(other);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ObjectAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
