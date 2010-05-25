/*
 * Created on May 24, 2010
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
import static org.fest.javafx.launcher.JavaFxClassLauncher.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import org.fest.javafx.scripts.ButtonDemo;
import org.fest.javafx.test.core.SequentialTestCase;
import org.fest.javafx.test.io.PrintStreamStub;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicNodePrinter#printNodes(java.io.PrintStream, NodeMatcher, Scene)}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicNodePrinter_printNodes_inSceneWithMatcher_Test extends SequentialTestCase {

  private PrintStreamStub out;
  private Scene root;
  private BasicNodePrinter printer;

  @Override protected void onSetUp() {
    root = sceneIn(launch(ButtonDemo.class));
    out = new PrintStreamStub();
    printer = new BasicNodePrinter();
  }

  @Test
  public void should_print_all_nodes_in_Scene() {
    printer.printNodes(out, new NodeMatcherByType(Button.class), root);
    String[] printed = out.printed();
    assertThat(printed).hasSize(1);
    assertThat(printed[0]).contains("javafx.scene.control.Button");
  }
}
