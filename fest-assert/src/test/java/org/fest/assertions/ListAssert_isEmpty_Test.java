/*
 * Created on Mar 29, 2009
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

import static org.fest.util.Collections.list;

import java.util.*;
import java.util.Collections;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link ListAssert#isEmpty()}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ListAssert_isEmpty_Test extends GroupAssert_isEmpty_TestCase<List<?>> {

  private static List<?> empty;
  private static List<?> notEmpty;

  @BeforeClass
  public static void setUpOnce() {
    empty = Collections.emptyList();
    notEmpty = list("Yoda", "Luke");
  }

  protected ListAssert assertionsFor(List<?> actual) {
    return new ListAssert(actual);
  }

  protected List<?> emptyGroup() {
    return empty;
  }

  protected List<?> notEmptyGroup() {
    return notEmpty;
  }
}
