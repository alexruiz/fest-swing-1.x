/*
 * Created on Mar 4, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.core;

import java.awt.Component;
import java.awt.Container;
import java.util.Collection;

import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Looks up GUI <code>{@link Component}</code>s based on different search criteria, such as a {@code Component}'s name,
 * type or label, and custom search criteria as well.
 *
 * @author Alex Ruiz
 */
@RunsInEDT
public interface ComponentFinder {

  /**
   * Returns the <code>{@link ComponentPrinter}</code> in this finder.
   * @return the {@code ComponentPrinter} in this finder.
   */
  ComponentPrinter printer();

  /**
   * Finds a <code>{@link Component}</code> by type. If this finder is attached to a <code>{@link Robot}</code>, it will
   * use the component lookup scope in the <code>Robot</code>'s <code>{@link Settings}</code> to determine whether the
   * component to find should be showing or not. If this finder is <em>not</em> attached to any <code>Robot</code>, the
   * component to find does not have to be showing.
   * <p>
   * Example:
   * <pre>
   * JTextField textbox = finder.findByType(JTextField.class);
   * </pre>
   * </p>
   * @param <T> the parameterized type of the component to find.
   * @param type the type of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   */
  <T extends Component> T findByType(Class<T> type);

  /**
   * Finds a <code>{@link Component}</code> by type. For example:
   * @param <T> the parameterized type of the component to find.
   * @param type the type of the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByType(Class)
   */
  <T extends Component> T findByType(Class<T> type, boolean showing);

  /**
   * <p>
   * Finds a <code>{@link Component}</code> by type in the hierarchy under the given root. If this finder is attached to
   * a <code>{@link Robot}</code>, it will use the component lookup scope in the <code>Robot</code>'s
   * <code>{@link Settings}</code> to determine whether the component to find should be showing or not. If this finder
   * is <em>not</em> attached to any <code>Robot</code>, the component to find does not have to be showing.
   * </p>
   * <p>
   * Let's assume we have the following <code>{@link javax.swing.JFrame}</code> containing a
   * <code>{@link javax.swing.JTextField}</code>:
   *
   * <pre>
   * JFrame myFrame = new JFrame();
   * myFrame.add(new JTextField());
   * </pre>
   *
   * </p>
   * <p>
   * If we want to get a reference to the <code>{@link javax.swing.JTextField}</code> in that particular
   * <code>{@link javax.swing.JFrame}</code> without going through the whole AWT component hierarchy, we could simply
   * specify:
   *
   * <pre>
   * JTextField textbox = finder.findByType(myFrame, JTextField.class);
   * </pre>
   *
   * </p>
   * @param <T> the parameterized type of the component to find.
   * @param root the root used as the starting point of the search.
   * @param type the type of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   */
  <T extends Component> T findByType(Container root, Class<T> type);

  /**
   * Finds a <code>{@link Component}</code> by type in the hierarchy under the given root.
   * @param <T> the parameterized type of the component to find.
   * @param root the root used as the starting point of the search.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @param type the type of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByType(Container, Class)
   */
  <T extends Component> T findByType(Container root, Class<T> type, boolean showing);

  /**
   * <p>
   * Finds a <code>{@link Component}</code> by by the text of its associated <code>{@link JLabel}</code>. If this finder
   * is attached to  a <code>{@link Robot}</code>, it will use the component lookup scope in the <code>Robot</code>'s
   * <code>{@link Settings}</code> to determine whether the component to find should be showing or not. If this finder
   * is <em>not</em> attached to any <code>Robot</code>, the component to find does not have to be showing.
   * </p>
   * <p>
   * Let's assume we have the <code>{@link javax.swing.JTextField}</code> with a <code>{@link JLabel}</code> with text
   * "Name";
   *
   * <pre>
   * JLabel label = new JLabel("Name");
   * JTextField textbox = new JTextField();
   * label.setLabelFor(textBox);
   * </pre>
   *
   * </p>
   * <p>
   * To get a reference to this <code>{@link javax.swing.JTextField}</code> by the text of its associated
   * <code>JLabel</code>, we can specify:
   *
   * <pre>
   * JTextField textBox = (JTextField) finder.findByLabel(&quot;Name&quot;);
   * </pre>
   *
   * </p>
   * <p>
   * Please note that you need to cast the result of the lookup to the right type. To avoid casting, please use one of
   * following:
   * <ol>
   * <li><code>{@link #findByLabel(String, Class)}</code></li>
   * <li><code>{@link #findByLabel(String, Class, boolean)}</code></li>
   * <li><code>{@link #findByLabel(Container, String, Class)}</code></li>
   * <li><code>{@link #findByLabel(Container, String, Class, boolean)}</code></li>
   * </ol>
   * </p>
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   */
  Component findByLabel(String label);

