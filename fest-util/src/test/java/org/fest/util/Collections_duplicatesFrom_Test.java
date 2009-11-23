/*
 * Created on Apr 29, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Collections_duplicatesFrom_Test {

  @Test
  public void should_return_existing_duplicates() {
    Collection<String> duplicates = Collections.duplicatesFrom(Collections.list("Frodo", "Sam", "Frodo"));
    assertEquals(1, duplicates.size());
    assertTrue(duplicates.contains("Frodo"));
  }

  @Test
  public void should_not_return_any_duplicates() {
    Collection<String> duplicates = Collections.duplicatesFrom(Collections.list("Frodo", "Sam", "Gandalf"));
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_Collection_is_empty() {
    Collection<String> duplicates = Collections.duplicatesFrom(new ArrayList<String>());
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_Collection_is_null() {
    Collection<String> duplicates = Collections.duplicatesFrom(null);
    assertTrue(duplicates.isEmpty());
  }
}
