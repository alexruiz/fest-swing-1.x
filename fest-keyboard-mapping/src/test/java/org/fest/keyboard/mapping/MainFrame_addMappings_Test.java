/*
 * Created on Apr 19, 2010
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

import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.JTableFixture;
import org.junit.Test;

/**
 * Tests for <code>{@link MainFrame}</code> that verify that character mappings are successfully recorded.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@GUITest
public class MainFrame_addMappings_Test extends MainFrame_TestCase {

  @Test
  public void should_create_mapping_from_entered_char() {
    JTableFixture table = frame.table();
    table.requireRowCount(0);
    frame.textBox().enterText("a");
    table.requireRowCount(1)
         .requireSelectedRows(0);
    table.requireContents(new String[][] {
        { "a", "A", "NO_MASK" }
    });
  }

  @Test
  public void should_create_a_mapping_per_entered_char() {
    JTableFixture table = frame.table();
    table.requireRowCount(0);
    frame.textBox().enterText("aAs");
    table.requireRowCount(3)
         .requireSelectedRows(2);
    table.requireContents(new String[][] {
        { "a", "A", "NO_MASK" },
        { "A", "A", "SHIFT_MASK" },
        { "s", "S", "NO_MASK" }
    });
  }
}
