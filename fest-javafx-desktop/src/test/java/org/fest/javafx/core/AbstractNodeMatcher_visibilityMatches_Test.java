/*
 * Created on May 11, 2010
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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import javafx.scene.Node;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link AbstractNodeMatcher#visibilityMatches(javafx.scene.Node)}</code>.
 *
 * @author Alex Ruiz
 */
public class AbstractNodeMatcher_visibilityMatches_Test {

  private Node node;

  @Before
  public void setUp() {
    node = createMock(Node.class);
  }

  @Test
  public void should_return_true_if_visibility_not_required_and_node_is_visible() {
    final AbstractNodeMatcher matcher = new ConcreteNodeMatcher();
    new EasyMockTemplate(node) {
      @Override protected void expectations() {
        expect(node.get$visible()).andReturn(true);
      }

      @Override protected void codeToTest() {
        assertThat(matcher.visibilityMatches(node)).isEqualTo(true);
      }
    };
  }

  private static class ConcreteNodeMatcher extends AbstractNodeMatcher {
    @Override public boolean matches(Node node) {
      return false;
    }
  }
}
