/*
 * Created on Aug 8, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.UnexpectedException.unexpected;
import static org.fest.swing.test.query.JFileChooserCurrentDirectoryQuery.currentDirectoryOf;
import static org.fest.util.Files.temporaryFolder;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for {@link JFileChooserSetCurrentDirectoryTask#validateAndSetCurrentDirectory(JFileChooser, File)}.
 * 
 * @author Yvonne Wang
 */
public class JFileChooserSetCurrentDirectoryTask_validateAndSetCurrentDirectory_Test extends RobotBasedTestCase {
  private JFileChooser fileChooser;
  private File directoryToSelect;

  @Override
  protected void onSetUp() {
    directoryToSelect = temporaryFolder();
    MyWindow window = MyWindow.createNew();
    fileChooser = window.fileChooser;
    robot.showWindow(window);
  }

  @Override
  protected void onTearDown() {
    directoryToSelect.delete();
  }

  @Test
  public void should_set_current_directory() {
    JFileChooserSetCurrentDirectoryTask.validateAndSetCurrentDirectory(fileChooser, directoryToSelect);
    robot.waitForIdle();
    assertThat(currentDirectoryPath()).isEqualTo(canonicalPathOf(directoryToSelect));
  }

  private String currentDirectoryPath() {
    return canonicalPathOf(currentDirectoryOf(fileChooser));
  }

  private static String canonicalPathOf(File file) {
    try {
      return file.getCanonicalPath();
    } catch (IOException e) {
      throw unexpected(e);
    }
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    final JFileChooser fileChooser = new JFileChooser();

    private MyWindow() {
      super(JFileChooserSelectFileTask.class);
      add(fileChooser);
    }
  }
}
