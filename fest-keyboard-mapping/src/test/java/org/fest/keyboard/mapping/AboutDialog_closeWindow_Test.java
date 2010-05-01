/*
 * Created on May 1, 2010
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

import static org.fest.swing.finder.WindowFinder.findDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link AboutDialog}</code> that verify that the dialog is closed when the user presses the "Close"
 * button.
 *
 * @author Alex Ruiz
 */
@GUITest
public class AboutDialog_closeWindow_Test extends RobotBasedTestCase {

  private FrameFixture frame;

  @Override protected void onSetUp() {
    frame = new FrameFixture(robot, MyWindow.createNew());
    frame.show();
  }

  @Test
  public void should_close_dialog_when_button_is_pressed() {
    frame.button().click();
    DialogFixture aboutDialog = findDialog(AboutDialog.class).using(robot);
    aboutDialog.requireVisible();
    aboutDialog.button().click();
    aboutDialog.requireNotVisible();
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    static MyWindow createNew() {
      return GuiActionRunner.execute(new GuiQuery<MyWindow>() {
        @Override protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    @RunsInCurrentThread
    private MyWindow() {
      super(AboutDialog_closeWindow_Test.class);
      JButton button = new JButton("About");
      button.addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {
          AboutDialog d = new AboutDialog(MyWindow.this, true);
          d.setVisible(true);
        }
      });
      addComponents(button);
    }
  }
}
