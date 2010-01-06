/*
 * Created on Mar 22, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import static java.lang.Boolean.TRUE;
import static org.fest.swing.query.ComponentParentQuery.parentOf;

import java.awt.*;
import java.util.*;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a mapping of unique event queues to the set of root windows found on each queue.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class WindowEventQueueMapping {

  final Map<EventQueue, Map<Window, Boolean>> queueMap = new WeakHashMap<EventQueue, Map<Window, Boolean>>();

  void addQueueFor(Toolkit toolkit) {
    queueMap.put(toolkit.getSystemEventQueue(), new WeakHashMap<Window, Boolean>());
  }

  void addQueueFor(Component component) {
    EventQueue queue = component.getToolkit().getSystemEventQueue();
    Map<Window, Boolean> windowMapping = queueMap.get(queue);
    if (windowMapping == null) windowMapping = createWindowMapping(queue);
    if (!(component instanceof Window) || parentOf(component) != null) return;
    windowMapping.put((Window)component, TRUE);
  }

  private Map<Window, Boolean> createWindowMapping(EventQueue queue) {
    Map<Window, Boolean> windowMapping = new WeakHashMap<Window, Boolean>();
    queueMap.put(queue, windowMapping);
    return windowMapping;
  }

  @RunsInCurrentThread
  void removeMappingFor(Component component) {
    EventQueue queue = component.getToolkit().getSystemEventQueue();
    removeComponent(component, queue);
    for (EventQueue q : queueMap.keySet()) removeComponent(component, q);
  }

  private void removeComponent(Component component, EventQueue queue) {
    Map<Window, Boolean> windowMapping = queueMap.get(queue);
    if (windowMapping != null) windowMapping.remove(component);
  }

  Collection<Window> windows() {
    Set<Window> rootWindows = new HashSet<Window>();
      for (EventQueue queue : queueMap.keySet())
        rootWindows.addAll(queueMap.get(queue).keySet());
    return rootWindows;
  }

  Collection<EventQueue> eventQueues() {
    return queueMap.keySet();
  }
}
