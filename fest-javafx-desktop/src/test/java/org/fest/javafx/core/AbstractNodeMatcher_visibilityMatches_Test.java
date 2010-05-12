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
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;

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
  public void should_always_match_if_visibility_is_not_required() {
    assertThat(matchAnyNode().visibilityMatches(node)).isTrue();
  }

  @Test
  public void should_match_if_visibility_is_required_and_Node_is_visible() {
    new EasyMockTemplate(node) {
      @Override protected void expectations() {
        expect(node.get$visible()).andReturn(true);
      }

      @Override protected void codeToTest() {
        assertThat(matchVisibleNode().visibilityMatches(node)).isTrue();
      }
    }.run();
  }

  @Test
  public void should_not_match_if_visibility_is_required_and_Node_is_not_visible() {
    new EasyMockTemplate(node) {
      @Override protected void expectations() {
        expect(node.get$visible()).andReturn(false);
      }

      @Override protected void codeToTest() {
        assertThat(matchVisibleNode().visibilityMatches(node)).isFalse();
      }
    }.run();
  }

  private static ConcreteNodeMatcher matchVisibleNode() {
    return new ConcreteNodeMatcher(REQUIRE_VISIBLE);
  }
  
  @Test
  public void should_not_match_if_Node_is_null() {
    assertThat(matchAnyNode().visibilityMatches(null));
  }

  private static ConcreteNodeMatcher matchAnyNode() {
    return new ConcreteNodeMatcher();
  }
}
