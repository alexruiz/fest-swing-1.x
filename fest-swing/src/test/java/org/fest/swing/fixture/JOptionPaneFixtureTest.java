/*
 * Created on Feb 13, 2007
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
import static org.fest.swing.test.builder.JButtons.button;
import static org.fest.swing.test.core.Regex.regex;

import java.util.regex.Pattern;

import javax.swing.JButton;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link JOptionPaneFixture}.
 * 
 * @author Alex Ruiz
 */
public class JOptionPaneFixtureTest extends JOptionPaneFixture_TestCase {
  // TODO Reorganize into smaller units

  private static JButton button;

  @BeforeClass
  public static void setUpHelperComponents() {
    button = button().createNew();
  }

  @Test
  public void should_require_title() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireTitle(target(), "A Title");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireTitle("A Title"));
      }
    }.run();
  }

  @Test
  public void should_require_title_matching_pattern() {
    final Pattern p = regex("Title");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireTitle(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireTitle(p));
      }
    }.run();
  }

  @Test
  public void should_require_message() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireMessage(target(), "A Message");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireMessage("A Message"));
      }
    }.run();
  }

  @Test
  public void should_require_message_matching_pattern() {
    final Pattern p = regex("Message");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireMessage(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireMessage(p));
      }
    }.run();
  }

  @Test
  public void should_require_options() {
    final Object[] options = new Object[0];
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireOptions(target(), options);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireOptions(options));
      }
    }.run();
  }

  @Test
  public void should_return_OK_button() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().okButton(target())).andReturn(button);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().okButton();
        assertThat(result.target()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_Cancel_button() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().cancelButton(target())).andReturn(button);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().cancelButton();
        assertThat(result.target()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_Yes_button() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().yesButton(target())).andReturn(button);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().yesButton();
        assertThat(result.target()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_No_button() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().noButton(target())).andReturn(button);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().noButton();
        assertThat(result.target()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_button_with_text() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().buttonWithText(target(), "A Button")).andReturn(button);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().buttonWithText("A Button");
        assertThat(result.target()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_button_with_text_matching_pattern() {
    final Pattern p = regex("Butt.*");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().buttonWithText(target(), p)).andReturn(button);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().buttonWithText(p);
        assertThat(result.target()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_require_error_message() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireErrorMessage(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireErrorMessage());
      }
    }.run();
  }

  @Test
  public void should_require_information_message() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireInformationMessage(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireInformationMessage());
      }
    }.run();
  }

  @Test
  public void should_require_warning_message() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireWarningMessage(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireWarningMessage());
      }
    }.run();
  }

  @Test
  public void should_require_question_message() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireQuestionMessage(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireQuestionMessage());
      }
    }.run();
  }

  @Test
  public void should_require_plain_message() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requirePlainMessage(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requirePlainMessage());
      }
    }.run();
  }
}
