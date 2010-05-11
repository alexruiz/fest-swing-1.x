/*
 * Created on Dec 22, 2007
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
package org.fest.swing.core;

import static java.lang.String.valueOf;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.*;

import java.awt.Component;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands <code>{@link java.awt.Component}</code> matching by name and (optionally) by type.
 *
 * @author Alex Ruiz
 */
public final class NameMatcher extends AbstractComponentMatcher {

  private final String name;
  private final Class<? extends Component> type;

  /**
   * Creates a new <code>{@link NameMatcher}</code>. The component to match does not have to be showing.
   * @param name the name of the component we are looking for.
   * @throws NullPointerException if the given name is <code>null</code>.
   * @throws IllegalArgumentException if the given name is empty.
   */
  public NameMatcher(String name) {
    this(name, false);
  }

  /**
   * Creates a new <code>{@link NameMatcher}</code>.
   * @param name the name of the component we are looking for.
   * @param requireShowing indicates if the component to match should be showing or not.
   * @throws NullPointerException if the given name is <code>null</code>.
   * @throws IllegalArgumentException if the given name is empty.
   */
  public NameMatcher(String name, boolean requireShowing) {
    this(name, Component.class, requireShowing);
  }

  /**
   * Creates a new <code>{@link NameMatcher}</code>. The component to match does not have to be showing.
   * @param name the name of the component we are looking for.
   * @param type the type of the component we are looking for.
   * @throws NullPointerException if the given name is empty.
   * @throws IllegalArgumentException if the given name is empty.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  public NameMatcher(String name, Class<? extends Component> type) {
    this(name, type, false);
  }

  /**
   * Creates a new <code>{@link NameMatcher}</code>.
   * @param name the name of the component we are looking for.
   * @param type the type of the component we are looking for.
   * @param requireShowing indicates if the component to match should be showing or not.
   * @throws NullPointerException if the given name is empty.
   * @throws IllegalArgumentException if the given name is empty.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  public NameMatcher(String name, Class<? extends Component> type, boolean requireShowing) {
    super(requireShowing);
    if (name == null) throw new NullPointerException("The name of the component to find should not be null");
    if (isEmpty(name)) throw new IllegalArgumentException("The name of the component to find should not be empty");
    if (type == null) throw new NullPointerException("The type of component to find should not be null");
    this.name = name;
    this.type = type;
  }

  /**
   * Indicates whether the name and visibility of the given <code>{@link java.awt.Component}</code> matches the value
   * specified in this matcher.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @return <code>true</code> if the name and visibility of the given <code>Component</code> matches the values
   * specified in this matcher, <code>false</code> otherwise.
   */
  @RunsInCurrentThread
  public boolean matches(Component c) {
    return areEqual(name, c.getName()) && type.isInstance(c) && requireShowingMatches(c);
  }

  @Override public String toString() {
    return concat(
        getClass().getName(), "[",
        "name=", quote(name), ", ",
        "type=", type.getName(), ", ",
        "requireShowing=", valueOf(requireShowing()),
        "]"
    );
  }
}
