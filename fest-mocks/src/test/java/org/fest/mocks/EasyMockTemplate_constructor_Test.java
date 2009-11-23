/*
 * Created on Aug 23, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.mocks;

import org.junit.Test;

/**
 * Tests for <code>{@link EasyMockTemplate#EasyMockTemplate(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class EasyMockTemplate_constructor_Test extends EasyMockTemplate_TestCase {

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_array_of_mocks_is_null() {
    new DummyEasyMockTemplate(new Object[0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_array_of_mocks_contains_null() {
    Object[] mocks = new Object[] { mockServer, null };
    new DummyEasyMockTemplate(mocks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_array_of_mocks_is_empty() {
    new DummyEasyMockTemplate((Object[])null);
  }
}
