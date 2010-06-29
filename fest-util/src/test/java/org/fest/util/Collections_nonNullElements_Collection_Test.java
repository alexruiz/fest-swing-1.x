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

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.fest.util.Collections.nonNullElements;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#nonNullElements(java.util.Collection)}</code>.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
public class Collections_nonNullElements_Collection_Test {

  @Test
  public void should_return_null_if_given_collection_is_null() {
    Collection<?> c = null;
    assertNull(nonNullElements(c));
  }

  @Test
  public void should_return_an_empty_collection_if_given_collection_has_only_null_elements() {
    Collection<String> c = new ArrayList<String>();
    c.add(null);
    assertTrue(nonNullElements(c).isEmpty());
  }

  @Test
  public void should_return_an_empty_collection_if_given_collection_is_empty() {
    Collection<String> c = new ArrayList<String>();
    assertEquals(0, nonNullElements(c).size());
  }

  @Test
  public void should_return_a_collection_without_null_elements() {
    Collection<String> c = asList("Frodo", null, "Sam", null);
    Collection<String> actual = nonNullElements(c);
    assertEquals(2, actual.size());
    assertTrue(actual.contains("Frodo"));
    assertTrue(actual.contains("Sam"));
  }
}
