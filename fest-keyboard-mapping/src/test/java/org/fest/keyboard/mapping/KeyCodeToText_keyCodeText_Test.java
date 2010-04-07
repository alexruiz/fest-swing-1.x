/*
 * Created on Apr 7, 2010
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

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static java.awt.event.KeyEvent.*;
import static java.util.Arrays.asList;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link KeyCodeToText#keyCodeText(int)}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class KeyCodeToText_keyCodeText_Test {

  private final int keyCode;
  private final String keyCodeText;

  @Parameters
  public static Collection<Object[]> keyCodes() {
    Object[][] data = new Object[][] { 
        { VK_A, "A" }, 
        { VK_SPACE, "SPACE" }, 
        { VK_EQUALS, "EQUALS" }
    };
    return asList(data);
  }
  
  public KeyCodeToText_keyCodeText_Test(int keyCode, String keyCodeText) {
    this.keyCode = keyCode;
    this.keyCodeText = keyCodeText;
  }
  
  @Test
  public void should_return_text_of_key_code() {
    assertThat(KeyCodeToText.keyCodeText(keyCode)).isEqualTo(keyCodeText);
  }
}
