/*
 * Created on Jul 13, 2008
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

import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_B;
import static java.awt.event.KeyEvent.VK_C;
import static org.fest.swing.core.KeyPressInfo.keyCode;

import java.awt.Component;

import org.fest.swing.core.KeyPressInfo;
import org.junit.Test;

/**
 * Test case for implementations of {@link KeyboardInputSimulationFixture}
 * 
 * @param <T> the type of component supported by the fixture to test.
 * 
 * @author Alex Ruiz
 */
public abstract class KeyboardInputSimulationFixture_TestCase<T extends Component> extends
ComponentFixture_Implementations_TestCase<T> {
  @Test
  public final void should_press_and_release_key() {
    final KeyPressInfo keyPressInfo = keyCode(VK_A).modifiers(SHIFT_MASK);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().pressAndReleaseKey(target(), keyPressInfo);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().pressAndReleaseKey(keyPressInfo));
      }
    }.run();
  }

  @Test
  public void should_press_and_release_keys() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().pressAndReleaseKeys(target(), VK_A, VK_B, VK_C);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().pressAndReleaseKeys(VK_A, VK_B, VK_C));
      }
    }.run();
  }

  @Test
  public final void should_press_key() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().pressKey(target(), VK_A);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().pressKey(VK_A));
      }
    }.run();
  }

  @Test
  public final void should_release_key() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().releaseKey(target(), VK_A);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().releaseKey(VK_A));
      }
    }.run();
  }

  @Override
  abstract KeyboardInputSimulationFixture fixture();
}