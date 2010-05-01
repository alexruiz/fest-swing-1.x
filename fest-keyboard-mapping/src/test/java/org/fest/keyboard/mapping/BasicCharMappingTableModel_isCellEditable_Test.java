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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.keyboard.mapping.CharMapping.newCharMapping;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicCharMappingTableModel#isCellEditable(int, int)}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicCharMappingTableModel_isCellEditable_Test {

  private BasicCharMappingTableModel model;

  @Before
  public void setUp() {
    model = new BasicCharMappingTableModel();
    model.addOrReplace(newCharMapping("a", "A", "NO_MASK"));
  }

  @Test
  public void should_always_return_false() {
    assertThat(model.isCellEditable(0, 0)).isFalse();
    assertThat(model.isCellEditable(0, 1)).isFalse();
    assertThat(model.isCellEditable(0, 2)).isFalse();
  }
}
