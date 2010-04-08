/*
 * Created on Apr 8, 2010
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

import static java.awt.event.InputEvent.*;
import static java.awt.event.InputEvent.ALT_GRAPH_MASK;
import static java.awt.event.InputEvent.SHIFT_MASK;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link ModifierToText#modifierToText(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class ModifierToText_modifierToText_Test {

  @Test
  public void should_return_SHIFT_MASK() {
    assertThat(ModifierToText.modifierToText(SHIFT_MASK | ALT_MASK)).isEqualTo("SHIFT_MASK");
  }

  @Test
  public void should_return_ALT_GRAPH_MASK() {
    assertThat(ModifierToText.modifierToText(ALT_GRAPH_MASK)).isEqualTo("ALT_GRAPH_MASK");
  }

  @Test
  public void should_return_NO_MASK() {
    assertThat(ModifierToText.modifierToText(0)).isEqualTo("NO_MASK");
  }
}
