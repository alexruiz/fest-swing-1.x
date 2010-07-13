/*
 * Created on Feb 16, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayInspection.*;

import java.util.*;

/**
 * Understands assertions for arrays.
 * @param <T> the generic type of the arrays.
 *
 * @author Alex Ruiz
 */
public abstract class ArrayAssert<T> extends ItemGroupAssert<T> {

  /**
   * Creates a new </code>{@link ArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected ArrayAssert(T actual) {
    super(actual);
  }

  /**
   * Returns the size of the actual array.
   * @return the size of the actual array.
   * @throws NullPointerException if the actual array is <code>null</code>.
   */
  protected final int actualGroupSize() {
    isNotNull();
    return sizeOf(actual);
  }

  /** {@inheritDoc} */
  protected Set<Object> actualAsSet() {
    return toSet(actual);
  }

  /** {@inheritDoc} */
  protected List<Object> actualAsList() {
    return toList(actual);
  }
}
