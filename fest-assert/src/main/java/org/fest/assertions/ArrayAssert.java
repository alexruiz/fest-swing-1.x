/*
 * Created on Feb 16, 2008
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
import static org.fest.assertions.ArrayInspection.sizeOf;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

import java.util.ArrayList;
import java.util.List;

/**
 * Understands assertions for arrays.
 * @param <T> the generic type of the arrays.
 *
 * @author Alex Ruiz
 */
public abstract class ArrayAssert<T> extends GroupAssert<T> {

  /**
   * Creates a new </code>{@link ArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected ArrayAssert(T actual) {
    super(actual);
  }

  /**
   * Returns the size of the actual array.
   * @return the size of the actual array.
   */
  protected final int actualGroupSize() {
    isNotNull();
    return sizeOf(actual);
  }

  /**
   * Verifies that the actual <code>byte</code> array contains the given values.
   * @param values the values to look for.
   * @throws AssertionError if the actual <code>byte</code> array does not contain the given values.
   */
  protected final void assertContains(List<Object> values) {
    List<Object> notFound = notFoundInActual(values);
    if (notFound.isEmpty()) return;
    failIfCustomMessageIsSet();
    failureIfExpectedElementsNotFound(notFound.toArray());
  }

  private List<Object> notFoundInActual(List<Object> values) {
    List<Object> copy = copy(actual);
    List<Object> notFound = new ArrayList<Object>();
    for (Object value : values) {
      if (copy.contains(value)) continue;
      notFound.add(value);
    }
    return notFound;
  }

  /**
   * Verifies that the actual array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @throws AssertionError if the actual array does not contain the given objects, or if the actual array contains
   * elements other than the ones specified.
   */
  protected final void assertContainsOnly(List<Object> values) {
    List<Object> copy = copy(actual);
    List<Object> notFound = notFoundInCopy(copy, values);
    if (!notFound.isEmpty()) failureIfExpectedElementsNotFound(notFound.toArray());
    if (copy.isEmpty()) return;
    throw failureIfUnexpectedElementsFound(copy.toArray());
  }

  private List<Object> notFoundInCopy(List<Object> copy, List<Object> values) {
    List<Object> notFound = new ArrayList<Object>();
    for (Object value : values) {
      if (!copy.contains(value)) {
        notFound.add(value);
        continue;
      }
      copy.remove(value);
    }
    return notFound;
  }

  private void failureIfExpectedElementsNotFound(Object[] notFound) {
    failIfCustomMessageIsSet();
    fail(concat("array:", actualInBrackets(), " does not contain element(s):", inBrackets(notFound)));
  }

  private AssertionError failureIfUnexpectedElementsFound(Object[] unexpected) {
    failIfCustomMessageIsSet();
    return failure(concat("unexpected element(s):", inBrackets(unexpected), " in array:", actualInBrackets()));
  }

  /**
   * Verifies that the actual array does not contain the given values.
   * @param values the values the array should exclude.
   * @throws AssertionError if the actual array contains any of the given values.
   */
  protected final void assertExcludes(List<Object> values) {
    List<Object> copyOfActual = copy(actual);
    List<Object> found = new ArrayList<Object>();
    for (Object value : values) if (copyOfActual.contains(value)) found.add(value);
    if (found.isEmpty()) return;
    failIfCustomMessageIsSet();
    fail(concat("array:", actualInBrackets(), " does not exclude element(s):", inBrackets(found.toArray())));
  }

  /**
   * Returns the <code>String</code> representation of the actual array in between brackets ("<" and ">").
   * @return the <code>String</code> representation of the actual array in between brackets ("<" and ">").
   */
  protected final String actualInBrackets() {
    return inBrackets(actual);
  }

  /**
   * Verifies that the actual array is not <code>null</code>.
   * @throws AssertionError if the actual array is <code>null</code>.
   */
  protected final void assertThatActualIsNotNull() {
    if (actual != null) return;
    failIfCustomMessageIsSet();
    fail("expecting actual array not to be null");
  }

  /**
   * Verifies that the actual array is empty (not <code>null</code> with zero elements.)
   * @throws AssertionError if the actual array is <code>null</code> or not empty.
   */
  public final void isEmpty() {
    if (actualGroupSize() == 0) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting empty array, but was:", actualInBrackets()));
  }

  /**
   * Verifies that the actual array is <code>null</code> or empty.
   * @throws AssertionError if the actual array is not <code>null</code> or not empty.
   */
  public final void isNullOrEmpty() {
    if (actual == null || actualGroupSize() == 0) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting a null or empty array, but was:", actualInBrackets()));
  }

  /**
   * Verifies that the actual array contains at least on element.
   * @throws AssertionError if the actual array is <code>null</code>.
   * @throws AssertionError if the actual array is empty.
   */
  protected final void assertThatActualIsNotEmpty() {
    if (actualGroupSize() > 0) return;
    failIfCustomMessageIsSet();
    fail("expecting a non-empty array, but it was empty");
  }

  /**
   * Verifies that the number of elements in the actual array is equal to the given one.
   * @param expected the expected number of elements in the actual array.
   * @throws AssertionError if the actual array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual array is not equal to the given one.
   */
  protected final void assertThatActualHasSize(int expected) {
    int actualSize = actualGroupSize();
    if (actualSize == expected) return;
    failIfCustomMessageIsSet();
    fail(concat(
        "expected size:", inBrackets(expected)," but was:", inBrackets(actualSize), " for array:", actualInBrackets()));
  }
}
