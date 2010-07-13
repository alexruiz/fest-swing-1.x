/*
 * Created on Oct 27, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Tests for <code>{@link Fail#failIfNotEqual(String, Description, Object, Object)}</code>.
 *
 * @author Alex Ruiz
 */
public class Fail_failIfNotEqual_withStubs_Test {

  private ConstructorInvoker invoker;

  @Before
  public void setUp() {
    invoker = new ConstructorInvoker() {
      @Override Object newInstance(String className, Class<?>[] parameterTypes, Object[] parameterValues) {
        // simulate that ComparisonFailure cannot be created (e.g. if JUnit is not in the classpath)
        return null;
      }
    };
    ComparisonFailureFactory.constructorInvoker(invoker);
  }

  @After
  public void tearDown() {
    ComparisonFailureFactory.constructorInvoker(new ConstructorInvoker());
  }

  @Test
  public void should_throw_AssertionError_when_failing_if_ComparisonFailure_is_not_available() {
    try {
      Fail.failIfNotEqual(null, new BasicDescription("message"), "expected", "actual");
      fail("Expecting AssertionError");
    } catch (AssertionError e) {
      assertFalse(e instanceof ComparisonFailure);
      assertEquals("[message] expected:<'actual'> but was:<'expected'>", e.getMessage());
    }
  }
}
