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

import org.fest.mocks.EasyMockTemplate;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;

/**
 * Tests for <code>{@link NodeMatcherByType#matches(javafx.scene.Node)}</code>.
 *
 * @author Alex Ruiz
 */
public class NodeMatcherByType_matches_Test {

  private Node node;
  
  @Before
  public void setUp() {
    node = createMock(Button.class);
  }
  
  @Test
  public void should_match_if_types_are_equal_and_visibility_matches() {
    new EasyMockTemplate(node) {
      @Override protected void expectations() {
        expect(node.get$visible()).andReturn(true);
      }
      
      @Override protected void codeToTest() {
        assertThat(matchVisibleButton().matches(node)).isTrue();
      }
    }.run();
  }
  
  @Test
  public void should_not_match_if_types_not_compatible() {
    NodeMatcherByType matcher = new NodeMatcherByType(Text.class);
    assertThat(matcher.matches(node)).isFalse();
  }
  
  @Test
  public void should_not_match_if_visibility_does_not_match() {
    new EasyMockTemplate(node) {
      @Override protected void expectations() {
        expect(node.get$visible()).andReturn(false);
      }
      
      @Override protected void codeToTest() {
        assertThat(matchVisibleButton().matches(node)).isFalse();
      }
    }.run();
  }
  
  @Test
  public void should_not_match_null_Node() {
    assertThat(matchVisibleButton().matches(null)).isFalse();
  }

  private static NodeMatcherByType matchVisibleButton() {
    return new NodeMatcherByType(Button.class, REQUIRE_VISIBLE);
  }
}
