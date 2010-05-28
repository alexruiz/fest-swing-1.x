/*
 * Created on May 4, 2010
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
package org.fest.javafx.threading;

import static javax.swing.SwingUtilities.isEventDispatchThread;
import static org.fest.ui.testing.exception.ActionFailedException.actionFailure;

import org.fest.ui.testing.exception.ActionFailedException;

/**
 * Understands a task that should be executed in the UI thread.
 *
 * @author Alex Ruiz
 */
public abstract class GuiTask extends GuiAction {

  /**
   * Runs this task action and verifies that it is executed in the UI thread.
   * @throws ActionFailedException if this task is not executed in the UI thread.
   */
  public final void run() {
    if (!isEventDispatchThread())
      throw actionFailure("Task should be executed in the UI thread");
    try {
      executeInUIThread();
    } catch (Throwable t) {
      catchedException(t);
    } finally {
      notifyExecutionCompleted();
    }
  }

  /**
   * Specifies the action to execute in the UI thread.
   * @throws Throwable any error thrown when executing an action in the UI thread.
   */
  protected abstract void executeInUIThread() throws Throwable;
}
