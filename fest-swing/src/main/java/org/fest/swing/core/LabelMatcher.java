/*
 * Created on Dec 2, 2008
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

import static java.lang.String.valueOf;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.*;

import java.awt.Component;

import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands <code>{@link java.awt.Component}</code> matching by the text of the associated
 * <code>{@link JLabel}</code> and (optionally) by type.
 * @see JLabel#getLabelFor()
 * @see JLabel#setLabelFor(Component)
 *
 * @author Alex Ruiz
 */
public class LabelMatcher extends AbstractComponentMatcher {

  private final String label;
  private final Class<? extends Component> type;

  /**
   * Creates a new <code>{@link LabelMatcher}</code>. The component to match does not have to be showing.
   * @param label the text of the label associated to the component we are looking for.
   * @throws NullPointerException if the given label is <code>null</code>.
   * @throws IllegalArgumentException if the given label is empty.
   */
  public LabelMatcher(String label) {
    this(label, false);
  }

  /**
   * Creates a new <code>{@link LabelMatcher}</code>.
   * @param label the text of the label associated to the component we are looking for.
   * @param requireShowing indicates if the component to match should be showing or not.
   * @throws NullPointerException if the given label is <code>null</code>.
   * @throws IllegalArgumentException if the given label is empty.
   */
  public LabelMatcher(String label, boolean requireShowing) {
    this(label, Component.class, requireShowing);
  }

  /**
   * Creates a new <code>{@link LabelMatcher}</code>. The component to match does not have to be showing.
   * @param label the text of the label associated to the component we are looking for.
   * @param type the type of the component we are looking for.
   * @throws NullPointerException if the given label is <code>null</code>.
   * @throws IllegalArgumentException if the given label is empty.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  public LabelMatcher(String label, Class<? extends Component> type) {
    this(label, type, false);
  }

  /**
   * Creates a new <code>{@link LabelMatcher}</code>.
   * @param label the text of the label associated to the component we are looking for.
   * @param type the type of the component we are looking for.
   * @param requireShowing indicates if the component to match should be showing or not.
   * @throws NullPointerException if the given label is <code>null</code>.
   * @throws IllegalArgumentException if the given label is empty.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  public LabelMatcher(String label, Class<? extends Component> type, boolean requireShowing) {
    super(requireShowing);
    if (label == null)
      throw new NullPointerException("The text of the label associated to the component to find should not be null");
    if (isEmpty(label))
      throw new IllegalArgumentException("The text of the label associated to the component to find should not be empty");
    if (type == null) throw new NullPointerException("The type of component to find should not be null");
    this.label = label;
    this.type = type;
  }

  /**
   * Indicates whether the given <code>{@link java.awt.Component}</code> matches the criteria specified in this
   * matcher:
   * <ol>
   * <li>the text of the <code>{@link JLabel}</code></li> attached to the component to look for matches the text
   * specified in this matcher
   * <li>the component to look for is of the type specified in this matcher (if specified)</li>
   * <li>visibility of the given <code>{@link java.awt.Component}</code> matches the value specified in this matcher
   * </li>
   * </ol>
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @return <code>true</code> if the name and visibility of the given <code>Component</code> matches the values
   * specified in this matcher, <code>false</code> otherwise.
   */
  @RunsInCurrentThread
  public boolean matches(Component c) {
    if (!(c instanceof JLabel)) return false;
    JLabel labelForComponent = (JLabel)c;
    if (!areEqual(labelForComponent.getText(), label)) return false;
    Component labeled = labelForComponent.getLabelFor();
    return type.isInstance(labeled) && requireShowingMatches(labeled);
  }

  @Override public String toString() {
    return concat(
        getClass().getName(), "[",
        "label=", quote(label), ", ",
        "type=", type.getName(), ", ",
        "requireShowing=", valueOf(requireShowing()),
        "]"
    );
  }
}
