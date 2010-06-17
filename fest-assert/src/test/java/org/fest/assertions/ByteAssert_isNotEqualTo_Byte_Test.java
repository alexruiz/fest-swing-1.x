/*
 * Created on Apr 26, 2010
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

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link ByteAssert#isNotEqualTo(Byte)}</code>.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class ByteAssert_isNotEqualTo_Byte_Test extends GenericAssert_isNotEqualTo_TestCase<Byte> {

  private static Byte actual;
  private static Byte notEqualValue;

  @BeforeClass
  public static void setUpOnce() {
    actual = 6;
    notEqualValue = 8;
  }

  protected ByteAssert assertObject() {
    return new ByteAssert(actual);
  }

  protected Byte notEqualValue() {
    return notEqualValue;
  }
}
