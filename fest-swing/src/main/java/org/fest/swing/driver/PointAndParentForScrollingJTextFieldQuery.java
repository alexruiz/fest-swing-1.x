/*
 * Created on Oct 21, 2008
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

import java.awt.*;

import javax.swing.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.util.Pair;

/**
 * Understands an action that returns the point and parent to use as a reference when scrolling a
 * <code>{@link JTextField}</code> up or down.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class PointAndParentForScrollingJTextFieldQuery {

  @RunsInCurrentThread
  static Pair<Point, Container> pointAndParentForScrolling(final JTextField textField) {
    Point origin = new Point(textField.getX(), textField.getY());
    Container parent = textField.getParent();
    while (parent != null && !(parent instanceof JComponent) && !(parent instanceof CellRendererPane)) {
      origin = addRectangleToPoint(parent.getBounds(), origin);
      parent = parent.getParent();
    }
    return new Pair<Point, Container>(origin, parent);
  }

  private static Point addRectangleToPoint(Rectangle r, Point p) {
    Point newPoint = new Point(p);
    newPoint.x += r.x;
    newPoint.y += r.y;
    return newPoint;
  }

  private PointAndParentForScrollingJTextFieldQuery() {}
}
