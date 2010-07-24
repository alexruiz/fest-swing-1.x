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
import static javax.swing.JFileChooser.*;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.Component;

import javax.swing.JFileChooser;

/**
 * Understands a formatter for <code>{@link JFileChooser}</code>s.
 *
 * @author Yvonne Wang
 */
public class JFileChooserFormatter extends ComponentFormatterTemplate {

  private static final IntEnum DIALOG_TYPES = new IntEnum();
  static {
    DIALOG_TYPES.put(OPEN_DIALOG,   "OPEN_DIALOG")
                .put(SAVE_DIALOG,   "SAVE_DIALOG")
                .put(CUSTOM_DIALOG, "CUSTOM_DIALOG");
  }

  /**
   * Returns the <code>String</code> representation of the given <code>{@link Component}</code>, which should be a
   * <code>{@link JFileChooser}</code> (or subclass.)
   * @param c the given {@code Component}.
   * @return the <code>String</code> representation of the given <code>JFileChooser</code>.
   */
  protected String doFormat(Component c) {
    JFileChooser fileChooser = (JFileChooser)c;
    return concat(
        fileChooser.getClass().getName(), "[",
        "name=",             quote(fileChooser.getName()),                  ", ",
        "dialogTitle=",      quote(fileChooser.getDialogTitle()),           ", ",
        "dialogType=",       DIALOG_TYPES.get(fileChooser.getDialogType()), ", ",
        "currentDirectory=", fileChooser.getCurrentDirectory(),             ", ",
        "enabled=",          valueOf(fileChooser.isEnabled()),              ", ",
        "visible=",          valueOf(fileChooser.isVisible()),              ", ",
        "showing=",          valueOf(fileChooser.isShowing()),
        "]"
    );
  }

  /**
   * Indicates that this formatter supports <code>{@link JFileChooser}</code> only.
   * @return <code>JFileChooser.class</code>.
   */
  public Class<? extends Component> targetType() {
    return JFileChooser.class;
  }
}
