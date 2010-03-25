/*
 * Created on Feb 13, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JButtons.button;
import static org.fest.swing.test.core.Regex.regex;

import java.util.regex.Pattern;

import javax.swing.JButton;
import org.fest.mocks.EasyMockTemplate;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link JOptionPaneFixture}</code>.
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
      protected void expectations() {
        driver().requireTitle(target(), "A Title");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireTitle("A Title"));
      }
    }.run();
  }

  @Test
  public void should_require_title_matching_pattern() {
    final Pattern p = regex("Title");
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireTitle(target(), p);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireTitle(p));
      }
    }.run();
  }

  @Test
  public void should_require_message() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireMessage(target(), "A Message");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireMessage("A Message"));
      }
    }.run();
  }

  @Test
  public void should_require_message_matching_pattern() {
    final Pattern p = regex("Message");
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireMessage(target(), p);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireMessage(p));
      }
    }.run();
  }

  @Test
  public void should_require_options() {
    final Object[] options = new Object[0];
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireOptions(target(), options);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireOptions(options));
      }
    }.run();
  }

  @Test
  public void should_return_OK_button() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().okButton(target())).andReturn(button);
      }

      protected void codeToTest() {
        JButtonFixture result = fixture().okButton();
        assertThat(result.component()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_Cancel_button() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().cancelButton(target())).andReturn(button);
      }

      protected void codeToTest() {
        JButtonFixture result = fixture().cancelButton();
        assertThat(result.component()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_Yes_button() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().yesButton(target())).andReturn(button);
      }

      protected void codeToTest() {
        JButtonFixture result = fixture().yesButton();
        assertThat(result.component()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_No_button() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().noButton(target())).andReturn(button);
      }

      protected void codeToTest() {
        JButtonFixture result = fixture().noButton();
        assertThat(result.component()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_button_with_text() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().buttonWithText(target(), "A Button")).andReturn(button);
      }

      protected void codeToTest() {
        JButtonFixture result = fixture().buttonWithText("A Button");
        assertThat(result.component()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_return_button_with_text_matching_pattern() {
    final Pattern p = regex("Butt.*");
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().buttonWithText(target(), p)).andReturn(button);
      }

      protected void codeToTest() {
        JButtonFixture result = fixture().buttonWithText(p);
        assertThat(result.component()).isSameAs(button);
      }
    }.run();
  }

  @Test
  public void should_require_error_message() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireErrorMessage(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireErrorMessage());
      }
    }.run();
  }

  @Test
  public void should_require_information_message() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireInformationMessage(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireInformationMessage());
      }
    }.run();
  }

  @Test
  public void should_require_warning_message() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireWarningMessage(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireWarningMessage());
      }
    }.run();
  }

  @Test
  public void should_require_question_message() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireQuestionMessage(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireQuestionMessage());
      }
    }.run();
  }

  @Test
  public void should_require_plain_message() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requirePlainMessage(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requirePlainMessage());
      }
    }.run();
  }
}
