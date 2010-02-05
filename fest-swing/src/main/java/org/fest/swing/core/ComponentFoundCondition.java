/*
 * Created on Nov 15, 2007
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

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.BasicDescription;
import org.fest.assertions.Description;
import org.fest.swing.timing.Condition;

/**
 * Understands a condition that is satisfied if a GUI component that matches certain search criteria can be found.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class ComponentFoundCondition extends Condition {

  private final ComponentFinder finder;
  private final ComponentMatcher matcher;
  private final Container root;

  private Component found;

  /**
   * Creates a new <code>{@link ComponentFoundCondition}</code>
   * @param description the description of this condition.
   * @param finder performs the component search.
   * @param matcher specifies the condition that the component we are looking for needs to match.
   */
  public ComponentFoundCondition(String description, ComponentFinder finder, ComponentMatcher matcher) {
    this(description, finder, matcher, null);
  }

  /**
   * Creates a new <code>{@link ComponentFoundCondition}</code>
   * @param description the description of this condition.
   * @param finder performs the component search.
   * @param matcher specifies the condition that the component we are looking for needs to match.
   * @param root the root used as the starting point of the search.
   */
  public ComponentFoundCondition(String description, ComponentFinder finder, ComponentMatcher matcher, Container root) {
    this(new BasicDescription(description), finder, matcher, root);
  }

  /**
   * Creates a new <code>{@link ComponentFoundCondition}</code>
   * @param description the description of this condition.
   * @param finder performs the component search.
   * @param matcher specifies the condition that the component we are looking for needs to match.
   */
  public ComponentFoundCondition(Description description, ComponentFinder finder, ComponentMatcher matcher) {
    this(description, finder, matcher, null);
  }

  /**
   * Creates a new <code>{@link ComponentFoundCondition}</code>
   * @param description the description of this condition.
   * @param finder performs the component search.
   * @param matcher specifies the condition that the component we are looking for needs to match.
   * @param root the root used as the starting point of the search.
   */
  public ComponentFoundCondition(Description description, ComponentFinder finder, ComponentMatcher matcher, Container root) {
    super(description);
    this.finder = finder;
    this.matcher = matcher;
    this.root = root;
  }

  /**
   * Returns <code>true</code> if a component that matches the search criteria in this condition's
   * <code>{@link ComponentMatcher}</code> can be found. Otherwise, this method returns <code>false</code>.
   * @return <code>true</code> if a matching component can be found, <code>false</code> otherwise.
   */
  public boolean test() {
    boolean matchFound = false;
    List<Component> allFound = new ArrayList<Component>(finder.findAll(root, matcher));
    matchFound = allFound.size() == 1;
    if (matchFound) found = allFound.get(0);
    resetMatcher(matchFound);
    return matchFound;
  }

  private void resetMatcher(boolean matchFound) {
    if (!(matcher instanceof ResettableComponentMatcher)) return;
    ((ResettableComponentMatcher)matcher).reset(matchFound);
  }

  /**
   * Returns the component found (if any.)
   * @return the component found.
   */
  public Component found() { return found; }
}
