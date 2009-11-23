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

import static org.junit.Assert.assertSame;

import java.util.*;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#filter(Collection, CollectionFilter)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Collections_filter_Test {

  @Test
  public void shouldFilterCollection() {
    List<Object> expectedResult = new ArrayList<Object>();
    FilterStub filter = new FilterStub(expectedResult);
    List<Object> filtered = Collections.filter(new ArrayList<Object>(), filter);
    assertSame(filtered, expectedResult);
  }

  private static class FilterStub implements CollectionFilter<Object> {
    private final List<Object> expectedResult;

    FilterStub(List<Object> expectedResult) {
      this.expectedResult = expectedResult;
    }

    public List<Object> filter(Collection<?> target) {
      return expectedResult;
    }
  }
}
