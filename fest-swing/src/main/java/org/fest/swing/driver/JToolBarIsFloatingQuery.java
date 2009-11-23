package org.fest.swing.driver;

import static javax.swing.SwingUtilities.getWindowAncestor;

import java.awt.Frame;
import java.awt.Window;

import javax.swing.JToolBar;
import javax.swing.plaf.ToolBarUI;
import javax.swing.plaf.basic.BasicToolBarUI;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action, executed in the event dispatch thread, that indicates whether a 
 * <code>{@link JToolBar}</code> is floating or not.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
final class JToolBarIsFloatingQuery {
  
  @RunsInCurrentThread
  static boolean isJToolBarFloating(JToolBar toolBar) {
    ToolBarUI ui = toolBar.getUI();
    if (ui instanceof BasicToolBarUI) return ((BasicToolBarUI)ui).isFloating();
    // Have to guess; probably ought to check for sibling components
    Window w = getWindowAncestor(toolBar);
    return !(w instanceof Frame) && toolBar.getParent().getComponentCount() == 1;
  }
  
  private JToolBarIsFloatingQuery() {}
}