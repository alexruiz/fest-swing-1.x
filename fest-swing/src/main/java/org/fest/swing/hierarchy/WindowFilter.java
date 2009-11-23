/*
 * Created on Oct 31, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static org.fest.swing.awt.AWT.isSharedInvisibleFrame;

import java.awt.Component;
import java.awt.Window;
import java.util.*;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a filter of windows to ignore in a component hierarchy.
 *
 * @author Alex Ruiz
 */
class WindowFilter {

  private final ParentFinder parentFinder;
  private final ChildrenFinder childrenFinder;

  WindowFilter() {
    this(new ParentFinder(), new ChildrenFinder());
  }

  WindowFilter(ParentFinder parentFinder, ChildrenFinder childrenFinder) {
    this.parentFinder = parentFinder;
    this.childrenFinder = childrenFinder;
  }

  // Map of components to ignore
  final Map<Component, Boolean> ignored = new WeakHashMap<Component, Boolean>();

  // Map of components implicitly ignored; these will be removed if they are re-shown.
  final Map<Component, Boolean> implicitlyIgnored = new WeakHashMap<Component, Boolean>();

  boolean isImplicitlyIgnored(Component c) {
    return implicitlyIgnored.containsKey(c);
  }

  @RunsInCurrentThread
  boolean isIgnored(Component c) {
    if (c == null) return false;
    // TODO if ("sun.plugin.ConsoleWindow".equals(c.getClass().getName())) return !trackAppletConsole;
    if (ignored.containsKey(c)) return true;
    if (c instanceof Window && isIgnored(c.getParent())) return true;
    return !(c instanceof Window) && isWindowIgnored(c);
  }

  private boolean isWindowIgnored(Component c) {
    Window w = parentFinder.windowFor(c);
    return w != null && isIgnored(w);
  }

  void implicitlyIgnore(Component c) {
    implicitlyIgnored.put(c, true);
  }

  @RunsInCurrentThread
  void ignore(Component c) {
    filter(c, true);
  }

  @RunsInCurrentThread
  void recognize(Component c) {
    filter(c, false);
  }

  Collection<Component> filtered() {
    return ignored.keySet();
  }

  private void filter(Component c, boolean ignore) {
    // Never filter the shared frame
    if (isSharedInvisibleFrame(c)) {
      for (Component child : childrenFinder.childrenOf(c))
        filter(child, ignore);
      return;
    }
    doFilter(c, ignore);
    implicitlyIgnored.remove(c);
    if (!(c instanceof Window)) return;
    for (Window owned : ((Window)c).getOwnedWindows())
      filter(owned, ignore);
  }

  private void doFilter(Component c, boolean ignore) {
    if (ignore) {
      ignored.put(c, true);
      return;
    }
    ignored.remove(c);
  }
}
