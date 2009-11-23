/*
 * Created on Oct 31, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns an array of <code>String</code>s that
 * represents the contents of a given <code>{@link JComboBox}</code>.
 *
 * @author Alex Ruiz
 */
final class JComboBoxContentQuery {

  @RunsInEDT
  static String[] contents(final JComboBox comboBox, final JComboBoxCellReader cellReader) {
    return execute(new GuiQuery<String[]>() {
      protected String[] executeInEDT() {
        String[] values = new String[comboBox.getItemCount()];
        for (int i = 0; i < values.length; i++)
          values[i] = cellReader.valueAt(comboBox, i);
        return values;
      }
    });
  }

  private JComboBoxContentQuery() {}
}
