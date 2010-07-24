/*
 * Created on Jan 13, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.timing.Timeout.timeout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands lookup of <code>{@link Component}</code>s contained in a <code>{@link Container}</code>.
 *
 * @author Alex Ruiz
 */
public interface ComponentContainerFixture {

  /** The timeout to use when looking for a dialog. It's value is 100 ms. **/
  public static Timeout DEFAULT_DIALOG_LOOKUP_TIMEOUT = timeout(100);

  /**
   * Returns a <code>{@link JButton}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JButton</code> found.
   * @throws ComponentLookupException if a <code>JButton</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JButton</code> is found.
   */
  JButtonFixture button();

  /**
   * Finds a <code>{@link JButton}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JButton</code>.
   * @return a fixture that manages the <code>JButton</code> found.
   * @throws ComponentLookupException if a <code>JButton</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JButton</code> that matches the given search criteria is
   * found.
   */
  JButtonFixture button(GenericTypeMatcher<? extends JButton> matcher);

  /**
   * Finds a <code>{@link JButton}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JButton</code> found.
   * @throws ComponentLookupException if a <code>JButton</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JButton</code> having a matching name is found.
   */
  JButtonFixture button(String name);

  /**
   * Returns a <code>{@link JCheckBox}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JCheckBox</code> found.
   * @throws ComponentLookupException if a <code>JCheckBox</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JCheckBox</code> is found.
   */
  JCheckBoxFixture checkBox();

  /**
   * Finds a <code>{@link JCheckBox}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JCheckBox</code>.
   * @return a fixture that manages the <code>JCheckBox</code> found.
   * @throws ComponentLookupException if a <code>JCheckBox</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JCheckBox</code> that matches the given search criteria is
   * found.
   */
  JCheckBoxFixture checkBox(GenericTypeMatcher<? extends JCheckBox> matcher);

  /**
   * Finds a <code>{@link JCheckBox}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JCheckBox</code> found.
   * @throws ComponentLookupException if a <code>JCheckBox</code> having a matching name could not be found.
   */
  JCheckBoxFixture checkBox(String name);

  /**
   * Returns a <code>{@link JComboBox}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the {@code JComboBox} found.
   * @throws ComponentLookupException if a {@code JComboBox} could not be found.
   * @throws ComponentLookupException if more than one {@code JComboBox} is found.
   */
  JComboBoxFixture comboBox();

  /**
   * Finds a <code>{@link JComboBox}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a {@code JComboBox}.
   * @return a fixture that manages the {@code JComboBox} found.
   * @throws ComponentLookupException if a {@code JComboBox} that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one {@code JComboBox} that matches the given search criteria is
   * found.
   */
  JComboBoxFixture comboBox(GenericTypeMatcher<? extends JComboBox> matcher);

  /**
   * Finds a <code>{@link JComboBox}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the {@code JComboBox} found.
   * @throws ComponentLookupException if a {@code JComboBox} having a matching name could not be found.
   * @throws ComponentLookupException if more than one {@code JComboBox} having a matching name is found.
   */
  JComboBoxFixture comboBox(String name);

  /**
   * Returns the only <code>{@link Dialog}</code> currently available (if any.) This method uses the value defined in
   * <code>{@link #DEFAULT_DIALOG_LOOKUP_TIMEOUT}</code> as the default lookup timeout.
   * @return a fixture that manages the <code>Dialog</code> found.
   * @throws WaitTimedOutError if a <code>Dialog</code> could not be found.
   * @see #dialog(Timeout)
   */
  DialogFixture dialog();

  /**
   * Returns the only <code>{@link Dialog}</code> currently available (if any.)
   * @param timeout the amount of time to wait for a <code>Dialog</code> to be found.
   * @return a fixture that manages the <code>Dialog</code> found.
   * @throws WaitTimedOutError if a <code>Dialog</code> could not be found.
   * @since 1.2
   */
  DialogFixture dialog(Timeout timeout);

  /**
   * Finds a <code>{@link Dialog}</code> that matches the specified search criteria. This method uses the value defined
   * in <code>{@link #DEFAULT_DIALOG_LOOKUP_TIMEOUT}</code> as the default lookup timeout.
   * @param matcher contains the search criteria for finding a <code>Dialog</code>.
   * @return a fixture that manages the <code>Dialog</code> found.
   * @throws WaitTimedOutError if a <code>Dialog</code> that matches the given search criteria could not be found.
   * @see #dialog(GenericTypeMatcher, Timeout)
   */
  DialogFixture dialog(GenericTypeMatcher<? extends Dialog> matcher);

