/*
 * Created on 2010-4-24
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
 * Copyright @2007-2010 the original author or authors.
 */

package org.fest.assertions;

public class ShortAssert_Generic_isEqualTo_Test extends GenericAssert_isEqualTo_TestBase<Short, ShortAssert> {

  protected Short actualValueX() {
    return (short) 8;
  }

  protected Short actualValueY() {
    return (short) -17;
  }

  protected String messageStringRepresentingX() {
    return "[8]";
  }

  protected String messageStringRepresentingY() {
    return "[-17]";
  }

  protected ShortAssert createAssertForActual(Short actual) {
    return new ShortAssert(actual);
  }
}