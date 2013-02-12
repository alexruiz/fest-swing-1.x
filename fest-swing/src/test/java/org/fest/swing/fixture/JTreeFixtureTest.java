/*
 * Created on May 21, 2007
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
import static org.fest.swing.test.builder.JPopupMenus.popupMenu;
import static org.fest.util.Arrays.array;

import javax.swing.JPopupMenu;

import org.fest.swing.cell.JTreeCellReader;
import org.junit.Test;

/**
 * Tests for {@link JTreeFixture}.
 * 
 * @author Keith Coughtrey
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTreeFixtureTest extends JTreeFixture_TestCase {
  @Test
  public void shouldDoubleClickRow() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().doubleClickRow(target(), 0);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().doubleClickRow(0));
      }
    }.run();
  }

  @Test
  public void shouldDoubleClickPath() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().doubleClickPath(target(), "root");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().doubleClickPath("root"));
      }
    }.run();
  }

  @Test
  public void shouldExpandRow() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().expandRow(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().expandRow(8));
      }
    }.run();
  }

  @Test
  public void shouldCollapseRow() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().collapseRow(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().collapseRow(8));
      }
    }.run();
  }

  @Test
  public void shouldExpandPath() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().expandPath(target(), "root");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().expandPath("root"));
      }
    }.run();
  }

  @Test
  public void shouldCollapsePath() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().collapsePath(target(), "root");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().collapsePath("root"));
      }
    }.run();
  }

  @Test
  public void shouldDragAtRow() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drag(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drag(8));
      }
    }.run();
  }

  @Test
  public void shouldDragAtTreePath() {
    final String path = "root/node1";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drag(target(), path);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drag(path));
      }
    }.run();
  }

  @Test
  public void shouldDropAtRow() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drop(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drop(8));
      }
    }.run();
  }

  @Test
  public void shouldDropAtTreePath() {
    final String path = "root/node1";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drop(target(), path);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drop(path));
      }
    }.run();
  }

  @Test
  public void shouldSelectRow() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectRow(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectRow(8));
      }
    }.run();
  }

  @Test
  public void shouldSelectRows() {
    final int[] rows = { 6, 8 };
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectRows(target(), rows);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectRows(rows));
      }
    }.run();
  }

  @Test
  public void shouldSelectTreePath() {
    final String path = "root/node1";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectPath(target(), path);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectPath(path));
      }
    }.run();
  }

  @Test
  public void shouldSelectTreePaths() {
    final String[] paths = array("root/node1", "root/node2");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectPaths(target(), paths);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectPaths(paths));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectedTreePath() {
    final String[] paths = { "root/node1" };
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), paths);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(paths));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectedRow() {
    final int[] rows = { 0 };
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), rows);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(rows));
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
  public void shouldToggleRow() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().toggleRow(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().toggleRow(8));
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
  public void shouldRequireSelectedPath() {
    final String[] paths = { "root/node1" };
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), paths);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(paths));
      }
    }.run();

  }

  @Test
  public void shouldSetCellReaderInDriver() {
    final JTreeCellReader reader = createMock(JTreeCellReader.class);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().cellReader(reader);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().replaceCellReader(reader));
      }
    }.run();
  }

  @Test
  public void shouldReturnSeparatorFromDriver() {
    final String separator = "\\";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().separator()).andReturn(separator);
      }

      @Override
      protected void codeToTest() {
        String result = fixture().separator();
        assertThat(result).isSameAs(separator);
      }
    }.run();
  }

  @Test
  public void shouldSetSeparatorInDriver() {
    final String separator = "\\";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().separator(separator);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().separator(separator));
      }
    }.run();
  }

  @Test
  public void shouldShowPopupMenuAtRow() {
    final int row = 0;
    final JPopupMenu popupMenu = popupMenu().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().showPopupMenu(target(), row)).andReturn(popupMenu);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture showPopupMenuFixture = fixture().showPopupMenuAt(row);
        assertThat(showPopupMenuFixture.target()).isSameAs(popupMenu);
      }
    }.run();
  }

  @Test
  public void shouldShowPopupMenuAtPath() {
    final String path = "root";
    final JPopupMenu popupMenu = popupMenu().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().showPopupMenu(target(), path)).andReturn(popupMenu);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture showPopupMenuFixture = fixture().showPopupMenuAt(path);
        assertThat(showPopupMenuFixture.target()).isSameAs(popupMenu);
      }
    }.run();
  }

}