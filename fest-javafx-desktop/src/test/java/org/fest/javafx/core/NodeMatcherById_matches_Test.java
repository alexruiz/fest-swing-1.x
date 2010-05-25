/*
 * Created on May 12, 2010
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
import static org.fest.javafx.test.builder.Nodes.node;
import static org.fest.javafx.test.query.NodeSetIdTask.updateId;
import static org.fest.javafx.test.query.NodeSetVisibleTask.makeNotVisible;
import static org.fest.javafx.test.query.NodeSetVisibleTask.makeVisible;

/**
 * Tests for <code>{@link NodeMatcherById#matches(javafx.scene.Node)}</code>.
 *
 * @author Alex Ruiz
 */
public class NodeMatcherById_matches_Test {

  private Node node;
  private NodeMatcherById matcher;

  @Before
  public void setUp() {
    node = node().withId("myNode").createNew();
    matcher = new NodeMatcherById("myNode", REQUIRE_VISIBLE);
  }

  @Test
  public void should_return_true_if_ids_and_visibility_match() {
    makeVisible(node);
    assertThat(matcher.matches(node)).isTrue();
  }

  @Test
  public void should_return_false_if_ids_are_not_equal() {
    updateId(node, "hello");
    assertThat(matcher.matches(node)).isFalse();
  }

  @Test
  public void should_return_false_if_visibility_does_not_match() {
    makeNotVisible(node);
    assertThat(matcher.matches(node)).isFalse();
  }

  @Test
  public void should_return_false_if_Node_is_null() {
    assertThat(matcher.matches(null)).isFalse();
  }
}
