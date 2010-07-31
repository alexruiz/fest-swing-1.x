/*
 * Created on Jan 13, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.core;

import static javax.swing.SwingUtilities.convertRectangle;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.*;

import javax.swing.*;

import org.fest.swing.edt.GuiTask;

/**
 * Understands utility methods related to scrolling.
 *
 * @author Juhos Csaba-Zsolt
 *
 * @since 1.2
 */
public final class Scrolling {

  /**
   * Scrolls a <code>{@link JComponent}</code> into view within a container.
   * @param robot simulates user input.
   * @param c the given component.
   */
  public static void scrollToVisible(Robot robot, JComponent c) {
    JComponent root = findClosestValidatingRootAncestor(c);
    // scroll the component to view within each validating root ancestor, starting from the nearest
    while (root != null) {
      scrollToVisible(robot, root, c);
      // find the next validating root
      root = findClosestValidatingRootAncestor(root);
    }
  }

  /**
   * Returns a component's closest validating root ancestor in the AWT containment hierarchy.
   * @param c the given component.
   * @return the found ancestor or {@code null} if there isn't one.
   */
  private static JComponent findClosestValidatingRootAncestor(JComponent c) {
    // the candidate validating root at every iteration (candidate = not necessarily a root)
    Container root = c;
    // we go up to the top of the hierarchy
    while (root != null) {
      Container parent = root.getParent();
      // the new candidate root becomes the parent of the previous one
      root = parent;
      // if the candidate isn't a JComponent, we're not interested in it (we need JComponent#scrollRectToVisible)
      if (!(root instanceof JComponent)) continue;
      // we don't have to take JFrame into account, it's not a JComponent (ant it's a top-level container anyway)
      if (root instanceof JViewport || root instanceof JInternalFrame) return (JComponent) root;
    }
    return null;
  }

  /**
   * Scrolls a component into view within a container.
   * @param robot simulates user input.
   * @param container the given container.
   * @param target the given component.
   */
  private static void scrollToVisible(Robot robot, JComponent container, Component target) {
    Rectangle r = convertRectangle(target.getParent(), target.getBounds(), container);
    scrollToVisible(robot, container, r);
  }

  /**
   * Scrolls a rectangular region of a component into view.
   * @param robot simulates user input.
   * @param c the component.
   * @param rectangle the rectangular region.
   */
  private static void scrollToVisible(Robot robot, final JComponent c, final Rectangle rectangle) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        c.scrollRectToVisible(rectangle);
      }
    });
    robot.waitForIdle();
  }

  private Scrolling() {}
}
