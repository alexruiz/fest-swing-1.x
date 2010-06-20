/*
 * Created on Jan 24, 2008
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

import static org.fest.assertions.MapAssert.entry;
import static org.fest.assertions.MapFactory.map;

import java.util.Map;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link MapAssert#isNotEqualTo(Map)}</code>.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssert_isNotEqualTo_Test extends GenericAssert_isNotEqualTo_TestCase<Map<?, ?>> {

  private static Map<?, ?> notNullValue;
  private static Map<?, ?> unequalValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = map(entry("key1", 1), entry("key2", 2));
    unequalValue = map(entry("key1", 1), entry("key3", 3));
  }

  protected MapAssert assertionsFor(Map<?, ?> actual) {
    return new MapAssert(actual);
  }

  protected Map<?, ?> notNullValue() {
    return notNullValue;
  }

  protected Map<?, ?> unequalValue() {
    return unequalValue;
  }
}
