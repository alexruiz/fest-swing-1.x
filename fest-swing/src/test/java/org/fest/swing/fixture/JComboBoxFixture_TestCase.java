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

import static org.fest.swing.test.builder.JComboBoxes.comboBox;

import javax.swing.JComboBox;

import org.fest.swing.driver.JComboBoxDriver;
import org.junit.BeforeClass;

/**
 * Test cases for {@link JComboBoxFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class JComboBoxFixture_TestCase extends ComponentFixture_Implementations_TestCase<JComboBox> {
  private static JComboBox target;

  private JComboBoxDriver driver;
  private JComboBoxFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = comboBox().createNew();
  }

  @Override
  final void onSetUp() {
    driver = createMock(JComboBoxDriver.class);
    fixture = new JComboBoxFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  final JComboBoxDriver driver() {
    return driver;
  }

  @Override
  final JComboBox target() {
    return target;
  }

  @Override
  final JComboBoxFixture fixture() {
    return fixture;
  }
}
