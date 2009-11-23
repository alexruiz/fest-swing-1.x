/*
 * Created on Oct 7, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.test.swing;

import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.query.ComponentHasFocusQuery.hasFocus;
import static org.fest.util.Strings.concat;

import java.awt.Component;

import org.fest.swing.timing.Condition;

/**
 * Understands a condition that checks that a <code>{@link Component}</code> has focus.
 *
 * @author Yvonne Wang
 */
public class ComponentHasFocusCondition extends Condition {

  private Component component;

  public static ComponentHasFocusCondition untilFocused(Component component) {
    return new ComponentHasFocusCondition(component);
  }

  private ComponentHasFocusCondition(Component component) {
    super(concat(format(component), " has focus"));
    this.component = component;
  }

  public boolean test() {
    return hasFocus(component);
  }

  @Override protected void done() {
    component = null;
  }
}
