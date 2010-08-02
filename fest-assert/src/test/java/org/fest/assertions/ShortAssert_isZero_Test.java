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
 * Tests for <code>{@link ShortAssert#isZero()}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ShortAssert_isZero_Test extends NumberAssert_isZero_TestCase<Short> {

  private static Short notZero;
  private static Short zero;

  @BeforeClass
  public static void setUpOnce() {
    notZero = 6;
    zero = 0;
  }

  @Override protected Short notZero() {
    return notZero;
  }

  @Override protected Short zero() {
    return zero;
  }

  @Override protected ShortAssert assertionsFor(Short actual) {
    return new ShortAssert(actual);
  }
}
