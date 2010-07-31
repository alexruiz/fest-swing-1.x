package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the pop-up menu associated with a
 * <code>{@link JMenu}</code>.
 * @see JMenu#getPopupMenu()
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JMenuPopupMenuQuery {

  @RunsInEDT
  static JPopupMenu popupMenuOf(final JMenu menu) {
    return execute(new GuiQuery<JPopupMenu>() {
      @Override protected JPopupMenu executeInEDT() {
        return menu.getPopupMenu();
      }
    });
  }

  private JMenuPopupMenuQuery() {}
}