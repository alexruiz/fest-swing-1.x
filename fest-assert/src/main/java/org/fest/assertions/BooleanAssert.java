/*
 * Created on Mar 19, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.Boolean.valueOf;

/**
 * Understands assertion methods for {@code Boolean}s and {@code boolean}s. To create a new instance of this class call
 * <code>{@link Assertions#assertThat(Boolean)}</code> or <code>{@link Assertions#assertThat(boolean)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 */
public class BooleanAssert extends GenericAssert<Boolean> {

  /**
   * Creates a new <code>{@link BooleanAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected BooleanAssert(boolean actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link BooleanAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected BooleanAssert(Boolean actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public BooleanAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public BooleanAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code Boolean} value is <code>true</code>.
   * @throws AssertionError if the actual {@code Boolean} value is <code>false</code>.
   */
  public void isTrue() {
    isEqualTo(true);
  }

  /**
   * Verifies that the actual {@code Boolean} value is <code>false</code>.
   * @throws AssertionError if the actual {@code Boolean} value is <code>true</code>.
   */
  public void isFalse() {
    isEqualTo(false);
  }

  /**
   * Verifies that the actual {@code Boolean} is equal to the given one.
   * @param expected the given {@code boolean} to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} is not equal to the given one.
   */
  public BooleanAssert isEqualTo(boolean expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Boolean} value is equal to the given one.
   * @param expected the given {@code Boolean} value to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} value is not equal to the given one.
   * @since 1.3
   */
  @Override
  public BooleanAssert isEqualTo(Boolean expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Boolean} is not equal to the given one.
   * @param other the given {@code boolean} to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} is equal to the given one.
   */
  public BooleanAssert isNotEqualTo(boolean other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Boolean} is not equal to the given one.
   * @param other the given {@code Boolean} to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} value is equal to the given one.
   * @since 1.3
   */
  @Override
  public BooleanAssert isNotEqualTo(Boolean other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual {@code Boolean} satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Boolean} does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  @Override
  public BooleanAssert satisfies(Condition<Boolean> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Boolean} does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  @Override
  public BooleanAssert doesNotSatisfy(Condition<Boolean> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Boolean} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public BooleanAssert is(Condition<Boolean> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual {@code Boolean} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public BooleanAssert isNot(Condition<Boolean> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Boolean} is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} value is <code>null</code>.
   * @since 1.3
   */
  @Override
  public BooleanAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual {@code Boolean} is the same object as the given one.
   * @param expected the given {@code Boolean} to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} value is not the same as the given one.
   * @since 1.3
   */
  @Override
  public BooleanAssert isSameAs(Boolean expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Boolean} is not the same object as the given one.
   * @param other the given {@code Boolean} to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} value is the same as the given one.
   * @since 1.3
   */
  @Override
  public BooleanAssert isNotSameAs(Boolean other) {
    assertNotSameAs(other);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public BooleanAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
