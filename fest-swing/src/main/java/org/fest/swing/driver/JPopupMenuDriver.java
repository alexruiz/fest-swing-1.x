/*
 * Created on Jan 31, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.driver.JPopupMenuElementsAsTextQuery.menuElementsAsText;

import java.awt.Container;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands functional testing of <code>{@link JPopupMenu}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JPopupMenuDriver extends JComponentDriver {

  /**
   * Creates a new </code>{@link JPopupMenuDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JPopupMenuDriver(Robot robot) {
    super(robot);
  }

  /**
   * Returns the contents of the pop-up menu as a <code>String</code> array.
   * @param popupMenu the target {@code JPopupMenu}.
   * @return the contents of the pop-up menu as a <code>String</code> array.
   */
  @RunsInEDT
  public String[] menuLabelsOf(JPopupMenu popupMenu) {
    return menuElementsAsText(popupMenu);
  }

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in the <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param popupMenu the target {@code JPopupMenu}.
   * @param name the name to match.
   * @return the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> having a matching name is found.
   */
  @RunsInEDT
  public JMenuItem menuItem(JPopupMenu popupMenu, String name) {
    return robot.finder().findByName(popupMenu, name, JMenuItem.class, false);
  }

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in the <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param popupMenu the target {@code JPopupMenu}.
   * @param matcher contains the search criteria for finding a <code>JMenuItem</code>.
   * @return the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> that matches the given search criteria
   * is found.
   */
  public JMenuItem menuItem(JPopupMenu popupMenu, GenericTypeMatcher<? extends JMenuItem> matcher) {
    return robot.finder().find(popupMenu, matcher);
  }
}
