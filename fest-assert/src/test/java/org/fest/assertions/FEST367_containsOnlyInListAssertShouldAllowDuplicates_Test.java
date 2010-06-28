/*
 * Created on Jun 27, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.util.Collections.list;

import java.util.List;

import org.junit.Test;

/**
 * Tests for bug <a href="http://jira.codehaus.org/browse/FEST-367" target="_blank">FEST-367</a>.
 *
 * @author Yvonne Wang
 */
public class FEST367_containsOnlyInListAssertShouldAllowDuplicates_Test {

  @Test
  public void should_pass_if_actual_contains_only_given_values_and_actual_has_duplicates() {
    List<String> list = list("Gandalf", "Sam", "Frodo", "Sam");
    new ListAssert(list).containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_and_expected_has_duplicates() {
    List<String> list = list("Gandalf", "Sam", "Frodo");
    new ListAssert(list).containsOnly("Gandalf", "Sam", "Frodo", "Sam");
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_and_both_have_duplicates() {
    List<String> list = list("Sam", "Gandalf", "Sam", "Frodo", "Sam");
    new ListAssert(list).containsOnly("Gandalf", "Sam", "Frodo", "Sam");
  }
}
