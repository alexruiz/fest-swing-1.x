/*
 * Created on May 21, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

/**
 * Understands a template for assertion methods related to classes representing groups of values.
 * @param <T> the type of object implementations of this template can verify.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class GroupAssert<T> extends GenericAssert<T> {

  /**
   * Creates a new <code>{@link GroupAssert}</code>.
   * @param actual the target to verify.
   */
  protected GroupAssert(T actual) {
    super(actual);
  }

  /**
   * Verifies that the actual group of values is <code>null</code> or empty.
   * @throws AssertionError if the actual group of values is not <code>null</code> or not empty.
   */
  public void isNullOrEmpty() {
    if (actual == null || !hasElements()) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting null or empty, but was:", inBrackets(actual)));
  }

  /**
   * Verifies that the actual group of values is empty.
   * @throws AssertionError if the actual group of values is <code>null</code> or not empty.
   */
  public void isEmpty() {
    isNotNull();
    if (!hasElements()) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting empty, but was:", inBrackets(actual)));
  }

  private boolean hasElements() {
    return actualGroupSize() > 0;
  }

  /**
   * Verifies that the actual group contains at least on value.
   * @return this assertion object.
   * @throws AssertionError if the actual group is <code>null</code> or empty.
   */
  protected abstract GroupAssert<T> isNotEmpty();

  /**
   * Verifies that the number of values in the actual group is equal to the given one.
   * @param expected the expected number of values in the actual group.
   * @return this assertion object.
   * @throws AssertionError if the number of values of the actual group is not equal to the given one.
   */
  protected abstract GroupAssert<T> hasSize(int expected);

  /**
   * Verifies that the number of elements in the actual group of values is equal to the given one.
   * @param expected the expected number of elements in the actual group of values.
   * @throws AssertionError if the actual group of values is <code>null</code>.
   * @throws AssertionError if the number of elements of the actual group of values is not equal to the given one.
   */
  protected final void assertHasSize(int expected) {
    int size = actualGroupSize();
    if (size == expected) return;
    failIfCustomMessageIsSet();
    throw failure(concat("expected size:", inBrackets(expected), " but was:", inBrackets(size), " for ", inBrackets(actual)));
  }

  /**
   * Returns the size of the actual group of values (array, collection, etc.)
   * @return the size of the actual group of values.
   */
  protected abstract int actualGroupSize();

  /** {@inheritDoc} */
  protected abstract GroupAssert<T> as(String description);

  /** {@inheritDoc} */
  protected abstract GroupAssert<T> describedAs(String description);

  /** {@inheritDoc} */
  protected abstract GroupAssert<T> as(Description description);

  /** {@inheritDoc} */
  protected abstract GroupAssert<T> describedAs(Description description);

  /** {@inheritDoc} */
  protected abstract GroupAssert<T> overridingErrorMessage(String message);
}