  /**
   * Finds a <code>{@link Dialog}</code> that matches the specified search criteria.
   * @param matcher contains the search criteria for finding a <code>Dialog</code>.
   * @param timeout the amount of time to wait for a <code>Dialog</code> to be found.
   * @return a fixture that manages the <code>Dialog</code> found.
   * @throws WaitTimedOutError if a <code>Dialog</code> that matches the given search criteria could not be found.
   * @since 1.2
   */
  DialogFixture dialog(GenericTypeMatcher<? extends Dialog> matcher, Timeout timeout);

  /**
   * Finds a <code>{@link Dialog}</code> with a name matching the specified one. This method uses the value defined in
   * <code>{@link #DEFAULT_DIALOG_LOOKUP_TIMEOUT}</code> as the default lookup timeout.
   * @param name the name to match.
   * @return a fixture that manages the <code>Dialog</code> found.
   * @throws WaitTimedOutError if a <code>Dialog</code> that a matching name could not be found.
   * @see #dialog(String, Timeout)
   */
  DialogFixture dialog(String name);

  /**
   * Finds a <code>{@link Dialog}</code> with a name matching the specified one.
   * @param name the name to match.
   * @param timeout the amount of time to wait for a <code>Dialog</code> to be found.
   * @return a fixture that manages the <code>Dialog</code> found.
   * @throws WaitTimedOutError if a <code>Dialog</code> that a matching name could not be found.
   * @since 1.2
   */
  DialogFixture dialog(String name, Timeout timeout);

  /**
   * Returns the only <code>{@link JFileChooser}</code> currently available (if any.)  This method uses the value
   * defined in <code>{@link #DEFAULT_DIALOG_LOOKUP_TIMEOUT}</code> as the default lookup timeout.
   * @return a fixture that manages the <code>JFileChooser</code> found.
   * @throws WaitTimedOutError if a <code>JFileChooser</code> could not be found.
   * @see #fileChooser(Timeout)
   */
  JFileChooserFixture fileChooser();

  /**
   * Returns the only <code>{@link JFileChooser}</code> currently available (if any.)
   * @param timeout the amount of time to wait for a <code>JFileChooser</code> to be found.
   * @return a fixture that manages the <code>JFileChooser</code> found.
   * @throws WaitTimedOutError if a <code>JFileChooser</code> could not be found.
   */
  JFileChooserFixture fileChooser(Timeout timeout);

  /**
   * Finds a <code>{@link JFileChooser}</code> that matches the specified search criteria. This method uses the value
   * defined in <code>{@link #DEFAULT_DIALOG_LOOKUP_TIMEOUT}</code> as the default lookup timeout.
   * @param matcher contains the search criteria for finding a <code>JFileChooser</code>.
   * @return a fixture that manages the <code>JFileChooser</code> found.
   * @throws WaitTimedOutError if a <code>JFileChooser</code> could not be found.
   * @see #fileChooser(GenericTypeMatcher, Timeout)
   */
  JFileChooserFixture fileChooser(GenericTypeMatcher<? extends JFileChooser> matcher);

  /**
   * Finds a <code>{@link JFileChooser}</code> that matches the specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JFileChooser</code>.
   * @param timeout the amount of time to wait for a <code>JFileChooser</code> to be found.
   * @return a fixture that manages the <code>JFileChooser</code> found.
   * @throws WaitTimedOutError if a <code>JFileChooser</code> could not be found.
   */
  JFileChooserFixture fileChooser(GenericTypeMatcher<? extends JFileChooser> matcher, Timeout timeout);

  /**
   * Finds a <code>{@link JFileChooser}</code> with a name matching the specified one. This method uses the value
   * defined in <code>{@link #DEFAULT_DIALOG_LOOKUP_TIMEOUT}</code> as the default lookup timeout.
   * @param name the name to match.
   * @return a fixture that manages the <code>JFileChooser</code> found.
   * @throws WaitTimedOutError if a <code>JFileChooser</code> could not be found.
   * @see #fileChooser(String, Timeout)
   */
  JFileChooserFixture fileChooser(String name);

