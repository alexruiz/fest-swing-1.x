/*
 * Created on Apr 14, 2008
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

import static java.lang.String.valueOf;
import static org.fest.swing.driver.ModelValueToString.asText;

import java.awt.*;

import javax.swing.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.cell.JTableCellReader;

/**
 * Understands the default implementation of <code>{@link JTableCellReader}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BasicJTableCellReader implements JTableCellReader {

  private final CellRendererReader rendererReader;
  private final BasicJComboBoxCellReader comboBoxCellReader = new BasicJComboBoxCellReader();

  /**
   * Creates a new </code>{@link BasicJTableCellReader}</code> that uses a
   * <code>{@link BasicCellRendererReader}</code> to read the value from the cell renderer component in a
   * <code>JTable</code>.
   */
  public BasicJTableCellReader() {
    this(new BasicCellRendererReader());
  }

  /**
   * Creates a new </code>{@link BasicJTableCellReader}</code>.
   * @param reader knows how to read values from the cell renderer component in a
   * <code>JTable</code>.
   * @throws NullPointerException if <code>reader</code> is <code>null</code>.
   */
  public BasicJTableCellReader(CellRendererReader reader) {
    if (reader == null)
      throw new NullPointerException("CellRendererReader should not be null");
    this.rendererReader = reader;
  }

  /**
   * Returns the internal value of a cell in a <code>{@link JTable}</code> as expected in a test. This method first
   * tries to return the value displayed in the <code>JTable</code>'s cell renderer.
   * <ul>
   * <li>if the renderer is a <code>{@link JLabel}</code>, this method returns its text</li>
   * <li>if the renderer is a <code>{@link JComboBox}</code>, this method returns the value of its selection as a
   * <code>String</code></li>
   * <li>if the renderer is a <code>{@link JCheckBox}</code>, this method returns whether it is selected or not</li>
   * </ul>
   * If it fails reading the cell renderer, this method will get the value from the <code>toString</code> implementation
   * of the object stored in the <code>JTable</code>'s model at the specified indices.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param table the given <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the internal value of a cell in a <code>JTable</code> as expected in a test.
   */
  @RunsInCurrentThread
  public String valueAt(JTable table, int row, int column) {
    Component c = cellRendererIn(table, row, column);
    String value = (c != null) ? rendererReader.valueFrom(c) : null;
    if (value != null) return value;
    if (c instanceof JLabel) return ((JLabel)c).getText();
    if (c instanceof JCheckBox) return valueOf(((JCheckBox)c).isSelected());
    if (c instanceof JComboBox) return valueAsText((JComboBox)c);
    return asText(table.getValueAt(row, column));
  }

  private String valueAsText(JComboBox comboBox) {
    int selectedIndex = comboBox.getSelectedIndex();
    if (selectedIndex == -1) return null;
    return comboBoxCellReader.valueAt(comboBox, selectedIndex);
  }

  /**
   * Returns the font of the cell renderer for the given table cell.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param table the given <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the font of the cell renderer for the given table cell.
   */
  @RunsInCurrentThread
  public Font fontAt(JTable table, int row, int column) {
    Component c = cellRendererIn(table, row, column);
    return c != null ? c.getFont() : null;
  }

  /**
   * Returns the background color of the cell renderer for the given table cell.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param table the given <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the background color of the cell renderer for the given table cell.
   */
  @RunsInCurrentThread
  public Color backgroundAt(JTable table, int row, int column) {
    Component c = cellRendererIn(table, row, column);
    return c != null ? c.getBackground() : null;
  }

  /**
   * Returns the foreground color of the cell renderer for the given table cell.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param table the given <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the foreground color of the cell renderer for the given table cell.
   */
  @RunsInCurrentThread
  public Color foregroundAt(JTable table, int row, int column) {
    Component c = cellRendererIn(table, row, column);
    return c != null ? c.getForeground() : null;
  }

  @RunsInCurrentThread
  private Component cellRendererIn(final JTable table, final int row, final int column) {
    return table.prepareRenderer(table.getCellRenderer(row, column), row, column);
  }
}
