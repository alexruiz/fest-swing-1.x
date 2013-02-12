/*
 * Created on Oct 26, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.hierarchy;

import java.awt.Component;
import java.util.Collection;

/**
 * Understands a subclass of {@link ChildrenFinder} which methods have been overriden to be public, allowing us to
 * create mocks.
 * 
 * @author Alex Ruiz
 */
public class MockChildrenFinder extends ChildrenFinder {
  public static ChildrenFinder mock() {
    return createMock(MockChildrenFinder.class);
  }

  @Override
  public Collection<Component> childrenOf(Component c) {
    return null;
  }

  public MockChildrenFinder() {}
}
