/*
 * Created on May 20, 2010
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
package org.fest.javafx.core;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.*;

import static org.fest.javafx.threading.GuiActionRunner.execute;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Understands a task, executed in the UI thread, that retrieves the <code>{@link Scene}</code> from a given
 * <code>{@link Stage}</code>.
 *
 * @author Alex Ruiz
 */
public final class SceneFromStageQuery {

  /**
   * Retrieves the <code>{@link Scene}</code> from a given <code>{@link Stage}</code>.
   * @param stage the given {@code Stage}.
   * @return the <code>{@link Scene}</code> retrieved from the given <code>{@link Stage}</code>.
   */
  @RunsInUIThread
  public static Scene sceneIn(final Stage stage) {
    return execute(new GuiQuery<Scene>() {
      @Override protected Scene executeInUIThread() {
        return stage.get$scene();
      }
    });
  }
  
  private SceneFromStageQuery() {}
}
