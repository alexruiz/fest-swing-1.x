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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for <code>{@link EasyMockTemplate#run()}</code>.
 *
 * @author Alex Ruiz
 */
public class EasyMockTemplate_run_rethrowingExceptions_Test extends EasyMockTemplate_TestCase {

  @Test
  public void should_wrap_and_rethrow_Exception_thrown_by_setUp() {
    final Throwable expected = new Throwable();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void setUp() throws Throwable {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (UnexpectedError e) {
      assertSame(expected, e.getCause());
    }
  }

  @Test
  public void should_rethrow_RuntimeException_thrown_by_setup() {
    final RuntimeException expected = new RuntimeException();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void setUp() {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (RuntimeException e) {
      assertSame(expected, e);
    }
  }

  @Test
  public void should_wrap_and_rethrow_Exception_thrown_by_expectations() {
    final Throwable expected = new Throwable();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void expectations() throws Throwable {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (UnexpectedError e) {
      assertSame(expected, e.getCause());
    }
  }

  @Test
  public void should_rethrow_RuntimeException_thrown_by_expectations() {
    final RuntimeException expected = new RuntimeException();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void expectations() {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (RuntimeException e) {
      assertSame(expected, e);
    }
  }

  @Test
  public void should_wrap_and_rethrow_Exception_thrown_by_codeToTest() {
    final Throwable expected = new Throwable();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void codeToTest() throws Throwable {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (UnexpectedError e) {
      assertSame(expected, e.getCause());
    }
  }

  @Test
  public void should_rethrow_RuntimeException_thrown_by_codeToTest() {
    final RuntimeException expected = new RuntimeException();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void codeToTest() {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (RuntimeException e) {
      assertSame(expected, e);
    }
  }

  @Test
  public void should_wrap_and_rethrow_Exception_thrown_by_cleanUp() {
    final Throwable expected = new Throwable();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void cleanUp() throws Throwable {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (UnexpectedError e) {
      assertSame(expected, e.getCause());
    }
  }

  @Test
  public void should_rethrow_RuntimeException_thrown_by_cleanUp() {
    final RuntimeException expected = new RuntimeException();
    DummyEasyMockTemplate template = new DummyEasyMockTemplate(mockServer) {
      @Override protected void cleanUp() {
        throw expected;
      }
    };
    try {
      template.run();
      failWhenExpectingException();
    } catch (RuntimeException e) {
      assertSame(expected, e);
    }
  }

  private void failWhenExpectingException() {
    fail("Expecting exception");
  }
}
