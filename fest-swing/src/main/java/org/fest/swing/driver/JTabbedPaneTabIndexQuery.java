/*
 * Created on Aug 22, 2008
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

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTabbedPane;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.TextMatcher;

/**
 * Understands an action that returns the index of a tab (in a <code>{@link JTabbedPane}</code>) whose title matches the
 * given text. This action returns -1 if a matching tab could not be found.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JTabbedPaneTabIndexQuery {

  @RunsInCurrentThread
  static int indexOfTab(final JTabbedPane tabbedPane, final TextMatcher matcher) {
    return execute(new GuiQuery<Integer>() {
      @Override
      protected Integer executeInEDT() {
        int tabCount = tabbedPane.getTabCount();
        for (int i = 0; i < tabCount; i++)
          if (matcher.isMatching(tabbedPane.getTitleAt(i))) return i;
        return -1;
      }
    });
  }

  private JTabbedPaneTabIndexQuery() {}
}