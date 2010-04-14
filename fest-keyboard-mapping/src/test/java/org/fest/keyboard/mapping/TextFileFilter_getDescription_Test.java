/*
 * Created on Apr 14, 2010
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
package org.fest.keyboard.mapping;

import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link TextFileFilter#getDescription()}</code>.
 *
 * @author Alex Ruiz
 */
public class TextFileFilter_getDescription_Test {

  private static TextFileFilter filter;

  @BeforeClass
  public static void setUpOnce() {
    filter = new TextFileFilter();
  }

  @Test
  public void should_return_txt_as_description() {
    assertThat(filter.getDescription()).isEqualTo("Text Files (*.txt)");
  }
}
