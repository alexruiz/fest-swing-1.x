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

import java.util.List;
import java.util.Map;

/**
 * Understands a <code>{@link Condition}</code> that verifies that a value is not <code>null</code>.
 *
 * @author Alex Ruiz
 */
class NotNull<T> extends Condition<T> {

  static <T> NotNull<T> notNull() {
    return new NotNull<T>();
  }

  static NotNull<int[]> notNullIntArray() {
    return new NotNull<int[]>();
  }

  static NotNull<List<?>> notNullList() {
    return new NotNull<List<?>>();
  }

  static NotNull<long[]> notNullLongArray() {
    return new NotNull<long[]>();
  }

  static NotNull<Map<?, ?>> notNullMap() {
    return new NotNull<Map<?, ?>>();
  }

  static NotNull<Object> notNullObject() {
    return new NotNull<Object>();
  }

  static NotNull<Object[]> notNullObjectArray() {
    return new NotNull<Object[]>();
  }

  static NotNull<short[]> notNullShortArray() {
    return new NotNull<short[]>();
  }

  static NotNull<Throwable> notNullThrowable() {
    return new NotNull<Throwable>();
  }

  private NotNull() {}

  public boolean matches(T value) {
    return value != null;
  }
}
