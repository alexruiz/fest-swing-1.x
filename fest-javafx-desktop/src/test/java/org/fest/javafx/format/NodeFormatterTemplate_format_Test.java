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

import java.lang.reflect.Method;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import org.junit.*;

import org.fest.mocks.EasyMockTemplate;
import org.fest.test.CodeToTest;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.test.ExpectedFailure.expect;
import static org.fest.util.Strings.concat;

/**
 * Tests for <code>{@link NodeFormatterTemplate#format(javafx.scene.Node)}</code>.
 *
 * @author Alex Ruiz
 */
public class NodeFormatterTemplate_format_Test {

  private static Method doFormatMethod;
  private ConcreteNodeFormatter formatter;
  
  @BeforeClass
  public static void setUpOnce() throws Exception {
    doFormatMethod = ConcreteNodeFormatter.class.getDeclaredMethod("doFormat", Node.class);
  }
  
  @Before
  public void setUp() {
    formatter = createMock(ConcreteNodeFormatter.class, doFormatMethod);
  }
  
  @Test
  public void should_format_node_only_if_node_type_is_supported() {
    final String formattedNode = "Hello World!";
    final Node node = createMock(Button.class);
    formatter.updateTargetType(node.getClass());
    new EasyMockTemplate(formatter) {
      @Override protected void expectations() {
        expect(formatter.doFormat(node)).andReturn(formattedNode);
      }
      
      @Override protected void codeToTest() {
        assertThat(formatter.format(node)).isEqualTo(formattedNode);
      }
    };
  }
  
  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_node_is_null() {
    formatter.format(null);
  }
  
  @Test
  public void should_throw_error_if_node_type_is_not_supported() {
    Class<? extends Node> supportedType = Button.class;
    formatter.updateTargetType(supportedType);
    String message = concat("This formatter only supports components of type ", supportedType.getName());
    expect(IllegalArgumentException.class).withMessage(message).on(new CodeToTest() {
      @Override public void run() {
        formatter.format(createMock(Text.class));
      }
    }); 
  }
  
  private static class ConcreteNodeFormatter extends NodeFormatterTemplate {
    private Class<? extends Node> targetType;

    @Override protected String doFormat(Node n) {
      return null;
    }
    
    void updateTargetType(Class<? extends Node> newTargetType) {
      targetType = newTargetType;
    }

    @Override public Class<? extends Node> targetType() {
      return targetType;
    }
  }
}
