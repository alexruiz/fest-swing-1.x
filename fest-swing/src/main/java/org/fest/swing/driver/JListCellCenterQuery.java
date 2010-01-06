/*
 * Created on Jun 22, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.awt.AWT.*;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JList;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands calculation of the center of a cell in a <code>{@link JList}</code>.
 *
 * @author Alex Ruiz
 */
final class JListCellCenterQuery {

  /*
   * Sometimes the cell can be a lot longer than the JList (e.g. when a list item has long text and the JList is in
   * a JScrollPane.) In this case, we return the center of visible rectangle of the JList (issue FEST-65.)
   */
  @RunsInCurrentThread
  static Point cellCenter(JList list, Rectangle cellBounds) {
    Point cellCenter = centerOf(cellBounds);
    Point translatedCellCenter = translate(list, cellCenter.x, cellCenter.y);
    int listVisibleWidth = list.getVisibleRect().width;
    if (translatedCellCenter.x < listVisibleWidth) return cellCenter;
    Point listCenter = centerOfVisibleRect(list);
    return new Point(listCenter.x, cellCenter.y);
  }

  private JListCellCenterQuery() {}

}
