/*
 * Created on Mar 1, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.objectArray;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link ObjectArrayAssert#isEqualTo(Object[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectArrayAssert_isEqualTo_Test extends GenericAssert_isEqualTo_TestCase<Object[]> {

  private static Object[] notNullValue;
  private static Object[] unequalValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = objectArray(6, 8);
    unequalValue = objectArray(6);
  }

  @Override
  protected ObjectArrayAssert assertionsFor(Object[] actual) {
    return new ObjectArrayAssert(actual);
  }

  @Override
  protected Object[] notNullValue() {
    return notNullValue;
  }

  @Override
  protected Object[] unequalValue() {
    return unequalValue;
  }
}
