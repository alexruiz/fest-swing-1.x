/*
 * Created on Nov 3, 2008
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

import static org.fest.swing.driver.JListItemIndexValidator.validateIndex;

import java.awt.Rectangle;

import javax.swing.JList;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action that returns the bounding rectangle for the cell specified by the given index.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * @see JList#getCellBounds(int, int)
 * 
 * @author Alex Ruiz
 */
final class JListCellBoundsQuery {

  @RunsInCurrentThread
  static Rectangle cellBounds(JList list, int index) {
    validateIndex(list, index);
    return list.getCellBounds(index, index);
  }

  private JListCellBoundsQuery() {}
}
