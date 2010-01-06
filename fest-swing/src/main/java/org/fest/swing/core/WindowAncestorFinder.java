/*
 * Created on Mar 30, 2008
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
package org.fest.swing.core;

import static org.fest.swing.awt.AWT.invokerOf;

import java.awt.Component;
import java.awt.Window;

import javax.swing.MenuElement;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.hierarchy.ComponentHierarchy;
import org.fest.swing.hierarchy.ExistingHierarchy;

/**
 * Understands lookup of a component's <code>{@link java.awt.Window ancestor}</code>.
 *
 * @author Yvonne Wang
 */
public final class WindowAncestorFinder {

  private static ComponentHierarchy hierarchy = new ExistingHierarchy();

  /**
   * Similar to <code>{@link javax.swing.SwingUtilities#getWindowAncestor(Component)}</code>, but returns the
   * <code>{@link Component}</code> itself if it is a <code>{@link Window}</code>, or the invoker's <code>Window</code>
   * if on a pop-up.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param c the <code>Component</code> to get the <code>Window</code> ancestor of.
   * @return the <code>Window</code> ancestor of the given <code>Component</code>, the <code>Component</code> itself if
   * it is a <code>Window</code>, or the invoker's <code>Window</code> if on a pop-up.
   */
  @RunsInCurrentThread
  public static Window windowAncestorOf(Component c) {
    if (c == null) return null;
    if (c instanceof Window) return (Window) c;
    if (c instanceof MenuElement) {
      Component invoker = invokerOf(c);
      if (invoker != null) return windowAncestorOf(invoker);
    }
    return windowAncestorOf(hierarchy.parentOf(c));
  }

  private WindowAncestorFinder() {}
}
