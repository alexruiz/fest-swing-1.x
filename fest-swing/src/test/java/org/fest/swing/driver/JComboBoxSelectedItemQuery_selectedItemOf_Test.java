/*
 * Created on Oct 11, 2008
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
import static org.fest.swing.driver.JComboBoxSetEditableTask.setEditable;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.task.JComboBoxSetSelectedItemTask.setSelectedItem;

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.MethodInvocations;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link JComboBoxSelectedItemQuery#selectedItemOf(JComboBox)}</code>.
 *
 * @author Alex Ruiz
 */
public class JComboBoxSelectedItemQuery_selectedItemOf_Test extends RobotBasedTestCase {

  private MyComboBox comboBox;

  @Override protected void onSetUp() {
    MyWindow window = MyWindow.createNew();
    comboBox = window.comboBox;
  }

  @Test
  public void should_return_selected_item_of_editable_JComboBox() {
    selectInJComboBox("Hello World");
    comboBox.startRecording();
    assertThat(JComboBoxSelectedItemQuery.selectedItemOf(comboBox)).isEqualTo("Hello World");
    comboBox.requireInvoked("getSelectedItem");
  }

  @Test
  public void should_return_selected_item_of_not_editable_JComboBox() {
    makeJComboBoxNotEditable();
    selectInJComboBox("one");
    comboBox.startRecording();
    assertThat(JComboBoxSelectedItemQuery.selectedItemOf(comboBox)).isEqualTo("one");
    comboBox.requireInvoked("getSelectedItem");
  }

  @RunsInEDT
  private void makeJComboBoxNotEditable() {
    setEditable(comboBox, false);
    robot.waitForIdle();
  }

  @RunsInEDT
  private void selectInJComboBox(String item) {
    setSelectedItem(comboBox, item);
    robot.waitForIdle();
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final MyComboBox comboBox = new MyComboBox("one", "two", "three");

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JComboBoxSelectedItemQuery_selectedItemOf_Test.class);
      add(comboBox);
    }
  }

  private static class MyComboBox extends JComboBox {
    private static final long serialVersionUID = 1L;

    private boolean recording;
    private final MethodInvocations methodInvocations = new MethodInvocations();

    MyComboBox(Object... items) {
      super(items);
      setEditable(true);
    }

    @Override public Object getSelectedItem() {
      if (recording) methodInvocations.invoked("getSelectedItem");
      return super.getSelectedItem();
    }

    void startRecording() { recording = true; }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
}
