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


import javafx.scene.control.Control;
import javafx.stage.Stage;

import javax.swing.JFrame;

import com.sun.javafx.tk.TKStage;
import com.sun.javafx.tk.swing.FrameStage;

/**
 * Understands utility methods related to the location of a node or control.
 *
 * @author Alex Ruiz
 */
public final class Location {

  /**
   * Returns the coordinates of the center of the given control. The coordinates are relative to the screen.
   * @param control the given control.
   * @return the coordinates, relative to the screen, of the given control.
   */
  public static Point centerOf(Control control) {
    Point center = new Point(control.localToScene(control.get$width() / 2, control.get$height() / 2));
    JFrame w = windowFrom(control.get$scene().get$stage());
    java.awt.Point screenLocation = w.getContentPane().getLocationOnScreen();
    return center.translate(screenLocation.x, screenLocation.y);
  }

  private static JFrame windowFrom(Stage stage) {
    TKStage peer = stage.get$Stage$impl_peer();
    FrameStage frameStage = (FrameStage)peer;
    return (JFrame) frameStage.window;
  }

  private Location() {}
}
