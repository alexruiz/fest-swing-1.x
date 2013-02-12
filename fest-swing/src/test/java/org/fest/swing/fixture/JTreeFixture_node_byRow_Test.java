/*
 * Created on Dec 26, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for {@link JTreeFixture#node(int)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeFixture_node_byRow_Test extends JTreeFixture_TestCase {
  @Test
  public void should_return_row() {
    final int row = 6;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().checkRowInBounds(target(), row);
      }

      @Override
      protected void codeToTest() {
        JTreeNodeFixture nodeFixture = fixture().node(row);
        assertThat(nodeFixture).isInstanceOf(JTreeRowFixture.class);
        JTreeRowFixture rowFixture = (JTreeRowFixture) nodeFixture;
        assertThat(rowFixture.index()).isEqualTo(row);
      }
    }.run();
  }
}
