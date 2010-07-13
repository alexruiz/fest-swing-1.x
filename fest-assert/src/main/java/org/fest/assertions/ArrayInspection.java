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

import static java.lang.reflect.Array.*;

import java.util.*;

/**
 * Understands utility methods for arrays.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public final class ArrayInspection {

  /**
   * Copies the contents of the given array into an array of objects.
   * @param array the array to copy.
   * @return an array of objects containing the contents of the array.
   * @throws IllegalArgumentException if the given object is not an array.
   */
  public static Object[] copy(Object array) {
    List<Object> list = toList(array);
    return list == null ? null : list.toArray();
  }

  /**
   * Copies the contents of the given array into a list.
   * @param array the array to copy.
   * @return a list containing the contents of the array.
   * @throws IllegalArgumentException if the given object is not an array.
   * @since 1.3.
   */
  public static List<Object> toList(Object array) {
    return copy(array, new ArrayList<Object>());
  }

  /**
   * Copies the contents of the given array into a list.
   * @param array the array to copy.
   * @return a list containing the contents of the array.
   * @throws IllegalArgumentException if the given object is not an array.
   * @since 1.3.
   */
  public static Set<Object> toSet(Object array) {
    return copy(array, new HashSet<Object>());
  }

  private static <T extends Collection<Object>> T copy(Object array, T destination) {
    if (array == null) return null;
    int length = sizeOf(array);
    for (int i = 0; i < length; i++) destination.add(get(array, i));
    return destination;
  }

  /**
   * Returns the size of the given array.
   * @param array the array.
   * @return the size of the given array.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given object is not an array.
   */
  public static int sizeOf(Object array) {
    if (array == null) throw new NullPointerException("The given array should not be null");
    validateIsArray(array);
    return getLength(array);
  }

  private static void validateIsArray(Object array) {
    if (!array.getClass().isArray()) throw new IllegalArgumentException("The given object is not an array");
  }

  private ArrayInspection() {}
}
