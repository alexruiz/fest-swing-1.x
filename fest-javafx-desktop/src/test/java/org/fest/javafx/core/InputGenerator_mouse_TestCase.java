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
package org.fest.javafx.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.SceneFromStageQuery.sceneIn;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;
import static org.fest.javafx.launcher.GuiLauncher.launch;
import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.javafx.util.MousePointer.mousePointerOnScreen;
import static org.fest.javafx.util.Nodes.centerOf;
import static org.fest.javafx.util.Scenes.closeInUIThread;
import static org.fest.javafx.util.ScreenLocations.translateToScreenCoordinates;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.scripts.ButtonDemo;
import org.fest.javafx.test.core.SequentialTestCase;
import org.fest.javafx.threading.GuiQuery;
import org.fest.javafx.util.Point;

/**
 * Base test class for implementations of <code>{@link InputGenerator}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class InputGenerator_mouse_TestCase extends SequentialTestCase {

  private NodeFinder finder;
  private Scene scene;
  private Button button;
  private Point centerOfButton;
  private InputGenerator inputGenerator;

  @Override protected final void onSetUp() {
    finder = new BasicNodeFinder();
    scene = sceneIn(launch(ButtonDemo.class));
    button = nodeFinder().findByType(scene, Button.class, REQUIRE_VISIBLE);
    centerOfButton = calculateCenterOfButton();
    inputGenerator = createInputGenerator();
  }

  abstract InputGenerator createInputGenerator();

  @RunsInUIThread
  private Point calculateCenterOfButton() {
    return execute(new GuiQuery<Point>() {
      @Override protected Point executeInUIThread() {
        return centerOf(button());
      }
    });
  }

  @Override protected void onTearDown() {
    closeInUIThread(scene);
  }

  final Button button() {
    return button;
  }

  final Point centerOfButton() {
    return centerOfButton;
  }

  final void verifyMousePointerIsOverCenterOfButton() {
    Point expectedMousePointerLocation = translateToScreenCoordinates(button, centerOfButton);
    assertThat(mousePointerOnScreen()).isEqualTo(expectedMousePointerLocation);
  }

  final NodeFinder nodeFinder() { return finder; }
  final Scene scene() { return scene; }
  final InputGenerator inputGenerator() { return inputGenerator; }
}
