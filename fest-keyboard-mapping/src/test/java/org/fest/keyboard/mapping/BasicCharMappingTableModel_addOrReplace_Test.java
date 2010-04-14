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
import static org.fest.keyboard.mapping.CharMapping.newCharMapping;

/**
 * Tests for <code>{@link BasicCharMappingTableModel#addOrReplace(CharMapping)}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicCharMappingTableModel_addOrReplace_Test {

  private BasicCharMappingTableModel model;
  
  @Before
  public void setUp() {
    model = new BasicCharMappingTableModel();
    assertThatRowCountIs(0);
  }
  
  @Test
  public void should_add_mapping() {
    CharMapping mapping = newCharMapping("a", "A", "NO_MASK");
    model.addOrReplace(mapping);
    assertThatRowCountIs(1);
    assertThatContainsMappingInRow(mapping, 0);
  }

  @Test
  public void should_replace_existing_mapping_with_same_character() {
    model.addOrReplace(newCharMapping("a", "A", "NO_MASK"));
    CharMapping mapping = newCharMapping("a", "Q", "SHIFT_MASK");
    model.addOrReplace(mapping);
    assertThatRowCountIs(1);
    assertThatContainsMappingInRow(mapping, 0);
  }
  
  private void assertThatRowCountIs(int expected) {
    assertThat(model.getRowCount()).isEqualTo(expected);
  }

  private void assertThatContainsMappingInRow(CharMapping mapping, int row) {
    assertThat(model.getValueAt(row, 0)).isEqualTo(mapping.character);
    assertThat(model.getValueAt(row, 1)).isEqualTo(mapping.keyCode);
    assertThat(model.getValueAt(row, 2)).isEqualTo(mapping.modifier);
  }
}
