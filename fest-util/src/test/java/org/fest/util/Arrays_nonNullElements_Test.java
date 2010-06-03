/*
 * Created on May 13, 2007
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

import static org.fest.util.Arrays.nonNullElements;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for <code>{@link Arrays#nonNullElements(Object[])}</code>.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
public class Arrays_nonNullElements_Test {

  @Test
  public void should_return_null_if_array_is_null() {
    String[] array = null;
    assertNull(Arrays.nonNullElements(array));
  }

  @Test
  public void should_return_an_empty_array_if_given_array_has_only_null_elements() {
    String[] array = new String[] { null };
    assertEquals(0, nonNullElements(array).length);
  }

  @Test
  public void should_return_an_empty_array_if_given_array_is_empty() {
    String[] array = new String[0];
    assertEquals(0, nonNullElements(array).length);
  }

  @Test
  public void should_return_an_array_without_null_elements() {
    String[] array = { "Frodo", null, "Sam", null };
    String[] expected = { "Frodo", "Sam" };
    String[] actual = nonNullElements(array);
    assertArrayEquals(expected, actual);
  }
}
