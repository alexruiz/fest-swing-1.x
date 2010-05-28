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
import javax.swing.JFrame;

import org.fest.javafx.annotations.RunsInCurrentThread;

/**
 * Understands utility methods related to the screen location of a node or control.
 *
 * @author Alex Ruiz
 */
public final class ScreenLocations {

  /**
   * Translates the given coordinates to the location on screen of the given <code>{@link Control}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the UI thread. Clients are responsible for
   * calling this method from the UI thread.
   * </p>
   * @param c the given {@code Control}.
   * @param p the given coordinates.
   * @return the translated coordinates.
   */
  @RunsInCurrentThread
  public static Point translateToScreenCoordinates(Control c, Point p) {
    return locationOnScreenOf(c, p);
  }

  private static Point locationOnScreenOf(Control c, Point p) {
    java.awt.Point screenLocation = windowScreenLocation(c);
    return p.translate(screenLocation.x, screenLocation.y);
  }

  @RunsInCurrentThread
  private static java.awt.Point windowScreenLocation(Control control) {
    JFrame w = Scenes.windowFrom(control.get$scene());
    return w.getContentPane().getLocationOnScreen();
  }

  private ScreenLocations() {}
}
