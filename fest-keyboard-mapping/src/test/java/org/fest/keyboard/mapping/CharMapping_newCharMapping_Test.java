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

import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import org.junit.*;

import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;
import static java.awt.event.KeyEvent.*;
import static java.lang.System.currentTimeMillis;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link CharMapping#newCharMapping(java.awt.event.KeyEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMapping_newCharMapping_Test {

  private KeyEvent keyEvent;
  
  @Before
  public void setUp() {
    JComponent source = createMock(JComponent.class);
    keyEvent = new KeyEvent(source, KEY_PRESSED, currentTimeMillis(), SHIFT_DOWN_MASK, VK_A, 'a');
  }
  
  @Test
  public void should_create_CharMapping_from_KeyEvent() throws MappingNotFoundError {
    CharMapping mapping = CharMapping.newCharMapping(keyEvent);
    assertThat(mapping.character).isEqualTo("a");
    assertThat(mapping.keyCode).isEqualTo("A");
    assertThat(mapping.modifier).isEqualTo("SHIFT_MASK");
  }
}
