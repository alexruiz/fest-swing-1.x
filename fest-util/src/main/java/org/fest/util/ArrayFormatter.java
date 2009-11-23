/*
 * Created on Mar 29, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.util;

import static java.lang.reflect.Array.getLength;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.util.*;
import java.util.Arrays;

/**
 * Understands how to create a <code>String</code> representation of an array.
 *
 * @author Alex Ruiz
 */
final class ArrayFormatter {

  private static final String NULL = "null";

  String format(Object o) {
    if (!isArray(o)) return null;
    if (isObjectArray(o)) return formatObjectArray(o);
    return formatPrimitiveArray(o);
  }

  private boolean isArray(Object o) {
    if (o == null) return false;
    return o.getClass().isArray();
  }

  private boolean isObjectArray(Object o) {
    Class<?> type = o.getClass();
    return type.isArray() && !type.getComponentType().isPrimitive();
  }

  private String formatObjectArray(Object o) {
    Object[] array = (Object[])o;
    int arrayLength = getLength(o);
    if (arrayLength == 0) return "[]";
    StringBuilder buffer = new StringBuilder((20 * (arrayLength - 1)));
    deepToString(array, buffer, new HashSet<Object[]>());
    return buffer.toString();
  }

  private void deepToString(Object[] array, StringBuilder buffer, Set<Object[]> alreadyFormatted) {
    if (array == null) {
      buffer.append(NULL);
      return;
    }
    alreadyFormatted.add(array);
    buffer.append('[');
    int length = array.length;
    for (int i = 0; i < length; i++) {
      if (i != 0) buffer.append(", ");
      Object element = array[i];
      if (!isArray(element)) {
        buffer.append(element == null ? NULL : quote(element));
        continue;
      }
      if (!isObjectArray(element)) {
        buffer.append(formatPrimitiveArray(element));
        continue;
      }
      if (alreadyFormatted.contains(element)) {
        buffer.append("[...]");
        continue;
      }
      deepToString((Object[]) element, buffer, alreadyFormatted);
    }
    buffer.append(']');
    alreadyFormatted.remove(array);
  }

  private String formatPrimitiveArray(Object o) {
    if (!isArray(o)) return null;
    Class<?> elementType = o.getClass().getComponentType();
    if (elementType.equals(boolean.class)) return format((boolean[])o);
    if (elementType.equals(char.class)) return format((char[])o);
    if (elementType.equals(byte.class)) return format((byte[])o);
    if (elementType.equals(short.class)) return format((short[])o);
    if (elementType.equals(int.class)) return format((int[])o);
    if (elementType.equals(long.class)) return format((long[])o);
    if (elementType.equals(float.class)) return format((float[])o);
    if (elementType.equals(double.class)) return format((double[])o);
    throw new IllegalArgumentException(concat("<", o, "> is not an array of primitives"));
  }

  private String format(boolean[] array) {
    return Arrays.toString(array);
  }

  private String format(char[] array) {
    return Arrays.toString(array);
  }

  private String format(byte[] array) {
    return Arrays.toString(array);
  }

  private String format(short[] array) {
    return Arrays.toString(array);
  }

  private String format(int[] array) {
    return Arrays.toString(array);
  }

  private String format(long[] array) {
    return Arrays.toString(array);
  }

  private String format(float[] array) {
    return Arrays.toString(array);
  }

  private String format(double[] array) {
    return Arrays.toString(array);
  }
}
