/*
 * Created on Aug 8, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Arrays.array;

import javax.swing.JComboBox;
import javax.swing.JList;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.query.ComponentShowingQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for {@link JComboBoxSetPopupVisibleTask#setPopupVisible(JComboBox, boolean)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JComboBoxSetPopupVisibleTask_setPopupVisible_Test extends RobotBasedTestCase {
  private MyWindow window;
  private JComboBox comboBox;

  @Override
  protected void onSetUp() {
    window = MyWindow.createNew();
    comboBox = window.comboBox;
    robot.showWindow(window);
  }

  @Test
  public void should_set_popup_visible_and_invisible() {
    setPopupVisible(true);
    assertThat(popupIsShowing()).isTrue();
    setPopupVisible(false);
    try {
      comboBoxPopup();
      fail("Expecting JList not found!");
    } catch (ComponentLookupException expected) {
    }
  }

  private void setPopupVisible(final boolean visible) {
    JComboBoxSetPopupVisibleTask.setPopupVisible(comboBox, visible);
    robot.waitForIdle();
  }

  private boolean popupIsShowing() {
    return ComponentShowingQuery.isShowing(comboBoxPopup());
  }

  private JList comboBoxPopup() {
    return robot.finder().findByType(window, JList.class);
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    final JComboBox comboBox = new JComboBox(array("One", "Two"));

    private MyWindow() {
      super(JComboBoxSetPopupVisibleTask_setPopupVisible_Test.class);
      addComponents(comboBox);
    }
  }
}