  /**
   * Finds a <code>{@link JFileChooser}</code> with a name matching the specified one.
   * @param name the name to match.
   * @param timeout the amount of time to wait for a <code>JFileChooser</code> to be found.
   * @return a fixture that manages the <code>JFileChooser</code> found.
   * @throws WaitTimedOutError if a <code>JFileChooser</code> could not be found.
   */
  JFileChooserFixture fileChooser(String name, Timeout timeout);

  /**
   * Returns a <code>{@link JLabel}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JLabel</code> found.
   * @throws ComponentLookupException if a <code>JLabel</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JLabel</code> is found.
   */
  JLabelFixture label();

  /**
   * Finds a <code>{@link JLabel}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JLabel</code>.
   * @return a fixture that manages the <code>JLabel</code> found.
   * @throws ComponentLookupException if a <code>JLabel</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JLabel</code> that matches the given search criteria is
   * found.
   */
  JLabelFixture label(GenericTypeMatcher<? extends JLabel> matcher);

  /**
   * Finds a <code>{@link JLabel}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JLabel</code> found.
   * @throws ComponentLookupException if a <code>JLabel</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JLabel</code> having a matching name could is found.
   */
  JLabelFixture label(String name);

  /**
   * Returns a <code>{@link JList}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JList</code> found.
   * @throws ComponentLookupException if a <code>JList</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JList</code> is found.
   */
  JListFixture list();

  /**
   * Finds a <code>{@link JList}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JList</code>.
   * @return a fixture that manages the <code>JList</code> found.
   * @throws ComponentLookupException if a <code>JList</code> that matches the given search criteria could not be found.
   * @throws ComponentLookupException if more than one <code>JList</code> that matches the given search criteria is
   * found.
   */
  JListFixture list(GenericTypeMatcher<? extends JList> matcher);

  /**
   * Finds a <code>{@link JList}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JList</code> found.
   * @throws ComponentLookupException if a <code>JList</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JList</code> having a matching name is found.
   */
  JListFixture list(String name);

  /**
   * Finds a <code>{@link JMenuItem}</code> in this fixture's <code>{@link Container}</code>, which path matches
   * the given one.
   * <p>
   * For example, if we are looking for the menu with text "New" contained under the menu with text "File", we can
   * simply call
   *
   * <pre>
   * JMenuItemFixture menuItem = container.<strong>menuItemWithPath(&quot;File&quot;, &quot;Menu&quot;)</strong>;
   * </pre>
   *
   * </p>
   * @param path the path of the menu to find.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> under the given path could not be found.
   * @throws AssertionError if the {@code Component} found under the given path is not a <code>JMenuItem</code>.
   */
  JMenuItemFixture menuItemWithPath(String... path);

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in this fixture's <code>{@link Container}</code>,
   * which name matches the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> having a matching name is found.
   */
  JMenuItemFixture menuItem(String name);

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in this fixture's <code>{@link Container}</code>,
   * that matches the specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JMenuItem</code>.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> that matches the given search criteria is
   * found.
   */
  JMenuItemFixture menuItem(GenericTypeMatcher<? extends JMenuItem> matcher);

  /**
   * Returns the only <code>{@link JOptionPane}</code> currently available (if any.) This method uses the value defined
   * in <code>{@link #DEFAULT_DIALOG_LOOKUP_TIMEOUT}</code> as the default lookup timeout.
   * @return a fixture that manages the <code>JOptionPane</code> found.
   * @throws WaitTimedOutError if a <code>JOptionPane</code> could not be found.
   * @see #optionPane(Timeout)
   */
  JOptionPaneFixture optionPane();

  /**
   * Returns the only <code>{@link JOptionPane}</code> currently available (if any.)
   * @param timeout the amount of time to wait for a <code>JOptionPane</code> to be found.
   * @return a fixture that manages the <code>JOptionPane</code> found.
   * @throws WaitTimedOutError if a <code>JOptionPane</code> could not be found.
   */
  JOptionPaneFixture optionPane(Timeout timeout);

  /**
   * Returns a <code>{@link JPanel}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JPanel</code> found.
   * @throws ComponentLookupException if a <code>JPanel</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JPanel</code> is found.
   */
  JPanelFixture panel();

  /**
   * Finds a <code>{@link JPanel}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JPanel</code>.
   * @return a fixture that manages the <code>JPanel</code> found.
   * @throws ComponentLookupException if a <code>JPanel</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JPanel</code> that matches the given search criteria is
   * found.
   */
  JPanelFixture panel(GenericTypeMatcher<? extends JPanel> matcher);

