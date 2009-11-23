/*
 * Created on Dec 26, 2006
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
 * Copyright @2006-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

import org.fest.util.Strings;

/**
 * Understands assertion methods for <code>String</code>s. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(String)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class StringAssert extends GroupAssert<String> {

  /**
   * Creates a new </code>{@link StringAssert}</code>.
   * @param actual the target to verify.
   */
  protected StringAssert(String actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public StringAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public StringAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public StringAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public StringAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>String</code> satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> does not satisfy the given condition.
   * @see #is(Condition)
   */
  public StringAssert satisfies(Condition<String> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>String</code> does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> satisfies the given condition.
   * @see #isNot(Condition)
   */
  public StringAssert doesNotSatisfy(Condition<String> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> does not satisfy the given condition.
   * @since 1.2
   */
  public StringAssert is(Condition<String> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> satisfies the given condition.
   * @since 1.2
   */
  public StringAssert isNot(Condition<String> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>String</code> is empty (not <code>null</code> with zero characters.)
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> is not empty.
   */
  public void isEmpty() {
    isNotNull();
    if (Strings.isEmpty(actual)) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting empty String but was:", inBrackets(actual)));
  }

  /**
   * Verifies that the actual <code>String</code> is <code>null</code> or empty.
   * @throws AssertionError if the actual <code>String</code> is not <code>null</code> or not empty.
   */
  public final void isNullOrEmpty() {
    if (Strings.isEmpty(actual)) return;
    failIfCustomMessageIsSet();
    fail(concat("expecting a null or empty String, but was:", inBrackets(actual)));
  }

  /**
   * Verifies that the actual <code>String</code> contains at least on character.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code> or empty.
   */
  public StringAssert isNotEmpty() {
    isNotNull();
    if (!Strings.isEmpty(actual)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("expecting a non-empty String, but it was empty"));
  }

  /**
   * Verifies that the actual <code>String</code> is equal to the given one.
   * @param expected the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is not equal to the given one.
   */
  public StringAssert isEqualTo(String expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>String</code> is not equal to the given one.
   * @param other the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is equal to the given one.
   */
  public StringAssert isNotEqualTo(String other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual <code>String</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   */
  public StringAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>String</code> is not the same as the given one.
   * @param other the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is the same as the given one.
   */
  public StringAssert isNotSameAs(String other) {
    assertNotSameAs(other);
    return this;
  }

  /**
   * Verifies that the actual <code>String</code> is the same as the given one.
   * @param expected the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is not the same as the given one.
   */
  public StringAssert isSameAs(String expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the number of characters in the actual <code>String</code> is equal to the given one.
   * @param expected the expected number of characters in the actual <code>String</code>.
   * @return this assertion object.
   * @throws AssertionError if the number of characters of the actual <code>String</code> is not equal to the given
   * one.
   */
  public StringAssert hasSize(int expected) {
    int actualSize = actualGroupSize();
    if (actualSize == expected) return this;
    failIfCustomMessageIsSet();
    throw failure(concat("expected size:", inBrackets(expected)," but was:", inBrackets(actualSize), " for String:", actual()));
  }

  /**
   * Returns the number of elements in the actual <code>String</code>.
   * @return the number of elements in the actual <code>String</code>.
   */
  protected int actualGroupSize() {
    isNotNull();
    return actual.length();
  }

  /**
   * Verifies that the actual <code>String</code> contains the given one.
   * @param expected the given <code>String</code> expected to be contained in the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> does not contain the given one.
   */
  public StringAssert contains(String expected) {
    isNotNull();
    if (actual.indexOf(expected) != -1) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(actual(), " should contain the String:", inBrackets(expected)));
  }

  /**
   * Verifies that the actual <code>String</code> ends with the given one.
   * @param expected the given <code>String</code> expected to be at the end of the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> does not end with the given one.
   */
  public StringAssert endsWith(String expected) {
    isNotNull();
    if (actual.endsWith(expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(actual(), " should end with:", inBrackets(expected)));
  }

  /**
   * Verifies that the actual <code>String</code> starts with the given one.
   * @param expected the given <code>String</code> expected to be at the beginning of the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> does not start with the given one.
   */
  public StringAssert startsWith(String expected) {
    isNotNull();
    if (actual.startsWith(expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(actual(), " should start with:", inBrackets(expected)));
  }

  /**
   * Verifies that the actual <code>String</code> does not contains the given one.
   * @param s the given <code>String</code> expected not to be contained in the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   * @throws AssertionError if the actual <code>String</code> does contain the given one.
   */
  public StringAssert excludes(String s) {
    isNotNull();
    if (actual.indexOf(s) == -1) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(actual(), " should not contain the String:", inBrackets(s)));
  }

  /**
   * Verifies that the actual <code>String</code> matches the given one.
   * @param regex the given regular expression expected to be matched by the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> does not match the given regular expression.
   */
  public StringAssert matches(String regex) {
    isNotNull();
    if (actual.matches(regex)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(actual(), " should match the regular expression:", inBrackets(regex)));
  }

  /**
   * Verifies that the actual <code>String</code> does not match the given one.
   * @param regex the given regular expression expected not to be matched by the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> matches the given regular expression.
   */
  public StringAssert doesNotMatch(String regex) {
    isNotNull();
    if (!actual.matches(regex)) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(actual(), " should not match the regular expression:", inBrackets(regex)));
  }

  private String actual() {
    return inBrackets(actual);
  }

  /** {@inheritDoc} */
  public StringAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
