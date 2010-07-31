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

import static org.fest.util.Collections.*;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#hasOnlyNullElements(java.util.Collection)}</code>
 *
 * @author Joel Costigliola
 */
public class Collections_hasOnlyNullElements_Test {

  @Test
  public void should_return_false_if_given_collection_is_empty() {
    assertFalse(hasOnlyNullElements(new ArrayList<String>()));
  }

  @Test
  public void should_return_false_if_given_collection_has_non_null_elements() {
    assertFalse(hasOnlyNullElements(list("Frodo")));
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_given_collection_is_null() {
    hasOnlyNullElements(null);
  }
}
