/*
 * Created on Feb 15, 2008
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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Mocks.createComparisonFailureFrom;
import static org.junit.Assert.assertNull;

import org.fest.mocks.EasyMockTemplate;
import org.junit.*;

/**
 * Tests for <code>{@link ComparisonFailureFactory#comparisonFailure(String, Object, Object)}</code>.
 *
 * @author Alex Ruiz
 */
public class ComparisonFailureFactory_comparisonFailure_withMocks_Test {

  private ConstructorInvoker invoker;

  @Before
  public void setUp() {
    invoker = createMock(ConstructorInvoker.class);
    ComparisonFailureFactory.constructorInvoker(invoker);
  }

  @After
  public void tearDown() {
    ComparisonFailureFactory.constructorInvoker(new ConstructorInvoker());
  }

  @Test
  public void should_return_null_if_created_Object_is_not_AssertionError() {
    new EasyMockTemplate(invoker) {
      protected void expectations() {
        expect(createComparisonFailure()).andReturn(new Object());
      }

      protected void codeToTest() {
        AssertionError created = ComparisonFailureFactory.comparisonFailure("message", "expected", "actual");
        assertNull(created);
      }
    }.run();
  }

  @Test
  public void should_return_null_if_call_to_constructor_throws_exception() {
    new EasyMockTemplate(invoker) {
      protected void expectations() {
        expect(createComparisonFailure()).andThrow(new Exception());
      }

      protected void codeToTest() {
        AssertionError created = ComparisonFailureFactory.comparisonFailure("message", "expected", "actual");
        assertNull(created);
      }
    }.run();
  }

  private Object createComparisonFailure() {
    return createComparisonFailureFrom(invoker);
  }
}
