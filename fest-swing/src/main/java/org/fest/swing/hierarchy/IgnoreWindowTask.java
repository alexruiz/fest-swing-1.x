package org.fest.swing.hierarchy;

import java.awt.Window;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a task that given a <code>{@link WindowFilter}</code>, marks a given <code>{@link Window}</code> as
 * "ignored" if it is already implicitly ignored by such filter. This task should be executed in the event dispatch
 * thread.
 *
 * @author Alex Ruiz
 */
class IgnoreWindowTask implements Runnable {

  private final Window w;
  private final WindowFilter filter;

  IgnoreWindowTask(Window w, WindowFilter filter) {
    this.w = w;
    this.filter = filter;
  }

  @RunsInCurrentThread
  public void run() {
    // If the window was shown again since we queued this action, it will have removed the window from the
    // implicit filtered set, and we shouldn't filter.
    if (filter.isImplicitlyIgnored(w)) filter.ignore(w);
  }
}