/*
 * Created on Nov 19, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expectLastCall;

import java.awt.Component;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Understands test methods for implementations of <code>{@link FocusableComponentFixture}</code>.
 * @param <T> the type of component supported by the fixture to test.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class FocusableComponentFixture_TestCase<T extends Component> extends ComponentFixture_TestCase<T> {

  @Test
  public final void should_give_focus() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().focus(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().focus());
      }
    }.run();
  }

  @Test
  public final void should_require_focused() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireFocused(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireFocused());
      }
    }.run();
  }

  abstract FocusableComponentFixture fixture();
}
