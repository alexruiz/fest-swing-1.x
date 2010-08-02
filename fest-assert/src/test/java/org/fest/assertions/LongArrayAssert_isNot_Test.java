/*
 * Created on Oct 7, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.EmptyArrays.emptyLongArray;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link LongArrayAssert#isNot(Condition)}</code>.
 *
 * @author Alex Ruiz
 */
public class LongArrayAssert_isNot_Test extends GenericAssert_isNot_TestCase<long[]> {

  private static long[] notNullValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = emptyLongArray();
  }

  @Override protected LongArrayAssert assertionsFor(long[] actual) {
    return new LongArrayAssert(actual);
  }

  @Override protected long[] notNullValue() {
    return notNullValue;
  }
}
