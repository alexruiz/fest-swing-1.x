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

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.*;

import org.fest.javafx.launcher.JavaFxClassLauncher;
import org.fest.javafx.scripts.ButtonDemo;
import org.fest.ui.testing.lock.ScreenLock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.SceneFromStageQuery.sceneIn;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;

/**
 * Tests for <code>{@link BasicNodeFinder#findById(javafx.scene.Scene, String, Visibility)}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicNodeFinder_findById_Test {

  private Scene scene;
  private BasicNodeFinder finder = new BasicNodeFinder();
  
  @Before
  public void setUp() {
    ScreenLock.instance().acquire(this);
    Stage stage = JavaFxClassLauncher.launch(ButtonDemo.class);
    scene = sceneIn(stage);
  }
  
  @After
  public void tearDown() {
    ScreenLock.instance().release(this);
  }
  
  @Test
  public void should_find_node_with_matching_id() {
    Node found = finder.findById(scene, "button", REQUIRE_VISIBLE);
    assertThat(found).isInstanceOf(Button.class);
  }
}
