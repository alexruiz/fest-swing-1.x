/*
 * Created on Dec 4, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JProgressBar;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the text of a
 * code>{@link JProgressBar}</code>.
 * @see JProgressBar#getString()
 *
 * @author Alex Ruiz
 */
final class JProgressBarStringQuery {

  @RunsInEDT
  static String stringOf(final JProgressBar progressBar) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        return progressBar.getString();
      }
    });
  }

  private JProgressBarStringQuery() {}
}