  /**
   * Finds a <code>{@link JPanel}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JPanel</code> found.
   * @throws ComponentLookupException if a <code>JPanel</code> having a matching name could not be found.
   */
  JPanelFixture panel(String name);

  /**
   * Returns a <code>{@link JProgressBar}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JProgressBar</code> found.
   * @throws ComponentLookupException if a <code>JProgressBar</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JProgressBar</code> is found.
   */
  JProgressBarFixture progressBar();

  /**
   * Finds a <code>{@link JProgressBar}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JProgressBar</code>.
   * @return a fixture that manages the <code>JProgressBar</code> found.
   * @throws ComponentLookupException if a <code>JProgressBar</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JProgressBar</code> that matches the given search criteria
   * is found.
   */
  JProgressBarFixture progressBar(GenericTypeMatcher<? extends JProgressBar> matcher);

  /**
   * Finds a <code>{@link JProgressBar}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JProgressBar</code> found.
   * @throws ComponentLookupException if a <code>JProgressBar</code> having a matching name could not be found.
   */
  JProgressBarFixture progressBar(String name);

  /**
   * Returns a <code>{@link JRadioButton}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JRadioButton</code> found.
   * @throws ComponentLookupException if a <code>JRadioButton</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JRadioButton</code> is found.
   */
  JRadioButtonFixture radioButton();

  /**
   * Finds a <code>{@link JRadioButton}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JRadioButton</code>.
   * @return a fixture that manages the <code>JRadioButton</code> found.
   * @throws ComponentLookupException if a <code>JRadioButton</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JRadioButton</code> that matches the given search criteria
   * is found.
   */
  JRadioButtonFixture radioButton(GenericTypeMatcher<? extends JRadioButton> matcher);

  /**
   * Finds a <code>{@link JRadioButton}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JRadioButton</code> found.
   * @throws ComponentLookupException if a <code>JRadioButton</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JRadioButton</code> having a matching name is found.
   */
  JRadioButtonFixture radioButton(String name);

  /**
   * Returns a <code>{@link JScrollBar}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JScrollBar</code> found.
   * @throws ComponentLookupException if a <code>JScrollBar</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JScrollBar</code> is found.
   */
  JScrollBarFixture scrollBar();

  /**
   * Finds a <code>{@link JScrollBar}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JScrollBar</code>.
   * @return a fixture that manages the <code>JScrollBar</code> found.
   * @throws ComponentLookupException if a <code>JScrollBar</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JScrollBar</code> that matches the given search criteria is
   * found.
   */
  JScrollBarFixture scrollBar(GenericTypeMatcher<? extends JScrollBar> matcher);

  /**
   * Finds a <code>{@link JScrollBar}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JScrollBar</code> found.
   * @throws ComponentLookupException if a <code>JScrollBar</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JScrollBar</code> having a matching name is found.
   */
  JScrollBarFixture scrollBar(String name);

  /**
   * Returns a <code>{@link JScrollPane}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JScrollPane</code> found.
   * @throws ComponentLookupException if a <code>JScrollPane</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JScrollPane</code> is found.
   */
  JScrollPaneFixture scrollPane();

  /**
   * Finds a <code>{@link JScrollPane}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JScrollPane</code>.
   * @return a fixture that manages the <code>JScrollPane</code> found.
   * @throws ComponentLookupException if a <code>JScrollPane</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JScrollPane</code> that matches the given search criteria
   * is found.
   */
  JScrollPaneFixture scrollPane(GenericTypeMatcher<? extends JScrollPane> matcher);

  /**
   * Finds a <code>{@link JScrollPane}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JScrollPane</code> found.
   * @throws ComponentLookupException if a <code>JScrollPane</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JScrollPane</code> having a matching name is found.
   */
  JScrollPaneFixture scrollPane(String name);

  /**
   * Returns a <code>{@link JSlider}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JSlider</code> found.
   * @throws ComponentLookupException if a <code>JSlider</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JSlider</code> is found.
   */
  JSliderFixture slider();

  /**
   * Finds a <code>{@link JSlider}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JSlider</code>.
   * @return a fixture that manages the <code>JSlider</code> found.
   * @throws ComponentLookupException if a <code>JSlider</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JSlider</code> that matches the given search criteria is
   * found.
   */
  JSliderFixture slider(GenericTypeMatcher<? extends JSlider> matcher);

