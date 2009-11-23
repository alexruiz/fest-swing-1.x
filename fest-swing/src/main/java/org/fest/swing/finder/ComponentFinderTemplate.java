/*
 * Created on Oct 29, 2007
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
package org.fest.swing.finder;

import static org.fest.swing.timing.Pause.pause;
import static org.fest.util.Strings.concat;

import java.awt.Component;
import java.util.concurrent.TimeUnit;

import org.fest.swing.core.*;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.fixture.ComponentFixture;

/**
 * Understands a template for <code>{@link Component}</code> finders.
 * @param <T> the type of component this finder can search.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
abstract class ComponentFinderTemplate<T extends Component> {

  static final long TIMEOUT = 5000;
  
  private long timeout = TIMEOUT;

  private final ComponentMatcher matcher;
  private final String searchDescription;
  
  ComponentFinderTemplate(String componentName, Class<? extends T> componentType) {
    this(new NameMatcher(componentName, componentType, true));
  }

  ComponentFinderTemplate(GenericTypeMatcher<? extends T> matcher) {
    this((ComponentMatcher)matcher);
  }
  
  ComponentFinderTemplate(Class<? extends T> componentType) {
    this(new TypeMatcher(componentType, true));
  }
  
  private ComponentFinderTemplate(ComponentMatcher matcher) {
    if (matcher == null) throw new NullPointerException("The matcher should not be null");
    this.matcher = matcher;
    searchDescription = concat("component to be found using matcher ", matcher);
  }
  
  ComponentFinderTemplate<T> withTimeout(long newTimeout, TimeUnit unit) {
    if (unit == null) throw new NullPointerException("Time unit cannot be null");
    return withTimeout(unit.toMillis(newTimeout));
  }

  ComponentFinderTemplate<T> withTimeout(long newTimeout) {
    if (newTimeout < 0) throw new IllegalArgumentException("Timeout cannot be a negative number");
    this.timeout = newTimeout;
    return this;
  }

  /**
   * Finds a component by name or type using the given robot.
   * @param robot contains the underlying finding to delegate the search to.
   * @return a fixture capable of managing the found component.
   * @throws WaitTimedOutError if a component with the given name or of the given type could not be found.
   */
  public abstract ComponentFixture<T> using(Robot robot);

  /**
   * Finds the component using either by name or type.
   * @param robot contains the underlying finding to delegate the search to.
   * @return the found component.
   * @throws WaitTimedOutError if a component with the given name or of the given type could not be found.
   */
  final T findComponentWith(Robot robot) {
    ComponentFoundCondition condition = new ComponentFoundCondition(searchDescription, robot.finder(), matcher);
    pause(condition, timeout);
    return cast(condition.found());
  }
  
  abstract T cast(Component c);
}
