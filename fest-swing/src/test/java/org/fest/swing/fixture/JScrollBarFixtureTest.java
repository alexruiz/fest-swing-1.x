/*
 * Created on Dec 25, 2007
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

import org.junit.Test;

/**
 * Tests for {@link JScrollBarFixture}.
 * 
 * @author Alex Ruiz
 */
public class JScrollBarFixtureTest extends JScrollBarFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldRequireValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireValue(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireValue(8));
      }
    }.run();
  }

  @Test
  public void shouldScrollBlockDown() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollBlockDown(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollBlockDown());
      }
    }.run();
  }

  @Test
  public void shouldScrollBlockDownTheGivenTimes() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollBlockDown(target(), 6);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollBlockDown(6));
      }
    }.run();
  }

  @Test
  public void shouldScrollBlockUp() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollBlockUp(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollBlockUp());
      }
    }.run();
  }

  @Test
  public void shouldScrollBlockUpTheGivenTimes() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollBlockUp(target(), 6);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollBlockUp(6));
      }
    }.run();
  }

  @Test
  public void shouldScrollToMaximum() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollToMaximum(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollToMaximum());
      }
    }.run();
  }

  @Test
  public void shouldScrollToMinimum() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollToMinimum(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollToMinimum());
      }
    }.run();
  }

  @Test
  public void shouldScrollToPosition() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollTo(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollTo(8));
      }
    }.run();
  }

  @Test
  public void shouldScrollUnitDown() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollUnitDown(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollUnitDown());
      }
    }.run();
  }

  @Test
  public void shouldScrollUnitDownTheGivenTimes() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollUnitDown(target(), 6);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollUnitDown(6));
      }
    }.run();
  }

  @Test
  public void shouldScrollUnitUp() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollUnitUp(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollUnitUp());
      }
    }.run();
  }

  @Test
  public void shouldScrollUnitUpTheGivenTimes() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().scrollUnitUp(target(), 6);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().scrollUnitUp(6));
      }
    }.run();
  }
}
