/*
 * Created on 2010-4-26
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
 * Test ensuring that {@link ByteAssert} obeys the {@link GenericAssert#isNotEqualTo(Object)} contract for {@link
 * Byte}.
 *
 * @author Ansgar Konermann
 */
public class ByteAssert_Generic_isNotEqualTo_Test extends GenericAssert_isNotEqualTo_TestBase<Byte> {

  @Override
  protected Byte eight() {
    return 8;
  }

  @Override
  protected Byte zero() {
    return 0;
  }

  @Override
  protected ByteAssert assertionFor(Byte actual) {
    return new ByteAssert(actual);
  }

  @Override
  protected String eightAsString() {
    return "8";
  }

}
