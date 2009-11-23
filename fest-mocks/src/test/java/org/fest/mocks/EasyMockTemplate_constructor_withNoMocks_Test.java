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
import static org.easymock.EasyMock.createMock;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link EasyMockTemplate#EasyMockTemplate(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class EasyMockTemplate_constructor_withNoMocks_Test extends EasyMockTemplate_TestCase {

  private final Object[] mocks;

  @Parameters
  public static Collection<Object[]> notMocks() {
   return asList(new Object[][] {
       { new Object[] { createMock(Server.class), "Not a mock" } },
       { new Object[] { "Not a mock" } },
       { new Object[] { "Not a mock", "Not a mock either" } },
       { new Object[] { "Not a mock", createMock(Server.class) } }
   });
  }

  public EasyMockTemplate_constructor_withNoMocks_Test(Object[] mocks) {
    this.mocks = mocks;
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_arguments_are_not_mocks() {
    new DummyEasyMockTemplate(mocks);
  }
}
