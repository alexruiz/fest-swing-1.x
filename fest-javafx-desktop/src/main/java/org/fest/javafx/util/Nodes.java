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
package org.fest.javafx.util;

import javafx.geometry.Bounds;
import javafx.scene.Node;

import org.fest.javafx.annotations.RunsInCurrentThread;

/**
 * Understands utility methods related to <code>{@link Node}</code>.
 *
 * @author Alex Ruiz
 */
public final class Nodes {

  /**
   * Returns the coordinates of the center of the given <code>{@link Node}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the UI thread. Clients are responsible for
   * calling this method from the UI thread.
   * </p>
   * @param n the given {@code Node}.
   * @return the coordinates, relative to the screen, of the given {@code Node}.
   */
  @RunsInCurrentThread
  public static Point centerOf(Node n) {
    Bounds bounds = n.get$boundsInLocal();
    return new Point(n.localToScene(bounds.get$width() / 2, bounds.get$height() / 2));
  }

  private Nodes() {}
}
