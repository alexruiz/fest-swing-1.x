/*
 * Created on Jun 10, 2007
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

import javax.swing.AbstractButton;

import org.fest.swing.driver.AbstractButtonDriver;
import org.junit.Test;

/**
 * Understands test methods for implementations of {@link TwoStateButtonFixture}.
 * 
 * @param <T> the type of window supported by the fixture to test.
 * 
 * @author Alex Ruiz
 */
public abstract class TwoStateButtonFixture_TestCase<T extends AbstractButton> extends
ComponentFixture_Implementations_TestCase<T> {
  @Override
  abstract AbstractButtonDriver driver();

  @Override
  abstract TwoStateButtonFixture fixture();

  @Test
  public void should_require_not_selected() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireNotSelected(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireNotSelected());
      }
    }.run();
  }

  @Test
  public void should_require_selected() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelected(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelected());
      }
    }.run();
  }
}
