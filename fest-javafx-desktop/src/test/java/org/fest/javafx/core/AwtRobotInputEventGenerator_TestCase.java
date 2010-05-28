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

import static org.fest.javafx.core.SceneFromStageQuery.sceneIn;
import static org.fest.javafx.launcher.GuiLauncher.launch;
import static org.fest.javafx.util.Scenes.close;
import javafx.scene.Scene;

import org.fest.javafx.scripts.ButtonDemo;
import org.fest.javafx.test.core.SequentialTestCase;

/**
 * Base test class for <code>{@link AwtRobotInputEventGenerator}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class AwtRobotInputEventGenerator_TestCase extends SequentialTestCase {

  private NodeFinder finder;
  private AwtRobotInputEventGenerator inputEventGenerator;
  private Scene scene;

  @Override protected final void onSetUp() {
    finder = new BasicNodeFinder();
    inputEventGenerator = new AwtRobotInputEventGenerator();
    scene = sceneIn(launch(ButtonDemo.class));
  }

  @Override protected void onTearDown() {
    close(scene);
  }

  final NodeFinder nodeFinder() { return finder; }
  final AwtRobotInputEventGenerator inputEventGenerator() { return inputEventGenerator; }
  final Scene scene() { return scene; }
}
