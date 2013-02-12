/*
 * Created on Sep 10, 2007
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
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseButton.RIGHT_BUTTON;
import static org.fest.swing.core.MouseClickInfo.leftButton;

import org.fest.swing.core.MouseButton;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link JListItemFixture}.
 * 
 * @author Alex Ruiz
 */
public class JListItemFixtureTest {
  // TODO Reorganize into smaller units

  private JListFixture list;
  private int index;
  private JListItemFixture fixture;

  @Before
  public void setUp() {
    list = createMock(JListFixture.class);
    index = 8;
    fixture = new JListItemFixture(list, index);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfJListFixtureIsNull() {
    new JListItemFixture(null, 0);
  }

  @Test
  public void shouldSelect() {
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        expect(list.selectItem(index)).andReturn(list);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.select());
      }
    }.run();
  }

  @Test
  public void shouldClick() {
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        expect(list.clickItem(index)).andReturn(list);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.click());
      }
    }.run();
  }

  @Test
  public void shouldClickUsingMouseClickInfo() {
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        list.clickItem(index, LEFT_BUTTON, 2);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.click(leftButton().times(2)));
      }
    }.run();
  }

  @Test
  public void shouldDoubleClick() {
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        list.clickItem(index, LEFT_BUTTON, 2);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.doubleClick());
      }
    }.run();
  }

  @Test
  public void shouldRightClick() {
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        list.clickItem(index, RIGHT_BUTTON, 1);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.rightClick());
      }
    }.run();
  }

  @Test
  public void shouldClickGivenButton() {
    final MouseButton button = MouseButton.LEFT_BUTTON;
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        list.clickItem(index, button, 1);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.click(button));
      }
    }.run();
  }

  @Test
  public void shouldShowPopupMenu() {
    final JPopupMenuFixture popup = createMock(JPopupMenuFixture.class);
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        expect(list.showPopupMenuAt(index)).andReturn(popup);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture result = fixture.showPopupMenu();
        assertThat(result).isSameAs(popup);
      }
    }.run();
  }

  @Test
  public void shouldReturnContents() {
    final String content = "Hello";
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        expect(list.valueAt(index)).andReturn(content);
      }

      @Override
      protected void codeToTest() {
        Object result = fixture.value();
        assertThat(result).isSameAs(content);
      }
    }.run();
  }

  @Test
  public void shouldDrag() {
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        expect(list.drag(index)).andReturn(list);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.drag());
      }
    }.run();
  }

  @Test
  public void shouldDrop() {
    new EasyMockTemplate(list) {
      @Override
      protected void expectations() {
        expect(list.drop(index)).andReturn(list);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsThis(fixture.drop());
      }
    }.run();
  }

  @Test
  public void shouldReturnIndex() {
    assertThat(fixture.index()).isEqualTo(index);
  }

  private void assertThatReturnsThis(JListItemFixture result) {
    assertThat(result).isSameAs(fixture);
  }
}
