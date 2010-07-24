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

import static org.fest.swing.util.Strings.isDefaultToString;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.cell.JTreeCellReader;

/**
 * Understands the default implementation of <code>{@link JTreeCellReader}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BasicJTreeCellReader implements JTreeCellReader {

  private final CellRendererReader rendererReader;

  /**
   * Creates a new </code>{@link BasicJTreeCellReader}</code> that uses a
   * <code>{@link BasicCellRendererReader}</code> to read the value from the cell renderer component in a
   * <code>JTree</code>.
   */
  public BasicJTreeCellReader() {
    this(new BasicCellRendererReader());
  }

  /**
   * Creates a new </code>{@link BasicJTreeCellReader}</code>.
   * @param rendererReader knows how to read values from the cell renderer component in a
   * <code>JTree</code>.
   * @throws NullPointerException if <code>rendererReader</code> is {@code null}.
   */
  public BasicJTreeCellReader(CellRendererReader rendererReader) {
    if (rendererReader == null)
      throw new NullPointerException("CellRendererReader should not be null");
    this.rendererReader = rendererReader;
  }

  /**
   * Returns the internal value of a cell in a <code>{@link JTree}</code> as expected in a test.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param tree the given <code>JTree</code>.
   * @param modelValue the value of a cell, retrieved from the model.
   * @return the internal value of a cell in a <code>JTree</code> as expected in a test.
   */
  @RunsInCurrentThread
  public String valueAt(JTree tree, Object modelValue) {
    TreeCellRenderer r = tree.getCellRenderer();
    Component c = r.getTreeCellRendererComponent(tree, modelValue, false, false, false, 0, false);
    String value = (c != null) ? rendererReader.valueFrom(c) : null;
    if (value != null) return value;
    value= tree.convertValueToText(modelValue, false, false, false, 0, false);
    if (isDefaultToString(value)) return null;
    return value;
  }
}
