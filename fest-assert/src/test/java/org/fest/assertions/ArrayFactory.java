/*
 * Created on Sep 14, 2009
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

/**
 * Understands creation of arrays.
 *
 * @author Alex Ruiz
 */
final class ArrayFactory {

  static boolean[] booleanArray(boolean...values) {
    return values;
  }

  static byte[] byteArray(int...values) {
    int arraySize = values.length;
    byte[] array = new byte[arraySize];
    for (int i = 0; i < arraySize; i++) array[i] = (byte)values[i];
    return array;
  }

  static char[] charArray(char...values) {
    return values;
  }

  static double[] doubleArray(double...values) {
    return values;
  }

  static float[] floatArray(float...values) {
    return values;
  }

  static int[] intArray(int...values) {
    return values;
  }

  static long[] longArray(long...values) {
    return values;
  }

  static short[] shortArray(int...values) {
    int arraySize = values.length;
    short[] array = new short[arraySize];
    for (int i = 0; i < arraySize; i++) array[i] = (short)values[i];
    return array;
  }

  static Object[] objectArray(Object...values) {
    return values;
  }

  private ArrayFactory() {}
}
