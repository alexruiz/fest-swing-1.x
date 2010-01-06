/*
 * Created on May 31, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Arrays.array;

import java.awt.Component;
import java.awt.Container;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link HierarchyBasedFocusOwnerFinder#focusOwner()}</code>.
 *
 * @author Alex Ruiz
 */
public class HierarchyBasedFocusOwnerFinder_focusOwner_Test {

  private ContainerFocusOwnerFinder delegate;
  private HierarchyRootsSource rootsSource;
  private HierarchyBasedFocusOwnerFinder finder;

  @Before public void setUp() {
    delegate = createMock(ContainerFocusOwnerFinder.class);
    rootsSource = createMock(HierarchyRootsSource.class);
    finder = new HierarchyBasedFocusOwnerFinder(delegate, rootsSource);
  }

  @Test
  public void should_return_focus_owner_from_delegate() {
    final Component focusOwner = createMock(Component.class);
    new EasyMockTemplate(delegate, rootsSource) {
      protected void expectations() {
        Container c = mockContainer();
        expect(rootsSource.existingHierarchyRoots()).andReturn(array(c));
        expect(delegate.focusOwnerOf(c)).andReturn(focusOwner);
      }

      protected void codeToTest() {
        assertThat(finder.focusOwner()).isSameAs(focusOwner);
      }
    }.run();
  }

  @Test
  public void should_return_null_if_delegate_did_not_find_focus_owner() {
    new EasyMockTemplate(delegate, rootsSource) {
      protected void expectations() {
        Container c = mockContainer();
        expect(rootsSource.existingHierarchyRoots()).andReturn(array(c));
        expect(delegate.focusOwnerOf(c)).andReturn(null);
      }

      protected void codeToTest() {
        assertThat(finder.focusOwner()).isNull();
      }
    }.run();
  }

  private static Container mockContainer() {
    return createMock(Container.class);
  }

  @Test
  public void should_return_null_if_there_are_not_any_root_Containers() {
    new EasyMockTemplate(delegate, rootsSource) {
      protected void expectations() {
        expect(rootsSource.existingHierarchyRoots()).andReturn(new Container[0]);
      }

      protected void codeToTest() {
        assertThat(finder.focusOwner()).isNull();
      }
    }.run();
  }
}