  /**
   * Finds a <code>{@link JSlider}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JSlider</code> found.
   * @throws ComponentLookupException if a <code>JSlider</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JSlider</code> having a matching name is found.
   */
  JSliderFixture slider(String name);

  /**
   * Returns a <code>{@link JSpinner}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JSpinner</code> found.
   * @throws ComponentLookupException if a <code>JSpinner</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JSpinner</code> is found.
   */
  JSpinnerFixture spinner();

  /**
   * Finds a <code>{@link JSpinner}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JSpinner</code>.
   * @return a fixture that manages the <code>JSpinner</code> found.
   * @throws ComponentLookupException if a <code>JSpinner</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JSpinner</code> that matches the given search criteria is
   * found.
   */
  JSpinnerFixture spinner(GenericTypeMatcher<? extends JSpinner> matcher);

  /**
   * Finds a <code>{@link JSpinner}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JSpinner</code> found.
   * @throws ComponentLookupException if a <code>JSpinner</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JSpinner</code> having a matching name is found.
   */
  JSpinnerFixture spinner(String name);

  /**
   * Returns the <code>{@link JSplitPane}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JSplitPane</code> found.
   * @throws ComponentLookupException if a <code>JSplitPane</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JSplitPane</code> is found.
   */
  JSplitPaneFixture splitPane();

  /**
   * Finds a <code>{@link JSplitPane}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JSplitPane</code>.
   * @return a fixture that manages the <code>JSplitPane</code> found.
   * @throws ComponentLookupException if a <code>JSplitPane</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JSplitPane</code> that matches the given search criteria is
   * found.
   */
  JSplitPaneFixture splitPane(GenericTypeMatcher<? extends JSplitPane> matcher);

  /**
   * Finds a <code>{@link JSplitPane}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JSplitPane</code> found.
   * @throws ComponentLookupException if a <code>JSplitPane</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JSplitPane</code> having a matching name is found.
   */
  JSplitPaneFixture splitPane(String name);

  /**
   * Returns a <code>{@link JTabbedPane}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JTabbedPane</code> found.
   * @throws ComponentLookupException if a <code>JTabbedPane</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JTabbedPane</code> is found.
   */
  JTabbedPaneFixture tabbedPane();

  /**
   * Finds a <code>{@link JTabbedPane}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JTabbedPane</code>.
   * @return a fixture that manages the <code>JTabbedPane</code> found.
   * @throws ComponentLookupException if a <code>JTabbedPane</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JTabbedPane</code> that matches the given search criteria
   * is found.
   */
  JTabbedPaneFixture tabbedPane(GenericTypeMatcher<? extends JTabbedPane> matcher);

  /**
   * Finds a <code>{@link JTabbedPane}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JTabbedPane</code> found.
   * @throws ComponentLookupException if a <code>JTabbedPane</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JTabbedPane</code> having a matching name is found.
   */
  JTabbedPaneFixture tabbedPane(String name);

  /**
   * Returns a <code>{@link JTable}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JTable</code> found.
   * @throws ComponentLookupException if a <code>JTable</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JTable</code> having a matching name is found.
   */
  JTableFixture table();

  /**
   * Finds a <code>{@link JTable}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JTable</code>.
   * @return a fixture that manages the <code>JTable</code> found.
   * @throws ComponentLookupException if a <code>JTable</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JTable</code> that matches the given search criteria is
   * found.
   */
  JTableFixture table(GenericTypeMatcher<? extends JTable> matcher);

  /**
   * Finds a <code>{@link JTable}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JTable</code> found.
   * @throws ComponentLookupException if a <code>JTable</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JTable</code> having a matching name is found.
   */
  JTableFixture table(String name);

  /**
   * Returns a <code>{@link JTextComponent}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JTextComponent</code> found.
   * @throws ComponentLookupException if a <code>JTextComponent</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JTextComponent</code> having a matching name is found.
   */
  JTextComponentFixture textBox();

  /**
   * Finds a <code>{@link JTextComponent}</code> in this fixture's <code>{@link Container}</code> managed by this
   * fixture, that matches the specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JTextComponent</code>.
   * @return a fixture that manages the <code>JTextComponent</code> found.
   * @throws ComponentLookupException if a <code>JTextComponent</code> that matches the given search criteria could not
   * be found.
   * @throws ComponentLookupException if more than one <code>JTextComponent</code> that matches the given search
   * criteria is found.
   */
  JTextComponentFixture textBox(GenericTypeMatcher<? extends JTextComponent> matcher);

