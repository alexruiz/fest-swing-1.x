/*
 * Created on May 13, 2007
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

import org.junit.Test;

/**
 * Tests for <code>{@link Arrays#isEmpty(Object[])}</code>.
 *
 * @author Alex Ruiz
 */
public class Arrays_isEmpty_Test {

  @Test
  public void should_return_true_if_array_is_empty() {
    assertTrue(Arrays.isEmpty(new String[0]));
  }

  @Test
  public void should_return_true_if_array_is_null() {
    assertTrue(Arrays.isEmpty(null));
  }

  @Test
  public void should_return_false_if_array_has_elements() {
    assertFalse(Arrays.isEmpty(new String[] { "Tuzi" }));
  }
}
