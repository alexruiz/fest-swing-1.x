/*
 * Created on Mar 29, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Collections.found;
import static org.fest.assertions.Collections.notFound;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.reflect.core.Reflection.property;
import static org.fest.util.Collections.duplicatesFrom;
import static org.fest.util.Collections.list;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.concat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fest.reflect.beanproperty.Invoker;
import org.fest.reflect.util.PropertiesUtils;
import org.fest.util.Collections;

/**
 * Understands assertions for <code>{@link List}</code>s. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(List)}</code>.
 * @since 1.1
 * 
 * @author Alex Ruiz
 */
public class ListAssert extends GroupAssert<List<?>> {

  /**
   * Creates a new </code>{@link ListAssert}</code>.
   * @param actual the target to verify.
   */
  protected ListAssert(List<?> actual) {
    super(actual);
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains the given object at the given index.
   * @param o the object to look for.
   * @param index the index where the object should be stored in the actual <code>List</code>.
   * @return this assertion object.
   * @throws NullPointerException if the given <code>Index</code> is <code>null</code>.
   * @throws IndexOutOfBoundsException if the value of the given <code>Index</code> is negative, or equal to or greater
   *           than the size of the actual <code>List</code>.
   * @throws AssertionError if the given <code>List</code> does not contain the given object at the given index.
   */
  public ListAssert contains(Object o, Index index) {
    if (index == null) throw new NullPointerException(formattedErrorMessage("The given index should not be null"));
    isNotNull().isNotEmpty();
    int indexValue = index.value();
    int listSize = actualGroupSize();
    if (indexValue < 0 || indexValue >= listSize) failIndexOutOfBounds(indexValue);
    Object actualElement = actual.get(indexValue);
    if (!areEqual(actualElement, o)) failElementNotFound(o, actualElement, indexValue);
    return this;
  }

  private void failElementNotFound(Object e, Object a, int index) {
    failIfCustomMessageIsSet();
    fail(concat("expecting ", inBrackets(e), " at index ", inBrackets(index), " but found ", inBrackets(a)));
  }

  private void failIndexOutOfBounds(int index) {
    throw new IndexOutOfBoundsException(formattedErrorMessage(concat("The index ", inBrackets(index),
        " should be greater than or equal to zero and less than ", actualGroupSize())));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains the given sequence of objects, without any other
   * objects between them.
   * @param sequence the sequence of objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws AssertionError if the given array is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> does not contain the given sequence of objects.
   */
  public ListAssert containsSequence(Object... sequence) {
    isNotNull();
    validateIsNotNull(sequence);
    int sequenceSize = sequence.length;
    if (sequenceSize == 0) return this;
    int indexOfFirst = actual.indexOf(sequence[0]);
    if (indexOfFirst == -1) failIfSequenceNotFound(sequence);
    int listSize = actualGroupSize();
    for (int i = 0; i < sequenceSize; i++) {
      int actualIndex = indexOfFirst + i;
      if (actualIndex > listSize - 1) failIfSequenceNotFound(sequence);
      if (!areEqual(sequence[i], actual.get(actualIndex))) failIfSequenceNotFound(sequence);
    }
    return this;
  }

  private void failIfSequenceNotFound(Object[] notFound) {
    failIfCustomMessageIsSet();
    fail(concat("list:", inBrackets(actual), " does not contain the sequence:", inBrackets(notFound)));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> starts with the given sequence of objects, without any other
   * objects between them. Same as <code>{@link #containsSequence}</code>, but verifies also that first given object is
   * also first element of <code>List</code>.
   * @param sequence the sequence of objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws AssertionError if the given array is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> is not empty and with the given sequence of objects is
   *           empty.
   * @throws AssertionError if the actual <code>List</code> does not start with the given sequence of objects.
   */
  public ListAssert startsWith(Object... sequence) {
    isNotNull();
    validateIsNotNull(sequence);
    int sequenceSize = sequence.length;
    int listSize = actualGroupSize();
    if (sequenceSize == 0 && listSize == 0) return this;
    if (sequenceSize == 0 && listSize != 0) failIfNotStartingWithSequence(sequence);
    if (listSize < sequenceSize) failIfNotStartingWithSequence(sequence);
    for (int i = 0; i < sequenceSize; i++)
      if (!areEqual(sequence[i], actual.get(i))) failIfNotStartingWithSequence(sequence);
    return this;
  }

  private void failIfNotStartingWithSequence(Object[] notFound) {
    failIfCustomMessageIsSet();
    fail(concat("list:", inBrackets(actual), " does not start with the sequence:", inBrackets(notFound)));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> ends with the given sequence of objects, without any other
   * objects between them. Same as <code>{@link #containsSequence}</code>, but verifies also that last given object is
   * also last element of <code>List</code>.
   * @param sequence the sequence of objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws AssertionError if the given array is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> is not empty and with the given sequence of objects is
   *           empty.
   * @throws AssertionError if the actual <code>List</code> does not end with the given sequence of objects.
   */
  public ListAssert endsWith(Object... sequence) {
    isNotNull();
    validateIsNotNull(sequence);
    int sequenceSize = sequence.length;
    int listSize = actualGroupSize();
    if (sequenceSize == 0 && listSize == 0) return this;
    if (sequenceSize == 0 && listSize != 0) failIfNotEndingWithSequence(sequence);
    if (listSize < sequenceSize) failIfNotEndingWithSequence(sequence);
    for (int i = 0; i < sequenceSize; i++) {
      int sequenceIndex = sequenceSize - 1 - i;
      int listIndex = listSize - 1 - i;
      if (!areEqual(sequence[sequenceIndex], actual.get(listIndex))) failIfNotEndingWithSequence(sequence);
    }
    return this;
  }

  private void failIfNotEndingWithSequence(Object[] notFound) {
    failIfCustomMessageIsSet();
    fail(concat("list:", inBrackets(actual), " does not end with the sequence:", inBrackets(notFound)));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains the given objects, in any order.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> does not contain the given objects.
   */
  public ListAssert contains(Object... objects) {
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
   * Verifies that the actual <code>{@link List}</code> contains the given objects <strong>only</strong>, in any order.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> does not contain the given objects, or if the actual
   *           <code>List</code> contains elements other than the ones specified.
   */
  public ListAssert containsOnly(Object... objects) {
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
    return failure(concat("list:", inBrackets(actual), " does not contain element(s):", inBrackets(notFound)));
  }

  private AssertionError failureIfUnexpectedElementsFound(List<Object> unexpected) {
    failIfCustomMessageIsSet();
    return failure(concat("unexpected element(s):", inBrackets(unexpected), " in list:", inBrackets(actual)));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> does not contain the given objects.
   * @param objects the objects that the <code>List</code> should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> contains any of the given objects.
   */
  public ListAssert excludes(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> found = found(actual, objects);
    if (found.isEmpty()) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("list:", inBrackets(actual), " does not exclude element(s):", inBrackets(found)));
  }

  private void validateIsNotNull(Object[] objects) {
    if (objects == null)
      throw new NullPointerException(formattedErrorMessage("the given array of objects should not be null"));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> does not have duplicates.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> has duplicates.
   */
  public ListAssert doesNotHaveDuplicates() {
    isNotNull();
    Collection<?> duplicates = duplicatesFrom(actual);
    if (duplicates.isEmpty()) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("list:", inBrackets(actual), " contains duplicate(s):", inBrackets(duplicates)));
  }

  /** {@inheritDoc} */
  @Override
  public ListAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ListAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public ListAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ListAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>{@link List}</code> satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> does not satisfy the given condition.
   * @see #is(Condition)
   */
  @Override
  public ListAssert satisfies(Condition<List<?>> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link List}</code> does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> satisfies the given condition.
   * @see #isNot(Condition)
   */
  @Override
  public ListAssert doesNotSatisfy(Condition<List<?>> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> does not satisfy the given condition.
   * @since 1.2
   */
  @Override
  public ListAssert is(Condition<List<?>> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> satisfies the given condition.
   * @since 1.2
   */
  @Override
  public ListAssert isNot(Condition<List<?>> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the number of elements in the actual <code>{@link List}</code> is equal to the given one.
   * @param expected the expected number of elements in the actual <code>List</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws AssertionError if the number of elements of the actual <code>List</code> is not equal to the given one.
   */
  @Override
  public ListAssert hasSize(int expected) {
    int actualSize = actualGroupSize();
    if (actualSize == expected) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("expected size:", inBrackets(expected), " but was:", inBrackets(actualSize), " for list:",
        inBrackets(actual)));
  }

  /**
   * Returns the number of elements in the actual <code>{@link List}</code>.
   * @return the number of elements in the actual <code>List</code>.
   */
  @Override
  protected int actualGroupSize() {
    isNotNull();
    return actual.size();
  }

  /**
   * Verifies that the actual <code>{@link List}</code> is empty (not <code>null</code> with zero elements.)
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> is not empty.
   */
  @Override
  public void isEmpty() {
    isNotNull();
    if (Collections.isEmpty(actual)) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting empty list, but was:", inBrackets(actual)));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> is empty.
   */
  @Override
  public ListAssert isNotEmpty() {
    isNotNull();
    if (!actual.isEmpty()) return this;
    failIfCustomMessageIsSet();
    throw failure("expecting a non-empty list, but it was empty");
  }

  /**
   * Verifies that the actual <code>{@link List}</code> is <code>null</code> or empty.
   * @throws AssertionError if the actual <code>List</code> is not <code>null</code> or not empty.
   */
  @Override
  public void isNullOrEmpty() {
    if (Collections.isEmpty(actual)) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting a null or empty list, but was:", inBrackets(actual)));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   */
  @Override
  public ListAssert isNotNull() {
    if (actual != null) return this;
    failIfCustomMessageIsSet();
    throw failure("expecting actual list not to be null");
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains the given objects, in the same order. This method works
   * just like <code>{@link #isEqualTo(List)}</code>, with the difference that internally the given array is converted
   * to a <code>List</code>.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual <code>List</code> does not contain the given objects.
   */
  public ListAssert containsExactly(Object... objects) {
    validateIsNotNull(objects);
    return isNotNull().isEqualTo(list(objects));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> is equal to the given one.
   * @param expected the given <code>List</code> to compare the actual <code>List</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is not equal to the given one.
   */
  @Override
  public ListAssert isEqualTo(List<?> expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link List}</code> is not equal to the given one.
   * @param other the given <code>List</code> to compare the actual <code>List</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is equal to the given one.
   */
  @Override
  public ListAssert isNotEqualTo(List<?> other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link List}</code> is the same as the given one.
   * @param expected the given <code>List</code> to compare the actual <code>List</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is not the same as the given one.
   */
  @Override
  public ListAssert isSameAs(List<?> expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link List}</code> is not the same as the given one.
   * @param other the given <code>List</code> to compare the actual <code>List</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>List</code> is the same as the given one.
   */
  @Override
  public ListAssert isNotSameAs(List<?> other) {
    assertNotSameAs(other);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ListAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  /**
   * Creates a new instance of <code>{@link ListAssert}</code> composed of actual list <i>element.propertyName</i>
   * values.<br>
   * You can then apply all list assertions on that new list, it works with simple properties like <i>Person.age</i> and
   * nested properties <i>Person.father.age</i>.
   * <p>
   * For example, let's say you have a list of Person objects and you want to verify their age, you can write :<br>
   * <code>- assertThat(persons).onProperty("age").containsOnly(25, 16, 44, 37); // simple property</code><br>
   * <code>- assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property</code>
   * <p>
   * Note that null list elements are ignored and an assertion error is thrown when an element doesn't have the
   * requested property.
   * 
   * @param propertyName the property we want to extract values from actual list to build a new <code>{@link ListAssert}
   *          </code>.
   * @return a new instance of <code>{@link ListAssert}</code> composed of actual list <i>element.propertyName</i>
   *         values.
   * @throws AssertionError if an element of actual list doesn't have the requested property.
   */
  public ListAssert onProperty(String propertyName) {
    // if actual list is null or empty, no need to select elements property values
    if (Collections.isEmpty(actual)) { return Assertions.assertThat(actual); }
    List<Object> extractedPropertyValues = extractValuesOfGivenPropertyFromNonNullListElements(propertyName, actual);
    // back to list assert on the property values of actual elements
    return Assertions.assertThat(extractedPropertyValues);
  }

  // TODO : to be replaced by fest reflect nested property support when available
  private List<Object> extractValuesOfGivenPropertyFromNonNullListElements(String propertyToExtract, List<?> collection) {
    // if collection contains only null elements, just return an empty collection.
    if (Collections.hasOnlyNullElements(collection)) { return new ArrayList<Object>(); }
    // ignore null elements, we can't extract a property from a null object
    List<Object> nonNullElements = Collections.nonNullElements(collection);
    if (PropertiesUtils.isNestedProperty(propertyToExtract)) {
      // property is a nested property, like 'adress.street.number', extract sub properties until reaching a simple
      // property, on our example :
      // - extract a collection of 'adress' from collection elements -> adresses collection
      // remaining property is 'street.number'
      // - extract a collection of 'street' from adresses collection -> streets collection
      // remaining property is 'number'
      // - extract a collection of 'number' from streets collection -> numbers collection
      String firstProperty = PropertiesUtils.extractFirstSubProperty(propertyToExtract);
      String nestedPropertyWithoutFirstProperty = PropertiesUtils.substractFirstSubProperty(propertyToExtract);
      List<Object> firstPropertyValues = extractValuesOfGivenPropertyFromNonNullListElements(firstProperty,
          nonNullElements);
      // extract next sub property values until reaching a last sub property
      return extractValuesOfGivenPropertyFromNonNullListElements(nestedPropertyWithoutFirstProperty,
          firstPropertyValues);
    } else {
      // Property is a simple property, like 'name'
      // To extract property values, we must figure out the type of actual collection elements (can't simply use Object
      // since actual collection may contain primitive elements)
      // We take the first element type as reference assuming that all remaining elements have the same type.
      // We are sure that there is at least one non null element (check done with the call to hasOnlyNullElements)
      Class<?> propertyType = Invoker.descriptorForProperty(propertyToExtract, nonNullElements.iterator().next())
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
