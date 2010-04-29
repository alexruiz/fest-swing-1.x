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

/**
 * Test ensuring that {@link CharAssert} obeys the {@link GenericAssert#isEqualTo(Object)} contract for {@link
 * Character}.
 *
 * @author Ansgar Konermann
 */

public class CharAssert_Generic_isEqualTo_Test extends GenericAssert_isEqualTo_TestBase<Character, CharAssert> {

  protected Character actualValueX() {
    return '1';
  }

  protected Character actualValueY() {
    return '2';
  }

  protected String messageStringRepresentingX() {
    return "[1]";
  }

  protected String messageStringRepresentingY() {
    return "[2]";
  }

  protected CharAssert createAssertForActual(Character actual) {
    return new CharAssert(actual);
  }
}