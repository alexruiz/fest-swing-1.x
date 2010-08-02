/*
 * Created on Jun 18, 2007
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

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link LongAssert#isZero()}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class LongAssert_isZero_Test extends NumberAssert_isZero_TestCase<Long> {

  private static Long notZero;
  private static Long zero;

  @BeforeClass
  public static void setUpOnce() {
    notZero = 6L;
    zero = 0L;
  }

  @Override protected Long notZero() {
    return notZero;
  }

  @Override protected Long zero() {
    return zero;
  }

  @Override protected LongAssert assertionsFor(Long actual) {
    return new LongAssert(actual);
  }
}
