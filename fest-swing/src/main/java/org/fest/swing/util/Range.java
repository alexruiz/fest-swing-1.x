/*
 * Created on Dec 5, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.util;

/**
 * Understands a range (from, to.)
 * <p>
 * Usage:
 * <pre>
 * Range range = <code>{@link #from(int) from}</code>(0).<code>{@link #to(int) to}</code>(8);
 * </pre>
 * </p>
 *
 * @author Alex Ruiz
 */
public final class Range {

  /**
   * Creates a new <code>{@link From}</code>, representing the starting value of a range.
   * @param value the starting value of the range.
   * @return the created <code>From</code>.
   */
  public static final From from(int value) {
    return new From(value);
  }

  /**
   * Creates a new <code>{@link To}</code>, representing the ending value of a range.
   * @param value the ending value of the range.
   * @return the created <code>To</code>.
   */
  public static final To to(int value) {
    return new To(value);
  }

  /**
   * Understands the starting value of a range.
   * @see Range
   *
   * @author Alex Ruiz
   */
  public static class From {
    public final int value;
    From(int value) { this.value = value; }
  }

  /**
   * Understands the ending value of a range.
   * @see Range
   *
   * @author Alex Ruiz
   */
  public static class To {
    public final int value;
    To(int value) { this.value = value; }
  }

  private Range() {}
}
