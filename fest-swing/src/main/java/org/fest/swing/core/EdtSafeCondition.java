/*
 * Created on Mar 7, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.swing.edt.GuiActionRunner.execute;

import org.fest.assertions.Description;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.timing.Condition;

/**
 * Understands a <code>{@link Condition}</code> that is evaluated in the event dispatch thread (EDT.)
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public abstract class EdtSafeCondition extends Condition {

  /**
   * Creates a new </code>{@link EdtSafeCondition}</code>.
   * @param description describes this condition.
   */
  public EdtSafeCondition(String description) {
    super(description);
  }

  /**
   * Creates a new </code>{@link EdtSafeCondition}</code>.
   * @param description describes this condition.
   */
  public EdtSafeCondition(Description description) {
    super(description);
  }

  /**
   * Checks if the condition has been satisfied.
   * @return {@code true} if the condition has been satisfied, otherwise {@code false}.
   */
  @Override public final boolean test() {
    boolean result = execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInEDT() {
        return testInEDT();
      }
    });
    return result;
  }

  /**
   * Checks if the condition has been satisfied. This method is guaranteed to be executed in the event dispatch thread.
   * @return {@code true} if the condition has been satisfied, otherwise {@code false}.
   */
  protected abstract boolean testInEDT();
}
