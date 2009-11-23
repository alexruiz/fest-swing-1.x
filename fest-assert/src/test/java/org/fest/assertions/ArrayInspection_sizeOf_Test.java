/*
 * Created on Oct 2, 2009
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
package org.fest.assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for <code>{@link ArrayInspection#sizeOf(Object)}</code>.
 *
 * @author Alex Ruiz
 */
public class ArrayInspection_sizeOf_Test {

  @Test
  public void should_throw_error_if_array_is_null() {
    try {
      ArrayInspection.copy(null);
      fail("Expecting NullPointerException");
    } catch (NullPointerException e) {
      assertEquals("The array should not be null", e.getMessage());
    }
  }

  @Test
  public void should_throw_error_if_object_is_not_array() {
    try {
      ArrayInspection.copy("Hello");
      fail("Expecting IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("The given object is not an array", e.getMessage());
    }
  }

  @Test
  public void should_return_size_of_array() {
    boolean[] array = { true, false };
    assertEquals(2, ArrayInspection.sizeOf(array));
  }
}
