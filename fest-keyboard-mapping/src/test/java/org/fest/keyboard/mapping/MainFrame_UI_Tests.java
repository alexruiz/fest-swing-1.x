/*
 * Created on Apr 19, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static java.awt.event.KeyEvent.VK_DELETE;
import static org.fest.swing.core.KeyPressInfo.keyCode;
import static org.fest.swing.data.TableCell.row;
import static org.fest.swing.edt.GuiActionRunner.execute;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JMenuItemFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.junit.Test;

/**
 * Tests for <code>{@link MainFrame}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@GUITest
public class MainFrame_UI_Tests extends RobotBasedTestCase {

  private FrameFixture frame;

  @Override protected void onSetUp() {
    MainFrame mainFrame = execute(new GuiQuery<MainFrame>() {
      protected MainFrame executeInEDT() {
        return new MainFrame();
      }
    });
    frame = new FrameFixture(robot, mainFrame);
    frame.show();
  }

  @Test
  public void should_create_mapping_from_entered_char() {
    JTableFixture table = frame.table();
    table.requireRowCount(0);
    frame.textBox().enterText("a");
    table.requireRowCount(1)
         .requireSelectedRows(0);
    table.cell(row(0).column(0)).requireValue("a");
    table.cell(row(0).column(1)).requireValue("A");
    table.cell(row(0).column(2)).requireValue("NO_MASK");
  }

  @Test
  public void should_remove_selected_rows_when_pressing_delete_key() {
    frame.textBox().enterText("a")
                   .enterText("A")
                   .enterText("s");
    JTableFixture table = frame.table();
    table.requireRowCount(3)
         .pressAndReleaseKey(keyCode(VK_DELETE))
         .requireRowCount(2)
         .requireSelectedRows(1);
    JMenuItemFixture deleteSelectedRowsMenu = frame.menuItemWithPath("Edit", "Delete Selected Rows");
    deleteSelectedRowsMenu.requireEnabled();
    table.selectRows(0, 1)
         .pressAndReleaseKey(keyCode(VK_DELETE))
         .requireRowCount(0);
    deleteSelectedRowsMenu.requireDisabled();
  }
}
