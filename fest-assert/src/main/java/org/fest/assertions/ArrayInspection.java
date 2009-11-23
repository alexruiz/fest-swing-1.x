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

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

import java.util.ArrayList;
import java.util.List;

/**
 * Understands utility methods for arrays.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public final class ArrayInspection {

  /**
   * Copies the contents of the given array into a list.
   * @param array the array to copy.
   * @return a list containing the contents of the array.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given object is not an array.
   */
  public static List<Object> copy(Object array) {
    int length = sizeOf(array);
    List<Object> copy = new ArrayList<Object>(length);
    for (int i = 0; i < length; i++) copy.add(get(array, i));
    return copy;
  }

  /**
   * Returns the size of the given array.
   * @param array the array.
   * @return the size of the given array.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given object is not an array.
   */
  public static int sizeOf(Object array) {
    validateIsArray(array);
    return getLength(array);
  }

  private static void validateIsArray(Object array) {
    if (array == null) throw new NullPointerException("The array should not be null");
    if (!array.getClass().isArray()) throw new IllegalArgumentException("The given object is not an array");
  }

  private ArrayInspection() {}
}
