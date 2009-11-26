/*
 * Created on Nov 26, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.*;

import javax.swing.JSplitPane;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a template for implementations of <code>{@link JSplitPaneLocationCalculatorStrategy}</code>.
 *
 * @author Alex Ruiz
 */
abstract class JSplitPaneLocationCalculatorTemplate implements JSplitPaneLocationCalculatorStrategy {

  @RunsInCurrentThread
  public final int calculateLocationToMoveDividerTo(JSplitPane splitPane, int desiredLocation) {
    int minimum = calculateMinimum(splitPane);
    int maximum = calculateMaximum(splitPane);
    if (maximum < minimum) minimum = maximum = 0;
    return min(maximum, max(minimum, desiredLocation));
  }

  private int calculateMinimum(JSplitPane splitPane) {
    Component leftComponent = splitPane.getLeftComponent();
    if (leftComponent == null || !leftComponent.isVisible()) return 0;
    int minimum = widthOrHeightOf(leftComponent.getMinimumSize());
    Insets insets = splitPane.getInsets();
    if (insets != null) minimum += leftOrTopOf(insets);
    return minimum;
  }

  abstract int leftOrTopOf(Insets insets);

  private int calculateMaximum(JSplitPane splitPane) {
    Component rightComponent = splitPane.getRightComponent();
    if (splitPane.getLeftComponent() == null || rightComponent == null) return -1; // Don't allow dragging.
    Insets insets = splitPane.getInsets();
    int dividerSize = splitPane.getDividerSize();
    int inset = (insets != null) ? rightOrBottomOf(insets) : 0;
    int splitPaneWidthOrHeight = widthOrHeightOf(splitPane.getSize());
    if (rightComponent.isVisible())
      return max(0, splitPaneWidthOrHeight - (dividerSize + inset) - widthOrHeightOf(rightComponent.getMinimumSize()));
    return max(0, splitPaneWidthOrHeight - (dividerSize + inset));
  }

  abstract int rightOrBottomOf(Insets insets);
  abstract int widthOrHeightOf(Dimension size);
}
