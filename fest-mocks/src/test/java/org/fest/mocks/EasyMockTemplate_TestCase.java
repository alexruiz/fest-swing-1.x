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

import static org.easymock.EasyMock.createMock;

import org.junit.Before;


/**
 * Base test case for <code>{@link EasyMockTemplate}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class EasyMockTemplate_TestCase {

  Server mockServer;

  @Before public final void setUp() {
    mockServer = createMock(Server.class);
    onSetUp();
  }

  void onSetUp() {}

  static interface Server {
    void process(String s);
  }

  static class Client {
    final Server delegate;

    Client(Server delegate) {
      this.delegate = delegate;
    }

    void process(String s) {
      delegate.process(s);
    }
  }

  static class DummyEasyMockTemplate extends EasyMockTemplate {
    DummyEasyMockTemplate(Object... mocks) {
      super(mocks);
    }

    protected void expectations() throws Throwable {}
    protected void codeToTest() throws Throwable {}
  }
}
