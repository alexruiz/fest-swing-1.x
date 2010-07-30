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
import static org.fest.util.Strings.*;

import java.awt.Component;
import java.util.*;

import javax.swing.JComboBox;

import org.fest.util.Arrays;

/**
 * Understands a formatter for <code>{@link JComboBox}</code>es.
 *
 * @author Yvonne Wang
 */
public class JComboBoxFormatter extends ComponentFormatterTemplate {

  /**
   * Returns the <code>String</code> representation of the given <code>{@link Component}</code>, which should be a
   * <code>{@link JComboBox}</code> (or subclass.)
   * @param c the given {@code Component}.
   * @return the <code>String</code> representation of the given {@code JComboBox}.
   */
  @Override
  protected String doFormat(Component c) {
    JComboBox comboBox = (JComboBox)c;
    return concat(
        comboBox.getClass().getName(), "[",
        "name=",         quote(comboBox.getName()),            ", ",
        "selectedItem=", quote(comboBox.getSelectedItem()),    ", ",
        "contents=",     Arrays.format(contentsOf(comboBox)),  ", ",
        "editable=",     valueOf(comboBox.isEditable()),       ", ",
        "enabled=",      valueOf(comboBox.isEnabled()),        ", ",
        "visible=",      valueOf(comboBox.isVisible()),        ", ",
        "showing=",      valueOf(comboBox.isShowing()),
        "]"
    );
  }

  private Object[] contentsOf(final JComboBox comboBox) {
    List<Object> contents = new ArrayList<Object>();
    int count = comboBox.getItemCount();
    for (int i = 0; i < count; i++) contents.add(comboBox.getItemAt(i));
    return contents.toArray();
  }

  /**
   * Indicates that this formatter supports <code>{@link JComboBox}</code> only.
   * @return <code>JComboBox.class</code>.
   */
  public Class<? extends Component> targetType() {
    return JComboBox.class;
  }
}
