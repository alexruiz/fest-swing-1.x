/*
 * Created on Apr 1, 2010
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

import static org.easymock.classextension.EasyMock.createMock;

import org.fest.swing.hierarchy.ComponentHierarchy;

/**
 * Understands implementations of <code>{@link ComponentHierarchy}</code> to be used for testing purposes.
 *
 * @author Alex Ruiz
 */
public final class ComponentHierarchies {

  public static ComponentHierarchy newComponentHierarchyMock() {
    return createMock(ComponentHierarchy.class);
  }

  private ComponentHierarchies() {}
}
