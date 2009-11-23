/*
 * Created on Oct 4, 2009
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

/**
 * Understands a template of assertion methods for numbers (primitives and objects.)
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public interface NumberAssert {

  /**
   * Verifies that the actual number is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual number is not equal to zero.
   */
  NumberAssert isZero();

  /**
   * Verifies that the actual number is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual number is not positive.
   */
  NumberAssert isPositive();

  /**
   * Verifies that the actual number is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual number is not negative.
   */
  NumberAssert isNegative();
}