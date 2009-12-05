/*
 * Created on Dec 2, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JProgressBar;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the value of a
 * <code>{@link JProgressBar}</code>.
 * @see JProgressBar#getValue()
 *
 * @author Alex Ruiz
 */
final class JProgressBarValueQuery {

  @RunsInEDT
  static int valueOf(final JProgressBar progressBar) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        return progressBar.getValue();
      }
    });
  }

  private JProgressBarValueQuery() {}
}
