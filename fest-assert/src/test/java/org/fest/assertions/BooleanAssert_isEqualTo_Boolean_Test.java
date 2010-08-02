/*
 * Created on Apr 24, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.Boolean.*;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link BooleanAssert#isEqualTo(Boolean)}</code>.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class BooleanAssert_isEqualTo_Boolean_Test extends GenericAssert_isEqualTo_TestCase<Boolean> {

  private static Boolean notNullValue;
  private static Boolean unequalValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = TRUE;
    unequalValue = FALSE;
  }

  @Override protected BooleanAssert assertionsFor(Boolean actual) {
    return new BooleanAssert(actual);
  }

  @Override protected Boolean notNullValue() {
    return notNullValue;
  }

  @Override protected Boolean unequalValue() {
    return unequalValue;
  }
}
