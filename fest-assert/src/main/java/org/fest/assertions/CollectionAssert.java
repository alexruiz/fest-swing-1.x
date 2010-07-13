/*
 * Created on Dec 27, 2006
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
 * Copyright @2006-2010 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.assertions.PropertySupport.propertyValues;

import java.util.*;

import org.fest.util.IntrospectionError;

/**
 * Understands assertions for collections. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(Collection)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert extends ObjectGroupAssert<Collection<?>> {

  /**
   * Creates a new </code>{@link CollectionAssert}</code>.
   * @param actual the target to verify.
   */
  protected CollectionAssert(Collection<?> actual) {
    super(actual);
  }

  /**
   * Verifies that the actual collection contains the given objects.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual collection does not contain the given objects.
   */
  public CollectionAssert contains(Object... objects) {
    assertContains(objects);
    return this;
  }

  /**
   * Verifies that the actual collection contains the given objects <strong>only</strong>.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual collection does not contain the given objects, or if the actual collection
   *           contains elements other than the ones specified.
   */
  public CollectionAssert containsOnly(Object... objects) {
    assertContainsOnly(objects);
    return this;
  }

  /**
   * Verifies that the actual collection does not contain the given objects.
   * @param objects the objects that the collection should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual collection contains any of the given objects.
   */
  public CollectionAssert excludes(Object... objects) {
    assertExcludes(objects);
    return this;
  }

  /**
   * Verifies that the actual collection does not have duplicates.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws AssertionError if the actual collection has duplicates.
   */
  public CollectionAssert doesNotHaveDuplicates() {
    assertDoesNotHaveDuplicates();
    return this;
  }

  /** {@inheritDoc} */
  public CollectionAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public CollectionAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public CollectionAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public CollectionAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual collection satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual collection does not satisfy the given condition.
   * @see #is(Condition)
   */
  public CollectionAssert satisfies(Condition<Collection<?>> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual collection does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual collection satisfies the given condition.
   * @see #isNot(Condition)
   */
  public CollectionAssert doesNotSatisfy(Condition<Collection<?>> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual collection does not satisfy the given condition.
   * @since 1.2
   */
  public CollectionAssert is(Condition<Collection<?>> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual collection satisfies the given condition.
   * @since 1.2
   */
  public CollectionAssert isNot(Condition<Collection<?>> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual collection is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   */
  public CollectionAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual collection contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws AssertionError if the actual collection is empty.
   */
  public CollectionAssert isNotEmpty() {
    assertIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the number of elements in the actual collection is equal to the given one.
   * @param expected the expected number of elements in the actual collection.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws AssertionError if the number of elements of the actual collection is not equal to the given one.
   */
  public CollectionAssert hasSize(int expected) {
    assertHasSize(expected);
    return this;
  }

  /**
   * Returns the number of elements in the actual collection.
   * @return the number of elements in the actual collection.
   * @throws AssertionError if the actual collection is <code>null</code>.
   */
  protected int actualGroupSize() {
    isNotNull();
    return actual.size();
  }

  /**
   * Verifies that the actual collection is equal to the given one.
   * @param expected the given collection to compare the actual collection to.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is not equal to the given one.
   */
  public CollectionAssert isEqualTo(Collection<?> expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual collection is not equal to the given one.
   * @param other the given collection to compare the actual collection to.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is equal to the given one.
   */
  public CollectionAssert isNotEqualTo(Collection<?> other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual collection is the same as the given one.
   * @param expected the given collection to compare the actual collection to.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is not the same as the given one.
   */
  public CollectionAssert isSameAs(Collection<?> expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual collection is not the same as the given one.
   * @param other the given collection to compare the actual collection to.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is the same as the given one.
   */
  public CollectionAssert isNotSameAs(Collection<?> other) {
    assertNotSameAs(other);
    return this;
  }

  /** {@inheritDoc} */
  public CollectionAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Creates a new instance of <code>{@link CollectionAssert}</code> whose target collection contains the values of the
   * given property name from the elements of this {@code CollectionAssert}'s collection. Property access works with
   * both simple properties like {@code Person.age} and nested properties {@code Person.father.age}.
   * </p>
   * <p>
   * For example, let's say we have a collection of {@code Person} objects and you want to verify their age:
   * <pre>
   * assertThat(persons).onProperty("age").containsOnly(25, 16, 44, 37); // simple property
   * assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property
   * </p>
   * @param propertyName the name of the property to extract values from the actual collection to build a new
   * {@code CollectionAssert}.
   * @return a new {@code CollectionAssert} containing the values of the given property name from the elements of this
   * {@code CollectionAssert}'s collection.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws IntrospectionError if an element in the given collection does not have a matching property.
   * @since 1.3
   */
  public CollectionAssert onProperty(String propertyName) {
    isNotNull();
    if (actual.isEmpty()) return new CollectionAssert(emptyList());
    return new CollectionAssert(propertyValues(propertyName, actual));
  }

  /** {@inheritDoc} */
  protected Set<Object> actualAsSet() {
    return new HashSet<Object>(actual);
  }

  /** {@inheritDoc} */
  protected List<Object> actualAsList() {
    return new ArrayList<Object>(actual);
  }
}