  /**
   * Finds a <code>{@link Component}</code> by the text of its associated <code>{@link JLabel}</code> and type. If this
   * finder is attached to  a <code>{@link Robot}</code>, it will use the component lookup scope in the
   * <code>Robot</code>'s <code>{@link Settings}</code> to determine whether the component to find should be showing or
   * not. If this finder is <em>not</em> attached to any <code>Robot</code>, the component to find does not have to be
   * showing.
   * @param <T> the parameterized type of the component to find.
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @param type the type of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByLabel(String)
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   */
  <T extends Component> T findByLabel(String label, Class<T> type);

  /**
   * Finds a <code>{@link Component}</code> by the text of its associated <code>{@link JLabel}</code> and type.
   * @param <T> the parameterized type of the component to find.
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @param type the type of the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByLabel(String)
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   */
  <T extends Component> T findByLabel(String label, Class<T> type, boolean showing);

  /**
   * Finds a <code>{@link Component}</code> by by the text of its associated <code>{@link JLabel}</code>.
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByLabel(String)
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   */
  Component findByLabel(String label, boolean showing);

  /**
   * Finds a <code>{@link Component}</code> by the text of its associated <code>{@link JLabel}</code>, in the hierarchy
   * under the given root. If this finder is attached to  a <code>{@link Robot}</code>, it will use the component lookup
   * scope in the <code>Robot</code>'s <code>{@link Settings}</code> to determine whether the component to find should
   * be showing or not. If this finder is <em>not</em> attached to any <code>Robot</code>, the component to find does
   * not have to be showing.
   * @param root the root used as the starting point of the search.
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByLabel(String)
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   */
  Component findByLabel(Container root, String label);

  /**
   * Finds a <code>{@link Component}</code> by the text of its associated <code>{@link JLabel}</code>, in the hierarchy
   * under the given root.
   * @param root the root used as the starting point of the search.
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByLabel(String)
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   */
  Component findByLabel(Container root, String label, boolean showing);

  /**
   * Finds a <code>{@link Component}</code> by the text of its associated <code>{@link JLabel}</code> and type, in the
   * hierarchy under the given root. If this finder is attached to  a <code>{@link Robot}</code>, it will use the
   * component lookup scope in the <code>Robot</code>'s <code>{@link Settings}</code> to determine whether the component
   * to find should be showing or not. If this finder is <em>not</em> attached to any <code>Robot</code>, the component
   * to find does not have to be showing.
   * @param <T> the parameterized type of the component to find.
   * @param root the root used as the starting point of the search.
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @param type the type of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByLabel(String)
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   */
  <T extends Component> T findByLabel(Container root, String label, Class<T> type);

  /**
   * Finds a <code>{@link Component}</code> by the text of its associated <code>{@link JLabel}</code> and type, in the
   * hierarchy under the given root.
   * @param <T> the parameterized type of the component to find.
   * @param root the root used as the starting point of the search.
   * @param label the text of the <code>JLabel</code> associated to the component to find.
   * @param type the type of the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByLabel(String)
   * @see JLabel#getLabelFor()
   * @see JLabel#setLabelFor(Component)
   */
  <T extends Component> T findByLabel(Container root, String label, Class<T> type, boolean showing);

