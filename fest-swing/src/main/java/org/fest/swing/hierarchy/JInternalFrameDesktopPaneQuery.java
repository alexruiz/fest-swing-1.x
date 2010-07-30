package org.fest.swing.hierarchy;

import javax.swing.*;
import javax.swing.JInternalFrame.JDesktopIcon;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action, executed in the event dispatch thread, that returns the desktop the given
 * <code>{@link JInternalFrame}</code> belongs to when iconified.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * @see JInternalFrame#getDesktopIcon()
 * @see JDesktopIcon#getDesktopPane()
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JInternalFrameDesktopPaneQuery {

  @RunsInCurrentThread
  static JDesktopPane desktopPaneOf(final JInternalFrame internalFrame) {
    JDesktopIcon icon = internalFrame.getDesktopIcon();
    if (icon != null) return icon.getDesktopPane();
    return null;
  }

  private JInternalFrameDesktopPaneQuery() {}
}