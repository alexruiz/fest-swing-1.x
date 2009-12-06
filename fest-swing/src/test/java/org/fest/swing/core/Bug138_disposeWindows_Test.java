/*
 * Created on Jun 1, 2008
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
package org.fest.swing.core;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JFrames.frame;
import static org.fest.swing.test.task.WindowDestroyTask.hideAndDisposeInEDT;
import static org.fest.util.Collections.list;

import java.awt.Container;
import java.util.List;

import javax.swing.JFrame;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.hierarchy.ComponentHierarchy;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;

/**
 * Test case for <a href="http://code.google.com/p/fest/issues/detail?id=138">Bug 138</a>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Bug138_disposeWindows_Test extends EDTSafeTestCase {

  private ComponentHierarchy hierarchy;
  private BasicRobot robot;
  private JFrame frame;

  @Before
  public void setUp() {
    hierarchy = createMock(ComponentHierarchy.class);
    robot = new TestRobotFixture(hierarchy);
  }

  @After
  public void tearDown() {
    if (frame != null) hideAndDisposeInEDT(frame);
  }

  @Test
  public void should_dispose_windows() {
    frame = frame().withTitle("Hello").createNew();
    final List<Container> roots = rootsWith(frame);
    new EasyMockTemplate(hierarchy) {
      protected void expectations() {
        hierarchy.roots();
        expectLastCall().andReturn(roots);
        hierarchy.dispose(frame);
      }

      protected void codeToTest() {
        robot.cleanUp();
      }
    }.run();
  }

  private static List<Container> rootsWith(Container c) {
    return list(c);
  }

  @Test
  public void should_not_dispose_windows() {
    new EasyMockTemplate(hierarchy) {
      protected void expectations() {}

      protected void codeToTest() {
        robot.cleanUpWithoutDisposingWindows();
      }
    }.run();
  }

}
