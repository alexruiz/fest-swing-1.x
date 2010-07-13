/*
 * Created on Oct 1, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

/**
 * Test case for implementations of <code>{@link NumberAssert#isNegative()}</code>.
 *
 * @author Alex Ruiz
 */
public interface NumberAssert_isNegative_TestCase {

  void should_pass_if_actual_is_negative();

  void should_fail_if_actual_is_positive();

  void should_fail_and_display_description_of_assertion_if_actual_is_positive();

  void should_fail_with_custom_message_if_actual_is_positive();

  void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_positive();

  void should_fail_if_actual_is_zero();

  void should_fail_and_display_description_of_assertion_if_actual_is_zero();

  void should_fail_with_custom_message_if_actual_is_zero();

  void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_zero();
}