/*
 * Created on Mar 2, 2008
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static java.awt.Color.BLUE;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for {@link JTableCellFixture#background()}.
 * 
 * @author Alex Ruiz
 */
public class JTableCellFixture_background_Test extends JTableCellFixture_withMockTable_TestCase {
  @Test
  public void should_return_background_color() {
    final ColorFixture colorFixture = new ColorFixture(BLUE);
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.backgroundAt(cell)).andReturn(colorFixture);
      }

      @Override
      protected void codeToTest() {
        ColorFixture result = fixture.background();
        assertThat(result).isSameAs(colorFixture);
      }
    }.run();
  }
}
