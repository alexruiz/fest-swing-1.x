/*
 * Created on Oct 23, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.concat;

import java.awt.Component;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands validation of the state of a <code>{@link Component}</code>.
 *
 * @author Alex Ruiz
 */
public final class ComponentStateValidator {

  /**
   * Asserts that the <code>{@link Component}</code> is enabled and showing.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the target component.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInCurrentThread
  public static void validateIsEnabledAndShowing(Component c) {
    validateIsEnabled(c);
    validateIsShowing(c);
  }

  /**
   * Asserts that the <code>{@link Component}</code> is enabled.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the target component.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   */
  @RunsInCurrentThread
  public static void validateIsEnabled(Component c) {
    if (!c.isEnabled()) throw new IllegalStateException(concat("Expecting component ", format(c), " to be enabled"));
  }

  /**
   * Asserts that the <code>{@link Component}</code> is showing on the screen.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the target component.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInCurrentThread
  public static void validateIsShowing(Component c) {
    if (!c.isShowing()) throw componentNotShowingOnScreenFailure(c);
  }

  /**
   * Throws a <code>{@link IllegalStateException}</code> when a <code>{@link Component}</code> is not showing on the
   * screen.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the target component.
   * @return the thrown exception.
   */
  @RunsInCurrentThread
  public static IllegalStateException componentNotShowingOnScreenFailure(Component c) {
    return new IllegalStateException(concat("Expecting component ", format(c), " to be showing on the screen"));
  }

  private ComponentStateValidator() {}
}
