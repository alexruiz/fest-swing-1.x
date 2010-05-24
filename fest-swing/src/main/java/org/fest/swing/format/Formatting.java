/*
 * Created on Sep 16, 2007
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

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.*;

import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.util.VisibleForTesting;

/**
 * Understands utility methods related to formatting.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Formatting {


  private static final String MAXIMUM = "maximum";

  private static final String MINIMUM = "minimum";

  private static final String NULL_COMPONENT_MESSAGE = "Null Component";

  private static final String ENABLED = "enabled";
  private static final String NAME = "name";
  private static final String SHOWING = "showing";
  private static final String TEXT = "text";
  private static final String TITLE = "title";
  private static final String VALUE = "value";
  private static final String VISIBLE = "visible";

  private static final ConcurrentMap<Class<?>, ComponentFormatter> FORMATTERS = new ConcurrentHashMap<Class<?>, ComponentFormatter>();

  private static Logger logger = Logger.getLogger(Formatting.class.getName());

  static {
    register(instrospect(AbstractButton.class, NAME, TEXT, "selected", ENABLED, VISIBLE, SHOWING));
    register(instrospect(Dialog.class, NAME, TITLE, ENABLED, "modal", VISIBLE, SHOWING));
    register(instrospect(Frame.class, NAME, TITLE, ENABLED, VISIBLE, SHOWING));
    register(new JComboBoxFormatter());
    register(instrospect(JButton.class, NAME, TEXT, ENABLED, VISIBLE, SHOWING));
    register(new JFileChooserFormatter());
    register(instrospect(JLabel.class, NAME, TEXT, ENABLED, VISIBLE, SHOWING));
    register(empty(JLayeredPane.class));
    register(new JListFormatter());
    register(empty(JMenuBar.class));
    register(new JOptionPaneFormatter());
    register(nameOnly(JPanel.class));
    register(instrospect(JPopupMenu.class, NAME, "label", ENABLED, VISIBLE, SHOWING));
    register(instrospect(JProgressBar.class, NAME, VALUE, MINIMUM, MAXIMUM, "string", "stringPainted", ENABLED, VISIBLE, SHOWING));
    register(empty(JRootPane.class));
    register(instrospect(JScrollBar.class, NAME, VALUE, "blockIncrement", MINIMUM, MAXIMUM, ENABLED, VISIBLE, SHOWING));
    register(instrospect(JScrollPane.class, NAME, ENABLED, VISIBLE, SHOWING));
    register(instrospect(JSlider.class, NAME, VALUE, MINIMUM, MAXIMUM, ENABLED, VISIBLE, SHOWING));
    register(instrospect(JSpinner.class, NAME, VALUE, ENABLED, VISIBLE, SHOWING));
    register(new JTabbedPaneFormatter());
    register(new JTableFormatter());
    register(nameOnly(JToolBar.class));
    register(instrospect(JPasswordField.class, NAME, ENABLED, VISIBLE, SHOWING));
    register(instrospect(JTextComponent.class, NAME, TEXT, ENABLED, VISIBLE, SHOWING));
    register(new JTreeFormatter());
  }

  private static ComponentFormatter instrospect(Class<? extends Component> targetType, String...propertyNames) {
    return new IntrospectionComponentFormatter(targetType, propertyNames);
  }

  private static ComponentFormatter empty(Class<? extends Component> targetType) {
    return new IntrospectionComponentFormatter(targetType);
  }

  private static ComponentFormatter nameOnly(Class<? extends Component> targetType) {
    return new IntrospectionComponentFormatter(targetType, NAME);
  }

  /**
   * Registers the given formatter, replacing any other one previously registered for the same supported component type.
   * @param formatter the formatter to register.
   */
  public static void register(ComponentFormatter formatter) {
    Class<?> key = formatter.targetType();
    ComponentFormatter previous = FORMATTERS.putIfAbsent(key, formatter);
    if (previous != null)
      logger.info(
          concat("Replaced formatter ", previous, " with ", formatter, " for the type ", key.getName()));
  }

  @VisibleForTesting
  static ComponentFormatter formatter(Class<?> type) {
    return FORMATTERS.get(type);
  }

  /**
   * Returns a <code>String</code> representation of the given <code>{@link Component}</code>. This method is invoked in
   * the event dispatch thread.
   * @param c the given <code>Component</code>.
   * @return a <code>String</code> representation of the given <code>Component</code>.
   */
  @RunsInEDT
  public static String inEdtFormat(final Component c) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        return format(c);
      }
    });
  }

  /**
   * Returns a <code>String</code> representation of the given <code>{@link Component}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @return a <code>String</code> representation of the given <code>Component</code>.
   */
  @RunsInCurrentThread
  public static String format(Component c) {
    if (c == null) return NULL_COMPONENT_MESSAGE;
    ComponentFormatter formatter = formatterFor(c.getClass());
    if (formatter != null) return formatter.format(c);
    String name = c.getName();
    if (isEmpty(name)) return c.toString();
    return concat(c.getClass().getName(), "[name=", quote(name), "]");
  }

  private static ComponentFormatter formatterFor(Class<?> type) {
    ComponentFormatter formatter = FORMATTERS.get(type);
    if (formatter != null) return formatter;
    Class<?> superType = type.getSuperclass();
    if (superType != null) return formatterFor(superType);
    return null;
  }

  private Formatting() {}
}
