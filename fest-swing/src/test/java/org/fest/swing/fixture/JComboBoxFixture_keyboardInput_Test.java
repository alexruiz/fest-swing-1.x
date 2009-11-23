/*
 * Created on Nov 18, 2009
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
package org.fest.swing.fixture;

import static java.awt.event.KeyEvent.*;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JComboBoxes.comboBox;

import javax.swing.JComboBox;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.driver.JComboBoxDriver;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for methods in <code>{@link JComboBoxFixture}</code> that are inherited from
 * <code>{@link KeyboardInputSimulationFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JComboBoxFixture_keyboardInput_Test extends KeyboardInputSimulationFixture_TestCase<JComboBox> {

  private static JComboBox target;

  private JComboBoxDriver driver;
  private JComboBoxFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = comboBox().createNew();
  }

  void onSetUp() {
    driver = createMock(JComboBoxDriver.class);
    fixture = new JComboBoxFixture(robot(), target);
    fixture.driver(driver);
  }

  @Test
  @Override public void should_press_and_release_keys() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().pressAndReleaseKeys(target(), VK_A, VK_B, VK_C);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().pressAndReleaseKeys(VK_A, VK_B, VK_C));
      }
    }.run();
  }

  JComboBoxDriver driver() {  return driver; }
  JComboBox target() { return target; }
  JComboBoxFixture fixture() { return fixture; }
}
