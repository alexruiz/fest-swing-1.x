/*
 * Created on Nov 25, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.JSplitPane;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands calculation of a valid position of a vertical <code>{@link JSplitPane}</code>'s divider, while
 * respecting the minimum sizes of the right and left component inside the <code>JSplitPane</code>.
 *
 * @author Alex Ruiz
 */
final class VerticalJSplitPaneLocationCalculator {

  @RunsInCurrentThread
  static int calculateLocationToMoveDividerToVertically(final JSplitPane splitPane, final int desiredLocation) {
    int minimum = calculateMinimum(splitPane);
    int maximum = calculateMaximum(splitPane);
    if (maximum < minimum) minimum = maximum = 0;
    int newY = min(maximum, max(minimum, desiredLocation));
    return newY;
  }

  private static int calculateMinimum(JSplitPane splitPane) {
    Component left = splitPane.getLeftComponent();
    if (left == null || !left.isVisible()) return 0;
    int minimum = left.getMinimumSize().height;
    Insets insets = splitPane.getInsets();
    if (insets != null) minimum += insets.top;
    return minimum;
  }

  private static int calculateMaximum(JSplitPane splitPane) {
    Component right = splitPane.getRightComponent();
    if (splitPane.getLeftComponent() == null || right == null) return -1; // Don't allow dragging.
    Insets insets = splitPane.getInsets();
    int dividerSize = splitPane.getDividerSize();
    int maximum = 0;
    int bottom = (insets != null) ? insets.bottom : 0;
    if (right.isVisible())
      maximum = max(0, splitPane.getSize().height - (dividerSize + bottom) - right.getMinimumSize().height);
    else
      maximum = max(0, splitPane.getSize().height - (dividerSize + bottom));
    return maximum;
  }

  private VerticalJSplitPaneLocationCalculator() {}
}
