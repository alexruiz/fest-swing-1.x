/*
 * Created on Aug 10, 2008
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

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTabbedPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the titles of the tabs in a
 * <code>{@link JTabbedPane}</code>.
 * @see JTabbedPane#getTitleAt(int)
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JTabbedPaneTabTitlesQuery {

  @RunsInEDT
  static String[] tabTitlesOf(final JTabbedPane tabbedPane) {
    return execute(new GuiQuery<String[]>() {
      protected String[] executeInEDT() {
        int count = tabbedPane.getTabCount();
        String[] titles = new String[count];
        for (int i = 0; i < count; i++) titles[i] = tabbedPane.getTitleAt(i);
        return titles;
      }
    });
  }

  private JTabbedPaneTabTitlesQuery() {}
}