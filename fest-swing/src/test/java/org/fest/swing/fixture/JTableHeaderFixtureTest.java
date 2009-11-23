/*
 * Created on Mar 16, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseClickInfo.leftButton;
import static org.fest.swing.test.builder.JPopupMenus.popupMenu;
import static org.fest.swing.test.builder.JTableHeaders.tableHeader;
import static org.fest.swing.test.core.Regex.regex;

import java.util.regex.Pattern;

import javax.swing.JPopupMenu;
import javax.swing.table.JTableHeader;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.core.MouseButton;
import org.fest.swing.driver.ComponentDriver;
import org.fest.swing.driver.JTableHeaderDriver;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableHeaderFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JTableHeaderFixtureTest extends ComponentFixture_TestCase<JTableHeader> {

  private JTableHeaderDriver driver;
  private JTableHeader target;
  private JTableHeaderFixture fixture;

  void onSetUp() {
    target = tableHeader().createNew();
    fixture = new JTableHeaderFixture(robot(), target);
    driver = createMock(JTableHeaderDriver.class);
    fixture.driver(driver);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfDriverIsNull() {
    fixture.driver(null);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfRobotIsNull() {
    new JTableHeaderFixture(null, target);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfTargetIsNull() {
    new JTableHeaderFixture(robot(), null);
  }

  @Test
  public void shouldClickColumnUnderIndex() {
    final int index = 0;
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.clickColumn(target, index);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.clickColumn(index));
      }
    }.run();
  }

  @Test
  public void shouldClickColumnUnderIndexUsingGivingMouseButtonAndTimes() {
    final int index = 0;
    final MouseButton mouseButton = LEFT_BUTTON;
    final int times = 2;
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.clickColumn(target, index, mouseButton, times);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.clickColumn(index, leftButton().times(2)));
      }
    }.run();
  }

  @Test
  public void shouldClickColumnWithMatchingName() {
    final String name = "first";
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.clickColumn(target, name);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.clickColumn(name));
      }
    }.run();
  }

  @Test
  public void shouldClickColumnWithNameMatchingPattern() {
    final Pattern pattern = regex("first");
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.clickColumn(target, pattern);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.clickColumn(pattern));
      }
    }.run();
  }

  @Test
  public void shouldClickColumnWithMatchingNameUsingGivingMouseButtonAndTimes() {
    final String name = "first";
    final MouseButton mouseButton = LEFT_BUTTON;
    final int times = 2;
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.clickColumn(target, name, mouseButton, times);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.clickColumn(name, leftButton().times(2)));
      }
    }.run();
  }

  @Test
  public void shouldClickColumnWithNameMatchingPatternUsingGivingMouseButtonAndTimes() {
    final Pattern pattern = regex("first");
    final MouseButton mouseButton = LEFT_BUTTON;
    final int times = 2;
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.clickColumn(target, pattern, mouseButton, times);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.clickColumn(pattern, leftButton().times(2)));
      }
    }.run();
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorWhenClickingColumnByIndexIfMouseClickInfoIsNull() {
    fixture.clickColumn(0, null);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorWhenClickingColumnByNameIfMouseClickInfoIsNull() {
    fixture.clickColumn("first", null);
  }

  @Test
  public void shouldShowPopupMenuAtGivenColumnIndex() {
    final JPopupMenu popupMenu = popupMenu().createNew();
    new EasyMockTemplate(driver) {
      protected void expectations() {
        expect(driver.showPopupMenu(target, 1)).andReturn(popupMenu);
      }

      protected void codeToTest() {
        JPopupMenuFixture result = fixture.showPopupMenuAt(1);
        assertThat(result.component()).isSameAs(popupMenu);
      }
    }.run();
  }

  @Test
  public void shouldShowPopupMenuAtGivenColumnName() {
    final JPopupMenu popupMenu = popupMenu().createNew();
    final String name = "1";
    new EasyMockTemplate(driver) {
      protected void expectations() {
        expect(driver.showPopupMenu(target, name)).andReturn(popupMenu);
      }

      protected void codeToTest() {
        JPopupMenuFixture result = fixture.showPopupMenuAt(name);
        assertThat(result.component()).isSameAs(popupMenu);
      }
    }.run();
  }

  @Test
  public void shouldShowPopupMenuAtColumnWithNameMatchingPattern() {
    final JPopupMenu popupMenu = popupMenu().createNew();
    final Pattern pattern = regex("1");
    new EasyMockTemplate(driver) {
      protected void expectations() {
        expect(driver.showPopupMenu(target, pattern)).andReturn(popupMenu);
      }

      protected void codeToTest() {
        JPopupMenuFixture result = fixture.showPopupMenuAt(pattern);
        assertThat(result.component()).isSameAs(popupMenu);
      }
    }.run();
  }

  @Test
  public void shouldRequireToolTip() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver.requireToolTip(target(), "A ToolTip");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.requireToolTip("A ToolTip"));
      }
    }.run();
  }

  @Test
  public void shouldRequireToolTipToMatchPattern() {
    final Pattern pattern = regex(".");
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver.requireToolTip(target(), pattern);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.requireToolTip(pattern));
      }
    }.run();
  }

  @Test
  public void shouldReturnClientProperty() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver.clientProperty(target, "name")).andReturn("Luke");
      }

      protected void codeToTest() {
        assertThat(fixture.clientProperty("name")).isEqualTo("Luke");
      }
    }.run();
  }

  ComponentDriver driver() { return driver; }
  JTableHeader target() { return target; }
  JTableHeaderFixture fixture() { return fixture; }
}
