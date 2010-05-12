/*
 * Created on May 12, 2010
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
 * Understands an implementation of <code>{@link AbstractNodeMatcher}</code> for testing.
 *
 * @author Alex Ruiz
 */
class ConcreteNodeMatcher extends AbstractNodeMatcher {
  
  public ConcreteNodeMatcher() {}

  public ConcreteNodeMatcher(Visibility visibility) {
    super(visibility);
  }

  @Override public boolean matches(Node node) {
    return false;
  }
}