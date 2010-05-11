/*
 * Created on Jan 31, 2008
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
package org.fest.swing.driver;

import static org.fest.swing.query.ComponentShowingQuery.isShowing;

import java.awt.Component;

import javax.swing.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;

/**
 * Understands the location of a <code>{@link JMenuItem}</code>.
 *
 * @author Alex Ruiz
 */
public final class JMenuItemLocation {

  private Component parentOrInvoker;
  private JPopupMenu parentPopup;

  private final boolean inMenuBar;

  /**
   * Creates a new </code>{@link JMenuItemLocation}</code>.
   * <p>
   * <b>Note:</b> This constructor is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients
   * are responsible for ensuring that this constructor is executed in the EDT.
   * </p>
   * @param menuItem the target <code>JMenuItem</code>.
   */
  @RunsInCurrentThread
  public JMenuItemLocation(JMenuItem menuItem) {
    parentOrInvoker = menuItem.getParent();
    if (parentOrInvoker instanceof JPopupMenu) {
      parentPopup = (JPopupMenu)parentOrInvoker;
      parentOrInvoker = ((JPopupMenu)parentOrInvoker).getInvoker();
    }
    inMenuBar = parentOrInvoker instanceof JMenuBar;
  }

  /**
   * Indicates whether the <code>{@link JMenuItem}</code> is in a <code>{@link JMenuBar}</code>.
   * @return <code>true</code> if the <code>JMenuItem</code> is in a <code>JMenuBar</code>, <code>false</code>
   * otherwise.
   */
  public boolean inMenuBar() {
    return inMenuBar;
  }

  /**
   * Indicates whether the parent of the <code>{@link JMenuItem}</code> is another menu.
   * @return <code>true</code> if the parent of the <code>JMenuItem</code> is another menu, <code>false</code>
   * otherwise.
   */
  @RunsInEDT
  public boolean isParentAMenu() {
    if (!(parentOrInvoker instanceof JMenuItem)) return false;
    return parentPopup == null || !isShowing(parentPopup);
  }

  /**
   * Returns the parent of <code>{@link JMenuItem}</code>, or its invoker (if it is in a pop-up.)
   * @return the parent or the invoker of the <code>JMenuItem</code>.
   */
  public Component parentOrInvoker() {
    return parentOrInvoker;
  }

  /**
   * Returns the parent pop-up menu, or <code>null</code> if the <code>{@link JMenuItem}</code> is not in a pop-up.
   * @return the parent pop-up menu, or <code>null</code> if the <code>JMenuItem</code> is not in a pop-up.
   */
  public JPopupMenu parentPopup() {
    return parentPopup;
  }
}
