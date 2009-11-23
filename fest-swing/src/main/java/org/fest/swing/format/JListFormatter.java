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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.format;

import static java.lang.String.valueOf;
import static org.fest.swing.format.SwingIntEnums.SELECTION_MODES;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListModel;

import org.fest.util.Arrays;

/**
 * Understands a formatter for <code>{@link JList}</code>s.
 *
 * @author Yvonne Wang
 */
public class JListFormatter extends ComponentFormatterTemplate {

  /**
   * Returns the <code>String</code> representation of the given <code>{@link Component}</code>, which should be a
   * <code>{@link JList}</code> (or subclass.)
   * @param c the given <code>Component</code>.
   * @return the <code>String</code> representation of the given <code>JList</code>.
   */
  protected String doFormat(Component c) {
    JList list = (JList)c;
    return concat(
        list.getClass().getName(), "[",
        "name=",           quote(list.getName()),                        ", ",
        "selectedValues=", Arrays.format(list.getSelectedValues()),      ", ",
        "contents=",       Arrays.format(contentsOf(list)),              ", ",
        "selectionMode=",  SELECTION_MODES.get(list.getSelectionMode()), ", ",
        "enabled=",        valueOf(list.isEnabled()),                    ", ",
        "visible=",        valueOf(list.isVisible()),                    ", ",
        "showing=",        valueOf(list.isShowing()),
        "]"
    );
  }

  private Object[] contentsOf(JList list) {
    List<Object> contents = new ArrayList<Object>();
    ListModel model = list.getModel();
    int size = model.getSize();
    for (int i = 0; i < size; i++) contents.add(model.getElementAt(i));
    return contents.toArray();
  }

  /**
   * Indicates that this formatter supports <code>{@link JList}</code> only.
   * @return <code>JList.class</code>.
   */
  public Class<? extends Component> targetType() {
    return JList.class;
  }
}
