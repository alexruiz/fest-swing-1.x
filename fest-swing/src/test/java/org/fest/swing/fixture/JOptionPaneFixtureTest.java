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
 * Copyright @2007-2009 the original author or authors.
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
  public void shouldRequireTitle() {
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
  public void shouldRequireTitleMatchingPattern() {
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
  public void shouldRequireMessage() {
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
  public void shouldRequireMessageMatchingPattern() {
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
  public void shouldRequireOptions() {
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
  public void shouldReturnOkButton() {
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
  public void shouldReturnCancelButton() {
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
  public void shouldReturnYesButton() {
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
  public void shouldReturnNoButton() {
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
  public void shouldReturnButtonWithText() {
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
  public void shouldReturnButtonWithTextMatchingPattern() {
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
  public void shouldRequireErrorMessage() {
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
  public void shouldRequireInformationMessage() {
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
  public void shouldRequireWarningMessage() {
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
  public void shouldRequireQuestionMessage() {
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
  public void shouldRequirePlainMessage() {
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
