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

import org.fest.swing.driver.JTableHeaderDriver;

/**
 * Base test class for <code>{@link JTableHeaderFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class JTableHeaderFixture_TestCase extends ComponentFixture_Implementations_TestCase<JTableHeader> {

  private JTableHeaderDriver driver;
  private JTableHeader target;
  private JTableHeaderFixture fixture;

  final void onSetUp() {
    target = tableHeader().createNew();
    fixture = new JTableHeaderFixture(robot(), target);
    driver = createMock(JTableHeaderDriver.class);
    fixture.driver(driver);
    extraSetUp();
  }

  void extraSetUp() {}

  final JTableHeaderDriver driver() { return driver; }
  final JTableHeader target() { return target; }
  final JTableHeaderFixture fixture() { return fixture; }
}
