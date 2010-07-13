/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.byteArray;
import static org.fest.assertions.EmptyArrays.emptyByteArray;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link ByteArrayAssert#isNotEmpty()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ByteArrayAssert_isNotEmpty_Test extends GroupAssert_isNotEmpty_TestCase<byte[]> {

  private static byte[] notEmpty;

  @BeforeClass
  public static void setUpOnce() {
    notEmpty = byteArray(8, 6);
  }

  protected ByteArrayAssert assertionsFor(byte[] actual) {
    return new ByteArrayAssert(actual);
  }

  protected byte[] emptyGroup() {
    return emptyByteArray();
  }

  protected byte[] notEmptyGroup() {
    return notEmpty;
  }
}
