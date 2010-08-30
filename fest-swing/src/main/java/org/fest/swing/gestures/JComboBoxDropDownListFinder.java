/*
 * Created on Oct 22, 2008
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
package org.fest.swing.gestures;

import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.util.TimeoutWatch.startWatchWithTimeoutOf;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.*;
import org.fest.swing.util.TimeoutWatch;

/**
 * Looks up the <code>{@link JList}</code> in the pop-up raised by a <code>{@link JComboBox}</code>, if the LAF actually
 * uses one.
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
final class JComboBoxDropDownListFinder {

  static final ComponentMatcher LIST_MATCHER = new TypeMatcher(JList.class);

  private final Robot robot;

  JComboBoxDropDownListFinder(Robot robot) {
    this.robot = robot;
  }

  /**
   * Finds the <code>{@link JList}</code> in the pop-up raised by a <code>{@link JComboBox}</code>, if the LAF actually
   * uses one.
   * @return the found <code>JList</code>, or {@code null} if a drop-down list cannot be found.
   */
  @RunsInEDT
  JList findDropDownList() {
    JPopupMenu popup = robot.findActivePopupMenu();
    if (popup == null) {
      TimeoutWatch watch = startWatchWithTimeoutOf(robot.settings().timeoutToFindPopup());
      popup = robot.findActivePopupMenu();
      while (popup == null) {
        if (watch.isTimeOut()) return null;
        pause();
        popup = robot.findActivePopupMenu();
      }
    }
    return findListIn(popup);
  }

  private JList findListIn(Container parent) {
    List<Component> found = new ArrayList<Component>(robot.finder().findAll(parent, LIST_MATCHER));
    if (found.size() != 1) return null;
    return (JList)found.get(0);
  }
}
