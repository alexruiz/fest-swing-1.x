/*
 * Created on Jul 29, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.util;


/**
 * Understands a tuple of size 2.
 * @param <I> the generic type of the 1st. value in this tuple.
 * @param <II> the generic type of the 2nd. value in this tuple.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Pair<I, II> {

  /** The first value in this tuple. */
  public final I i;

  /** The second value in this tuple. */
  public final II ii;

  /**
   * Creates a new </code>{@link Pair}</code>.
   * @param i the 1st. value in this tuple.
   * @param ii the 2nd. value in this tuple.
   */
  public Pair(I i, II ii) {
    this.i = i;
    this.ii = ii;
  }
}
