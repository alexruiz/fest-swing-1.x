/*
 * Created on Feb 2, 2008
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

import static java.awt.Adjustable.*;

import java.awt.Point;
import java.util.*;

import javax.swing.JScrollBar;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a location in a <code>{@link JScrollBar}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class JScrollBarLocation {
  // TODO Test horizontal scroll bar

  private static final int BLOCK_OFFSET = 4;

  private static final Map<Integer, JScrollBarLocationStrategy> LOCATIONS = new HashMap<Integer, JScrollBarLocationStrategy>();

  static {
    LOCATIONS.put(HORIZONTAL, new HorizontalJScrollBarLocation());
    LOCATIONS.put(VERTICAL, new VerticalJScrollBarLocation());
  }

  /**
   * Returns the location where to move the mouse pointer to scroll to the given position.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param scrollBar the target <code>JScrollBar</code>.
   * @param position the position to scroll to.
   * @return the location where to move the mouse pointer to scroll to the given position.
   */
  @RunsInCurrentThread
  public Point thumbLocation(JScrollBar scrollBar, int position) {
    double fraction = (double) position / maximumMinusMinimum(scrollBar);
    return locationStrategyFor(scrollBar).thumbLocation(scrollBar, fraction);
  }

  @RunsInCurrentThread
  private int maximumMinusMinimum(JScrollBar scrollBar) {
    return scrollBar.getMaximum() - scrollBar.getMinimum();
  }

  /**
   * Returns the location where to move the mouse pointer to scroll one block up (or right.)
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param scrollBar the target <code>JScrollBar</code>.
   * @return the location where to move the mouse pointer to scroll one block up (or right.)
   */
  @RunsInCurrentThread
  public Point blockLocationToScrollUp(JScrollBar scrollBar) {
    Point p = unitLocationToScrollUp(scrollBar);
    int offset = BLOCK_OFFSET;
    return blockLocation(scrollBar, p, offset);
  }

  /**
   * Returns the location where to move the mouse pointer to scroll one block down (or left.)
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param scrollBar the target <code>JScrollBar</code>.
   * @return the location where to move the mouse pointer to scroll one block down (or left.)
   */
  @RunsInCurrentThread
  public Point blockLocationToScrollDown(JScrollBar scrollBar) {
    Point p = unitLocationToScrollDown(scrollBar);
    int offset = -BLOCK_OFFSET;
    return blockLocation(scrollBar, p, offset);
  }

  @RunsInCurrentThread
  private Point blockLocation(JScrollBar scrollBar, Point unitLocation, int offset) {
    return locationStrategyFor(scrollBar).blockLocation(scrollBar, unitLocation, offset);
  }

  /**
   * Returns the location where to move the mouse pointer to scroll one unit up (or right.)
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param scrollBar the target <code>JScrollBar</code>.
   * @return the location where to move the mouse pointer to scroll one unit up (or right.)
   */
  @RunsInCurrentThread
  public Point unitLocationToScrollUp(JScrollBar scrollBar) {
    int arrow = locationStrategyFor(scrollBar).arrow(scrollBar);
    return new Point(arrow / 2, arrow / 2);
  }

  /**
   * Returns the location where to move the mouse pointer to scroll one unit down (or left.)
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param scrollBar the target <code>JScrollBar</code>.
   * @return the location where to move the mouse pointer to scroll one unit down (or left.)
   */
  @RunsInCurrentThread
  public Point unitLocationToScrollDown(JScrollBar scrollBar) {
    return locationStrategyFor(scrollBar).unitLocationToScrollDown(scrollBar);
  }

  @RunsInCurrentThread
  private JScrollBarLocationStrategy locationStrategyFor(JScrollBar scrollBar) {
    return LOCATIONS.get(scrollBar.getOrientation());
  }
}
