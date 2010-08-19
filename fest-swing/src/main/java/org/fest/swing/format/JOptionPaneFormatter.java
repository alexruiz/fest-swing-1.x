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
import static javax.swing.JOptionPane.*;
import static org.fest.util.Strings.*;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * Understands a formatter for <code>{@link JOptionPane}</code>s.
 *
 * @author Alex Ruiz
 */
public class JOptionPaneFormatter extends ComponentFormatterTemplate {

  private static final IntEnum MESSAGE_TYPES = new IntEnum();
  static {
    MESSAGE_TYPES.put(ERROR_MESSAGE,       "ERROR_MESSAGE")
                 .put(INFORMATION_MESSAGE, "INFORMATION_MESSAGE")
                 .put(WARNING_MESSAGE,     "WARNING_MESSAGE")
                 .put(QUESTION_MESSAGE,    "QUESTION_MESSAGE")
                 .put(PLAIN_MESSAGE,       "PLAIN_MESSAGE");
  }

  private static final IntEnum OPTION_TYPES = new IntEnum();
  static {
    OPTION_TYPES.put(DEFAULT_OPTION,       "DEFAULT_OPTION")
                .put(YES_NO_OPTION,        "YES_NO_OPTION")
                .put(YES_NO_CANCEL_OPTION, "YES_NO_CANCEL_OPTION")
                .put(QUESTION_MESSAGE,     "QUESTION_MESSAGE")
                .put(OK_CANCEL_OPTION,     "OK_CANCEL_OPTION");
  }

  /**
   * Returns the {@code String} representation of the given <code>{@link Component}</code>, which should be a
   * <code>{@link JOptionPane}</code> (or subclass.)
   * @param c the given {@code Component}.
   * @return the {@code String} representation of the given <code>JOptionPane</code>.
   */
  @Override protected String doFormat(Component c) {
    JOptionPane optionPane = (JOptionPane)c;
    return concat(
        optionPane.getClass().getName(), "[",
        "message=",     quote(optionPane.getMessage()),                 ", ",
        "messageType=", MESSAGE_TYPES.get(optionPane.getMessageType()), ", ",
        "optionType=",  OPTION_TYPES.get(optionPane.getOptionType()),   ", ",
        "enabled=",     valueOf(optionPane.isEnabled()),                ", ",
        "visible=",     valueOf(optionPane.isVisible()),                ", ",
        "showing=",     valueOf(optionPane.isShowing()),
        "]"
    );
  }

  /**
   * Indicates that this formatter supports <code>{@link JOptionPane}</code> only.
   * @return <code>JOptionPane.class</code>.
   */
  public Class<? extends Component> targetType() {
    return JOptionPane.class;
  }
}
