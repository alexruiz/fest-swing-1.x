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

/**
 * Tests for <code>{@link DoubleAssert#isNotEqualTo(Double)}</code>.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class DoubleAssert_isNotEqualTo_Double_Test extends GenericAssert_isNotEqualTo_TestCase<Double> {

  protected DoubleAssert assertObject() {
    return new DoubleAssert(6d);
  }

  protected Double notEqualValue() {
    return 8d;
  }
}
