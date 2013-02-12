/*
 * Created on Aug 5, 2009
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
import static org.fest.swing.test.builder.JButtons.button;

import javax.swing.JButton;

import org.fest.swing.hierarchy.SingleComponentHierarchy;
import org.junit.Test;

/**
 * Tests for {@link SingleComponentHierarchy#contains(java.awt.Component)}.
 * 
 * @author Alex Ruiz
 */
public class SingleComponentHierarchy_contains_Test extends SingleComponentHierarchy_TestCase {
  @Test
  public void should_return_true_if_delegate_contains_Component_and_root_contains_Component() {
    final JButton button = button().createNew();
    root.add(button);
    new EasyMockTemplate(hierarchyDelegate) {
      @Override
      protected void expectations() {
        expect(hierarchyDelegate.contains(button)).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        assertThat(hierarchy.contains(button)).isTrue();
      }
    }.run();
  }

  @Test
  public void should_return_false_if_delegate_contains_Component_and_root_does_not_contain_Component() {
    final JButton button = button().createNew();
    new EasyMockTemplate(hierarchyDelegate) {
      @Override
      protected void expectations() {
        expect(hierarchyDelegate.contains(button)).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        assertThat(hierarchy.contains(button)).isFalse();
      }
    }.run();
  }

  @Test
  public void should_return_false_if_delegate_does_not_contain_Component_but_root_does() {
    final JButton button = button().createNew();
    root.add(button);
    new EasyMockTemplate(hierarchyDelegate) {
      @Override
      protected void expectations() {
        expect(hierarchyDelegate.contains(button)).andReturn(false);
      }

      @Override
      protected void codeToTest() {
        assertThat(hierarchy.contains(button)).isFalse();
      }
    }.run();
  }

  @Test
  public void should_return_false_if_both_delegate_and_root_do_not_contain_Component() {
    final JButton button = button().createNew();
    new EasyMockTemplate(hierarchyDelegate) {
      @Override
      protected void expectations() {
        expect(hierarchyDelegate.contains(button)).andReturn(false);
      }

      @Override
      protected void codeToTest() {
        assertThat(hierarchy.contains(button)).isFalse();
      }
    }.run();
  }
}
