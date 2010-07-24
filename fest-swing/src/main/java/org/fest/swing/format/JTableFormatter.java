/*
 * Created on Dec 23, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.format;

import static java.lang.String.valueOf;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.Component;

import javax.swing.JTable;

/**
 * Understands a formatter for <code>{@link JTable}</code>s.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableFormatter extends ComponentFormatterTemplate {

  /**
   * Returns the <code>String</code> representation of the given <code>{@link Component}</code>, which should be a
   * <code>{@link JTable}</code> (or subclass.)
   * @param c the given {@code Component}.
   * @return the <code>String</code> representation of the given <code>JTable</code>.
   */
  protected String doFormat(Component c) {
    JTable table = (JTable)c;
    return concat(
        table.getClass().getName(), "[",
        "name=",        quote(table.getName()),          ", ",
        "rowCount=",    valueOf(table.getRowCount()),    ", ",
        "columnCount=", valueOf(table.getColumnCount()), ", ",
        "enabled=",     valueOf(table.isEnabled()),      ", ",
        "visible=",     valueOf(table.isVisible()),      ", ",
        "showing=",     valueOf(table.isShowing()),
        "]"
    );
  }

  /**
   * Indicates that this formatter supports <code>{@link JTable}</code> only.
   * @return <code>JTable.class</code>.
   */
  public Class<? extends Component> targetType() {
    return JTable.class;
  }
}
