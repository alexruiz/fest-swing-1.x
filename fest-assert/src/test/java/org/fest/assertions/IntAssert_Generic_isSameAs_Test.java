/*
 * Created on 2010-4-27
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
 * Test ensuring that {@link IntAssert} obeys the {@link org.fest.assertions.GenericAssert#isSameAs(Object)} contract
 * for {@link Integer}.
 */
public class IntAssert_Generic_isSameAs_Test extends GenericAssert_isSameAs_TestBase<Integer> {

  private static final int ANY_FIXED_VALUE = 8;

  @Override
  protected Integer createEight() {
    // explicitly allocate a new instance here, since we want to test instance equality!
    return new Integer(ANY_FIXED_VALUE);
  }

  @Override
  protected String eightAsString() {
    return "8";
  }

  @Override
  protected IntAssert assertionFor(Integer actual) {
    return new IntAssert(actual);
  }
}
