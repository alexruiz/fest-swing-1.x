/*
 * Created on Apr 10, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.core.ComponentLookupScope.SHOWING_ONLY;
import static org.fest.swing.timing.Pause.pause;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.timing.Timeout;

/**
 * Understands lookup of <code>{@link Component}</code>s contained in a <code>{@link Container}</code>.
 * @param <T> the type of container handled by this fixture.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class ContainerFixture<T extends Container> extends ComponentFixture<T> implements
    ComponentContainerFixture, JPopupMenuInvokerFixture {

  private final JMenuItemFinder menuItemFinder;

  /**
   * Creates a new <code>{@link ContainerFixture}</code>.
   * @param robot performs simulation of user events on a <code>Container</code>.
   * @param type the type of the <code>Container</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>type</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see org.fest.swing.core.ComponentFinder#findByType(Class)
   */
  public ContainerFixture(Robot robot, Class<? extends T> type) {
    super(robot, type);
    menuItemFinder = new JMenuItemFinder(robot, target);
  }

  /**
   * Creates a new <code>{@link ContainerFixture}</code>.
   * @param robot performs simulation of user events on a <code>Container</code>.
   * @param name the name of the <code>Container</code> to find using the given <code>Robot</code>.
   * @param type the type of the <code>Container</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>type</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see org.fest.swing.core.ComponentFinder#findByName(String, Class)
   */
  public ContainerFixture(Robot robot, String name, Class<? extends T> type) {
    super(robot, name, type);
    menuItemFinder = new JMenuItemFinder(robot, target);
  }

  /**
   * Creates a new <code>{@link ContainerFixture}</code>.
   * @param robot performs simulation of user events on the given <code>Container</code>.
   * @param target the <code>Container</code> to be.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public ContainerFixture(Robot robot, T target) {
    super(robot, target);
    menuItemFinder = new JMenuItemFinder(robot, target);
  }

  /** {@inheritDoc} */
  public JButtonFixture button() {
    return new JButtonFixture(robot, findByType(JButton.class));
  }

  /** {@inheritDoc} */
  public JButtonFixture button(GenericTypeMatcher<? extends JButton> matcher) {
    return new JButtonFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JButtonFixture button(String name) {
    return new JButtonFixture(robot, findByName(name, JButton.class));
  }

  /** {@inheritDoc} */
  public JCheckBoxFixture checkBox() {
    return new JCheckBoxFixture(robot, findByType(JCheckBox.class));
  }

  /** {@inheritDoc} */
  public JCheckBoxFixture checkBox(GenericTypeMatcher<? extends JCheckBox> matcher) {
    return new JCheckBoxFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JCheckBoxFixture checkBox(String name) {
    return new JCheckBoxFixture(robot, findByName(name, JCheckBox.class));
  }

  /** {@inheritDoc} */
  public JComboBoxFixture comboBox() {
    return new JComboBoxFixture(robot, findByType(JComboBox.class));
  }

  /** {@inheritDoc} */
  public JComboBoxFixture comboBox(GenericTypeMatcher<? extends JComboBox> matcher) {
    return new JComboBoxFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JComboBoxFixture comboBox(String name) {
    return new JComboBoxFixture(robot, findByName(name, JComboBox.class));
  }

  /** {@inheritDoc} */
  public DialogFixture dialog() {
    return dialog(DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  public DialogFixture dialog(Timeout timeout) {
    TypeMatcher matcher = new TypeMatcher(Dialog.class, requireShowing());
    return findDialog(matcher, timeout);
  }

  /** {@inheritDoc} */
  public DialogFixture dialog(GenericTypeMatcher<? extends Dialog> matcher) {
    return dialog(matcher, DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  public DialogFixture dialog(GenericTypeMatcher<? extends Dialog> matcher, Timeout timeout) {
    return findDialog(matcher, timeout);
  }

  /** {@inheritDoc} */
  public DialogFixture dialog(String name) {
    return dialog(name, DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  public DialogFixture dialog(String name, Timeout timeout) {
    NameMatcher matcher = new NameMatcher(name, Dialog.class, requireShowing());
    return findDialog(matcher, timeout);
  }

  private DialogFixture findDialog(ComponentMatcher matcher, Timeout timeout) {
    String description = "dialog to be found using matcher " + matcher;
    ComponentFoundCondition condition = new ComponentFoundCondition(description, robot.finder(), matcher);
    pause(condition, timeout);
    return new DialogFixture(robot, (Dialog)condition.found());
  }

  /** {@inheritDoc} */
  public JFileChooserFixture fileChooser() {
    return fileChooser(DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  public JFileChooserFixture fileChooser(Timeout timeout) {
    TypeMatcher matcher = new TypeMatcher(JFileChooser.class, requireShowing());
    return findFileChooser(matcher, timeout);
  }

  /** {@inheritDoc} */
  public JFileChooserFixture fileChooser(GenericTypeMatcher<? extends JFileChooser> matcher) {
    return fileChooser(matcher, DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  public JFileChooserFixture fileChooser(GenericTypeMatcher<? extends JFileChooser> matcher, Timeout timeout) {
    return findFileChooser(matcher, timeout);
  }

  /** {@inheritDoc} */
  public JFileChooserFixture fileChooser(String name) {
    return new JFileChooserFixture(robot, findByName(name, JFileChooser.class));
  }

  /** {@inheritDoc} */
  public JFileChooserFixture fileChooser(String name, Timeout timeout) {
    NameMatcher matcher = new NameMatcher(name, JFileChooser.class, requireShowing());
    return findFileChooser(matcher, timeout);
  }

  private JFileChooserFixture findFileChooser(ComponentMatcher matcher, Timeout timeout) {
    String description = "file chooser to be found using matcher " + matcher;
    ComponentFoundCondition condition = new ComponentFoundCondition(description, robot.finder(), matcher);
    pause(condition, timeout);
    return new JFileChooserFixture(robot, (JFileChooser)condition.found());
  }

  /** {@inheritDoc} */
  public JLabelFixture label() {
    return new JLabelFixture(robot, findByType(JLabel.class));
  }

  /** {@inheritDoc} */
  public JLabelFixture label(GenericTypeMatcher<? extends JLabel> matcher) {
    return new JLabelFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JLabelFixture label(String name) {
    return new JLabelFixture(robot, findByName(name, JLabel.class));
  }

  /** {@inheritDoc} */
  public JListFixture list() {
    return new JListFixture(robot, findByType(JList.class));
  }

  /** {@inheritDoc} */
  public JListFixture list(GenericTypeMatcher<? extends JList> matcher) {
    return new JListFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JListFixture list(String name) {
    return new JListFixture(robot, findByName(name, JList.class));
  }

  /** {@inheritDoc} */
  public JMenuItemFixture menuItemWithPath(String... path) {
    return new JMenuItemFixture(robot, menuItemFinder.menuItemWithPath(path));
  }

  /** {@inheritDoc} */
  public JMenuItemFixture menuItem(String name) {
    boolean requireShowing = SHOWING_ONLY.equals(robot.settings().componentLookupScope());
    return new JMenuItemFixture(robot, finder().findByName(target, name, JMenuItem.class, requireShowing));
  }

  /** {@inheritDoc} */
  public JMenuItemFixture menuItem(GenericTypeMatcher<? extends JMenuItem> matcher) {
    return new JMenuItemFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JOptionPaneFixture optionPane() {
    return optionPane(DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  public JOptionPaneFixture optionPane(Timeout timeout) {
    TypeMatcher matcher = new TypeMatcher(JOptionPane.class, requireShowing());
    String description = "option pane to be found using matcher " + matcher;
    ComponentFoundCondition condition = new ComponentFoundCondition(description, robot.finder(), matcher);
    pause(condition, timeout);
    return new JOptionPaneFixture(robot, (JOptionPane)condition.found());
  }

  /** {@inheritDoc} */
  public JPanelFixture panel() {
    return new JPanelFixture(robot, findByType(JPanel.class));
  }

  /** {@inheritDoc} */
  public JPanelFixture panel(GenericTypeMatcher<? extends JPanel> matcher) {
    return new JPanelFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JPanelFixture panel(String name) {
    return new JPanelFixture(robot, findByName(name, JPanel.class));
  }

  /** {@inheritDoc} */
  public JRadioButtonFixture radioButton() {
    return new JRadioButtonFixture(robot, findByType(JRadioButton.class));
  }

  /** {@inheritDoc} */
  public JRadioButtonFixture radioButton(GenericTypeMatcher<? extends JRadioButton> matcher) {
    return new JRadioButtonFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JRadioButtonFixture radioButton(String name) {
    return new JRadioButtonFixture(robot, findByName(name, JRadioButton.class));
  }

  /** {@inheritDoc} */
  public JScrollBarFixture scrollBar() {
    return new JScrollBarFixture(robot, findByType(JScrollBar.class));
  }

  /** {@inheritDoc} */
  public JScrollBarFixture scrollBar(GenericTypeMatcher<? extends JScrollBar> matcher) {
    return new JScrollBarFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JScrollBarFixture scrollBar(String name) {
    return new JScrollBarFixture(robot, findByName(name, JScrollBar.class));
  }

  /** {@inheritDoc} */
  public JScrollPaneFixture scrollPane() {
    return new JScrollPaneFixture(robot, findByType(JScrollPane.class));
  }

  /** {@inheritDoc} */
  public JScrollPaneFixture scrollPane(GenericTypeMatcher<? extends JScrollPane> matcher) {
    return new JScrollPaneFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JScrollPaneFixture scrollPane(String name) {
    return new JScrollPaneFixture(robot, findByName(name, JScrollPane.class));
  }

  /** {@inheritDoc} */
  public JSliderFixture slider() {
    return new JSliderFixture(robot, findByType(JSlider.class));
  }

  /** {@inheritDoc} */
  public JSliderFixture slider(GenericTypeMatcher<? extends JSlider> matcher) {
    return new JSliderFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JSliderFixture slider(String name) {
    return new JSliderFixture(robot, findByName(name, JSlider.class));
  }

  /** {@inheritDoc} */
  public JSpinnerFixture spinner() {
    return new JSpinnerFixture(robot, findByType(JSpinner.class));
  }

  /** {@inheritDoc} */
  public JSpinnerFixture spinner(GenericTypeMatcher<? extends JSpinner> matcher) {
    return new JSpinnerFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JSpinnerFixture spinner(String name) {
    return new JSpinnerFixture(robot, findByName(name, JSpinner.class));
  }

  /** {@inheritDoc} */
  public JSplitPaneFixture splitPane() {
    return new JSplitPaneFixture(robot, findByType(JSplitPane.class));
  }

  /** {@inheritDoc} */
  public JSplitPaneFixture splitPane(GenericTypeMatcher<? extends JSplitPane> matcher) {
    return new JSplitPaneFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JSplitPaneFixture splitPane(String name) {
    return new JSplitPaneFixture(robot, findByName(name, JSplitPane.class));
  }

  /** {@inheritDoc} */
  public JTabbedPaneFixture tabbedPane() {
    return new JTabbedPaneFixture(robot, findByType(JTabbedPane.class));
  }

  /** {@inheritDoc} */
  public JTabbedPaneFixture tabbedPane(GenericTypeMatcher<? extends JTabbedPane> matcher) {
    return new JTabbedPaneFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JTabbedPaneFixture tabbedPane(String name) {
    return new JTabbedPaneFixture(robot, findByName(name, JTabbedPane.class));
  }

  /** {@inheritDoc} */
  public JTableFixture table() {
    return new JTableFixture(robot, findByType(JTable.class));
  }

  /** {@inheritDoc} */
  public JTableFixture table(GenericTypeMatcher<? extends JTable> matcher) {
    return new JTableFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JTableFixture table(String name) {
    return new JTableFixture(robot, findByName(name, JTable.class));
  }

  /** {@inheritDoc} */
  public JTextComponentFixture textBox() {
    return new JTextComponentFixture(robot, findByType(JTextComponent.class));
  }

  /** {@inheritDoc} */
  public JTextComponentFixture textBox(GenericTypeMatcher<? extends JTextComponent> matcher) {
    return new JTextComponentFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JTextComponentFixture textBox(String name) {
    return new JTextComponentFixture(robot, findByName(name, JTextComponent.class));
  }

  /** {@inheritDoc} */
  public JToggleButtonFixture toggleButton() {
    return new JToggleButtonFixture(robot, findByType(JToggleButton.class));
  }

  /** {@inheritDoc} */
  public JToggleButtonFixture toggleButton(GenericTypeMatcher<? extends JToggleButton> matcher) {
    return new JToggleButtonFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JToggleButtonFixture toggleButton(String name) {
    return new JToggleButtonFixture(robot, findByName(name, JToggleButton.class));
  }

  /** {@inheritDoc} */
  public JToolBarFixture toolBar() {
    return new JToolBarFixture(robot, findByType(JToolBar.class));
  }

  /** {@inheritDoc} */
  public JToolBarFixture toolBar(GenericTypeMatcher<? extends JToolBar> matcher) {
    return new JToolBarFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JToolBarFixture toolBar(String name) {
    return new JToolBarFixture(robot, findByName(name, JToolBar.class));
  }

  /** {@inheritDoc} */
  public JTreeFixture tree() {
    return new JTreeFixture(robot, findByType(JTree.class));
  }

  /** {@inheritDoc} */
  public JTreeFixture tree(GenericTypeMatcher<? extends JTree> matcher) {
    return new JTreeFixture(robot, find(matcher));
  }

  /** {@inheritDoc} */
  public JTreeFixture tree(String name) {
    return new JTreeFixture(robot, findByName(name, JTree.class));
  }

  /**
   * Finds a component by type, contained in this fixture's <code>{@link Container}</code>.
   * @param <C> the generic type of the component to find.
   * @param type the type of component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  protected final <C extends Component> C findByType(Class<C> type) {
    return finder().findByType(target, type, requireShowing());
  }

  /**
   * Finds a component by name and type, contained in this fixture's <code>{@link Container}</code>.
   * @param <C> the generic type of the component to find.
   * @param name the name of the component to find.
   * @param type the type of component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  protected final <C extends Component> C findByName(String name, Class<C> type) {
    return finder().findByName(target, name, type, requireShowing());
  }

  /**
   * Finds a <code>{@link Component}</code> using the given <code>{@link GenericTypeMatcher}</code>, contained in this
   * fixture's <code>{@link Container}</code>.
   * @param <C> the generic type of component the given matcher can handle.
   * @param matcher the matcher to use to find the component.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  protected final <C extends Component> C find(GenericTypeMatcher<? extends C> matcher) {
    return finder().find(target, matcher);
  }

  /** {@inheritDoc} */
  public <C extends Component, F extends ComponentFixture<C>> F with(ComponentFixtureExtension<C, F> extension) {
    return extension.createFixture(robot, target);
  }

  /**
   * Returns the <code>{@link ComponentFinder}</code> contained in this fixture's <code>{@link Robot}</code>.
   * @return the <code>ComponentFinder</code> contained in this fixture's <code>Robot</code>.
   */
  protected final ComponentFinder finder() { return robot.finder(); }
}
