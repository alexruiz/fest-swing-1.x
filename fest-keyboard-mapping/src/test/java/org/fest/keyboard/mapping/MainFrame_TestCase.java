/*
 * Created on Apr 19, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static org.fest.swing.edt.GuiActionRunner.execute;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.test.core.RobotBasedTestCase;

/**
 * Base class for tests for <code>{@link MainFrame}</code>.
 *
 * @author Yvonne Wang
 */
public class MainFrame_TestCase extends RobotBasedTestCase {

  protected FrameFixture frame;

  @Override protected void onSetUp() {
    MainFrame mainFrame = execute(new GuiQuery<MainFrame>() {
      protected MainFrame executeInEDT() {
        return new MainFrame();
      }
    });
    frame = new FrameFixture(robot, mainFrame);
    frame.show();
  }

}