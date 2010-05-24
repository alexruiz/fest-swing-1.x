/*
 * Created on May 24, 2010
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.test.node.Buttons.button;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link PropertyBuilder#value()}</code>.
 *
 * @author Alex Ruiz
 */
public class PropertyBuilder_value_Test {

  private PropertyBuilder builder;

  @Before
  public void setUp() {
    builder = new PropertyBuilder(button().withId("myButton").createNew());
  }

  @Test
  public void should_build_formatted_properties() {
    builder.add("id", "myButton");
    List<String> properties = new ArrayList<String>();
    properties.add("text='Click Me'");
    properties.add("visible=true");
    builder.add(properties);
    assertThat(builder.value()).isEqualTo("javafx.scene.control.Button[id='myButton', text='Click Me', visible=true]");
  }
}
