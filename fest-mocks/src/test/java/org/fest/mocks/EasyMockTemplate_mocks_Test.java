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

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link EasyMockTemplate#mocks()}</code>.
 *
 * @author Alex Ruiz
 */
public class EasyMockTemplate_mocks_Test extends EasyMockTemplate_TestCase {

  @Test
  public void should_return_mocks() {
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer);
    List<Object> actualMocks = template.mocks();
    assertEquals(asList(mockServer), actualMocks);
  }

}
