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

import static org.fest.swing.test.builder.JCheckBoxes.checkBox;

import javax.swing.JCheckBox;

import org.fest.swing.driver.AbstractButtonDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link JCheckBoxFixture} that are inherited from {@link JPopupMenuInvokerFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JCheckBoxFixture_JPopupMenuInvoker_Test extends JPopupMenuInvokerFixture_TestCase<JCheckBox> {
  private static JCheckBox target;

  private AbstractButtonDriver driver;
  private JCheckBoxFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = checkBox().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(AbstractButtonDriver.class);
    fixture = new JCheckBoxFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  AbstractButtonDriver driver() {
    return driver;
  }

  @Override
  JCheckBox target() {
    return target;
  }

  @Override
  JCheckBoxFixture fixture() {
    return fixture;
  }
}
