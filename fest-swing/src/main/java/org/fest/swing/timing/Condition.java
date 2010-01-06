/*
 * Created on Apr 14, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.timing;

import org.fest.assertions.BasicDescription;
import org.fest.assertions.Description;

/**
 * Understands a condition to verify, usually used in the method <code>{@link Pause#pause(Condition)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class Condition {

  private static final String NO_DESCRIPTION = "";
  
  private final Description description;

  /**
   * Creates a new <code>{@link Condition}</code>.
   * @param description describes this condition.
   */
  public Condition(String description) {
    this(new BasicDescription(description));
  }

  /**
   * Creates a new </code>{@link Condition}</code>.
   * @param description the description of this condition.
   */
  public Condition(Description description) {
    this.description = description;
  }

  /**
   * Checks if the condition has been satisfied.
   * @return <code>true</code> if the condition has been satisfied, otherwise <code>false</code>.
   */
  public abstract boolean test();

  /**
   * Returns the <code>String</code> representation of this condition, which is its description.
   * @return the description of this condition.
   */
  @Override public final String toString() {
    return description != null ? description.value() : NO_DESCRIPTION;
  }

  /**
   * Notification that this condition has been evaluated. This method is invoked by
   * <code>{@link Pause#pause(Condition)}</code> (and all overloaded methods) when this condition is evaluated (either
   * it was satisfied or it timed-out.) This is a good place to do any necessary resource cleanup.
   */
  protected void done() {}
}
