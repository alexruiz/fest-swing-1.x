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

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link BasicCharMappingTableModel#BasicCharMappingTableModel()}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicCharMappingTableModel_constructor_Test {

  @Test
  public void should_create_model_with_three_columns_and_zero_rows() {
    BasicCharMappingTableModel model = new BasicCharMappingTableModel();
    assertThat(model.getRowCount()).isEqualTo(0);
    assertThat(model.getColumnCount()).isEqualTo(3);
    assertThat(model.getColumnName(0)).isEqualTo("Character");
    assertThat(model.getColumnName(1)).isEqualTo("Key");
    assertThat(model.getColumnName(2)).isEqualTo("Modifier");
  }
}
