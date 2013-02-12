/*
 * Created on Jul 5, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;

import org.junit.Test;

/**
 * Tests for {@link JToolBarFixture#floatTo(Point)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JToolBarFixture_float_Test extends JToolBarFixture_TestCase {
  @Test
  public void should_float_to_point() {
    final Point p = new Point(8, 6);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().floatTo(target(), p.x, p.y);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().floatTo(p));
      }
    }.run();
  }
}
