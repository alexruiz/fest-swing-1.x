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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.SceneFromStageQuery.sceneIn;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;
import static org.fest.javafx.launcher.GuiLauncher.launch;
import static org.fest.javafx.util.Scenes.closeInUIThread;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import org.fest.javafx.scripts.ButtonDemo;
import org.fest.javafx.test.core.SequentialTestCase;
import org.fest.ui.testing.annotation.GuiTest;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicNodeFinder#findById(javafx.scene.Scene, String, Visibility)}</code>.
 *
 * @author Alex Ruiz
 */
@GuiTest
public class BasicNodeFinder_findById_Test extends SequentialTestCase {

  private Scene scene;
  private BasicNodeFinder finder;

  @Override protected void onSetUp() {
    scene = sceneIn(launch(ButtonDemo.class));
    finder = new BasicNodeFinder();
  }

  @Override protected void onTearDown() {
    closeInUIThread(scene);
  }

  @Test
  public void should_find_node_with_matching_id() {
    Node found = finder.findById(scene, "button", REQUIRE_VISIBLE);
    assertThat(found).isInstanceOf(Button.class);
  }
}
