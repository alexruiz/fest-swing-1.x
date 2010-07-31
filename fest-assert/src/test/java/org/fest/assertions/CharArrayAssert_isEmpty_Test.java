/*
 * Created on Feb 14, 2008
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

import static org.fest.assertions.ArrayFactory.charArray;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link CharArrayAssert#isEmpty()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CharArrayAssert_isEmpty_Test extends GroupAssert_isEmpty_TestCase<char[]> {

  private static char[] notEmpty;
  private static char[] empty;

  @BeforeClass
  public static void setUpOnce() {
    notEmpty = charArray('a', 'b');
    empty = new char[0];
  }

  @Override
  protected CharArrayAssert assertionsFor(char[] actual) {
    return new CharArrayAssert(actual);
  }

  @Override
  protected char[] emptyGroup() {
    return empty;
  }

  @Override
  protected char[] notEmptyGroup() {
    return notEmpty;
  }
}
