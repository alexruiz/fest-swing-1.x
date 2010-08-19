/*
 * Created on Jun 18, 2007
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
import static org.fest.util.Strings.concat;

import java.awt.Component;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Matches <code>{@link java.awt.Component}</code>s by type.
 *
 * @author Alex Ruiz
 */
public final class TypeMatcher extends AbstractComponentMatcher {

  private final Class<? extends Component> type;

  /**
   * Creates a new <code>{@link TypeMatcher}</code>. The component to match does not have to be showing.
   * @param type the type of the component we are looking for.
   * @throws NullPointerException if the given type is {@code null}.
   */
  public TypeMatcher(Class<? extends Component> type) {
    this(type, false);
  }

  /**
   * Creates a new <code>{@link TypeMatcher}</code>.
   * @param type the type of the component we are looking for.
   * @param requireShowing indicates if the component to match should be showing or not.
   * @throws NullPointerException if the given type is {@code null}.
   */
  public TypeMatcher(Class<? extends Component> type, boolean requireShowing) {
    super(requireShowing);
    if (type == null)
      throw new NullPointerException("The type of component to find should not be null");
    this.type = type;
  }

  /**
   * Indicates whether the type and visibility of the given <code>{@link java.awt.Component}</code> matches the value
   * specified in this matcher.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @return {@code true} if the type and visibility of the given {@code Component} matches the values
   * specified in this matcher, {@code false} otherwise.
   */
  @RunsInCurrentThread
  public boolean matches(Component c) {
    return type.isAssignableFrom(c.getClass()) && requireShowingMatches(c);
  }

  @Override public String toString() {
    return concat(
        getClass().getName(), "[",
        "type=", type.getName(), ", ",
        "requireShowing=", valueOf(requireShowing()),
        "]"
    );
  }
}
