/*
 * Created on Jul 26, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.timing.Pause.pause;

import java.awt.Component;
import java.awt.event.*;
import java.util.Collection;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.timing.Condition;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicRobot#requireNoJOptionPaneIsShowing()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BasicRobot_requireNoJOptionPaneIsShowing_Test extends BasicRobot_TestCase {

  private JButton button;

  @RunsInEDT
  @Override
  void beforeShowingWindow() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        button = new JButton("Click Me");
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(window, "A Message");
          }
        });
        window.add(button);
      }
    });
  }

  @Test
  public void should_pass_if_no_JOptionPane_is_showing() {
    robot.requireNoJOptionPaneIsShowing();
  }

  @Test
  public void should_fail_if_a_JOptionPane_is_showing() {
    robot.click(button);
    pauseTillJOptionPaneIsShowing();
    try {
      robot.requireNoJOptionPaneIsShowing();
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("Expecting no JOptionPane to be showing");
    }
  }

  private void pauseTillJOptionPaneIsShowing() {
    pause(new Condition("JOptionPane is showing") {
      @Override
      public boolean test() {
        Collection<Component> found = robot.finder().findAll(new TypeMatcher(JOptionPane.class));
        return !found.isEmpty();
      }
    });
  }
}
