/*
 * Created on Nov 19, 2009
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

import static org.fest.swing.test.builder.JRadioButtons.radioButton;

import javax.swing.JRadioButton;

import org.fest.swing.driver.AbstractButtonDriver;
import org.junit.BeforeClass;

/**
 * Test cases for {@link JRadioButtonFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class JRadioButtonFixture_TestCase extends ComponentFixture_Implementations_TestCase<JRadioButton> {
  private static JRadioButton target;

  private AbstractButtonDriver driver;
  private JRadioButtonFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = radioButton().createNew();
  }

  @Override
  final void onSetUp() {
    driver = createMock(AbstractButtonDriver.class);
    fixture = new JRadioButtonFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  AbstractButtonDriver driver() {
    return driver;
  }

  @Override
  JRadioButton target() {
    return target;
  }

  @Override
  JRadioButtonFixture fixture() {
    return fixture;
  }
}
