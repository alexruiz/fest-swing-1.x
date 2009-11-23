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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.cell;

import javax.swing.JTree;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands reading the internal value of a cell in a <code>{@link JTree}</code> as expected in a test.
 * <p>
 * <b>Note:</b> methods in this interface are <b>not</b> executed in the event dispatch thread (EDT.) Clients are 
 * responsible for invoking them in the EDT.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@RunsInCurrentThread
public interface JTreeCellReader {

  /**
   * Returns the internal value of a cell in a <code>{@link JTree}</code> as expected in a test.
   * <p>
   * <b>Note:</b> Implementations of this method should <b>not</b> use the event dispatch thread (EDT.) Clients are
   * responsible for invoking this method in the EDT.
   * </p>
   * @param tree the given <code>JTree</code>.
   * @param modelValue the value of a cell, retrieved from the model.
   * @return the internal value of a cell in a <code>JTree</code> as expected in a test.
   */
  String valueAt(JTree tree, Object modelValue);
}
