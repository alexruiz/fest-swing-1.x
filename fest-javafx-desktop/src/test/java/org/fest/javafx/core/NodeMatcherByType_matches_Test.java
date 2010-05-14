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
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;

/**
 * Tests for <code>{@link NodeMatcherByType#matches(javafx.scene.Node)}</code>.
 *
 * @author Alex Ruiz
 */
public class NodeMatcherByType_matches_Test {

  private Button node;
  private NodeMatcherByType matcher;

  @Before
  public void setUp() {
    node = new Button();
    matcher = new NodeMatcherByType(Button.class, REQUIRE_VISIBLE);
  }

  @Test
  public void should_return_true_if_types_and_visibility_match() {
    node.set$visible(true);
    assertThat(matcher.matches(node)).isTrue();
  }

  @Test
  public void should_return_true_if_types_are_compatible() {
    matcher = new NodeMatcherByType(Node.class);
    assertThat(matcher.matches(node)).isTrue();
  }

  @Test
  public void should_return_false_if_types_are_not_compatible() {
    matcher = new NodeMatcherByType(Text.class);
    assertThat(matcher.matches(node)).isFalse();
  }

  @Test
  public void should_return_false_if_visibility_does_not_match() {
    node.set$visible(false);
    assertThat(matcher.matches(node)).isFalse();
  }

  @Test
  public void should_return_false_if_Node_is_null() {
    assertThat(matcher.matches(null)).isFalse();
  }
}
