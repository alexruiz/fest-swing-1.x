/*
 * Created on Sep 9, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.test.task;

import static org.fest.swing.query.ComponentShowingQuery.isShowing;
import static org.fest.swing.timing.Pause.pause;

import java.awt.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.timing.Condition;

/**
 * Understands a task that makes a <code>{@link Frame}</code> visible. This task is <b>not</b> executed in the event
 * dispatch thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class FrameShowTask {

  @RunsInEDT
  public static void packAndShow(Frame frame, Dimension preferredSize) {
    frame.setPreferredSize(preferredSize);
    packAndShow(frame);
  }

  @RunsInEDT
  public static void packAndShow(Frame frame) {
    frame.pack();
    frame.setVisible(true);
  }

  @RunsInEDT
  public static void waitForShowing(final Frame frame) {
    pause(new Condition("Frame is showing") {
      @Override
      public boolean test() {
        return isShowing(frame);
      }
    }, 20000);
  }

  private FrameShowTask() {}
}
