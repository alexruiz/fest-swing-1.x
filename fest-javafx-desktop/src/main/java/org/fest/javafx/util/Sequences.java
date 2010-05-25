/*
 * Created on May 24, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.util;

import static com.sun.javafx.runtime.TypeInfo.getTypeInfo;

import java.util.List;

import com.sun.javafx.runtime.sequence.ObjectArraySequence;
import com.sun.javafx.runtime.sequence.Sequence;

/**
 * Understands utility methods related to <code>{@link Sequence}</code>s.
 *
 * @author Alex Ruiz
 */
public final class Sequences {

  /**
   * Creates an empty <code>{@link Sequence}</code>.
   * @param <T> the generic type of the values in the {@code Sequence} to create.
   * @param type the class of the values in the {@code Sequence} to create.
   * @return the created {@code Sequence}.
   */
  public static <T> Sequence<T> emptySequence(Class<T> type) {
    return getTypeInfo(type).emptySequence;
  }

  /**
   * Creates a new <code>{@link Sequence}</code>.
   * @param <T> the generic type of the values in the {@code Sequence} to create.
   * @param type the class of the values in the {@code Sequence} to create.
   * @param values the elements in the {@code Sequence}.
   * @return the created {@code Sequence}.
   */
  public static <T> Sequence<T> sequence(Class<T> type, T...values) {
    return new ObjectArraySequence<T>(getTypeInfo(type), values);
  }

  /**
   * Creates a new <code>{@link Sequence}</code>.
   * @param <T> the generic type of the values in the {@code Sequence} to create.
   * @param type the class of the values in the {@code Sequence} to create.
   * @param values the elements in the {@code Sequence}.
   * @return the created {@code Sequence}.
   */
  public static <T> Sequence<T> sequence(Class<T> type, List<T> values) {
    return new ObjectArraySequence<T>(getTypeInfo(type), values);
  }

  private Sequences() {}
}
