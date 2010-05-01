/*
 * Created on May 1, 2010
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

import static java.awt.Font.PLAIN;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.Font;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link Messages#fontStyleAsCSS(java.awt.Font)}</code>.
 *
 * @author Alex Ruiz
 */
public class Messages_fontStyleAsCSS_Test {

  private static Messages messages;

  @BeforeClass
  public static void setUpOnce() {
    messages = new Messages();
  }

  @Test
  public void should_return_font_style_as_CSS() {
    Font font = new Font("Courier New", PLAIN, 10);
    String msg = messages.fontStyleAsCSS(font);
    assertThat(msg).isEqualTo("font-family:'Courier New';font-size:10;");
  }
}
