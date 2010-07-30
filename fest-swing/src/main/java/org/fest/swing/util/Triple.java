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
 * Understands a tuple of size 3.
 * @param <I> the generic type of the 1st. value in this tuple.
 * @param <II> the generic type of the 2nd. value in this tuple.
 * @param <III> the generic type of the 3rd. value in this tuple.
 *
 * @author Alex Ruiz
 */
public class Triple<I, II, III> extends Pair<I, II> {

  /** The third value in this tuple. */
  public final III iii;

  /**
   * Creates a new </code>{@link Triple}</code>.
   * @param i the 1st. value in this tuple.
   * @param ii the 2nd. value in this tuple.
   * @param iii the 3rd. value in this tuple.
   */
  public Triple(I i, II ii, III iii) {
    super(i, ii);
    this.iii = iii;
  }

}
