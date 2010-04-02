/*
 * Created on Mar 30, 2010
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
package org.fest.swing.driver;

import static javax.swing.SwingUtilities.isEventDispatchThread;
import static org.fest.swing.core.TestRobots.singletonRobotMock;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.awt.TestAppletContexts.singletonAppletContextMock;

import java.applet.AppletContext;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;

/**
 * Base test case for <code>{@link JAppletDriver}</code>.
 *
 * @author Alex Ruiz
 */
public class JAppletDriver_TestCase extends EDTSafeTestCase {

  private AppletContext context;
  private JAppletStub applet;
  private JAppletDriver driver;

  @Before
  public final void setUp() {
    context = singletonAppletContextMock();
    applet = JAppletStub.createNew(context);
    driver = new JAppletDriver(singletonRobotMock());
  }

  final AppletContext context() { return context; }
  final JAppletStub applet() { return applet; }
  final JAppletDriver driver() { return driver; }

  static class JAppletStub extends JApplet {
    private static final long serialVersionUID = 1L;

    private final AppletContext context;
    private final Map<String, Boolean> methodCallsInEDT = new HashMap<String, Boolean>();

    @RunsInEDT
    static JAppletStub createNew(final AppletContext context) {
      return execute(new GuiQuery<JAppletStub>() {
        protected JAppletStub executeInEDT() {
          return new JAppletStub(context);
        }
      });
    }

    @RunsInCurrentThread
    private JAppletStub(AppletContext context) {
      this.context = context;
    }

    @Override public AppletContext getAppletContext() {
      registerMethodCall("getAppletContext");
      return context;
    }

    @Override
    public void resize(int width, int height) {
      registerMethodCall("resize(int, int)");
    }

    private void registerMethodCall(String methodName) {
      methodCallsInEDT.put(methodName, isEventDispatchThread());
    }

    boolean wasMethodCalledInEDT(String methodName) {
      Boolean calledInEDT = methodCallsInEDT.get(methodName);
      return calledInEDT == null ? false : calledInEDT;
    }
  }
}
