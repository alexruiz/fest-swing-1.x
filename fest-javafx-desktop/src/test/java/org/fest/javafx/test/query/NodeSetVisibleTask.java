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
package org.fest.javafx.test.query;

import static org.fest.javafx.threading.GuiActionRunner.execute;
import javafx.scene.Node;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiQuery;

/**
 * Understands an action, executed in the UI thread, that makes a <code>{@link Node}</code> visible or not visible.
 *
 * @author Alex Ruiz
 */
public class NodeSetVisibleTask {

  @RunsInUIThread
  public static boolean makeVisible(Node node) {
    return setVisible(node, true);
  }

  @RunsInUIThread
  public static boolean makeNotVisible(Node node) {
    return setVisible(node, false);
  }

  @RunsInUIThread
  private static boolean setVisible(final Node node, final boolean visible) {
    return execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInUIThread() {
        return node.set$visible(visible);
      }
    });
  }

  private NodeSetVisibleTask() {}
}
