/*
 * Created on Apr 15, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.test.swing;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.JOptionPane.*;
import static javax.swing.JRootPane.*;
import static javax.swing.UIManager.getLookAndFeel;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

import javax.swing.*;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a dialog that hosts a <code>{@link JOptionPane}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JOptionPaneHost extends JDialog {

  private static final long serialVersionUID = 1L;

  @RunsInCurrentThread
  public static JOptionPaneHost host(JOptionPane optionPane, String title) {
    JOptionPaneHost host = new JOptionPaneHost(title);
    host.setUp(optionPane);
    return host;
  }

  private JOptionPaneHost(String title) {
    super((Dialog)null, title, true);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);
    setResizable(false);
  }

  private void setUp(final JOptionPane optionPane) {
    setComponentOrientation(optionPane.getComponentOrientation());
    add(optionPane, CENTER);
    decorateUsingMessageTypeOf(optionPane);
    pack();
    WindowAdapter adapter = new WindowAdapter() {
      private boolean gotFocus;

      @Override public void windowClosing(WindowEvent e) {
        optionPane.setValue(null);
      }

      @Override public void windowGainedFocus(WindowEvent e) {
        if (gotFocus) return;
        optionPane.selectInitialValue();
        gotFocus = true;
      }
    };
    addWindowListener(adapter);
    addWindowFocusListener(adapter);
    addComponentListener(new ComponentAdapter() {
      @Override public void componentShown(ComponentEvent ce) {
        optionPane.setValue(UNINITIALIZED_VALUE);
      }
    });
    addPropertyChangeListener(new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent event) {
        if (!isVisible()) return;
        if (event.getSource() != optionPane) return;
        if (!event.getPropertyName().equals(VALUE_PROPERTY)) return;
        Object newValue = event.getNewValue();
        if (newValue == null || newValue == UNINITIALIZED_VALUE) return;
        setVisible(false);
      }
    });
  }

  private void decorateUsingMessageTypeOf(JOptionPane optionPane) {
    if (!isDefaultLookAndFeelDecorated()) return;
    if (!getLookAndFeel().getSupportsWindowDecorations()) return;
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(styleFrom(optionPane));
  }

  private static int styleFrom(JOptionPane optionPane) {
    switch (optionPane.getMessageType()) {
      case ERROR_MESSAGE: return ERROR_DIALOG;
      case QUESTION_MESSAGE: return QUESTION_DIALOG;
      case WARNING_MESSAGE: return WARNING_DIALOG;
      case INFORMATION_MESSAGE: return INFORMATION_DIALOG;
      default: return PLAIN_DIALOG;
    }
  }
}
