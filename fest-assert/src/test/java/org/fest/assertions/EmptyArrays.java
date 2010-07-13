/*
 * Created on Sep 9, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

/**
 * Understands empty arrays.
 *
 * @author Alex Ruiz
 */
final class EmptyArrays {

  private static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
  private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  private static final char[] EMPTY_CHAR_ARRAY = new char[0];
  private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
  private static final float[] EMPTY_FLOAT_ARRAY = new float[0];
  private static final int[] EMPTY_INT_ARRAY = new int[0];
  private static final long[] EMPTY_LONG_ARRAY = new long[0];
  private static final short[] EMPTY_SHORT_ARRAY = new short[0];
  private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

  static boolean[] emptyBooleanArray() {
    return EMPTY_BOOLEAN_ARRAY;
  }

  static byte[] emptyByteArray() {
    return EMPTY_BYTE_ARRAY;
  }

  static char[] emptyCharArray() {
    return EMPTY_CHAR_ARRAY;
  }

  static double[] emptyDoubleArray() {
    return EMPTY_DOUBLE_ARRAY;
  }

  static float[] emptyFloatArray() {
    return EMPTY_FLOAT_ARRAY;
  }

  static int[] emptyIntArray() {
    return EMPTY_INT_ARRAY;
  }

  static long[] emptyLongArray() {
    return EMPTY_LONG_ARRAY;
  }

  static short[] emptyShortArray() {
    return EMPTY_SHORT_ARRAY;
  }

  static Object[] emptyObjectArray() {
    return EMPTY_OBJECT_ARRAY;
  }

  private EmptyArrays() {}
}
