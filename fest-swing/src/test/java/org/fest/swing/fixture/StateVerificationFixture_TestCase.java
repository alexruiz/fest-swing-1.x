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

import static org.fest.swing.timing.Timeout.timeout;

import java.awt.Component;

import org.fest.swing.timing.Timeout;
import org.junit.Test;

/**
 * Tests case for implementations of {@link StateVerificationFixture}
 * 
 * @param <T> the type of component supported by the fixture to test.
 * 
 * @author Alex Ruiz
 */
public abstract class StateVerificationFixture_TestCase<T extends Component> extends
ComponentFixture_Implementations_TestCase<T> {
  @Test
  public final void should_require_disabled() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireDisabled(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireDisabled());
      }
    }.run();
  }

  @Test
  public final void should_require_enabled() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireEnabled(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireEnabled());
      }
    }.run();
  }

  @Test
  public final void should_require_enabled_using_timeout() {
    final Timeout timeout = timeout(2000);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireEnabled(target(), timeout);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireEnabled(timeout));
      }
    }.run();
  }

  @Test
  public final void should_require_not_visible() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireNotVisible(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireNotVisible());
      }
    }.run();
  }

  @Test
  public final void should_require_visible() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireVisible(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireVisible());
      }
    }.run();
  }

  @Override
  abstract StateVerificationFixture fixture();
}