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

import static org.fest.assertions.ArrayFactory.floatArray;

/**
 * Tests for <code>{@link FloatArrayAssert#isEqualTo(float[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FloatArrayAssert_isEqualTo_Test extends GenericAssert_isEqualTo_TestCase<float[]> {

  private static final float[] ACTUAL =  { 6f, 8f };
  private static final float[] NOT_EQUAL = { 6f };

  protected FloatArrayAssert assertObject() {
    return new FloatArrayAssert(ACTUAL);
  }

  protected FloatArrayAssert assertObjectWithNullTarget() {
    return new FloatArrayAssert(null);
  }

  protected float[] notEqualValue() {
    return floatArray(NOT_EQUAL);
  }
}
