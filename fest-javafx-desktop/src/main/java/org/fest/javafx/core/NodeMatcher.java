/*
 * Created on May 10, 2010
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
package org.fest.javafx.core;

import javafx.scene.Node;

/**
 * Understands some search criteria to be used to look up <code>{@link Node}</code>s.
 *
 * @author Alex Ruiz
 */
public interface NodeMatcher {

  /**
   * Verifies that the given <code>{@link Node}</code> matches the search criteria specified in this matcher.
   * @param node the {@code Node} to verify.
   * @return <code>true</code> if the {@code Node} matches the search criteria specified in this matcher;
   * <code>false</code> otherwise.
   */
  boolean matches(Node node);

  /**
   * Resets the internal state of this matcher.
   * @param matchFound indicates whether a match has been found before resetting.
   */
  void reset(boolean matchFound);
}
