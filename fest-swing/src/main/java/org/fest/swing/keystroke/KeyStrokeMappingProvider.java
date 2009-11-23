/*
 * Created on Mar 26, 2008
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
package org.fest.swing.keystroke;

import java.util.Collection;

/**
 * Understands how to provide a mapping between characters and <code>{@link javax.swing.KeyStroke}</code>s.
 * 
 * @author Alex Ruiz
 */
public interface KeyStrokeMappingProvider {

  /** Value to use when a key stroke is not using modifiers. */
  int NO_MASK = 0;
  
  /**
   * Returns a collection <code>{@link KeyStrokeMapping}</code>s to be used by <code>{@link KeyStrokeMap}</code>.
   * @return a <code>KeyStrokeMapping</code> collection.
   */
  Collection<KeyStrokeMapping> keyStrokeMappings();
}
