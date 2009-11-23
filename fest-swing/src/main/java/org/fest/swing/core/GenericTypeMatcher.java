/*
 * Created on Aug 6, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.core;

import java.awt.Component;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a <code>{@link ComponentMatcher}</code> that matches a <code>{@link Component}</code> by type and some 
 * custom search criteria.
 * @param <T> the type of <code>Component</code> supported by this matcher. 
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class GenericTypeMatcher<T extends Component> extends AbstractComponentMatcher {

  private final Class<T> supportedType;

  /** 
   * Creates a new </code>{@link GenericTypeMatcher}</code>. The component to match does not have to be showing. 
   * @param supportedType the type supported by this matcher.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  public GenericTypeMatcher(Class<T> supportedType) {
    this(supportedType, false);
  }

  /**
   * Creates a new </code>{@link GenericTypeMatcher}</code>.
   * @param supportedType the type supported by this matcher.
   * @param requireShowing indicates if the component to match should be showing or not.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  public GenericTypeMatcher(Class<T> supportedType, boolean requireShowing) {
    super(requireShowing);
    if (supportedType == null) throw new NullPointerException("The supported type should not be null");
    this.supportedType = supportedType;
  }
  
  /**
   * Verifies that the given <code>{@link Component}</code>:
   * <ol>
   * <li>Is an instance of the generic type specified in this <code>{@link ComponentMatcher}</code></li> 
   * <li>Matches some search criteria</li>
   * </ol>
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param c the <code>Component</code> to verify. 
   * @return <code>true</code> if the given <code>Component</code> is an instance of the generic type of this matcher 
   * and matches some search criteria. Otherwise, <code>false</code>. 
   */
  @RunsInCurrentThread
  public final boolean matches(Component c) {
    if (c == null) return false;
    if (!supportedType.isInstance(c)) return false;
    try {
      return (requireShowingMatches(c)) && isMatching(supportedType.cast(c));
    } catch(ClassCastException ignored) {
      return false;
    }
  }

  /**
   * Returns the supported type of this matcher.
   * @return the supported type of this matcher.
   */
  public final Class<T> supportedType() {
    return supportedType;
  }
  
  /**
   * Verifies that the given component matches some search criteria.
   * <p>
   * <b>Note:</b> Implementations of this method should <b>not</b> use the event dispatch thread (EDT.) Clients are
   * responsible for invoking this method in the EDT.
   * </p>
   * @param component the <code>Component</code> to verify.
   * @return <code>true</code> if the given component matches the defined search criteria; otherwise, 
   * <code>false</code>.
   */
  @RunsInCurrentThread
  protected abstract boolean isMatching(T component);
}
