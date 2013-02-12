/*
 * Created on Nov 19, 2009
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

import static org.fest.swing.core.MouseButton.MIDDLE_BUTTON;
import static org.fest.swing.core.MouseClickInfo.middleButton;

import java.awt.Component;

import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.junit.Test;

/**
 * Understands test methods for implementations of {@link MouseInputSimulationFixture}.
 * 
 * @param <T> the type of component supported by the fixture to test.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class MouseInputSimulationFixture_TestCase<T extends Component> extends
ComponentFixture_Implementations_TestCase<T> {
  @Test
  public final void should_click() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().click(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().click());
      }
    }.run();
  }

  @Test
  public final void should_click_using_given_mouse_button() {
    final MouseButton button = MIDDLE_BUTTON;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().click(target(), button);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().click(button));
      }
    }.run();
  }

  @Test
  public final void should_click_using_given_MouseClickInfo() {
    final MouseClickInfo mouseClickInfo = middleButton().times(2);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().click(target(), mouseClickInfo);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().click(mouseClickInfo));
      }
    }.run();
  }

  @Test
  public final void should_double_click() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().doubleClick(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().doubleClick());
      }
    }.run();
  }

  @Test
  public final void should_right_click() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().rightClick(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().rightClick());
      }
    }.run();
  }

  @Override
  abstract MouseInputSimulationFixture fixture();
}
