/*
 * Created on 2010-4-29
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

/**
 * Test ensuring that {@link org.fest.assertions.CharAssert} obeys the {@link org.fest.assertions.GenericAssert#isNotSameAs(Object)}
 * contract for {@link Character}.
 */
public class CharAssert_Generic_isNotSameAs_Test extends GenericAssert_isNotSameAs_TestBase<Character> {

  @Override
  protected Character createEight() {
    return new Character('8');
  }

  @Override
  protected String eightAsString() {
    return "8";
  }

  @Override
  protected CharAssert assertionFor(Character actual) {
    return new CharAssert(actual);
  }
}