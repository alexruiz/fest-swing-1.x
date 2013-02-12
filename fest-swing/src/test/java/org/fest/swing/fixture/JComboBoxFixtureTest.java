/*
 * Created on Apr 9, 2007
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
import static org.fest.swing.test.builder.JLists.list;
import static org.fest.swing.test.core.Regex.regex;
import static org.fest.util.Arrays.array;

import java.util.regex.Pattern;

import javax.swing.JList;

import org.fest.swing.cell.JComboBoxCellReader;
import org.junit.Test;

/**
 * Tests for {@link JComboBoxFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JComboBoxFixtureTest extends JComboBoxFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldReturnContents() {
    final String[] contents = array("Frodo", "Sam");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().contentsOf(target())).andReturn(contents);
      }

      @Override
      protected void codeToTest() {
        Object[] result = fixture().contents();
        assertThat(result).isSameAs(contents);
      }
    }.run();
  }

  @Test
  public void shouldReplaceText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().replaceText(target(), "Hello");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().replaceText("Hello"));
      }
    }.run();
  }

  @Test
  public void shouldSelectAllText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectAllText(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectAllText());
      }
    }.run();
  }

  @Test
  public void shouldEnterText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().enterText(target(), "Hello");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().enterText("Hello"));
      }
    }.run();
  }

  @Test
  public void shouldReturnList() {
    final JList list = list().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().dropDownList()).andReturn(list);
      }

      @Override
      protected void codeToTest() {
        JList result = fixture().list();
        assertThat(result).isSameAs(list);
      }
    }.run();
  }

  @Test
  public void shouldClearSelection() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clearSelection(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().clearSelection());
      }
    }.run();
  }

  @Test
  public void shouldSelectItemUnderIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItem(target(), 6);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItem(6));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemWithText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItem(target(), "Frodo");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItem("Frodo"));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemWithTextMatchingPattern() {
    final Pattern p = regex(".");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItem(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItem(p));
      }
    }.run();
  }

  @Test
  public void shouldReturnValueAtIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().value(target(), 8)).andReturn("Sam");
      }

      @Override
      protected void codeToTest() {
        Object result = fixture().valueAt(8);
        assertThat(result).isEqualTo("Sam");
      }
    }.run();
  }

  @Test
  public void shouldRequireEditable() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireEditable(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireEditable());
      }
    }.run();
  }

  @Test
  public void shouldRequireNotEditable() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireNotEditable(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireNotEditable());
      }
    }.run();
  }

  @Test
  public void shouldRequireNoSelection() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireNoSelection(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireNoSelection());
      }
    }.run();
  }

  @Test
  public void shouldSetCellReaderInDriver() {
    final JComboBoxCellReader reader = createMock(JComboBoxCellReader.class);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().replaceCellReader(reader);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().replaceCellReader(reader));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectionValue() {
    final String value = "Hello";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), value);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(value));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectionByPatternMatching() {
    final Pattern p = regex("Hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(p));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectionIndex() {
    final int index = 6;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), index);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(index));
      }
    }.run();
  }
}
