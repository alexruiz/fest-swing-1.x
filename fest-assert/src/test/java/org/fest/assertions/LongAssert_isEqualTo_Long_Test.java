/*
 * Created on Apr 24, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link LongAssert#isEqualTo(Long)}</code>
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class LongAssert_isEqualTo_Long_Test extends GenericAssert_isEqualTo_TestCase<Long> {

  private static Long notNullValue;
  private static Long unequalValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = 6L;
    unequalValue = 8L;
  }

  @Override
  protected LongAssert assertionsFor(Long actual) {
    return new LongAssert(actual);
  }

  @Override
  protected Long notNullValue() {
    return notNullValue;
  }

  @Override
  protected Long unequalValue() {
    return unequalValue;
  }
}