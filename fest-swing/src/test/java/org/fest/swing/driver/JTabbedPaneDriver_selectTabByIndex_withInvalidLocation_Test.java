/*
 * Created on Feb 25, 2008
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
package org.fest.swing.driver;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.driver.JTabbedPaneSelectTabTask.setSelectedTab;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.exception.LocationUnavailableException;
import org.junit.Test;

/**
 * Tests for <code>{@link JTabbedPaneDriver#selectTab(javax.swing.JTabbedPane, int)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTabbedPaneDriver_selectTabByIndex_withInvalidLocation_Test extends JTabbedPaneDriver_TestCase {

  @Test
  public void should_select_tab_directly_if_tab_location_not_found() {
    showWindow();
    selectFirstTab();
    final JTabbedPaneLocation location = createMock(JTabbedPaneLocation.class);
    final int index = 1;
    driver = new JTabbedPaneDriver(robot, location);
    new EasyMockTemplate(location) {
      protected void expectations() {
        location.validateIndex(tabbedPane, index);
        expect(location.pointAt(tabbedPane, index)).andThrow(new LocationUnavailableException("Thrown on purpose"));
      }

      protected void codeToTest() {
        driver.selectTab(tabbedPane, index);
        assertThatSelectedTabIndexIs(index);
      }
    }.run();
  }

  private void selectFirstTab() {
    setSelectedTab(tabbedPane, 0);
    robot.waitForIdle();
  }


}
