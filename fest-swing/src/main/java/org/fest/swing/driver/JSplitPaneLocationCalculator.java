/*
 * Created on Nov 25, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import static org.fest.swing.driver.VerticalJSplitPaneLocationCalculator.calculateLocationToMoveDividerToVertically;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JSplitPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands calculation of a valid position of a <code>{@link JSplitPane}</code>'s divider, while respecting the
 * minimum sizes of the right and left component inside the <code>JSplitPane</code>.
 *
 * @author Alex Ruiz
 */
final class JSplitPaneLocationCalculator {

  @RunsInEDT
  static int calculateLocationToMoveDividerTo(final JSplitPane splitPane, final int desiredLocation) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        if (splitPane.getOrientation() == VERTICAL_SPLIT)
          return calculateLocationToMoveDividerToVertically(splitPane, desiredLocation);
        return desiredLocation;
      }
    });
  }

  private JSplitPaneLocationCalculator() {}
}
