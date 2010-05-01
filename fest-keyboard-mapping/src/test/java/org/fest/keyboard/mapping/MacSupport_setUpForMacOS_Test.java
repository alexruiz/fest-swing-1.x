/*
 * Created on May 1, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link MacSupport#setUpForMacOS()}</code>.
 *
 * @author Alex Ruiz
 */
public class MacSupport_setUpForMacOS_Test extends MacSupport_TestCase {

  @Test
  public void should_set_up_system_if_OS_is_Mac() {
    new EasyMockTemplate(systemProperties) {
      @Override protected void expectations() {
        expect(systemProperties.get("os.name")).andReturn("Mac");
        expectMacSetup();
      }

      private void expectMacSetup() {
        systemProperties.set("apple.laf.useScreenMenuBar", "true");
        expectLastCall();
        systemProperties.set("com.apple.mrj.application.apple.menu.about.name", "FEST Keyboard Mapping Tool");
        expectLastCall();
      }

      @Override protected void codeToTest() {
        macSupport.setUpForMacOS();
      }
    }.run();
  }

  @Test
  public void should_not_set_up_system_if_OS_is_not_Mac() {
    new EasyMockTemplate(systemProperties) {
      @Override protected void expectations() {
        expectIsNotMacOS();
      }

      private void expectIsNotMacOS() {
        expect(systemProperties.get("os.name")).andReturn("Windows");
        expect(systemProperties.get("mrj.version")).andReturn(null);
      }

      @Override protected void codeToTest() {
        macSupport.setUpForMacOS();
      }
    }.run();
  }
}
