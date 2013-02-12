/*
 * Created on May 31, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.awt.TestComponents.singletonComponentMock;
import static org.fest.swing.test.awt.TestContainers.singletonContainerMock;
import static org.fest.util.Arrays.array;

import java.awt.Component;
import java.awt.Container;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link HierarchyBasedFocusOwnerFinder#focusOwner()}.
 * 
 * @author Alex Ruiz
 */
public class HierarchyBasedFocusOwnerFinder_focusOwner_Test {
  private static Container container;

  private ContainerFocusOwnerFinder delegate;
  private HierarchyRootsSource rootsSource;
  private HierarchyBasedFocusOwnerFinder finder;

  @BeforeClass
  public static void setUpOnce() {
    container = singletonContainerMock();
  }

  @Before
  public void setUp() {
    delegate = createMock(ContainerFocusOwnerFinder.class);
    rootsSource = createMock(HierarchyRootsSource.class);
    finder = new HierarchyBasedFocusOwnerFinder(delegate, rootsSource);
  }

  @Test
  public void should_return_focus_owner_from_delegate() {
    final Component focusOwner = singletonComponentMock();
    new EasyMockTemplate(delegate, rootsSource) {
      @Override
      protected void expectations() {
        expect(rootsSource.existingHierarchyRoots()).andReturn(array(container));
        expect(delegate.focusOwnerOf(container)).andReturn(focusOwner);
      }

      @Override
      protected void codeToTest() {
        Component foundFocusOwner = finder.focusOwner();
        assertThat(foundFocusOwner).isSameAs(focusOwner);
      }
    }.run();
  }

  @Test
  public void should_return_null_if_delegate_did_not_find_focus_owner() {
    new EasyMockTemplate(delegate, rootsSource) {
      @Override
      protected void expectations() {
        expect(rootsSource.existingHierarchyRoots()).andReturn(array(container));
        expect(delegate.focusOwnerOf(container)).andReturn(null);
      }

      @Override
      protected void codeToTest() {
        Component foundFocusOwner = finder.focusOwner();
        assertThat(foundFocusOwner).isNull();
      }
    }.run();
  }

  @Test
  public void should_return_null_if_there_are_not_any_root_Containers() {
    new EasyMockTemplate(delegate, rootsSource) {
      @Override
      protected void expectations() {
        expect(rootsSource.existingHierarchyRoots()).andReturn(new Container[0]);
      }

      @Override
      protected void codeToTest() {
        Component foundFocusOwner = finder.focusOwner();
        assertThat(foundFocusOwner).isNull();
      }
    }.run();
  }
}
