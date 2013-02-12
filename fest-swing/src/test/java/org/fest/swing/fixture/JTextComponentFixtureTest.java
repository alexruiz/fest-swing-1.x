/*
 * Created on Feb 8, 2007
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
 * Tests for {@link JTextComponentFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTextComponentFixtureTest extends JTextComponentFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldDeleteText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().deleteText(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().deleteText());
      }
    }.run();
  }

  @Test
  public void shouldEnterText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().enterText(target(), "Some Text");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().enterText("Some Text"));
      }
    }.run();
  }

  @Test
  public void shouldSetText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().setText(target(), "Some Text");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().setText("Some Text"));
      }
    }.run();
  }

  @Test
  public void shouldSelectText() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectText(target(), "Some Text");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().select("Some Text"));
      }
    }.run();
  }

  @Test
  public void shouldSelectTextInRange() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectText(target(), 6, 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectText(6, 8));
      }
    }.run();
  }

  @Test
  public void shouldSelectAll() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectAll(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectAll());
      }
    }.run();
  }

  @Test
  public void shouldRequireEmpty() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireEmpty(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireEmpty());
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
}
