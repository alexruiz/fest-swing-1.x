/*
 * Created on Sep 16, 2009
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
 * Understands a test case for implementations of <code>containsOnly</code>.
 *
 * @author Alex Ruiz
 */
public interface GroupAssert_containsOnly_TestCase {

  void should_pass_if_actual_contains_only_given_values();

  void should_pass_if_actual_contains_only_given_values_in_different_order();

  void should_fail_if_actual_is_null();

  void should_fail_and_display_description_of_assertion_if_actual_is_null();

  void should_throw_error_if_expected_is_null();

  void should_throw_error_and_display_description_of_assertion_if_expected_is_null();

  void should_fail_if_actual_is_empty_and_expecting_at_least_one_element();

  void should_fail_and_display_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element();

  void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element();

  void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element();

  void should_fail_if_actual_contains_unexpected_values();

  void should_fail_and_display_description_of_assertion_if_actual_contains_unexpected_values();

  void should_fail_with_custom_message_if_actual_contains_unexpected_values();

  void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_contains_unexpected_values();

  void should_fail_if_actual_does_not_contain_all_the_expected_values();

  void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_all_the_expected_values();

  void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values();

  void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_all_the_expected_values();
}