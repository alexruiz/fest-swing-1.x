/*
 * Created on Dec 24, 2007
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
package org.fest.swing.format;

import static java.lang.String.valueOf;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.Component;

import javax.swing.JTabbedPane;

import org.fest.util.Arrays;

/**
 * Understands a formatter for <code>{@link JTabbedPane}</code>s.
 *
 * @author Alex Ruiz
 */
public class JTabbedPaneFormatter extends ComponentFormatterTemplate {

  private static final String NO_SELECTION = "<No selection>";
  
  /**
   * Returns the <code>String</code> representation of the given <code>{@link Component}</code>, which should be a
   * <code>{@link JTabbedPane}</code> (or subclass.)
   * @param c the given <code>Component</code>.
   * @return the <code>String</code> representation of the given <code>JTabbedPane</code>.
   */
  protected String doFormat(Component c) {
    JTabbedPane tabbedPane = (JTabbedPane)c;
    return concat(
        tabbedPane.getClass().getName(), "[",
        "name=",             quote(tabbedPane.getName()),            ", ",
        "selectedTabIndex=", valueOf(tabbedPane.getSelectedIndex()), ", ",
        "selectedTabTitle=", selectedTab(tabbedPane),                ", ",
        "tabCount=",         valueOf(tabbedPane.getTabCount()),      ", ",
        "tabTitles=",        Arrays.format(tabTitles(tabbedPane)),   ", ",
        "enabled=",          valueOf(tabbedPane.isEnabled()),        ", ",
        "visible=",          valueOf(tabbedPane.isVisible()),        ", ",
        "showing=",          valueOf(tabbedPane.isShowing()),
        "]"
    );
  }

  private String selectedTab(JTabbedPane tabbedPane) {
    if (tabbedPane.getTabCount() == 0) return NO_SELECTION;
    int index = tabbedPane.getSelectedIndex();
    if (index == -1) return NO_SELECTION;
    return quote(tabbedPane.getTitleAt(index));
  }

  private String[] tabTitles(JTabbedPane tabbedPane) {
    int count = tabbedPane.getTabCount();
    if (count == 0) return new String[0];
    String[] titles = new String[count];
    for (int i = 0; i < count; i++) titles[i] = tabbedPane.getTitleAt(i);
    return titles;
  }

  /**
   * Indicates that this formatter supports <code>{@link JTabbedPane}</code> only.
   * @return <code>JTabbedPane.class</code>.
   */
  public Class<? extends Component> targetType() {
    return JTabbedPane.class;
  }
}
