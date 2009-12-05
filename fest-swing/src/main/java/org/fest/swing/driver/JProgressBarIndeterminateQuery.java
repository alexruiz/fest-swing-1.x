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
 * Understands an action, executed in the event dispatch thread, that returns the value of the property "indeterminate"
 * in a <code>{@link JProgressBar}</code>.
 * @see JProgressBar#isIndeterminate()
 *
 * @author Alex Ruiz
 */
final class JProgressBarIndeterminateQuery {

  @RunsInEDT
  static boolean isIndeterminate(final JProgressBar progressBar) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return progressBar.isIndeterminate();
      }
    });
  }

  private JProgressBarIndeterminateQuery() {}
}
