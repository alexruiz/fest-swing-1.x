/*
 * Created on Apr 3, 2007
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
import static org.fest.swing.data.Index.atIndex;
import static org.fest.swing.test.builder.JButtons.button;
import static org.fest.swing.test.core.Regex.regex;
import static org.fest.util.Arrays.array;

import java.awt.Component;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Tests for {@link JTabbedPaneFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTabbedPaneFixtureTest extends JTabbedPaneFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldSelectTabWithIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectTab(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectTab(8));
      }
    }.run();
  }

  @Test
  public void shouldReturnSelectedComponent() {
    final Component selected = button().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().selectedComponentOf(target())).andReturn(selected);
      }

      @Override
      protected void codeToTest() {
        assertThat(fixture().selectedComponent()).isSameAs(selected);
      }
    }.run();
  }

  @Test
  public void shouldRequireTitleAtTabIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireTabTitle(target(), "Hello", atIndex(1));
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireTitle("Hello", atIndex(1)));
      }
    }.run();
  }

  @Test
  public void shouldRequireTitleMatchingPatternAtTabIndex() {
    final Pattern pattern = regex("hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireTabTitle(target(), pattern, atIndex(1));
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireTitle(pattern, atIndex(1)));
      }
    }.run();
  }

  @Test
  public void shouldRequireTabTitles() {
    final String[] titles = array("One", "Two");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireTabTitles(target(), titles);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireTabTitles(titles));
      }
    }.run();

  }

  @Test
  public void shouldSelectTabWithText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectTab(target(), "A Tab");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectTab("A Tab"));
      }
    }.run();
  }

  @Test
  public void shouldSelectTabMatchingPattern() {
    final Pattern pattern = regex("hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectTab(target(), pattern);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectTab(pattern));
      }
    }.run();
  }

  @Test
  public void shouldReturnTabTitles() {
    final String[] titles = array("One", "Two");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().tabTitles(target())).andReturn(titles);
      }

      @Override
      protected void codeToTest() {
        String[] result = fixture().tabTitles();
        assertThat(result).isSameAs(titles);
      }
    }.run();
  }
}
