/*
 * Created on May 30, 2010
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

import static org.fest.javafx.core.SceneFromStageQuery.sceneIn;
import static org.fest.javafx.launcher.GuiLauncher.launch;
import static org.fest.javafx.util.Scenes.close;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.fest.ui.testing.lock.ScreenLock;
import org.fest.util.VisibleForTesting;

/**
 * Understands the default implementation of <code>{@link Robot}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicRobot implements Robot {

  /**
   * Creates a new <code>{@link Robot}</code>. Before creating a new {@code Robot}, this method will first acquire
   * <code>{@link ScreenLock}</code> to serialize UI tests.
   * @return the created {@code Robot}.
   */
  public static Robot createRobot() {
    Object screenLockOwner = new Object();
    ScreenLock.instance().acquire(screenLockOwner);
    return new BasicRobot(screenLockOwner);
  }

  private final Object screenLockOwner;
  private final InputGenerator inputGenerator;
  private final BasicNodeHierarchy hierarchy;

  private BasicRobot(Object screenLockOwner) {
    this.screenLockOwner = screenLockOwner;
    inputGenerator = new AwtRobotInputGenerator();
    hierarchy = new BasicNodeHierarchy();
  }

  @VisibleForTesting
  final boolean ownsScreenLock() {
    return ScreenLock.instance().acquiredBy(screenLockOwner);
  }

  /** {@inheritDoc} */
  @Override public Stage launchGui(Class<?> guiSource) {
    Stage stage = launch(guiSource);
    hierarchy.addScene(sceneIn(stage));
    return stage;
  }

  /** {@inheritDoc} */
  @Override public void cleanUp() {
    try {
      for (Scene root : hierarchy.roots())
        close(root);
    } finally {
      ScreenLock.instance().release(screenLockOwner);
    }
  }
}
