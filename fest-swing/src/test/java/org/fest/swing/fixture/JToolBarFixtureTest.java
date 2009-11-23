/*
 * Created on Jul 5, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expectLastCall;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.fixture.JToolBarFixture.UnfloatConstraint.*;

import java.awt.Point;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JToolBarFixture}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JToolBarFixtureTest extends JToolBarFixture_TestCase {

  // TODO Reorganize into smaller units

  @Test
  public void shouldFloatToPoint() {
    final Point p = new Point(8, 6);
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().floatTo(target(), p.x, p.y);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().floatTo(p));
      }
    }.run();
  }

  @Test
  public void shouldUnfloat() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().unfloat(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().unfloat());
      }
    }.run();
  }

  @Test
  public void shouldBeContainerFixture() {
    assertThat(fixture()).isInstanceOf(ContainerFixture.class);
  }

  @Test
  public void shouldUnfloatUsingGivingConstraint() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().unfloat(target(), NORTH.value);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().unfloat(NORTH));
      }
    }.run();
  }

  // TODO parameterize
  public Object[][] unfloatConstraints() {
    return new Object[][] { { NORTH }, { EAST }, { SOUTH }, { WEST } };
  }
}
