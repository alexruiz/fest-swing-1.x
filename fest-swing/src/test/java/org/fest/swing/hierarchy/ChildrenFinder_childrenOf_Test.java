/*
 * Created on Oct 26, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.awt.Components.newComponentMock;
import static org.fest.swing.test.awt.Containers.newContainerMock;
import static org.fest.util.Collections.list;

import java.awt.Component;
import java.awt.Container;
import java.util.Collection;
import java.util.List;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.fest.util.Arrays;
import org.junit.*;

/**
 * Tests for <code>{@link ChildrenFinder#childrenOf(Component)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ChildrenFinder_childrenOf_Test extends EDTSafeTestCase {

  private Container container;
  private ChildrenFinderStrategy strategy1;
  private ChildrenFinderStrategy strategy2;

  private Component child1;
  private Component child2;
  private Component child3;

  private List<ChildrenFinderStrategy> originalStrategies;

  private ChildrenFinder finder;

  @Before public void setUp() {
    container = newContainerMock();
    strategy1 = childrenFinderStrategyMock();
    strategy2 = childrenFinderStrategyMock();
    child1 = newComponentMock();
    child2 = newComponentMock();
    child3 = newComponentMock();
    finder = new ChildrenFinder();
    originalStrategies = ChildrenFinder.strategies();
    ChildrenFinder.replaceStrategiesWith(list(strategy1, strategy2));
  }

  private ChildrenFinderStrategy childrenFinderStrategyMock() {
    return createMock(ChildrenFinderStrategy.class);
  }

  @After public void tearDown() {
    ChildrenFinder.replaceStrategiesWith(originalStrategies);
  }

  @Test
  public void should_return_children_in_Container_and_strategies() {
    new EasyMockTemplate(strategy1, strategy2, container) {
      protected void expectations() {
        expect(container.getComponents()).andReturn(Arrays.array(child1));
        expect(strategy1.nonExplicitChildrenOf(container)).andReturn(list(child2));
        expect(strategy2.nonExplicitChildrenOf(container)).andReturn(list(child3));
      }

      protected void codeToTest() {
        Collection<Component> children = finder.childrenOf(container);
        assertThat(children).containsOnly(child1, child2, child3);
      }
    };
  }

  @Test
  public void should_return_empty_Collection_if_Component_is_not_Container() {
    new EasyMockTemplate(strategy1, strategy2, container) {
      protected void expectations() {
      }

      protected void codeToTest() {
        Collection<Component> children = finder.childrenOf(child1);
        assertThat(children).isEmpty();
      }
    };
  }
}
