/*
 * Created on Jan 10, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static java.lang.Boolean.FALSE;
import static junit.framework.Assert.*;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.*;

import org.fest.util.Files;
import org.junit.Test;

/**
 * Tests for all the overloaded versions of <code>assertThat</code> in <code>{@link Assertions}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Assertions_assertThat_Test {

  @Test
  public void should_return_BigDecimalAssert_if_argument_is_BigDecimal() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new BigDecimal("8")), BigDecimalAssert.class);
  }

  @Test
  public void should_return_BooleanArrayAssert_if_argument_is_array_of_boolean() {
    boolean[] booleans = new boolean[] { false };
    assertObjectIsInstanceOfType(Assertions.assertThat(booleans), BooleanArrayAssert.class);
  }

  @Test
  public void should_return_BooleanAssert_if_argument_is_boolean() {
    assertObjectIsInstanceOfType(Assertions.assertThat(false), BooleanAssert.class);
  }

  @Test
  public void should_return_BooleanAssert_if_argument_is_Boolean() {
    assertObjectIsInstanceOfType(Assertions.assertThat(FALSE), BooleanAssert.class);
  }

  @Test
  public void should_return_ByteArrayAssert_if_argument_is_array_of_byte() {
    byte[] bytes = new byte[] { 0 };
    assertObjectIsInstanceOfType(Assertions.assertThat(bytes), ByteArrayAssert.class);
  }

  @Test
  public void should_return_ByteAssert_if_argument_is_byte() {
    byte b = 0;
    assertObjectIsInstanceOfType(Assertions.assertThat(b), ByteAssert.class);
  }

  @Test
  public void should_return_ByteAssert_if_argument_is_Byte() {
    byte b = 0;
    assertObjectIsInstanceOfType(Assertions.assertThat(new Byte(b)), ByteAssert.class);
  }

  @Test
  public void should_return_CharArrayAssert_if_argument_is_array_of_char() {
    char[] chars = new char[] { 0 };
    assertObjectIsInstanceOfType(Assertions.assertThat(chars), CharArrayAssert.class);
  }

  @Test
  public void should_return_CharAssert_if_argument_is_char() {
    assertObjectIsInstanceOfType(Assertions.assertThat('a'), CharAssert.class);
  }

  @Test
  public void should_return_CharAssert_if_argument_is_Character() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new Character('a')), CharAssert.class);
  }

  @Test
  public void should_return_CollectionAssert_if_argument_is_Collection() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new HashSet<Object>()), CollectionAssert.class);
  }

  @Test
  public void should_return_ListAssert_if_argument_is_List() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new ArrayList<Object>()), ListAssert.class);
  }

  @Test
  public void should_return_CollectionAssert_if_argument_is_Iterator() {
    List<String> list = new ArrayList<String>();
    list.add("Frodo");
    CollectionAssert assertion = Assertions.assertThat(list.iterator());
    assertEquals(list, assertion.actual);
  }

  @Test
  public void should_return_DoubleArrayAssert_if_argument_is_array_of_double() {
    double[] doubles = new double[] { 0 };
    assertObjectIsInstanceOfType(Assertions.assertThat(doubles), DoubleArrayAssert.class);
  }

  @Test
  public void should_return_DoubleAssert_if_argument_is_double() {
    assertObjectIsInstanceOfType(Assertions.assertThat(86.0d), DoubleAssert.class);
  }

  @Test
  public void should_return_DoubleAssert_if_argument_is_Double() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new Double(86.0d)), DoubleAssert.class);
  }

  @Test
  public void should_return_FileAssert_if_argument_is_File() {
    assertObjectIsInstanceOfType(Assertions.assertThat(Files.temporaryFolder()), FileAssert.class);
  }

  @Test
  public void should_return_FloatArrayAssert_if_argument_is_array_of_float() {
    float[] floats = new float[] { 0f };
    assertObjectIsInstanceOfType(Assertions.assertThat(floats), FloatArrayAssert.class);
  }

  @Test
  public void should_return_FloatAssert_if_argument_is_float() {
    assertObjectIsInstanceOfType(Assertions.assertThat(86.0f), FloatAssert.class);
  }

  @Test
  public void should_return_FloatAssert_if_argument_is_Float() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new Float(86.0f)), FloatAssert.class);
  }

  @Test
  public void should_always_return_given_AssertExtension() {
    AssertExtension extension = new AssertExtension() {};
    assertSame(extension, Assertions.assertThat(extension));
  }

  @Test
  public void should_return_ImageAssert_if_argument_is_BufferedImage() {
    BufferedImage image = new BufferedImage(10, 10, TYPE_INT_RGB);
    assertObjectIsInstanceOfType(Assertions.assertThat(image), ImageAssert.class);
  }

  @Test
  public void should_return_IntArrayAssert_if_argument_is_array_of_int() {
    int[] ints = new int[] { 0 };
    assertObjectIsInstanceOfType(Assertions.assertThat(ints), IntArrayAssert.class);
  }

  @Test
  public void should_return_IntAssert_if_argument_is_int() {
    assertObjectIsInstanceOfType(Assertions.assertThat(8), IntAssert.class);
  }

  @Test
  public void should_return_IntAssert_if_argument_is_Integer() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new Integer(8)), IntAssert.class);
  }

  @Test
  public void should_return_LongArrayAssert_if_argument_is_array_of_long() {
    long[] longs = new long[] { 0 };
    assertObjectIsInstanceOfType(Assertions.assertThat(longs), LongArrayAssert.class);
  }

  @Test
  public void should_return_LongAssert_if_argument_is_long() {
    assertObjectIsInstanceOfType(Assertions.assertThat(8l), LongAssert.class);
  }

  @Test
  public void should_return_LongAssert_if_argument_is_Long() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new Long(86)), LongAssert.class);
  }

  @Test
  public void should_return_MapAssert_if_argument_is_Map() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new HashMap<Object, Object>()), MapAssert.class);
  }

  @Test
  public void should_return_ObjectArrayAssert_if_argument_is_array_of_Object() {
    String[] objects = new String[] { "One" };
    assertObjectIsInstanceOfType(Assertions.assertThat(objects), ObjectArrayAssert.class);
  }

  @Test
  public void should_return_ObjectAssert_if_argument_is_Object() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new Object()), ObjectAssert.class);
  }

  @Test
  public void should_return_ShortArrayAssert_if_argument_is_array_of_short() {
    short[] shorts = new short[] { 0 };
    assertObjectIsInstanceOfType(Assertions.assertThat(shorts), ShortArrayAssert.class);
  }

  @Test
  public void should_return_ShortAssert_if_argument_is_short() {
    short s = 8;
    assertObjectIsInstanceOfType(Assertions.assertThat(s), ShortAssert.class);
  }

  @Test
  public void should_return_ShortAssert_if_argument_is_Short() {
    short s = 8;
    assertObjectIsInstanceOfType(Assertions.assertThat(new Short(s)), ShortAssert.class);
  }

  @Test
  public void should_return_StringAssert_if_argument_is_String() {
    assertObjectIsInstanceOfType(Assertions.assertThat(""), StringAssert.class);
  }

  @Test
  public void should_return_ThrowableAssert_if_argument_is_Throwable() {
    assertObjectIsInstanceOfType(Assertions.assertThat(new Exception()), ThrowableAssert.class);
  }

  private void assertObjectIsInstanceOfType(Object object, Class<?> type) {
    assertEquals(type, object.getClass());
  }
}