  /**
   * <p>
   * Finds a <code>{@link Component}</code> by name. If this finder is attached to  a <code>{@link Robot}</code>, it
   * will use the component lookup scope in the <code>Robot</code>'s <code>{@link Settings}</code> to determine whether
   * the component to find should be showing or not. If this finder is <em>not</em> attached to any <code>Robot</code>,
   * the component to find does not have to be showing.
   * </p>
   * <p>
   * Let's assume we have the <code>{@link javax.swing.JTextField}</code> with name "myTextBox";
   *
   * <pre>
   * JTextField textbox = new JTextField();
   * textBox.setName(&quot;myTextBox&quot;);
   * </pre>
   *
   * </p>
   * <p>
   * To get a reference to this <code>{@link javax.swing.JTextField}</code> by its name, we can specify:
   *
   * <pre>
   * JTextField textBox = (JTextField) finder.findByName(&quot;myTextBox&quot;);
   * </pre>
   *
   * </p>
   * <p>
   * Please note that you need to cast the result of the lookup to the right type. To avoid casting, please use one of
   * following:
   * <ol>
   * <li><code>{@link #findByName(String, Class)}</code></li>
   * <li><code>{@link #findByName(String, Class, boolean)}</code></li>
   * <li><code>{@link #findByName(Container, String, Class)}</code></li>
   * <li><code>{@link #findByName(Container, String, Class, boolean)}</code></li>
   * </ol>
   * </p>
   * @param name the name of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   */
  Component findByName(String name);

  /**
   * Finds a <code>{@link Component}</code> by name and type. If this finder is attached to a
   * <code>{@link Robot}</code>, it will use the component lookup scope in the <code>Robot</code>'s
   * <code>{@link Settings}</code> to determine whether the component to find should be showing or not. If this finder
   * is <em>not</em> attached to any <code>Robot</code>, the component to find does not have to be showing.
   * @param <T> the parameterized type of the component to find.
   * @param name the name of the component to find.
   * @param type the type of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   * @see #findByName(String)
   */
  <T extends Component> T findByName(String name, Class<T> type);

  /**
   * Finds a <code>{@link Component}</code> by name and type.
   * @param <T> the parameterized type of the component to find.
   * @param name the name of the component to find.
   * @param type the type of the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByName(String)
   */
  <T extends Component> T findByName(String name, Class<T> type, boolean showing);

  /**
   * Finds a <code>{@link Component}</code> by name.
   * @param name the name of the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByName(String)
   */
  Component findByName(String name, boolean showing);

  /**
   * Finds a <code>{@link Component}</code> by name, in the hierarchy under the given root. If this finder is attached
   * to a <code>{@link Robot}</code>, it will use the component lookup scope in the <code>Robot</code>'s
   * <code>{@link Settings}</code> to determine whether the component to find should be showing or not. If this finder
   * is <em>not</em> attached to any <code>Robot</code>, the component to find does not have to be showing.
   * @param root the root used as the starting point of the search.
   * @param name the name of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   * @see #findByName(String)
   */
  Component findByName(Container root, String name);

  /**
   * Finds a <code>{@link Component}</code> by name, in the hierarchy under the given root.
   * @param root the root used as the starting point of the search.
   * @param name the name of the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByName(String)
   */
  Component findByName(Container root, String name, boolean showing);

  /**
   * Finds a <code>{@link Component}</code> by name and type, in the hierarchy under the given root. If this finder is
   * attached to a <code>{@link Robot}</code>, it will use the component lookup scope in the <code>Robot</code>'s
   * <code>{@link Settings}</code> to determine whether the component to find should be showing or not. If this finder
   * is <em>not</em> attached to any <code>Robot</code>, the component to find does not have to be showing.
   * @param <T> the parameterized type of the component to find.
   * @param root the root used as the starting point of the search.
   * @param name the name of the component to find.
   * @param type the type of the component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see Robot#settings()
   * @see Settings#componentLookupScope()
   * @see ComponentLookupScope
   * @see #findByName(String)
   */
  <T extends Component> T findByName(Container root, String name, Class<T> type);

  /**
   * Finds a <code>{@link Component}</code> by name and type, in the hierarchy under the given root.
   * @param <T> the parameterized type of the component to find.
   * @param root the root used as the starting point of the search.
   * @param name the name of the component to find.
   * @param type the type of the component to find.
   * @param showing indicates whether the component to find should be visible (or showing) or not.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see #findByName(String)
   */
  <T extends Component> T findByName(Container root, String name, Class<T> type, boolean showing);

