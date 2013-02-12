/*
 * Created on Jul 1, 2007
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
 * Tests for {@link JSliderFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JSliderFixtureTest extends JSliderFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldSlideToValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().slide(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().slideTo(8));
      }
    }.run();
  }

  @Test
  public void shouldSlideToMax() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().slideToMaximum(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().slideToMaximum());
      }
    }.run();
  }

  @Test
  public void shouldSlideToMin() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().slideToMinimum(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().slideToMinimum());
      }
    }.run();
  }
}
