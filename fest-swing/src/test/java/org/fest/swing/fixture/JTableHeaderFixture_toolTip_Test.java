/*
 * Created on Mar 16, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JTableHeaders.tableHeader;
import javax.swing.table.JTableHeader;

import org.fest.swing.driver.*;
import org.junit.BeforeClass;

/**
 * Tests for methods in <code>{@link JTableHeaderFixture}</code> that are inherited from
 * <code>{@link ToolTipDisplayFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JTableHeaderFixture_toolTip_Test extends ToolTipDisplayFixture_TestCase<JTableHeader> {

  private static JTableHeader target;

  private JTableHeaderDriver driver;
  private JTableHeaderFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = tableHeader().createNew();
  }

  void onSetUp() {
    driver = createMock(JTableHeaderDriver.class);
    fixture = new JTableHeaderFixture(robot(), target);
    fixture.driver(driver);
  }

  JTableHeaderDriver driver() {  return driver; }
  JTableHeader target() { return target; }
  JTableHeaderFixture fixture() { return fixture; }
}
