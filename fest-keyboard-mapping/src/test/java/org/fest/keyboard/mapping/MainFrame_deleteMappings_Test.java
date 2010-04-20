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

import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.JMenuItemFixture;
import org.fest.swing.fixture.JTableFixture;
import org.junit.Test;

/**
 * Tests for <code>{@link MainFrame}</code> that verify that recorded character mappings are successfully deleted.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@GUITest
public class MainFrame_deleteMappings_Test extends MainFrame_TestCase {

  @Test
  public void should_remove_selected_row_when_pressing_delete_key() {
    frame.textBox().enterText("a")
                   .enterText("A")
                   .enterText("s");
    JTableFixture table = frame.table();
    table.pressAndReleaseKey(keyCode(VK_DELETE))
         .requireRowCount(2)
         .requireSelectedRows(1);
    deleteSelectedRowsMenu().requireDisabled();
  }

  private JMenuItemFixture deleteSelectedRowsMenu() {
    return frame.menuItemWithPath("Edit", "Delete Selected Rows");
  }
}
