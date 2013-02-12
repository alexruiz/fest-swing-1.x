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

import static org.fest.swing.fixture.JToolBarFixture.UnfloatConstraint.EAST;
import static org.fest.swing.fixture.JToolBarFixture.UnfloatConstraint.NORTH;
import static org.fest.swing.fixture.JToolBarFixture.UnfloatConstraint.SOUTH;
import static org.fest.swing.fixture.JToolBarFixture.UnfloatConstraint.WEST;
import static org.fest.util.Lists.newArrayList;

import java.util.Collection;

import org.fest.swing.fixture.JToolBarFixture.UnfloatConstraint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link JToolBarFixture#unfloat(UnfloatConstraint)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JToolBarFixture_unfloat_withConstraint_Test extends JToolBarFixture_TestCase {
  private final UnfloatConstraint constraint;

  @Parameters
  public static Collection<Object[]> constraints() {
    return newArrayList(new Object[][] {
        { NORTH }, { EAST }, { SOUTH }, { WEST }
      });
  }

  public JToolBarFixture_unfloat_withConstraint_Test(UnfloatConstraint constraint) {
    this.constraint = constraint;
  }

  @Test
  public void should_unfloat_using_giving_constraint() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().unfloat(target(), constraint.value);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().unfloat(constraint));
      }
    }.run();
  }
}
