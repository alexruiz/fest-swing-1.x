/*
 * Created on Nov 8, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.util.Platform.*;

import java.awt.*;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands locations of the control buttons in a window-like container.
 *
 * @author Alex Ruiz
 */
final class WindowLikeContainerLocations {

  private static final int MAXIMIZE_BUTTON_OFFSET = isOSX() ? 25 : isWindows() ? -20 : 0;

  /**
   * Identifies the coordinates of the 'close' button.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param c the target window-like <code>Container</code>.
   * @return the coordinates of the 'close' button.
   */
  @RunsInCurrentThread
  static Point closeLocationOf(Container c) {
    Insets insets = c.getInsets();
    if (isOSX()) return new Point(insets.left + 15, insets.top / 2);
    return new Point(c.getWidth() - insets.right - 10, insets.top / 2);
  }

  /**
   * Identifies the coordinates of the 'maximize' button.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param c the target window-like <code>Container</code>.
   * @return the coordinates of the 'maximize' button.
   */
  @RunsInCurrentThread
  static Point maximizeLocationOf(Container c) {
    Point p = iconifyLocationOf(c);
    p.x += MAXIMIZE_BUTTON_OFFSET;
    return p;
  }

  /**
   * Identifies the coordinates of the 'iconify' button, returning (0, 0) if not found.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param c the target window-like <code>Container</code>.
   * @return the coordinates of the 'iconify' button, returning (0, 0) if not found.
   */
  @RunsInCurrentThread
  static Point iconifyLocationOf(Container c) {
    Insets insets = c.getInsets();
    // From Abbot: We know the exact layout of the window manager frames for w32 and  OSX. Currently no way of detecting
    // the WM under X11. Maybe we could send a WM message (WM_ICONIFY)?
    Point p = new Point();
    p.y = insets.top / 2;
    if (isOSX()) p.x = 35;
    if (isWindows()) {
      int offset = isWindowsXP() ? 64 : 45;
      p.x = c.getWidth() - insets.right - offset;
    }
    return p;
  }

  private WindowLikeContainerLocations() {}
}
