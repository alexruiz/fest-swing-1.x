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

import static org.fest.swing.test.builder.JSpinners.spinner;

import javax.swing.JSpinner;

import org.fest.swing.driver.JSpinnerDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link JSpinnerFixture} that are inherited from {@link ToolTipDisplayFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JSpinnerFixture_toolTip_Test extends ToolTipDisplayFixture_TestCase<JSpinner> {
  private static JSpinner target;

  private JSpinnerDriver driver;
  private JSpinnerFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = spinner().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(JSpinnerDriver.class);
    fixture = new JSpinnerFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  JSpinnerDriver driver() {
    return driver;
  }

  @Override
  JSpinner target() {
    return target;
  }

  @Override
  JSpinnerFixture fixture() {
    return fixture;
  }
}
