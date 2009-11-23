/*
 * Created on Jun 18, 2007
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
package org.fest.swing.core;

import java.awt.Component;

/**
 * Understands whether a <code>{@link Component}</code> matches some desired criteria.
 * 
 * @author Alex Ruiz
 */
public interface ComponentMatcher {
  
  /**
   * Verifies that the given <code>{@link Component}</code> matches some lookup criteria.
   * @param c the <code>Component</code> to verify. 
   * @return <code>true</code> if the given <code>Component</code> matches some lookup criteria, otherwise 
   * <code>false</code>. 
   */
  boolean matches(Component c);
}
