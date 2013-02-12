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

import static org.fest.swing.test.builder.JOptionPanes.optionPane;

import javax.swing.JOptionPane;

import org.fest.swing.driver.JOptionPaneDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link JOptionPaneFixture} that are inherited from {@link StateVerificationFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JOptionPaneFixture_stateVerification_Test extends StateVerificationFixture_TestCase<JOptionPane> {
  private static JOptionPane target;

  private JOptionPaneDriver driver;
  private JOptionPaneFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = optionPane().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(JOptionPaneDriver.class);
    fixture = new JOptionPaneFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  JOptionPaneDriver driver() {
    return driver;
  }

  @Override
  JOptionPane target() {
    return target;
  }

  @Override
  JOptionPaneFixture fixture() {
    return fixture;
  }
}
