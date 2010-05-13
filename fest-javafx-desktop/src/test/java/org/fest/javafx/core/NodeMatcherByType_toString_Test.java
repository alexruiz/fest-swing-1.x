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

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link NodeMatcherByType#toString()}</code>.
 *
 * @author Alex Ruiz
 */
public class NodeMatcherByType_toString_Test {

  @Test
  public void should_implement_toString() {
    NodeMatcherByType matcher = new NodeMatcherByType(Node.class);
    assertThat(matcher.toString()).isEqualTo(
        "org.fest.javafx.core.NodeMatcherByType[type=javafx.scene.Node, visibility=MAY_BE_VISIBLE]");
  }
}
