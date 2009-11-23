/*
 * Created on Sep 16, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for <code>{@link Formatting#inBrackets(Object)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Formatting_inBrackets_Test {

  @Test
  public void should_surround_Object_with_brackets() {
    assertEquals("<3>", Formatting.inBrackets(new Integer(3)));
  }

  @Test
  public void should_surround_String_with_brackets() {
    assertEquals("<'Yoda'>", Formatting.inBrackets("Yoda"));
  }

  @Test
  public void should_surround_null_with_brackets() {
    assertEquals("<null>", Formatting.inBrackets(null));
  }

  @Test
  public void should_surround_array_of_Objects_with_brackets() {
    Object o = new Object[] { "First", 3 };
    assertEquals("<['First', 3]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_booleans_with_brackets() {
    Object o = booleanArray(true, false);
    assertEquals("<[true, false]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_bytes_with_brackets() {
    Object o = byteArray(3, 8);
    assertEquals("<[3, 8]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_chars_with_brackets() {
    Object o = charArray('a', 'b');
    assertEquals("<[a, b]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_doubles_with_brackets() {
    Object o = doubleArray(6.8, 8.3);
    assertEquals("<[6.8, 8.3]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_floats_with_brackets() {
    Object o = floatArray(6.1f, 8.6f);
    assertEquals("<[6.1, 8.6]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_ints_with_brackets() {
    Object o = intArray(78, 66);
    assertEquals("<[78, 66]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_longs_with_brackets() {
    Object o = longArray(160l, 98l);
    assertEquals("<[160, 98]>", Formatting.inBrackets(o));
  }

  @Test
  public void should_surround_array_of_shorts_with_brackets() {
    Object o = shortArray(5, 8);
    assertEquals("<[5, 8]>", Formatting.inBrackets(o));
  }
}
