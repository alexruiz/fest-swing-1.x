/*
 * Created on Apr 12, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.test;

import static org.junit.Assert.*;

/**
 * Understands assert methods for <code>{@link EqualsHashCodeContractTestCase}</code>.
 *
 * @author Alex Ruiz
 */
public final class EqualsHashCodeContractAssert {

  /**
   * Verifies that the "equals" implementation of the given object returns <code>false</code> when the object is
   * compared to <code>null</code>.
   * @param obj the object to verify.
   * @throws AssertionError if the "equals" implementation of the given objects returns <code>true</code> when the
   * object compared to <code>null</code>.
   * @see EqualsHashCodeContractTestCase#should_not_be_equal_to_null()
   */
  public static void assertIsNotEqualToNull(Object obj) {
    assertFalse(obj.equals(null));
  }

  /**
   * Verifies that the "equals" implementation of the given object is reflexive.
   * @param obj the object to verify.
   * @throws AssertionError if the "equals" implementation of the given object is reflexive.
   * @see EqualsHashCodeContractTestCase#should_have_reflexive_equals()
   */
  public static void assertEqualsIsReflexive(Object obj) {
    assertEquals(obj, obj);
  }

  /**
   * Verifies that the "equals" implementation of the given objects is symmetric.
   * @param obj1 the object to verify.
   * @param obj2 the object to compare to.
   * @throws AssertionError if the "equals" implementation of the given object is not symmetric.
   * @see EqualsHashCodeContractTestCase#should_have_symmetric_equals()
   */
  public static void assertEqualsIsSymmetric(Object obj1, Object obj2) {
    assertEquals(obj1, obj2);
    assertEquals(obj2, obj1);
  }

  /**
   * Verifies that the "equals" implementation of the given objects is transitive.
   * @param obj1 the object to verify.
   * @param obj2 an object to compare to.
   * @param obj3 an object to compare to.
   * @throws AssertionError if the "equals" implementation of the given objects is not transitive.
   * @see EqualsHashCodeContractTestCase#should_have_transitive_equals()
   */
  public static void assertEqualsIsTransitive(Object obj1, Object obj2, Object obj3) {
    assertEquals(obj1, obj2);
    assertEquals(obj2, obj3);
    assertEquals(obj1, obj3);
  }

  /**
   * Verifies that the "equals/hashCode" contract of the given objects is implemented correctly.
   * @param obj1 the object to verify.
   * @param obj2 the object to compare to.
   * @throws AssertionError if the "equals/hashCode" contract of the given objects is not implemented correctly.
   * @see EqualsHashCodeContractTestCase#should_maintain_equals_and_hashCode_contract()
   */
  public static void assertMaintainsEqualsAndHashCodeContract(Object obj1, Object obj2) {
    assertEquals(obj1, obj2);
    assertEquals(obj1.hashCode(), obj2.hashCode());
  }

  private EqualsHashCodeContractAssert() {}
}
