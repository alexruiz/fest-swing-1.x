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

import java.awt.Component;

import javax.swing.JList;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.cell.JListCellReader;

/**
 * Understands the default implementation of <code>{@link JListCellReader}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicJListCellReader implements JListCellReader {

  private final CellRendererReader rendererReader;

  /**
   * Creates a new </code>{@link BasicJListCellReader}</code> that uses a
   * <code>{@link BasicCellRendererReader}</code> to read the value from the cell renderer component in a
   * <code>JList</code>.
   */
  public BasicJListCellReader() {
    this(new BasicCellRendererReader());
  }

  /**
   * Creates a new </code>{@link BasicJListCellReader}</code>.
   * @param rendererReader knows how to read values from the cell renderer component in a
   * <code>JList</code>.
   * @throws NullPointerException if <code>rendererReader</code> is <code>null</code>.
   */
  public BasicJListCellReader(CellRendererReader rendererReader) {
    if (rendererReader == null)
      throw new NullPointerException("CellRendererReader should not be null");
    this.rendererReader = rendererReader;
  }

  /**
   * Returns the internal value of a cell in a <code>{@link JList}</code> as expected in a test.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param list the given <code>JList</code>.
   * @param index the index of the cell.
   * @return the internal value of a cell in a <code>JList</code> as expected in a test.
   * @see CellRendererReader#valueFrom(Component)
   */
  @RunsInCurrentThread
  public String valueAt(JList list, int index) {
    Object element = list.getModel().getElementAt(index);
    Component c = list.getCellRenderer().getListCellRendererComponent(list, element, index, true, true);
    String value = (c != null) ? rendererReader.valueFrom(c) : null;
    if (value != null) return value;
    return asText(element);
  }
}
