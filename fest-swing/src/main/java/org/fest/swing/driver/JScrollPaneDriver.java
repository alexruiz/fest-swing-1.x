/*
 * Created on Sep 1, 2008
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

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands:
 * <ul>
 * <li>simulation of user input on a <code>{@link JScrollPane}</code> (if applicable)</li>
 * <li>state verification of a <code>{@link JScrollPane}</code></li>
 * </ul>
 * This class is intended for internal use only.
 *
 * @author Yvonne Wang
 */
public class JScrollPaneDriver extends JComponentDriver {

  /**
   * Creates a new </code>{@link JScrollPaneDriver}</code>.
   * @param robot the robot the robot to use to simulate user input.
   */
  public JScrollPaneDriver(Robot robot) {
    super(robot);
  }

  /**
   * Returns the horizontal <code>{@link JScrollBar}</code> in the given <code>{@link JScrollPane}</code>.
   * @param scrollPane the given <code>JScrollBar</code>.
   * @return the horizontal scroll bar in the given <code>JScrollBar</code>.
   */
  @RunsInEDT
  public JScrollBar horizontalScrollBarIn(JScrollPane scrollPane) {
    return horizontalScrollBar(scrollPane);
  }

  @RunsInEDT
  private static JScrollBar horizontalScrollBar(final JScrollPane scrollPane) {
    return execute(new GuiQuery<JScrollBar>() {
      protected JScrollBar executeInEDT() {
        return scrollPane.getHorizontalScrollBar();
      }
    });
  }

  /**
   * Returns the vertical <code>{@link JScrollBar}</code> in the given <code>{@link JScrollPane}</code>.
   * @param scrollPane the given <code>JScrollBar</code>.
   * @return the vertical scroll bar in the given <code>JScrollBar</code>.
   */
  @RunsInEDT
  public JScrollBar verticalScrollBarIn(JScrollPane scrollPane) {
    return verticalScrollBar(scrollPane);
  }

  @RunsInEDT
  private static JScrollBar verticalScrollBar(final JScrollPane scrollPane) {
    return execute(new GuiQuery<JScrollBar>() {
      protected JScrollBar executeInEDT() {
        return scrollPane.getVerticalScrollBar();
      }
    });
  }
}
