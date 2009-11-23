/*
 * Created on Oct 10, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Assert#equals(Object)} and <code>{@link Assert#hashCode()}</code>.
 *
 * @author Yvonne Wang
 */
public class Assert_equals_hashCode_Test {

  private Assert assertion;

  @Before
  public void setUp() {
    assertion = new Assert() {};
  }

  @Test
  public void should_throw_error_if_equals_is_called() {
    try {
      assertion.equals(null);
      fail("expecting an UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertEquals(e.getMessage(), "'equals' is not supported...maybe you intended to call 'isEqualTo'");
    }
  }

  @Test
  public void shouldReturnOneAsHashCode() {
    assertEquals(1, assertion.hashCode());
  }
}
