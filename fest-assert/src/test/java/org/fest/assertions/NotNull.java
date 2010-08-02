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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

/**
 * Understands a <code>{@link Condition}</code> that verifies that a value is not <code>null</code>.
 *
 * @author Alex Ruiz
 */
class NotNull<T> extends Condition<T> {

  static <T> NotNull<T> notNull() {
    return new NotNull<T>();
  }

  private NotNull() {}

  @Override public boolean matches(T value) {
    return value != null;
  }
}
