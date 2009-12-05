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
import org.fest.swing.util.Pair;

/**
 * Understands an action, executed in the event dispatch thread, that returns the minimum and maximum values of a
 * <code>{@link JProgressBar}</code>.
 *
 * @author Alex Ruiz
 */
final class JProgressBarMinimumAndMaximumQuery {

  @RunsInEDT
  static Pair<Integer, Integer> minimumAndMaximumOf(final JProgressBar progressBar) {
    return execute(new GuiQuery<Pair<Integer, Integer>>() {
      protected Pair<Integer, Integer> executeInEDT() {
        return new Pair<Integer, Integer>(progressBar.getMinimum(), progressBar.getMaximum());
      }
    });
  }

  private JProgressBarMinimumAndMaximumQuery() {}
}
