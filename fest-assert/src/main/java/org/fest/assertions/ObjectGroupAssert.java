/*
 * Created on Jun 28, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import org.fest.util.IntrospectionError;

/**
 * Understands a template for assertion methods related to arrays or collections.
 * @param <T> the type of object implementations of this template can verify.
 *
 * @author Yvonne Wang
 *
 * @since 1.3
 */
public abstract class ObjectGroupAssert<T> extends ItemGroupAssert<T> {

  /**
   * Creates a new </code>{@link ObjectGroupAssert}</code>.
   * @param actual the target to verify.
   */
  protected ObjectGroupAssert(T actual) {
    super(actual);
  }

  /**
   * Verifies that the actual group of objects contains the given objects.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual group of objects does not contain the given objects.
   */
  protected abstract ObjectGroupAssert<T> contains(Object... objects);

  /**
   * Verifies that the actual group of objects contains the given objects <strong>only</strong>, in any order.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given group of objects is <code>null</code>.
   * @throws AssertionError if the actual group of objects does not contain the given objects, or if the actual group of
   * objects contains elements other than the ones specified.
   */
  protected abstract ObjectGroupAssert<T> containsOnly(Object... objects);

  /**
   * Verifies that the actual group of objects does not contain the given objects.
   * @param objects the objects that the group of objects should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual group of objects contains any of the given objects.
   */
  protected abstract ObjectGroupAssert<T> excludes(Object... objects);

  /**
   * Verifies that the actual group of objects does not have duplicates.
   * @return this assertion object.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws AssertionError if the actual group of objects has duplicates.
   */
  protected abstract ObjectGroupAssert<T> doesNotHaveDuplicates();

  /**
   * Creates a new group of objects whose target collection contains the values of the given property name from the
   * elements of the actual group of objects. Property access works with both simple properties like {@code Person.age}
   * and nested properties {@code Person.father.age}.
   * <p>
   * For example, let's say we have a collection of {@code Person} objects and you want to verify their age:
   * <pre>
   * assertThat(persons).onProperty("age").containsOnly(25, 16, 44, 37); // simple property
   * assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property
   * </p>
   * @param propertyName the name of the property to extract values from the actual collection to build a new group of
   * objects.
   * @return a new group of objects containing the values of the given property name from the elements of the actual
   * group of objects.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws IntrospectionError if an element in the given collection does not have a matching property.
   * @since 1.3
   */
  protected abstract ObjectGroupAssert<T> onProperty(String propertyName);

  /** {@inheritDoc} */
  @Override
  protected abstract ObjectGroupAssert<T> as(String description);

  /** {@inheritDoc} */
  @Override
  protected abstract ObjectGroupAssert<T> describedAs(String description);

  /** {@inheritDoc} */
  @Override
  protected abstract ObjectGroupAssert<T> as(Description description);

  /** {@inheritDoc} */
  @Override
  protected abstract ObjectGroupAssert<T> describedAs(Description description);

  /** {@inheritDoc} */
  @Override
  protected abstract ObjectGroupAssert<T> overridingErrorMessage(String message);
}