  /**
   * Finds a <code>{@link JTextComponent}</code> in this fixture's <code>{@link Container}</code> managed by this
   * fixture, which name matches the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JTextComponent</code> found.
   * @throws ComponentLookupException if a <code>JTextComponent</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JTextComponent</code> having a matching name is found.
   */
  JTextComponentFixture textBox(String name);

  /**
   * Returns a <code>{@link JToggleButton}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JToggleButton</code> found.
   * @throws ComponentLookupException if a <code>JToggleButton</code> could not be found.
   * @throws ComponentLookupException if more than one <code>JToggleButton</code> is found.
   */
  JToggleButtonFixture toggleButton();

  /**
   * Finds a <code>{@link JToggleButton}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JToggleButton</code>.
   * @return a fixture that manages the <code>JToggleButton</code> found.
   * @throws ComponentLookupException if a <code>JToggleButton</code> that matches the given search criteria could not
   * be found.
   * @throws ComponentLookupException if more than one <code>JToggleButton</code> that matches the given search criteria
   * is found.
   */
  JToggleButtonFixture toggleButton(GenericTypeMatcher<? extends JToggleButton> matcher);

  /**
   * Finds a <code>{@link JToggleButton}</code> in this fixture's <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JToggleButton</code> found.
   * @throws ComponentLookupException if a <code>JToggleButton</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JToggleButton</code> having a matching name is found.
   */
  JToggleButtonFixture toggleButton(String name);

  /**
   * Returns a <code>{@link JToolBar}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JToolBar</code> found.
   * @throws ComponentLookupException if a <code>JToolBar</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JToolBar</code> having a matching name could is found.
   */
  JToolBarFixture toolBar();

  /**
   * Finds a <code>{@link JToolBar}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JToolBar</code>.
   * @return a fixture that manages the <code>JToolBar</code> found.
   * @throws ComponentLookupException if a <code>JToolBar</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JToolBar</code> that matches the given search criteria is
   * found.
   */
  JToolBarFixture toolBar(GenericTypeMatcher<? extends JToolBar> matcher);

  /**
   * Finds a <code>{@link JToolBar}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JToolBar</code> found.
   * @throws ComponentLookupException if a <code>JToolBar</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JToolBar</code> having a matching name is found.
   */
  JToolBarFixture toolBar(String name);

  /**
   * Returns a <code>{@link JTree}</code> found in this fixture's <code>{@link Container}</code>.
   * @return a fixture that manages the <code>JTree</code> found.
   * @throws ComponentLookupException if a <code>JTree</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JTree</code> having a matching name is found.
   */
  JTreeFixture tree();

  /**
   * Finds a <code>{@link JTree}</code> in this fixture's <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JTree</code>.
   * @return a fixture that manages the <code>JTree</code> found.
   * @throws ComponentLookupException if a <code>JTree</code> that matches the given search criteria could not be found.
   * @throws ComponentLookupException if more than one <code>JTree</code> that matches the given search criteria is
   * found.
   */
  JTreeFixture tree(GenericTypeMatcher<? extends JTree> matcher);

  /**
   * Finds a <code>{@link JTree}</code> in this fixture's <code>{@link Container}</code>, which name matches the
   * specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JTree</code> found.
   * @throws ComponentLookupException if a <code>JTree</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JTree</code> having a matching name is found.
   */
  JTreeFixture tree(String name);

  /**
   * Returns a <code>{@link ComponentFixture}</code> managing a component inside this fixture's
   * <code>{@link Container}</code>. This is an extension method, to allow implementations of
   * <code>{@link ContainerFixture}</code> handle custom GUI components.
   * @param <C> the type of {@code Component} the fixture to return can handle.
   * @param <F> the type of <code>ComponentFixture</code> to return.
   * @param extension the <code>ComponentFixtureExtension</code> that creates the <code>ComponentFixture</code> to
   * return.
   * @return a <code>ComponentFixture</code> managing a component inside this fixture's {@code Container}.
   */
  <C extends Component, F extends ComponentFixture<C>> F with(ComponentFixtureExtension<C, F> extension);
}