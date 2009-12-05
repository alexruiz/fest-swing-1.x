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
 * Understands a task that sets the property "indeterminate" in a <code>{@link JProgressBar}</code>. This task is
 * executed in the event dispatch thread.
 *
 * @author Alex Ruiz
 */
final class JProgressBarSetIndetermintateTask {

  @RunsInEDT
  static void setIntedeterminate(final JProgressBar progressBar, final boolean indeterminate) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        progressBar.setIndeterminate(indeterminate);
      }
    });
  }

  private JProgressBarSetIndetermintateTask() {}
}