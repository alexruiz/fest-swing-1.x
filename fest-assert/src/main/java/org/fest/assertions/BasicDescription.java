/*
 * Created on Oct 29, 2008
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
package org.fest.assertions;

/**
 * Understands the default implementation of <code>{@link Description}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicDescription implements Description {

  private final String description;

  /**
   * Creates a new </code>{@link BasicDescription}</code>.
   * @param description the value of this description.
   */
  public BasicDescription(String description) {
    this.description = description;
  }

  /** @see org.fest.assertions.Description#value() */
  public String value() {
    return description;
  }
}
