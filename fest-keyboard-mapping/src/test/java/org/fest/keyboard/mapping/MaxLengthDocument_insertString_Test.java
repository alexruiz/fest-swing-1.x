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

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.timing.Pause.pause;

import javax.swing.JTextField;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link MaxLengthDocument#insertString(int, String, javax.swing.text.AttributeSet)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@GUITest
public class MaxLengthDocument_insertString_Test extends RobotBasedTestCase {

  private FrameFixture frame;

  @Override protected void onSetUp() {
    frame = new FrameFixture(robot, MyWindow.createNew());
    frame.show();
  }

  @Test
  public void should_display_only_one_character() {
    frame.textBox().enterText("a");
    pause(100);
    frame.textBox().enterText("s")
                   .requireText("s");
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private final JTextField textField = new JTextField(20);

    @RunsInEDT
    private MyWindow() {
      super(MaxLengthDocument_insertString_Test.class);
      textField.setDocument(new MaxLengthDocument());
      addComponents(textField);
    }
  }
}
