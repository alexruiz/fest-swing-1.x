/*
 * Created on Dec 7, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Component;

import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands simulation of user events on an item belonging to a fixture's <code>{@link Component}</code>.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public interface ItemFixture extends MouseInputSimulationFixture {

  /**
   * Simulates a user selecting this fixture's item.
   * @return this fixture.
   */
  ItemFixture select();

  /**
   * Returns the <code>String</code> representation of this fixture's item, or <code>null</code> if one can not be 
   * obtained.
   * @return the <code>String</code> representation of this fixture's item.
   */
  String value();

  /**
   * Simulates a user dragging this fixture's item.
   * @return this fixture.
   */
  ItemFixture drag();

  /**
   * Simulates a user dropping into this fixture's item.
   * @return this fixture.
   */
  ItemFixture drop();

  /**
   * Shows a pop-up menu using this fixture's item as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  JPopupMenuFixture showPopupMenu();
}