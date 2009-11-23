/*
 * Created on Nov 12, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.JComboBoxSelectionValueQuery.NO_SELECTION_VALUE;
import static org.fest.swing.driver.JComboBoxSetSelectedIndexTask.setSelectedIndex;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Arrays.array;

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link JComboBoxSelectionValueQuery#selection(JComboBox, JComboBoxCellReader)}</code>.
 *
 * @author Alex Ruiz 
 */
public class JComboBoxSelectionValueQuery_selection_Test extends RobotBasedTestCase {

  private JComboBox comboBox;
  private JComboBoxCellReader cellReader;
  
  @Override protected void onSetUp() {
    MyWindow window = MyWindow.createNew();
    comboBox = window.comboBox;
    cellReader = new BasicJComboBoxCellReader();
  }

  @Test
  public void should_return_empty_String_if_not_editable_JComboBox_does_not_have_selection() {
    assertThat(JComboBoxSelectionValueQuery.selection(comboBox, cellReader)).isSameAs(NO_SELECTION_VALUE);
  }
  
  @Test
  public void should_return_text_of_selected_item_in_not_editable_JComboBox() {
    setSelectedIndex(comboBox, 0);
    robot.waitForIdle();
    assertThat(JComboBoxSelectionValueQuery.selection(comboBox, cellReader)).isSameAs("first");
  }
  
  @Test
  public void should_return_text_of_selected_item_in_editable_JComboBox() {
    makeEditableAndSelectIndex(comboBox, 0);
    robot.waitForIdle();
    assertThat(JComboBoxSelectionValueQuery.selection(comboBox, cellReader)).isSameAs("first");
  }
  
  @RunsInEDT
  private static void makeEditableAndSelectIndex(final JComboBox comboBox, final int index) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        comboBox.setEditable(true);
        comboBox.setSelectedIndex(index);
      }
    });
  }

  @Test
  public void should_return_text_of_entered_item_in_editable_JComboBox() {
    makeEditableAndSelectValue(comboBox, "Hello");
    robot.waitForIdle();
    assertThat(JComboBoxSelectionValueQuery.selection(comboBox, cellReader)).isSameAs("Hello");
  }

  @RunsInEDT
  private static void makeEditableAndSelectValue(final JComboBox comboBox, final String value) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        comboBox.setEditable(true);
        comboBox.setSelectedItem(value);
      }
    });
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JComboBox comboBox = new JComboBox(array("first", "second", "third"));

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JComboBoxSelectionValueQuery_selection_Test.class);
      comboBox.setSelectedIndex(-1);
      addComponents(comboBox);
    }
  }
}
