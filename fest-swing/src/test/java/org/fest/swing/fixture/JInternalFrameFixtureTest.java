/*
 * Created on Dec 17, 2007
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

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;

/**
 * Tests for {@link JInternalFrameFixture}.
 * 
 * @author Alex Ruiz
 */
public class JInternalFrameFixtureTest extends JInternalFrameFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldMoveToFront() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().moveToFront(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().moveToFront());
      }
    }.run();
  }

  @Test
  public void shouldMoveToBack() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().moveToBack(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().moveToBack());
      }
    }.run();
  }

  @Test
  public void shouldDeiconify() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().deiconify(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().deiconify());
      }
    }.run();
  }

  @Test
  public void shouldIconify() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().iconify(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().iconify());
      }
    }.run();
  }

  @Test
  public void shouldMaximize() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().maximize(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().maximize());
      }
    }.run();
  }

  @Test
  public void shouldNormalize() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().normalize(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().normalize());
      }
    }.run();
  }

  @Test
  public void shouldClose() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().close(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        fixture().close();
      }
    }.run();
  }

  @Test
  public void shouldRequireSize() {
    final Dimension size = new Dimension(800, 600);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSize(target(), size);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSize(size));
      }
    }.run();
  }

  @Test
  public void shouldMoveToPoint() {
    final Point p = new Point(6, 8);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().moveTo(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().moveTo(p));
      }
    }.run();
  }

  @Test
  public void shouldResizeHeight() {
    final int height = 68;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().resizeHeightTo(target(), height);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().resizeHeightTo(height));
      }
    }.run();
  }

  @Test
  public void shouldResizeWidth() {
    final int width = 68;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().resizeWidthTo(target(), width);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().resizeWidthTo(width));
      }
    }.run();
  }

  @Test
  public void shouldResizeWidthAndHeight() {
    final Dimension size = new Dimension(800, 600);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().resizeTo(target(), size);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().resizeTo(size));
      }
    }.run();
  }

  @Test
  public void shouldBeContainerFixture() {
    assertThat(fixture()).isInstanceOf(AbstractContainerFixture.class);
  }
}