  /**
   * Finds a <code>{@link Component}</code> using the given <code>{@link ComponentMatcher}</code>. The given matcher
   * will be evaluated in the event dispatch thread. Implementations of <code>ComponentMatcher</code> do not need to
   * be concerned about the event dispatch thread.
   * @param m the matcher to use to find the component of interest.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  Component find(ComponentMatcher m);

  /**
   * Finds a <code>{@link Component}</code> using the given <code>{@link GenericTypeMatcher}</code>.  The given matcher
   * will be evaluated in the event dispatch thread. Implementations of <code>GenericTypeMatcher</code> do not need to
   * be concerned about the event dispatch thread.
   * @param <T> the type of component the given matcher can handle.
   * @param m the matcher to use to find the component of interest.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  <T extends Component> T find(GenericTypeMatcher<T> m);

  /**
   * Finds a <code>{@link Component}</code> using the given <code>{@link GenericTypeMatcher}</code> in the hierarchy
   * under the given root. The given matcher will be evaluated in the event dispatch thread. Implementations of
   * <code>GenericTypeMatcher</code> do not need to be concerned about the event dispatch thread.
   * @param <T> the type of component the given matcher can handle.
   * @param root the root used as the starting point of the search.
   * @param m the matcher to use to find the component.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  <T extends Component> T find(Container root, GenericTypeMatcher<T> m);

  /**
   * Finds a <code>{@link Component}</code> using the given <code>{@link ComponentMatcher}</code> in the hierarchy
   * under the given root.  The given matcher will be evaluated in the event dispatch thread. Implementations of
   * <code>ComponentMatcher</code> do not need to be concerned about the event dispatch thread.
   * @param root the root used as the starting point of the search.
   * @param m the matcher to use to find the component.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  Component find(Container root, ComponentMatcher m);

  /**
   * Returns all the <code>{@link Component}</code>s that match the search criteria specified in the given
   * <code>{@link ComponentMatcher}</code>.
   * @param m the matcher to use to find the component.
   * @return all the {@code Component}s that match the search criteria specified in the given
   * <code>ComponentMatcher</code>; or an empty collection, if there are no matching components.
   */
  Collection<Component> findAll(ComponentMatcher m);

  /**
   * Returns all the <code>{@link Component}</code>s under the given root that match the search criteria specified in
   * the given <code>{@link ComponentMatcher}</code>.
   * @param root the root used as the starting point of the search.
   * @param m the matcher to use to find the component.
   * @return all the {@code Component}s under the given root that match the search criteria specified in the given
   * <code>ComponentMatcher</code>; or an empty collection, if there are no matching components.
   */
  Collection<Component> findAll(Container root, ComponentMatcher m);

  /**
   * Returns all the <code>{@link Component}</code>s that match the search criteria specified in the given
   * <code>{@link GenericTypeMatcher}</code>.
   * @param <T> the generic type of component that this search supports.
   * @param m the matcher to use to find the component.
   * @return all the {@code Component}s that match the search criteria specified in the given
   * <code>GenericTypeMatcher</code>; or an empty collection, if there are no matching components.
   */
  <T extends Component> Collection<T> findAll(GenericTypeMatcher<T> m);

  /**
   * Returns all the <code>{@link Component}</code>s under the given root that match the search criteria specified in
   * the given <code>{@link GenericTypeMatcher}</code>.
   * @param <T> the generic type of component that this search supports.
   * @param root the root used as the starting point of the search.
   * @param m the matcher to use to find the component.
   * @return all the {@code Component}s under the given root that match the search criteria specified in the given
   * <code>GenericTypeMatcher</code>; or an empty collection, if there are no matching components.
   */
  <T extends Component> Collection<T> findAll(Container root, GenericTypeMatcher<T> m);

  /**
   * Returns whether the message in a <code>{@link ComponentLookupException}</code> should include the current component
   * hierarchy. The default value is {@code true}.
   * @return {@code true} if the component hierarchy is included as part of the
   * <code>ComponentLookupException</code> message, {@code false} otherwise.
   */
  boolean includeHierarchyIfComponentNotFound();

  /**
   * Updates whether the message in a <code>{@link ComponentLookupException}</code> should include the current component
   * hierarchy. The default value is {@code true}.
   * @param newValue the new value to set.
   */
  void includeHierarchyIfComponentNotFound(boolean newValue);
}