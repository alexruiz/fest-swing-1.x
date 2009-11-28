/*
 * Created on Jul 31, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Point;

import javax.swing.JScrollBar;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a location in a horizontal <code>{@link JScrollBar}</code>.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class VerticalJScrollBarLocation extends JScrollBarLocationStrategy {

  @RunsInCurrentThread
  Point thumbLocation(JScrollBar scrollBar, double fraction) {
    int arrow = arrow(scrollBar);
    return new Point(arrow / 2, arrow + (int) (fraction * (scrollBar.getHeight() - 2 * arrow)));
  }

  @RunsInCurrentThread
  Point blockLocation(JScrollBar scrollBar, Point unitLocation, int offset) {
    Point p = new Point(unitLocation);
    p.y += offset;
    return p;
  }

  @RunsInCurrentThread
  Point unitLocationToScrollDown(JScrollBar scrollBar) {
    int arrow = arrow(scrollBar);
    return new Point(arrow / 2, scrollBar.getHeight() - arrow / 2);
  }

  @RunsInCurrentThread
  int arrow(JScrollBar scrollBar) {
    return scrollBar.getWidth();
  }
}
