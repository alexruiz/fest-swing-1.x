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

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;
import com.apple.mrj.MRJAboutHandler;

/**
 * Tests for <code>{@link MacAboutHandler#installMacAboutHandler(MainFrame, MacSupport)}</code>.
 *
 * @author Alex Ruiz
 */
public class MacAboutHandler_installMacAboutHandler_Test extends EDTSafeTestCase {

  private static MainFrame frame;
  private MacSupportStub macSupport;

  @BeforeClass
  public static void setUpOnce() {
    frame = createMock(MainFrame.class);
  }

  @Before
  public void setUp() {
    macSupport = new MacSupportStub();
  }

  @Test
  public void should_not_register_handler_if_is_not_Mac_OS() {
    macSupport.isMacOS = false;
    MacAboutHandler.installMacAboutHandler(frame, macSupport);
    assertThat(macSupport.registeredHandlers).isEmpty();
  }

  @Test
  public void should_register_handler_if_is_Mac_OS() {
    macSupport.isMacOS = true;
    MacAboutHandler.installMacAboutHandler(frame, macSupport);
    assertThat(macSupport.registeredHandlers).hasSize(1);
  }

  private static class MacSupportStub extends MacSupport {
    boolean isMacOS;
    final List<MRJAboutHandler> registeredHandlers = new ArrayList<MRJAboutHandler>();

    @Override boolean isMacOS() {
      return isMacOS;
    }

    @Override void register(MRJAboutHandler h) {
      registeredHandlers.add(h);
    }
  }
}
