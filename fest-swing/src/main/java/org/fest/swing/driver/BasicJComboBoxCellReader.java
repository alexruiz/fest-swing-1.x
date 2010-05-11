/*
 * Created on Apr 12, 2008
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

import static org.fest.swing.driver.ModelValueToString.asText;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Component;

import javax.swing.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands the default implementation of <code>{@link JComboBoxCellReader}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicJComboBoxCellReader implements JComboBoxCellReader {

  private static final JList REFERENCE_JLIST = newJList();

  private static JList newJList() {
    return execute(new GuiQuery<JList>() {
      protected JList executeInEDT() {
        return new JList();
      }
    });
  }

  private final CellRendererReader rendererReader;

  /**
   * Creates a new </code>{@link BasicJComboBoxCellReader}</code> that uses a
   * <code>{@link BasicCellRendererReader}</code> to read the value from the cell renderer component in a
   * <code>JComboBox</code>.
   */
  public BasicJComboBoxCellReader() {
    this(new BasicCellRendererReader());
  }

  /**
   * Creates a new </code>{@link BasicJComboBoxCellReader}</code>.
   * @param rendererReader knows how to read values from the cell renderer component in a
   * <code>JComboBox</code>.
   * @throws NullPointerException if <code>r</code> is <code>null</code>.
   */
  public BasicJComboBoxCellReader(CellRendererReader rendererReader) {
    if (rendererReader == null)
      throw new NullPointerException("CellRendererReader should not be null");
    this.rendererReader = rendererReader;
  }

  /**
   * Returns the internal value of a cell in a <code>{@link JComboBox}</code> as expected in a test.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param comboBox the given <code>JComboBox</code>.
   * @param index the index of the cell.
   * @return the internal value of a cell in a <code>JComboBox</code> as expected in a test.
   * @see CellRendererReader#valueFrom(Component)
   */
  @RunsInCurrentThread
  public String valueAt(JComboBox comboBox, int index) {
    Component c = cellRendererComponent(comboBox, index);
    String value = (c != null) ? rendererReader.valueFrom(c) : null;
    if (value != null) return value;
    return asText(comboBox.getItemAt(index));
  }

  @RunsInCurrentThread
  private Component cellRendererComponent(JComboBox comboBox, int index) {
    Object item = comboBox.getItemAt(index);
    ListCellRenderer renderer = comboBox.getRenderer();
    return renderer.getListCellRendererComponent(REFERENCE_JLIST, item, index, true, true);
  }
}
