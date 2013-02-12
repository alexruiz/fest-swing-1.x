/*
 * Created on Dec 20, 2009
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.Regex.regex;

import java.awt.Component;
import java.util.regex.Pattern;

import org.fest.swing.driver.ComponentDriver;
import org.fest.swing.driver.TextDisplayDriver;
import org.junit.Test;

/**
 * Understands test methods for implementations of {@link TextDisplayFixture}.
 * 
 * @param <T> the type of component supported by the fixture to test.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class TextDisplayFixture_TestCase<T extends Component> extends
ComponentFixture_Implementations_TestCase<T> {
  @Test
  public void should_require_text() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        textDisplayDriver().requireText(target(), "Some Text");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireText("Some Text"));
      }
    }.run();
  }

  @Test
  public void should_require_text_matching_pattern() {
    final Pattern pattern = regex(".");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        textDisplayDriver().requireText(target(), pattern);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireText(pattern));
      }
    }.run();
  }

  @Test
  public void should_return_text() {
    final String text = "Some Text";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(textDisplayDriver().textOf(target())).andReturn(text);
      }

      @Override
      protected void codeToTest() {
        assertThat(fixture().text()).isEqualTo(text);
      }
    }.run();
  }

  @SuppressWarnings("unchecked")
  private TextDisplayDriver<T> textDisplayDriver() {
    ComponentDriver driver = driver();
    assertThat(driver).isInstanceOf(TextDisplayDriver.class);
    return (TextDisplayDriver<T>) driver;
  }

  @Override
  abstract TextDisplayFixture fixture();
}
