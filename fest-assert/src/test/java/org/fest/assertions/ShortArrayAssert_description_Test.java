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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.shortArray;

/**
 * Tests for:
 * <ul>
 * <li><code>{@link ShortArrayAssert#as(Description)}</code></li>
 * <li><code>{@link ShortArrayAssert#as(String)}</code></li>
 * <li><code>{@link ShortArrayAssert#describedAs(Description)}</code></li>
 * <li><code>{@link ShortArrayAssert#describedAs(String)}</code></li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ShortArrayAssert_description_Test extends GenericAssert_description_TestCase<short[]> {

  @Override
  protected GenericAssert<short[]> assertionToTest() {
    return new ShortArrayAssert(shortArray(6));
  }
}
