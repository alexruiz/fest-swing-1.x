/*
 * Created on Apr 27, 2010
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
 * Tests for <code>{@link LongAssert#isSameAs(Long)}</code>.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class LongAssert_Generic_isSameAs_Test extends GenericAssert_isSameAs_TestCase<Long> {

  private static Long notNullValue;
  private static Long notSameValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = 6L;
    notSameValue = 8L;
  }

  protected LongAssert assertionsFor(Long actual) {
    return new LongAssert(actual);
  }

  protected Long notNullValue() {
    return notNullValue;
  }

  protected Long notSameValue() {
    return notSameValue;
  }
}
