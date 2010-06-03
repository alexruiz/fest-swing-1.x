/*
 * Created on Sep 6, 2009
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * Understands a <code>{@link Condition}</code> that verifies that a value is not <code>null</code>.
 *
 * @author Alex Ruiz
 */
class NotNull<T> extends Condition<T> {

  static NotNull<BigDecimal> notNullBigDecimal() {
    return new NotNull<BigDecimal>();
  }

  static NotNull<File> notNullFile() {
    return new NotNull<File>();
  }

  static NotNull<BufferedImage> notNullImage() {
    return new NotNull<BufferedImage>();
  }

  static NotNull<Boolean> notNullBoolean() {
    return new NotNull<Boolean>();
  }

  static NotNull<boolean[]> notNullBooleanArray() {
    return new NotNull<boolean[]>();
  }

  static NotNull<byte[]> notNullByteArray() {
    return new NotNull<byte[]>();
  }

  static NotNull<char[]> notNullCharArray() {
    return new NotNull<char[]>();
  }

  static NotNull<double[]> notNullDoubleArray() {
    return new NotNull<double[]>();
  }

  static NotNull<float[]> notNullFloatArray() {
    return new NotNull<float[]>();
  }

  static NotNull<int[]> notNullIntArray() {
    return new NotNull<int[]>();
  }

  static NotNull<long[]> notNullLongArray() {
    return new NotNull<long[]>();
  }

  static NotNull<short[]> notNullShortArray() {
    return new NotNull<short[]>();
  }

  static NotNull<Object[]> notNullObjectArray() {
    return new NotNull<Object[]>();
  }

  static NotNull<Object> notNullObject() {
    return new NotNull<Object>();
  }

  static NotNull<Collection<?>> notNullCollection() {
    return new NotNull<Collection<?>>();
  }

  static NotNull<List<?>> notNullList() {
    return new NotNull<List<?>>();
  }

  static NotNull<Map<?, ?>> notNullMap() {
    return new NotNull<Map<?, ?>>();
  }

  static NotNull<Throwable> notNullThrowable() {
    return new NotNull<Throwable>();
  }

  static <T> NotNull<T> instance() {
    return new NotNull<T>();
  }

  private NotNull() {}

  public boolean matches(T value) {
    return value != null;
  }
}
