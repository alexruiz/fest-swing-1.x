/*
 * Created on Nov 26, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import javax.swing.JSplitPane;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands calculation of a valid position of a <code>{@link JSplitPane}</code>'s divider based on a desired
 * position for the divider. Implementations must respect the minimum sizes of the right and left component inside the
 * given <code>JSplitPane</code>.
 * <p>
 * Implementations must access <code>Component</code> properties in the current thread, not the EDT.
 * </p>
 *
 * @author Alex Ruiz
 */
interface JSplitPaneLocationCalculatorStrategy {

  @RunsInCurrentThread
  int calculateLocationToMoveDividerTo(JSplitPane splitPane, int desiredLocation);
}
