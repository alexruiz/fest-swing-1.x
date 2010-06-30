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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import org.junit.BeforeClass;

/**
 * Test for <code>{@link DoubleAssert#isZero()}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class DoubleAssert_isZero_Test extends NumberAssert_isZero_TestCase<Double> {

  private static Double notZero;
  private static Double zero;

  @BeforeClass
  public static void setUpOnce() {
    notZero = 6d;
    zero = 0d;
  }

  protected Double notZero() {
    return notZero;
  }

  protected Double zero() {
    return zero;
  }

  protected DoubleAssert assertionsFor(Double actual) {
    return new DoubleAssert(actual);
  }
}
