/*
 * Created on Jan 10, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.util.Collections.list;

import java.util.*;
import java.util.Collections;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link CollectionAssert#isEmpty()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert_isEmpty_Test extends GroupAssert_isEmpty_TestCase<Collection<?>> {

  private static Collection<?> empty;
  private static Collection<?> notEmpty;

  @BeforeClass
  public static void setUpOnce() {
    empty = Collections.emptyList();
    notEmpty = list("Yoda", "Ben", "Luke");
  }

  protected CollectionAssert assertionsFor(Collection<?> actual) {
    return new CollectionAssert(actual);
  }

  protected Collection<?> emptyGroup() {
    return empty;
  }

  protected Collection<?> notEmptyGroup() {
    return notEmpty;
  }
}
