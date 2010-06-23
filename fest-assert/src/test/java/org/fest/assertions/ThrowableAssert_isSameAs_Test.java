/*
 * Created on Dec 23, 2007
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
 * Tests for <code>{@link ThrowableAssert#isSameAs(Throwable)}</code>.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_isSameAs_Test extends GenericAssert_isSameAs_TestCase<Throwable> {

  private static Throwable notNullValue;
  private static Throwable notSameValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = new NullPointerException();
    notSameValue = new Throwable();
  }

  protected ThrowableAssert assertionsFor(Throwable actual) {
    return new ThrowableAssert(actual);
  }

  protected Throwable notNullValue() {
    return notNullValue;
  }

  protected Throwable notSameValue() {
    return notSameValue;
  }
}
