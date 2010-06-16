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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.EmptyArrays.emptyIntArray;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link IntArrayAssert#doesNotSatisfy(Condition)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class IntArrayAssert_doesNotSatisfy_Test extends GenericAssert_doesNotSatisfy_TestCase<int[]> {

  private static int[] actual;

  @BeforeClass
  public static void setUpOnce() {
    actual = emptyIntArray();
  }

  protected IntArrayAssert assertObject() {
    return new IntArrayAssert(actual);
  }

  protected IntArrayAssert assertObjectWithNullTarget() {
    return new IntArrayAssert(null);
  }
}
