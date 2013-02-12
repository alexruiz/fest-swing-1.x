/*
 * Created on Dec 29, 2009
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

import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.test.core.Regex.regex;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Tests for {@link JListFixture#clickItem(Pattern)}.
 * 
 * @author Alex Ruiz
 */
public class JListFixture_clickItemByPattern_Test extends JListFixture_TestCase {
  @Test
  public void should_click_item() {
    final Pattern pattern = regex("Hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clickItem(target(), pattern, LEFT_BUTTON, 1);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().clickItem(pattern));
      }
    }.run();
  }
}
