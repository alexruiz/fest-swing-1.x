/*
 * Created on May 6, 2010
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

/**
 * Understands visibility options for a node.
 *
 * @author Alex Ruiz
 */
public enum Visibility {

  /** Indicates that a node should be showing on the screen. */
  REQUIRE_VISIBLE,

  /** Indicates that it does not matter if a node is showing on the screen or not. */
  MAY_BE_VISIBLE
}
