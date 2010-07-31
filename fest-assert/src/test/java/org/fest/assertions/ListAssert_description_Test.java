/*
 * Created on Mar 29, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

import java.util.*;

/**
 * Tests for:
 * <ul>
 * <li><code>{@link ListAssert#as(Description)}</code></li>
 * <li><code>{@link ListAssert#as(String)}</code></li>
 * <li><code>{@link ListAssert#describedAs(Description)}</code></li>
 * <li><code>{@link ListAssert#describedAs(String)}</code></li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public class ListAssert_description_Test extends GenericAssert_description_TestCase<List<?>> {

  @Override
  protected GenericAssert<List<?>> assertionToTest() {
    return new ListAssert(new ArrayList<String>());
  }
}
