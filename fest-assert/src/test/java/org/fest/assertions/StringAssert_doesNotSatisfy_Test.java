/*
 * Created on Jan 10, 2007
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

/**
 * Tests for <code>{@link StringAssert#doesNotSatisfy(Condition)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class StringAssert_doesNotSatisfy_Test extends GenericAssert_doesNotSatisfy_TestTemplate<String> {

  protected StringAssert assertObject() {
    return new StringAssert("a");
  }

  protected StringAssert assertObjectWithNullTarget() {
    return new StringAssert(null);
  }
}
