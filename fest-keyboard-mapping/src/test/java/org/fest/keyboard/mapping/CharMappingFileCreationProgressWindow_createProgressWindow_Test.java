/*
 * Created on May 3, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static org.fest.swing.edt.GuiActionRunner.execute;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link CharMappingFileCreationProgressWindow#createProgressWindow(javax.swing.JFrame, CharMappingFileCreationProgressPanel)}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMappingFileCreationProgressWindow_createProgressWindow_Test extends RobotBasedTestCase {

  private CharMappingFileCreationProgressWindow progressWindow;
  private DialogFixture dialogFixture;

  @Override protected void onSetUp() {
    final TestWindow owner = TestWindow.createNewWindow(getClass());
    progressWindow = execute(new GuiQuery<CharMappingFileCreationProgressWindow>() {
      @Override protected CharMappingFileCreationProgressWindow executeInEDT() {
        CharMappingFileCreationProgressPanel contents = new CharMappingFileCreationProgressPanel();
        return CharMappingFileCreationProgressWindow.createProgressWindow(owner, contents);
      }
    });
    robot.showWindow(owner);
    dialogFixture = new DialogFixture(robot, progressWindow);
    dialogFixture.show();
  }

  @Test
  public void should_never_close_progress_window() {
    robot.close(progressWindow);
    dialogFixture.requireVisible();
  }
}
