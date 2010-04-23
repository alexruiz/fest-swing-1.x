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

public class ByteAssert_Generic_isEqualTo_Test extends GenericAssert_isEqualTo_TestBase<Byte, ByteAssert> {

  protected Byte actualValueX() {
    return (byte) 123;
  }

  protected Byte actualValueY() {
    return (byte) 99;
  }

  protected String messageStringRepresentingX() {
    return "[123]";
  }

  protected String messageStringRepresentingY() {
    return "[99]";
  }

  protected ByteAssert createAssertForActual(Byte actual) {
    return new ByteAssert(actual);
  }
}