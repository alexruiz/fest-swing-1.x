/*
 * Created on Jun 17, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.util;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.fest.util.Collections.nonNullElements;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#nonNullElements(List)}</code>.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
public class Collections_nonNullElements_List_Test {

  @Test
  public void should_return_null_if_given_list_is_null() {
    List<?> l = null;
    assertNull(nonNullElements(l));
  }

  @Test
  public void should_return_an_empty_list_if_given_list_has_only_null_elements() {
    List<String> l = new ArrayList<String>();
    l.add(null);
    assertTrue(nonNullElements(l).isEmpty());
  }

  @Test
  public void should_return_an_empty_list_if_given_list_is_empty() {
    List<String> l = new ArrayList<String>();
    assertEquals(0, nonNullElements(l).size());
  }

  @Test
  public void should_return_a_list_without_null_elements() {
    List<String> c = asList("Frodo", null, "Sam", null);
    List<String> expected = asList("Frodo", "Sam");
    List<String> nonNullElements = nonNullElements(c);
    System.out.println(nonNullElements);
    assertEquals(expected, nonNullElements);
  }
}
