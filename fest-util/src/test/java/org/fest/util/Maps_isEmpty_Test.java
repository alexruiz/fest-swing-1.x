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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for <code>{@link Maps#isEmpty(Map)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Maps_isEmpty_Test {

  @Test
  public void should_return_true_if_Map_is_empty() {
    assertTrue(Maps.isEmpty(new HashMap<String, String>()));
  }

  @Test
  public void should_return_true_if_Map_is_null() {
    assertTrue(Maps.isEmpty(null));
  }

  @Test
  public void should_return_false_if_Map_has_elements() {
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("First", 1);
    assertFalse(Maps.isEmpty(map));
  }
}
