/*
 * Created on Oct 8, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;

/**
 * Understands the information collected by the monitors in this package.
 *
 * @author Alex Ruiz
 */
@ThreadSafe
class Windows {

  static int WINDOW_READY_DELAY = 10000;

  /** <code>{@link Window#isShowing() isShowing}</code> is true but are not yet ready for input. */
  @GuardedBy("lock") final Map<Window, TimerTask> pending = new WeakHashMap<Window, TimerTask>();

  /** Considered to be ready to use. */
  @GuardedBy("lock") final Map<Window, Boolean> open = new WeakHashMap<Window, Boolean>();

  /** Have sent a <code>{@link java.awt.event.WindowEvent#WINDOW_CLOSED WINDOW_CLOSED}</code> event. */
  @GuardedBy("lock") final Map<Window, Boolean> closed = new WeakHashMap<Window, Boolean>();

  /** Not visible. */
  @GuardedBy("lock") final Map<Window, Boolean> hidden = new WeakHashMap<Window, Boolean>();

  private final Timer windowReadyTimer;

  private final Object lock = new Object();

  Windows() {

    //windowReadyTimer = new Timer("Window Ready Timer", true);
	  windowReadyTimer = new Timer(WINDOW_READY_DELAY, null);
  }

  /**
   * Creates a new <code>{@link WindowVisibilityMonitor}</code> and attaches it to the given
   * <code>{@link Window}</code>.
   * @param target the {@code Window} to attach the new monitor to.
   */
  void attachNewWindowVisibilityMonitor(Window target) {
    WindowVisibilityMonitor monitor = new WindowVisibilityMonitor(this);
    target.addWindowListener(monitor);
    target.addComponentListener(monitor);
  }

  /**
   * Marks the given window as "ready to use" and if not showing, as "hidden."
   * @param w the given window.
   */
  @RunsInCurrentThread
  void markExisting(Window w) {
    synchronized(lock) {
      addWindowTo(w, open);
      if (!w.isShowing()) addWindowTo(w, hidden);
    }
  }

  /**
   * Marks the given window as "hidden."
   * @param w the given window.
   */
  void markAsHidden(Window w) {
    synchronized(lock) {
      addWindowTo(w, hidden);
      removeWindowFrom(w, pending);
    }
  }

  /**
   * Marks the given window as "showing."
   * @param w the given window.
   */
/*  void markAsShowing(final Window w) {
    synchronized(lock) {
      TimerTask task = new TimerTask() {
        public void run() { markAsReady(w); }
      };
      windowReadyTimer.schedule(new ProtectingTimerTask(task), WINDOW_READY_DELAY);
      pending.put(w, task);
    }
  }*/

  @RunsInEDT
  void markAsShowingInEDT(final Window w) {
	  synchronized(lock) {
		  final TimerTask task = new TimerTask() {
			public void run() {	markAsReady(w); }
		  };

		  windowReadyTimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					task.run();
				}});

		  windowReadyTimer.setCoalesce(false);
		  windowReadyTimer.setRepeats(false);
		  pending.put(w, task);
	  }
  }

  /**
   * Marks the given window as "ready to receive OS-level event input."
   * @param w the given window.
   */
  void markAsReady(Window w) {
    synchronized(lock) {
      if (!pending.containsKey(w)) return;
      removeWindowFrom(w, closed, hidden, pending);
      addWindowTo(w, open);
    }
  }

  /**
   * Marks the given window as "closed."
   * @param w the given window.
   */
  void markAsClosed(Window w) {
    synchronized(lock) {
      removeWindowFrom(w, open, hidden, pending);
      addWindowTo(w, closed);
    }
  }

  private void addWindowTo(Window w, Map<Window, Boolean> map) {
    map.put(w, true);
  }

  private void removeWindowFrom(Window w, Map<?, ?>... maps) {
    for (Map<?, ?> map : maps) map.remove(w);
  }

  /**
   * Returns {@code true} if the given component is a closed window.
   * @param c the given component.
   * @return {@code true} if the given component is a closed window, {@code false} otherwise.
   */
  boolean isClosed(Component c) {
    synchronized(lock) {
      return closed.containsKey(c);
    }
  }

  /**
   * Returns {@code true} if the given window is ready to receive OS-level event input.
   * @param w the given window.
   * @return {@code true} if the given window is ready to receive OS-level event input, {@code false}
   *         otherwise.
   */
  boolean isReady(Window w) {
    synchronized(lock) {
      return open.containsKey(w) && !hidden.containsKey(w);
    }
  }

  /**
   * Returns {@code true} if the given window is hidden.
   * @param w the given window.
   * @return {@code true} if the given window is hidden, {@code false} otherwise.
   */
  boolean isHidden(Window w) {
    synchronized(lock) {
      return hidden.containsKey(w);
    }
  }

  /**
   * Returns {@code true} if the given window is showing but not ready to receive OS-level event input.
   * @param w the given window.
   * @return {@code true} if the given window is showing but not not ready to receive OS-level event input,
   *         {@code false} otherwise.
   */
  boolean isShowingButNotReady(Window w) {
    synchronized(lock) {
      return pending.containsKey(w);
    }
  }
}
