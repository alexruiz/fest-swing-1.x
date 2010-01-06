/*
 * Created on Oct 14, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import static org.fest.swing.awt.AWT.ownerLessWindows;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Collections.list;

import java.awt.*;
import java.util.*;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands a monitor that maps event queues to GUI components and GUI components to event event queues.
 *
 * @author Alex Ruiz
 */
@ThreadSafe
class Context {

  /** Maps unique event queues to the set of root windows found on each queue. */
  @GuardedBy("lock") private final WindowEventQueueMapping windowEventQueueMapping;

  /** Maps components to their corresponding event queues. */
  @GuardedBy("lock") private final EventQueueMapping eventQueueMapping;

  private final Object lock = new Object();

  Context(Toolkit toolkit) {
    this(toolkit, new WindowEventQueueMapping(), new EventQueueMapping());
  }

  Context(Toolkit toolkit, WindowEventQueueMapping windowEventQueueMapping, EventQueueMapping eventQueueMapping) {
    this.windowEventQueueMapping = windowEventQueueMapping;
    this.eventQueueMapping = eventQueueMapping;
    this.windowEventQueueMapping.addQueueFor(toolkit);
  }

  /**
   * Return all available root windows. A root window is one that has a <code>null</code> parent. Nominally this means
   * a list similar to that returned by <code>{@link Frame#getFrames() Frame.getFrames()}</code>, but in the case of
   * an <code>{@link java.applet.Applet}</code> may return a few dialogs as well.
   * @return all available root windows.
   */
  Collection<Window> rootWindows() {
    Set<Window> rootWindows = new HashSet<Window>();
    synchronized (lock) {
      rootWindows.addAll(windowEventQueueMapping.windows());
    }
    rootWindows.addAll(list(Frame.getFrames()));
    rootWindows.addAll(list(ownerLessWindows()));
    return rootWindows;
  }

  EventQueue storedQueueFor(Component c) {
    synchronized (lock) {
      return eventQueueMapping.storedQueueFor(c);
    }
  }

  void removeContextFor(Component component) {
    synchronized (lock) {
      windowEventQueueMapping.removeMappingFor(component);
    }
  }

  void addContextFor(Component component) {
    synchronized (lock) {
      windowEventQueueMapping.addQueueFor(component);
      eventQueueMapping.addQueueFor(component);
    }
  }

  /**
   * Return the event queue corresponding to the given component. In most cases, this is the same as
   * <code>{@link java.awt.Toolkit#getSystemEventQueue()}</code>, but in the case of applets will bypass the
   * <code>AppContext</code> and provide the real event queue.
   * @param c the given component.
   * @return the event queue corresponding to the given component
   */
  @RunsInEDT
  EventQueue eventQueueFor(Component c) {
    Component component = topParentOf(c);
    synchronized (lock) {
      return eventQueueMapping.queueFor(component);
    }
  }

  @RunsInEDT
  private static Component topParentOf(final Component c) {
    return execute(new GuiQuery<Component>() {
      protected Component executeInEDT() {
        Component parent = c;
        // Components above the applet in the hierarchy may or may not share the same context with the applet itself.
        while (!(parent instanceof java.applet.Applet) && parent.getParent() != null)
          parent = parent.getParent();
        return parent;
      }
    });
  }

  /**
   * Returns all known event queues.
   * @return all known event queues.
   */
  Collection<EventQueue> allEventQueues() {
    Set<EventQueue> eventQueues = new HashSet<EventQueue>();
    synchronized (lock) {
      eventQueues.addAll(windowEventQueueMapping.eventQueues());
      eventQueues.addAll(eventQueueMapping.eventQueues());
    }
    return eventQueues;
  }
}
