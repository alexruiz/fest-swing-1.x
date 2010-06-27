/*
 * Created on Mar 3, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.assertions.PropertySupport.propertyValues;
import static org.fest.util.Collections.*;
import static org.fest.util.Strings.concat;

import java.util.*;

/**
 * Understands assertions for <code>Object</code> arrays. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(Object[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectArrayAssert extends ArrayAssert<Object[]> {

  /**
   * Creates a new </code>{@link ObjectArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected ObjectArrayAssert(Object... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that all the elements in the actual <code>Object</code> array belong to the specified type. Matching
   * includes subclasses of the given type.
   * <p>
   * For example, consider the following code listing:
   * <pre>
   * Number[] numbers = { 2, 6 ,8 };
   * assertThat(numbers).hasComponentType(Integer.class);
   * </pre>
   * The assertion <code>hasAllElementsOfType</code> will be successful.
   * </p>
   * @param type the expected type.
   * @return this assertion object.
   * @throws NullPointerException if the given type is <code>null</code>.
   * @throws AssertionError if the component type of the actual <code>Object</code> array is not the same as the
   * specified one.
   */
  public ObjectArrayAssert hasAllElementsOfType(Class<?> type) {
    validateIsNotNull(type);
    isNotNull();
    for (Object o : actual) {
      if (type.isInstance(o)) continue;
      failIfCustomMessageIsSet();
      fail(concat("not all elements in array:", actualInBrackets(), " belong to the type:", inBrackets(type)));
    }
    return this;
  }

  /**
   * Verifies that at least one element in the actual <code>Object</code> array belong to the specified type. Matching
   * includes subclasses of the given type.
   * @param type the expected type.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> does not have any elements of the given type.
   */
  public ObjectArrayAssert hasAtLeastOneElementOfType(Class<?> type) {
    validateIsNotNull(type);
    isNotNull();
    boolean found = false;
    for (Object o : actual) {
      if (!type.isInstance(o)) continue;
      found = true;
      break;
    }
    if (found) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("array:", actualInBrackets(), " does not have any elements of type:", inBrackets(type)));
  }

  private void validateIsNotNull(Class<?> type) {
    if (type == null) throw new NullPointerException(unexpectedNullType(rawDescription()));
  }

  /**
   * Verifies that the actual <code>Object</code> array contains the given objects.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>Object</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array does not contain the given objects.
   */
  public ObjectArrayAssert contains(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    assertContains(list(objects));
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array contains the given objects <strong>only</strong>.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>Object</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array does not contain the given objects, or if the actual
   * <code>Object</code> array contains elements other than the ones specified.
   */
  public ObjectArrayAssert containsOnly(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    assertContainsOnly(list(objects));
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array does not contain the given objects.
   * @param objects the objects the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is <code>null</code>.
   * @throws NullPointerException if the given <code>Object</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array contains any of the given objects.
   */
  public ObjectArrayAssert excludes(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    assertExcludes(list(objects));
    return this;
  }

  private void validateIsNotNull(Object[] objects) {
    if (objects == null)
      throw new NullPointerException(formattedErrorMessage("the given array of objects should not be null"));
  }

  /**
   * Verifies that the actual <code>Object</code> array does not have duplicates.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array has duplicates.
   */
  public ObjectArrayAssert doesNotHaveDuplicates() {
    isNotNull();
    Collection<?> actualAsList = list(actual);
    Collection<?> duplicates = duplicatesFrom(actualAsList);
    if (duplicates.isEmpty()) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("array:", actualInBrackets(), " contains duplicate(s):", inBrackets(duplicates)));
  }

  /**
   * Verifies that the actual <code>Object</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array does not satisfy the given condition.
   * @see #is(Condition)
   */
  public ObjectArrayAssert satisfies(Condition<Object[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array satisfies the given condition.
   * @see #isNot(Condition)
   */
  public ObjectArrayAssert doesNotSatisfy(Condition<Object[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array does not satisfy the given condition.
   * @since 1.2
   */
  public ObjectArrayAssert is(Condition<Object[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array satisfies the given condition.
   * @since 1.2
   */
  public ObjectArrayAssert isNot(Condition<Object[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is <code>null</code>.
   */
  public ObjectArrayAssert isNotNull() {
    assertThatActualIsNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>Object</code> array is empty.
   */
  public ObjectArrayAssert isNotEmpty() {
    assertThatActualIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#deepEquals(Object[], Object[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is not equal to the given one.
   */
  public ObjectArrayAssert isEqualTo(Object[] expected) {
    if (Arrays.deepEquals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>Object</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#deepEquals(Object[], Object[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is equal to the given one.
   */
  public ObjectArrayAssert isNotEqualTo(Object[] array) {
    if (!Arrays.deepEquals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual <code>Object</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>Object</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>Object</code> array is not equal to the given
   * one.
   */
  public ObjectArrayAssert hasSize(int expected) {
    assertThatActualHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is not the same as the given one.
   */
  public ObjectArrayAssert isSameAs(Object[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>Object</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>Object</code> array is the same as the given one.
   */
  @Override
  public ObjectArrayAssert isNotSameAs(Object[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Creates a new instance of <code>{@link ObjectArrayAssert}</code> whose target array contains the values of the
   * given property name from the elements of this {@code ObjectArrayAssert}'s array. Property access works with both
   * simple properties like {@code Person.age} and nested properties {@code Person.father.age}.
   * </p>
   * <p>
   * For example, let's say we have a array of {@code Person} objects and you want to verify their age:
   * <pre>
   * assertThat(persons).onProperty("age").containsOnly(25, 16, 44, 37); // simple property
   * assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property
   * </p>
   * @param propertyName the name of the property to extract values from the actual array to build a new
   * {@code ObjectArrayAssert}.
   * @return a new {@code ObjectArrayAssert} containing the values of the given property name from the elements of this
   * {@code ObjectArrayAssert}'s array.
   * @throws AssertionError if the actual array is <code>null</code>.
   * @throws AssertionError if an element of actual array doesn't have the requested property.
   * @since 1.3
   */
  public ObjectArrayAssert onProperty(String propertyName) {
    isNotNull();
    if (actual.length == 0) return new ObjectArrayAssert(new Object[0]);
    return new ObjectArrayAssert(propertyValues(propertyName, list(actual)).toArray());
  }
}
