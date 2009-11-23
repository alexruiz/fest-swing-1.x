/*
 * Created on Apr 22, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.mocks;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link EasyMockTemplate#run()}</code>.
 *
 * @author Alex Ruiz
 */
public class EasyMockTemplate_run_Test extends EasyMockTemplate_TestCase {

  private Client client;

  void onSetUp() {
    client = new Client(mockServer);
  }

  @Test
  public void should_complete_mock_usage_cycle() {
    final String s = "name";
    MethodOrderCheckerEasyMockTemplate template = new MethodOrderCheckerEasyMockTemplate(mockServer) {

      @Override protected void expectations() {
        super.expectations();
        mockServer.process(s);
      }

      @Override protected void codeToTest() {
        super.codeToTest();
        client.process(s);
      }
    };
    template.run();
    template.verifyOrderCall("setUp", "expectations", "codeToTest", "cleanUp");
  }

  private static class MethodOrderCheckerEasyMockTemplate extends EasyMockTemplate {
    private final List<String> methodsCalled = new ArrayList<String>();

    public MethodOrderCheckerEasyMockTemplate(Object... mocks) {
      super(mocks);
    }

    @Override protected void setUp() {
      methodsCalled.add("setUp");
    }

    protected void expectations() {
      methodsCalled.add("expectations");
    }

    protected void codeToTest() {
      methodsCalled.add("codeToTest");
    }

    @Override protected void cleanUp() {
      methodsCalled.add("cleanUp");
    }

    void verifyOrderCall(String...methodNames) {
      assertEquals(asList(methodNames), methodsCalled);
    }
  }
}
