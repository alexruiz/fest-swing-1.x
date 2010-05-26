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

import static junit.framework.Assert.assertEquals;

import static org.fest.util.Collections.list;
import static org.fest.util.Collections.nonNullElements;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#nonNullElements(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class Collections_nonNullElements_Test {

  @Test
  public void should_return_null_if_given_collection_is_null() {
    Collection<?> collection = null;
    assertNull(nonNullElements(collection));
  }

  @Test
  public void should_return_an_empty_collection_if_given_collection_has_only_null_elements() {
    Collection<String> collection = new ArrayList<String>();
    collection.add(null);
    assertTrue(nonNullElements(collection).isEmpty());
  }

  @Test
  public void should_return_an_empty_collection_if_given_collection_is_empty() {
    assertEquals(0, nonNullElements(list()).size());
  }

  @Test
  public void should_return_a_collection_without_null_elements() {
    Collection<String> collectionWithNullElements = list("Frodo", null, "Sam", null);
    Collection<String> expectedCollection = list("Frodo", "Sam");
    assertEquals(expectedCollection, nonNullElements(collectionWithNullElements));
  }

  @Test
  public void should_return_null_if_given_list_is_null() {
    List<?> list = null;
    assertNull(nonNullElements(list));
  }

  @Test
  public void should_return_an_empty_list_if_given_list_has_only_null_elements() {
    List<String> list = new ArrayList<String>();
    list.add(null);
    assertTrue(nonNullElements(list).isEmpty());
  }

  @Test
  public void should_return_a_list_without_null_elements() {
    List<String> listWithNullElements = Collections.list("Frodo", null, "Sam", null);
    List<String> expectedCollection = Collections.list("Frodo", "Sam");
    assertEquals(expectedCollection, nonNullElements(listWithNullElements));
  }
}
