/*
 * Created on Nov 17, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expectLastCall;

import java.awt.*;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.driver.WindowDriver;
import org.junit.Test;

/**
 * Understands test methods for implementations of <code>{@link WindowFixture}</code>.
 * @param <T> the type of window supported by the fixture to test.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class WindowFixture_implementation_TestCase<T extends Window> extends
    ComponentFixture_Implementations_TestCase<Window> {

  abstract WindowDriver driver();

  abstract WindowFixture<T> fixture();

  @Test
  public final void should_move_to_front() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().moveToFront(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().moveToFront());
      }
    }.run();
  }

  @Test
  public final void should_move_to_back() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().moveToBack(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().moveToBack());
      }
    }.run();
  }

  @Test
  public final void should_require_size() {
    final Dimension size = new Dimension(800, 600);
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireSize(target(), size);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSize(size));
      }
    }.run();
  }

  @Test
  public final void should_move_to_point() {
    final Point p = new Point(6, 8);
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().moveTo(target(), p);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().moveTo(p));
      }
    }.run();
  }

  @Test
  public final void should_resize_height() {
    final int height = 68;
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().resizeHeightTo(target(), height);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().resizeHeightTo(height));
      }
    }.run();
  }

  @Test
  public final void should_resize_width() {
    final int width = 68;
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().resizeWidthTo(target(), width);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().resizeWidthTo(width));
      }
    }.run();
  }

  @Test
  public final void should_resize_width_and_height() {
    final Dimension size = new Dimension(800, 600);
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().resizeTo(target(), size);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().resizeTo(size));
      }
    }.run();
  }

  @Test
  public final void should_show() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().show(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().show());
      }
    }.run();
  }

  @Test
  public final void should_show_with_given_size() {
    final Dimension size = new Dimension(800, 600);
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().show(target(), size);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().show(size));
      }
    }.run();
  }

  @Test
  public final void should_close() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().close(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        fixture().close();
      }
    }.run();
  }
}
