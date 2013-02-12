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

import static org.fest.swing.test.builder.JTables.table;

import javax.swing.JTable;

import org.fest.swing.driver.JTableDriver;
import org.junit.BeforeClass;

/**
 * Test cases for {@link JTableFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class JTableFixture_TestCase extends ComponentFixture_Implementations_TestCase<JTable> {
  private static JTable target;

  private JTableDriver driver;
  private JTableFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = table().createNew();
  }

  @Override
  final void onSetUp() {
    driver = createMock(JTableDriver.class);
    fixture = new JTableFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  final JTableDriver driver() {
    return driver;
  }

  @Override
  final JTable target() {
    return target;
  }

  @Override
  final JTableFixture fixture() {
    return fixture;
  }
}
