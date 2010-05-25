/*
 * Created on May 21, 2010
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
package org.fest.javafx.format;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import org.junit.*;

import org.fest.test.CodeToTest;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.test.builder.Nodes.node;
import static org.fest.test.ExpectedFailure.expect;
import static org.fest.util.Strings.concat;

/**
 * Tests for <code>{@link BasicNodeFormatter#format(javafx.scene.Node)}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicNodeFormatter_format_Test {

  private BasicNodeFormatter formatter;

  @Before
  public void setUp() {
    formatter = new BasicNodeFormatter();
  }

  @Test
  public void should_format_node_only_if_node_type_is_supported() {
    String formatted = formatter.format(node().withId("MyNode").createNew());
    assertThat(formatted).isEqualTo("org.fest.javafx.test.node.TestNode[id='MyNode', disabled=false, visible=true]");
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_node_is_null() {
    formatter.format(null);
  }

  @Test
  public void should_throw_error_if_node_type_is_not_supported() {
    formatter = new BasicNodeFormatter() {
      @Override public Class<? extends Node> targetType() {
        return Button.class;
      }
    };
    String message = concat("This formatter only supports components of type ", Button.class.getName());
    expect(IllegalArgumentException.class).withMessage(message).on(new CodeToTest() {
      @Override public void run() {
        formatter.format(createMock(Text.class));
      }
    });
  }
}
