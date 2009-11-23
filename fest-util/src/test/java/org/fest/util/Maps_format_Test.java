/*
 * Created on Apr 29, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.*;

import org.junit.Test;

/**
 * Tests for <code>{@link Maps#format(Map)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Maps_format_Test {

  @Test
  public void should_return_null_if_Map_is_null() {
    assertNull(Maps.format(null));
  }

  @Test
  public void should_return_empty_braces_if_Map_is_empty() {
    assertEquals(Maps.format(new HashMap<String, String>()), "{}");
  }

  @Test
  public void should_format_Map() {
    Map<String, Integer> map = new LinkedHashMap<String, Integer>();
    map.put("One", 1);
    map.put("Two", 2);
    assertEquals("{'One'=1, 'Two'=2}", Maps.format(map));
  }
}
