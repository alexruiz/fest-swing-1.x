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

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Window;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that hides and disposes a <code>{@link Window}</code>.
 *
 * @author Alex Ruiz
 */
public final class WindowDestroyTask {

  /**
   * Hides and disposes the given <code>{@link Window}</code>. This action is executed in the event dispatch thread.
   * @param w the <code>Window</code> to hide and dispose.
   */
  @RunsInEDT
  public static void hideAndDisposeInEDT(final Window w) {
    if (w == null) return;
    execute(new GuiTask() {
      protected void executeInEDT() {
        hideAndDispose(w);
      }
    });
  }

  /**
   * Hides and disposes the given <code>{@link Window}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param w the <code>Window</code> to hide and dispose.
   */
  @RunsInCurrentThread
  public static void hideAndDispose(Window w) {
    w.setVisible(false);
    w.dispose();
  }

  private WindowDestroyTask() {}
}
