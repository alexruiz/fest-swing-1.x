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

/**
 * Understands utility methods related to arrays.
 *
 * @author Alex Ruiz
 */
public class Arrays {

  private static final ArrayFormatter formatter = new ArrayFormatter();

  /**
   * Returns <code>true</code> if the given array is <code>null</code> or empty.
   * @param <T> the type of elements of the array.
   * @param array the array to check.
   * @return <code>true</code> if the given array is <code>null</code> or empty, otherwise <code>false</code>.
   */
  public static <T> boolean isEmpty(T[] array) {
    return array == null || array.length == 0;
  }

  /**
   * Returns an array containing the given arguments.
   * @param <T> the type of the array to return.
   * @param values the values to store in the array.
   * @return an array containing the given arguments.
   */
  public static <T> T[] array(T... values) {
    return values;
  }

  /**
   * Returns the <code>String</code> representation of the given array, or <code>null</code> if the
   * given object is either <code>null</code> or not an array. This method supports arrays having other arrays as
   * elements.
   * @param array the object that is expected to be an array.
   * @return the <code>String</code> representation of the given array.
   */
  public static String format(Object array) {
    return formatter.format(array);
  }

  private Arrays() {}
}
