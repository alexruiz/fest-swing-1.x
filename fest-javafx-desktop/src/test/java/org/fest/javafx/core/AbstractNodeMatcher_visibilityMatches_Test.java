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

import javafx.scene.Node;

import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;
import static org.fest.javafx.test.core.ConcreteNode.createNode;

/**
 * Tests for <code>{@link AbstractNodeMatcher#visibilityMatches(javafx.scene.Node)}</code>.
 *
 * @author Alex Ruiz
 */
public class AbstractNodeMatcher_visibilityMatches_Test {

  private static Node node;

  @BeforeClass
  public static void setUpOnce() {
    node = createNode();
  }

  @Test
  public void should_match_if_visibility_is_not_required_and_Node_is_visible() {
    node.set$visible(true);
    ConcreteNodeMatcher matcher = matchAnyNode();
    assertThat(matcher.visibilityMatches(node)).isTrue();
  }

  @Test
  public void should_match_if_visibility_is_not_required_and_Node_is_not_visible() {
    node.set$visible(false);
    ConcreteNodeMatcher matcher = matchAnyNode();
    assertThat(matcher.visibilityMatches(node)).isTrue();
  }

  @Test
  public void should_match_if_visibility_is_required_and_Node_is_visible() {
    node.set$visible(true);
    assertThat(matchVisibleNode().visibilityMatches(node)).isTrue();
  }

  @Test
  public void should_not_match_if_visibility_is_required_and_Node_is_not_visible() {
    node.set$visible(false);
    ConcreteNodeMatcher matcher = matchVisibleNode();
    assertThat(matcher.visibilityMatches(node)).isFalse();
  }

  private static ConcreteNodeMatcher matchVisibleNode() {
    return new ConcreteNodeMatcher(REQUIRE_VISIBLE);
  }

  @Test
  public void should_not_match_if_Node_is_null() {
    ConcreteNodeMatcher matcher = matchAnyNode();
    assertThat(matcher.visibilityMatches(null));
  }

  private static ConcreteNodeMatcher matchAnyNode() {
    return new ConcreteNodeMatcher();
  }
}
