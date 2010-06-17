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
 * Copyright @2006-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Collections.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.reflect.beanproperty.Invoker.descriptorForProperty;
import static org.fest.reflect.core.Reflection.property;
import static org.fest.reflect.util.PropertiesUtils.*;
import static org.fest.util.Collections.duplicatesFrom;
import static org.fest.util.Strings.concat;

import java.util.*;

import org.fest.util.Collections;

/**
 * Understands assertions for collections. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(Collection)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert extends GroupAssert<Collection<?>> {

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
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> notFound = notFoundInActual(objects);
    if (notFound.isEmpty()) return this;
    throw failureIfExpectedElementsNotFound(notFound);
  }

  private Collection<Object> notFoundInActual(Object... objects) {
    return notFound(actual, objects);
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
    isNotNull();
    validateIsNotNull(objects);
    List<Object> copy = new ArrayList<Object>(actual);
    List<Object> notFound = notFoundInCopy(copy, objects);
    if (!notFound.isEmpty()) throw failureIfExpectedElementsNotFound(notFound);
    if (copy.isEmpty()) return this;
    throw failureIfUnexpectedElementsFound(copy);
  }

  private List<Object> notFoundInCopy(List<Object> copy, Object... objects) {
    List<Object> notFound = new ArrayList<Object>();
    for (Object o : objects) {
      if (!copy.contains(o)) {
        notFound.add(o);
        continue;
      }
      copy.remove(o);
    }
    return notFound;
  }

  private AssertionError failureIfExpectedElementsNotFound(Collection<Object> notFound) {
    failIfCustomMessageIsSet();
    return failure(concat("collection:", format(actual), " does not contain element(s):", format(notFound)));
  }

  private AssertionError failureIfUnexpectedElementsFound(List<Object> unexpected) {
    failIfCustomMessageIsSet();
    return failure(concat("unexpected element(s):", format(unexpected), " in collection:", format(actual)));
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
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> found = found(actual, objects);
    if (found.isEmpty()) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("collection:", format(actual), " does not exclude element(s):", format(found)));
  }

  private void validateIsNotNull(Object[] objects) {
    if (objects == null)
      throw new NullPointerException(formattedErrorMessage("the given array of objects should not be null"));
  }

  /**
   * Verifies that the actual collection does not have duplicates.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws AssertionError if the actual collection has duplicates.
   */
  public CollectionAssert doesNotHaveDuplicates() {
    isNotNull();
    Collection<?> duplicates = duplicatesFrom(actual);
    if (duplicates.isEmpty()) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("collection:", format(actual), " contains duplicate(s):", format(duplicates)));
  }

  private String format(Collection<?> c) {
    return inBrackets(c);
  }

  /** {@inheritDoc} */
  @Override
  public CollectionAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public CollectionAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public CollectionAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
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
  @Override
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
  @Override
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
  @Override
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
  @Override
  public CollectionAssert isNot(Condition<Collection<?>> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual collection is <code>null</code> or empty.
   * @throws AssertionError if the actual collection is not <code>null</code> or not empty.
   */
  @Override
  public void isNullOrEmpty() {
    if (Collections.isEmpty(actual)) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting a null or empty collection, but was:", format(actual)));
  }

  /**
   * Verifies that the actual collection is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   */
  @Override
  public CollectionAssert isNotNull() {
    if (actual != null) return this;
    failIfCustomMessageIsSet();
    throw failure("expecting actual collection not to be null");
  }

  /**
   * Verifies that the actual collection is empty (not <code>null</code> with zero elements.)
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws AssertionError if the actual collection is not empty.
   */
  @Override
  public void isEmpty() {
    isNotNull();
    if (Collections.isEmpty(actual)) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting empty collection, but was:", format(actual)));
  }

  /**
   * Verifies that the actual collection contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws AssertionError if the actual collection is empty.
   */
  @Override
  public CollectionAssert isNotEmpty() {
    isNotNull();
    if (!actual.isEmpty()) return this;
    failIfCustomMessageIsSet();
    throw failure("expecting a non-empty collection, but it was empty");
  }

  /**
   * Verifies that the number of elements in the actual collection is equal to the given one.
   * @param expected the expected number of elements in the actual collection.
   * @return this assertion object.
   * @throws AssertionError if the actual collection is <code>null</code>.
   * @throws AssertionError if the number of elements of the actual collection is not equal to the given one.
   */
  @Override
  public CollectionAssert hasSize(int expected) {
    int actualSize = actualGroupSize();
    if (actualSize == expected) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("expected size:", inBrackets(expected), " but was:", inBrackets(actualSize),
        " for collection:", format(actual)));
  }

  /**
   * Returns the number of elements in the actual collection.
   * @return the number of elements in the actual collection.
   */
  @Override
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
  @Override
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
  @Override
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
  @Override
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
  @Override
  public CollectionAssert isNotSameAs(Collection<?> other) {
    assertNotSameAs(other);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public CollectionAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Creates a new instance of <code>{@link CollectionAssert}</code> composed of actual collection
   * <i>element.propertyName</i> values.<br>
   * You can then apply all collection assertions on that new collection, it works with simple properties like
   * <i>Person.age</i> and nested properties <i>Person.father.age</i>.
   * <p>
   * For example, let's say you have a collection of Person objects and you want to verify their age, you can write :<br>
   * <code>- assertThat(persons).onProperty("age").containsOnly(25, 16, 44, 37); // simple property</code><br>
   * <code>- assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property</code>
   * <p>
   * Note that null collection elements are ignored and an assertion error is thrown when an element doesn't have the
   * requested property.
   *
   * @param propertyName the property we want to extract values from actual collection to build a new <code>
   *          {@link CollectionAssert}</code>.
   * @return a new instance of <code>{@link CollectionAssert}</code> composed of actual collection
   *         <i>element.propertyName</i> values.
   * @throws AssertionError if an element of actual collection doesn't have the requested property.
   */
  public CollectionAssert onProperty(String propertyName) {
    // if actual list is null or empty, no need to select elements property values
    if (Collections.isEmpty(actual)) { return Assertions.assertThat(actual); }
    Collection<Object> extractedPropertyValues = extractValuesOfGivenPropertyFromNonNullCollectionElements(
        propertyName, actual);
    // back to collection assert on the property values of actual elements
    return Assertions.assertThat(extractedPropertyValues);
  }

  // TODO : to be replaced by fest reflect nested property support when available
  private Collection<Object> extractValuesOfGivenPropertyFromNonNullCollectionElements(String propertyToExtract,
      Collection<?> collection) {
    // if collection contains only null elements, just return an empty collection.
    if (Collections.isEmpty(collection) || Collections.hasOnlyNullElements(collection)) { return new ArrayList<Object>(); }
    // ignore null elements as we can't extract a property from a null object
    Collection<?> nonNullElements = Collections.nonNullElements(collection);
    if (isNestedProperty(propertyToExtract)) {
      // property is a nested property, like 'adress.street.number', extract sub properties until reaching a simple
      // property, on our example :
      // - extract a collection of 'adress' from collection elements -> adresses collection
      // remaining property is 'street.number'
      // - extract a collection of 'street' from adresses collection -> streets collection
      // remaining property is 'number'
      // - extract a collection of 'number' from streets collection -> numbers collection
      String firstProperty = extractFirstSubProperty(propertyToExtract);
      String nestedPropertyWithoutFirstProperty = substractFirstSubProperty(propertyToExtract);
      Collection<Object> firstPropertyValues = extractValuesOfGivenPropertyFromNonNullCollectionElements(firstProperty,
          nonNullElements);
      // extract next sub property values until reaching a last sub property
      return extractValuesOfGivenPropertyFromNonNullCollectionElements(nestedPropertyWithoutFirstProperty,
          firstPropertyValues);
    } else {
      // Property is a simple property, like 'name'
      // To extract property values, we must figure out the type of actual collection elements (can't simply use Object
      // since actual collection may contain primitive elements)
      // We take the first element type as reference assuming that all remaining elements have the same type.
      // We are sure that there is at least one non null element (check done with the call to hasOnlyNullElements)
      Class<?> propertyType = descriptorForProperty(propertyToExtract, nonNullElements.iterator().next())
          .getPropertyType();
      // fill list with property values of actual elements
      List<Object> extractedPropertyValues = new ArrayList<Object>();
      for (Object nonNullElement : nonNullElements) {
        // extract the awaited property value of current element list
        Object propertyValueOfElement = property(propertyToExtract).ofType(propertyType).in(nonNullElement).get();
        extractedPropertyValues.add(propertyValueOfElement);
      }
      return extractedPropertyValues;
    }
  }

}
