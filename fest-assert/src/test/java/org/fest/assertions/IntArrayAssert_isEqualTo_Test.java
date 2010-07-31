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

import static org.fest.assertions.ArrayFactory.intArray;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link IntArrayAssert#isEqualTo(int[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class IntArrayAssert_isEqualTo_Test extends GenericAssert_isEqualTo_TestCase<int[]> {

  private static int[] notNullValue;
  private static int[] unequalValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = intArray(6, 8);
    unequalValue = intArray(6);
  }

  @Override
  protected IntArrayAssert assertionsFor(int[] actual) {
    return new IntArrayAssert(actual);
  }

  @Override
  protected int[] notNullValue() {
    return notNullValue;
  }

  @Override
  protected int[] unequalValue() {
    return unequalValue;
  }
}
