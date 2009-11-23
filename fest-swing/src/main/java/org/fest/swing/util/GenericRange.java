/*
 * Created on Nov 19, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.util;

/**
 * Understands a range of values.
 * @param <T> the generic type of the values in this range.
 *
 * @author Yvonne Wang
 */
public final class GenericRange<T> {

  /** Initial value of this range. */
  public final T from;

  /** Final value of this range. */
  public final T to;

  /**
   * Creates a new </code>{@link GenericRange}</code>.
   * @param from the initial value of this range.
   * @param to the final value of this range.
   */
  public GenericRange(T from, T to) {
    this.from = from;
    this.to = to;
  }
}
