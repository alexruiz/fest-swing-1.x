/*
 * Created on May 27, 2010
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
package org.fest.javafx.util;

import static org.fest.javafx.threading.GuiActionRunner.execute;

import javax.swing.JFrame;

import org.fest.javafx.annotations.RunsInCurrentThread;
import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiTask;

import com.sun.javafx.tk.TKStage;
import com.sun.javafx.tk.swing.FrameStage;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Understands utility methods related to <code>{@link Scene}</code>.
 *
 * @author Alex Ruiz
 */
public final class Scenes {

  /**
   * Closes the <code>{@link Stage}</code> of the given <code>{@link Scene}</code>. This method is executed in the UI
   * thread.
   * @param scene the given {@code Scene}.
   */
  @RunsInUIThread
  public static void closeInUIThread(final Scene scene) {
    execute(new GuiTask() {
      @Override protected void executeInUIThread() {
        close(scene);
      }
    });
  }

  /**
   * Closes the <code>{@link Stage}</code> of the given <code>{@link Scene}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the UI thread. Clients are responsible for
   * calling this method from the UI thread.
   * </p>
   * @param scene the given {@code Scene}.
   */
  @RunsInCurrentThread
  public static void close(final Scene scene) {
    JFrame frame = windowFrom(scene);
    frame.setVisible(true);
    frame.dispose();
  }

  @RunsInCurrentThread
  static JFrame windowFrom(Scene scene) {
    TKStage peer = scene.get$stage().get$Stage$impl_peer();
    FrameStage frameStage = (FrameStage)peer;
    return (JFrame) frameStage.window;
  }

  private Scenes() {}
}
