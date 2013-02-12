/*
 * Created on Nov 18, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_B;
import static java.awt.event.KeyEvent.VK_C;
import static org.fest.swing.test.builder.JComboBoxes.comboBox;

import javax.swing.JComboBox;

import org.fest.swing.driver.JComboBoxDriver;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for methods in {@link JComboBoxFixture} that are inherited from {@link KeyboardInputSimulationFixture}.
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

  @Override
  void onSetUp() {
    driver = createMock(JComboBoxDriver.class);
    fixture = new JComboBoxFixture(robot(), target);
    fixture.driver(driver);
  }

  @Test
  @Override
  public void should_press_and_release_keys() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().pressAndReleaseKeys(target(), VK_A, VK_B, VK_C);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().pressAndReleaseKeys(VK_A, VK_B, VK_C));
      }
    }.run();
  }

  @Override
  JComboBoxDriver driver() {
    return driver;
  }

  @Override
  JComboBox target() {
    return target;
  }

  @Override
  JComboBoxFixture fixture() {
    return fixture;
  }
}
