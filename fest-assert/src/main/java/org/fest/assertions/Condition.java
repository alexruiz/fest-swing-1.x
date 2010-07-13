/*
 * Created on Sep 17, 2007
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
package org.fest.assertions;

import static org.fest.util.Strings.*;

/**
 * Understands a condition to be met by a given <code>{@link Object}</code>.
 * @param <T> the type of <code>Object</code> this condition accepts.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class Condition<T> {

  private String description;

  /**
   * Creates a new <code>{@link Condition}</code>.
   */
  public Condition() {}

  /**
   * Creates a new <code>{@link Condition}</code>.
   * @param description the description of this condition.
   */
  public Condition(String description) {
    as(description);
  }

  /**
   * Sets the description of this condition.
   * @param newDescription the description to set.
   * @return this condition.
   */
  public final Condition<T> as(String newDescription) {
    description = newDescription;
    return this;
  }

  final String addDescriptionTo(String s) {
    String descriptionToAdd = description();
    if (isEmpty(descriptionToAdd)) descriptionToAdd = getClass().getSimpleName();
    return concat(s, ":<", descriptionToAdd, ">");
  }

  /**
   * Returns the description of this condition, if any.
   * @return the description of this condition.
   */
  public final String description() { return description; }

  /**
   * Verifies that the given value satisfies this condition.
   * @param value the value to verify.
   * @return <code>true</code> if the given value satisfies this condition, <code>false</code> otherwise.
   */
  public abstract boolean matches(T value);
}
