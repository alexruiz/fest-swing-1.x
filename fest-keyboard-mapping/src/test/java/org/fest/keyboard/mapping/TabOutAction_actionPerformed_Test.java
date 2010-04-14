/*
 * Created on Apr 14, 2010
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

import javax.swing.*;

import org.junit.Test;

import org.fest.swing.annotation.*;
import org.fest.swing.edt.*;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Tests for <code>{@link TabOutAction#actionPerformed(java.awt.event.ActionEvent)}</code>.
 *
 * @author Alex Ruiz
 */
@GUITest
public class TabOutAction_actionPerformed_Test extends RobotBasedTestCase {

  private FrameFixture frame;
  
  @Override protected void onSetUp() {
    frame = new FrameFixture(robot, MyWindow.createNew());
    frame.show();
  }

  @Test
  public void should_give_focus_to_next_component() {
    frame.button().requireFocused();
    frame.button().click();
    frame.textBox().requireFocused();
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JButton button = new JButton();
    final JTextField textField = new JTextField(20);
    
    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }   
    
    @RunsInCurrentThread
    private MyWindow() {
      super(TabOutAction_actionPerformed_Test.class);
      button.setAction(new TabOutAction());
      button.setText("Hello");
      addComponents(button, textField);
    }
  }
  
}
