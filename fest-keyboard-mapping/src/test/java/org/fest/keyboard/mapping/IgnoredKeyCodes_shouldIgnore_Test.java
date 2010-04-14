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

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static java.awt.event.KeyEvent.*;
import static java.util.Arrays.asList;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link IgnoredKeyCodes#shouldIgnore(int)}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class IgnoredKeyCodes_shouldIgnore_Test {

  private final int keyCode;
  private final boolean shouldBeIgnored;
  
  @Parameters
  public static Collection<Object[]> keyCodes() {
    Object[][] data = new Object[][] {
        { VK_BACK_SPACE, true },
        { VK_DELETE, true },
        { VK_DOWN, true },
        { VK_ENTER, true },
        { VK_ESCAPE, true },
        { VK_LEFT, true },
        { VK_RIGHT, true },
        { VK_SHIFT, true },
        { VK_TAB, true },
        { VK_UP, true },
        { VK_SPACE, false }
    };
    return asList(data);
  }
  
  public IgnoredKeyCodes_shouldIgnore_Test(int keyCode, boolean shouldBeIgnored) {
    this.keyCode = keyCode;
    this.shouldBeIgnored = shouldBeIgnored;
  }

  @Test
  public void should_indicate_whether_key_code_should_be_ignored_or_not() {
    assertThat(IgnoredKeyCodes.shouldIgnore(keyCode)).isEqualTo(shouldBeIgnored);
  }
}
