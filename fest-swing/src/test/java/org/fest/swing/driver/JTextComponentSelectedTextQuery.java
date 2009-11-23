package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the selected text of a
 * <code>{@link JTextComponent}</code>.
 * 
 * @author Alex Ruiz
 */
final class JTextComponentSelectedTextQuery {
  
  @RunsInEDT
  static String selectedTextOf(final JTextComponent textBox) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        return textBox.getSelectedText();
      }
    });
  }
  
  private JTextComponentSelectedTextQuery() {}
}