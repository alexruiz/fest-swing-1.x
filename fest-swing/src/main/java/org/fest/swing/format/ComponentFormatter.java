/*
 * Created on Dec 22, 2007
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
package org.fest.swing.format;

import java.awt.Component;

/**
 * Understands a formatter that returns a <code>String</code> representation of a given <code>{@link Component}</code>.
 *
 * @author Alex Ruiz
 */
public interface ComponentFormatter {

  /**
   * Returns a <code>String</code> representation of the given <code>{@link Component}</code>.
   * @param c the given <code>Component</code>.
   * @return a <code>String</code> representation of the given <code>Component</code>.
   */
  String format(Component c);

  /**
   * Returns the type of <code>{@link Component}</code> this formatter supports.
   * @return the type of <code>Component</code> this formatter supports.
   */
  Class<? extends Component> targetType();
}
