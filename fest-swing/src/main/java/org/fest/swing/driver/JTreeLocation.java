/*
 * Created on Jan 12, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static java.lang.String.valueOf;
import static org.fest.util.Arrays.format;
import static org.fest.util.Strings.concat;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.exception.LocationUnavailableException;

/**
 * Understands a visible location on a <code>{@link JTree}</code>. A row index or a <code>{@link String}</code>ified
 * <code>{@link TreePath}</code> (i.e. each <code>{@link TreePath}</code> component is a <code>String</code>) or
 * a <code>{@link TreePath}</code> of <code>Object</code> may be used to indicate the location. Note that if a
 * <code>{@link TreePath}</code> is used, the entire path leading up to the designated node must be viewable at the
 * time the location is used.
 *
 * @author Alex Ruiz
 */
public final class JTreeLocation {

  /**
   * Converts the given row to an x, y coordinate.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param tree the target <code>JTree</code>.
   * @param row the given row.
   * @return the coordinates of the given row.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the <code>JTree</code>.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   */
  @RunsInCurrentThread
  public Point pointAt(JTree tree, int row) {
    Rectangle rowBounds = tree.getRowBounds(row);
    if (rowBounds != null) return pointAt(rowBounds);
    throw new LocationUnavailableException(concat("The tree row ", row, " is not visible"));
  }

  /**
   * Returns the path for the given row.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param tree the target <code>JTree</code>.
   * @param row the given row.
   * @return the path for the given row.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the <code>JTree</code>.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   */
  @RunsInCurrentThread
  public TreePath pathFor(JTree tree, int row) {
    TreePath path = tree.getPathForRow(validIndex(tree, row));
    if (path != null) return path;
    throw new LocationUnavailableException(concat("Unable to find tree path for row [", valueOf(row), "]"));
  }

  /**
   * Validates that the given row index is valid.
   * @param tree the target <code>JTree</code>.
   * @param row the row index to validate.
   * @return the validated row index.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the <code>JTree</code>.
   */
  @RunsInCurrentThread
  public int validIndex(JTree tree, int row) {
    int rowCount = tree.getRowCount();
    if (row >= 0 && row < rowCount) return row;
    throw new IndexOutOfBoundsException(concat(
        "The given row (", valueOf(row), ") should be greater than or equal to 0 and less than ", valueOf(rowCount)));
  }

  /**
   * Converts the given path to an x, y coordinate.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param tree the target <code>JTree</code>.
   * @param path the given path.
   * @return the coordinates of the given path.
   * @throws LocationUnavailableException if any part of the path is not visible.
   */
  @RunsInCurrentThread
  public Point pointAt(JTree tree, TreePath path) {
    Rectangle pathBounds = tree.getPathBounds(path);
    if (pathBounds != null) return pointAt(pathBounds);
    throw new LocationUnavailableException(concat("The tree path ", format(path.getPath()), " is not visible"));
  }

  private Point pointAt(Rectangle cellBounds) {
    return new Point(cellBounds.x + cellBounds.width / 2, cellBounds.y + cellBounds.height / 2);
  }
}
