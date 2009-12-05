/*
 * Created on Dec 4, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JProgressBar;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that sets value in a <code>{@link JProgressBar}</code>. This task is
 * executed in the event dispatch thread.
 *
 * @author Alex Ruiz
 */
final class JProgressBarSetValueTask {

  @RunsInEDT
  static void setValue(final JProgressBar progressBar, final int value) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        progressBar.setValue(value);
      }
    });
  }

  private JProgressBarSetValueTask() {}
}