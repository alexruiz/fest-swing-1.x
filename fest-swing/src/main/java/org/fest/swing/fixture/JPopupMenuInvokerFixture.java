/*
 * Created on Mar 1, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JPopupMenu;

import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands input simulation on <code>{@link Component}</code>s capable of invoking
 * <code>{@link JPopupMenu}</code>s.
 *
 * @author Alex Ruiz
 */
public interface JPopupMenuInvokerFixture {

  /**
   * Shows a pop-up menu using this fixture's <code>{@link Component}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>Component</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>Component</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  JPopupMenuFixture showPopupMenu();

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link Component}</code> as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>Component</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>Component</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  JPopupMenuFixture showPopupMenuAt(Point p);
}
