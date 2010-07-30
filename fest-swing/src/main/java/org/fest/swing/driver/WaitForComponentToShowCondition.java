/*
 * Created on Jul 17, 2008
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
package org.fest.swing.driver;

import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.query.ComponentShowingQuery.isShowing;
import static org.fest.util.Strings.concat;

import java.awt.Component;

import org.fest.swing.timing.Condition;

/**
 * Understands a condition that verifies that a <code>{@link Component}</code> is showing on the screen.
 *
 * @author Yvonne Wang
 */
public class WaitForComponentToShowCondition extends Condition {

  private Component c;

  /**
   * Creates a new </code>{@link WaitForComponentToShowCondition}</code>.
   * @param c the {@code Component} to verify.
   * @return the created condition.
   * @throws NullPointerException if the {@code Component} is {@code null}.
   */
  public static WaitForComponentToShowCondition untilIsShowing(Component c) {
    return new WaitForComponentToShowCondition(c);
  }

  private WaitForComponentToShowCondition(Component c) {
    super(concat("Component ", format(c), " to show on the screen"));
    if (c == null) throw new NullPointerException("The component to verify should not be null");
    this.c = c;
  }

  /**
   * Indicates whether the <code>{@link Component}</code> in this condition is showing on the screen or not.
   * @return {@code true} if the {@code Component} in this condition is showing on the screen,
   * {@code false} otherwise
   */
  @Override
  public boolean test() {
    return isShowing(c);
  }

  /** ${@inheritDoc} */
  @Override protected void done() {
    c = null;
  }
}
