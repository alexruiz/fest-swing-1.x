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

  /** {@inheritDoc} */
  protected abstract ObjectGroupAssert<T> as(String description);

  /** {@inheritDoc} */
  protected abstract ObjectGroupAssert<T> describedAs(String description);

  /** {@inheritDoc} */
  protected abstract ObjectGroupAssert<T> as(Description description);

  /** {@inheritDoc} */
  protected abstract ObjectGroupAssert<T> describedAs(Description description);

  /** {@inheritDoc} */
  protected abstract ObjectGroupAssert<T> overridingErrorMessage(String message);
}
