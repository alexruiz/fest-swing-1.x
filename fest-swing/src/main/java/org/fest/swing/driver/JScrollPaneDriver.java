/*
 * Created on Sep 1, 2008
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

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.util.InternalApi;

/**
 * <p>
 * Supports functional testing of {@code JScrollPane}s.
 * </p>
 * 
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * {@link org.fest.swing.fixture} in your tests.
 * </p>
 * 
 * @author Yvonne Wang
 */
@InternalApi
public class JScrollPaneDriver extends JComponentDriver {
  /**
   * Creates a new {@link JScrollPaneDriver}.
   * 
   * @param robot the robot the robot to use to simulate user input.
   */
  public JScrollPaneDriver(@Nonnull Robot robot) {
    super(robot);
  }

  /**
   * Returns the horizontal {@code JScrollBar} in the given {@code JScrollPane}.
   * 
   * @param scrollPane the given {@code JScrollBar}.
   * @return the horizontal scroll bar in the given {@code JScrollBar}.
   */
  @RunsInEDT
  public @Nullable JScrollBar horizontalScrollBarIn(@Nonnull JScrollPane scrollPane) {
    return horizontalScrollBar(scrollPane);
  }

  @RunsInEDT
  private static @Nullable JScrollBar horizontalScrollBar(final @Nonnull JScrollPane scrollPane) {
    return execute(new GuiQuery<JScrollBar>() {
      @Override
      protected JScrollBar executeInEDT() {
        return scrollPane.getHorizontalScrollBar();
      }
    });
  }

  /**
   * Returns the vertical {@code JScrollBar} in the given {@code JScrollPane}.
   * 
   * @param scrollPane the given {@code JScrollBar}.
   * @return the vertical scroll bar in the given {@code JScrollBar}.
   */
  @RunsInEDT
  public @Nullable JScrollBar verticalScrollBarIn(@Nonnull JScrollPane scrollPane) {
    return verticalScrollBar(scrollPane);
  }

  @RunsInEDT
  private static @Nullable JScrollBar verticalScrollBar(final @Nonnull JScrollPane scrollPane) {
    return execute(new GuiQuery<JScrollBar>() {
      @Override
      protected JScrollBar executeInEDT() {
        return scrollPane.getVerticalScrollBar();
      }
    });
  }
}
