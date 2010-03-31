/*
 * Created on Mar 30, 2010
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
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.applet.AppletContext;
import java.net.URL;

import javax.swing.JApplet;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;

/**
 * Understands functional testing of <code>{@link JApplet}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Mel Llaguno
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class JAppletDriver extends ComponentDriver {

  /**
   * Creates a new </code>{@link JAppletDriver}</code>.
   * @param robot the robot to use simulate user input.
   */
  public JAppletDriver(Robot robot) {
    super(robot);
  }

  /**
   * Returns the <code>{@link AppletContext}</code> of the given <code>{@link JApplet}</code>.
   * @param applet the given {@code JApplet}.
   * @return the {@code AppletContext} of the given {@code JApplet}.
   */
  @RunsInEDT
  public AppletContext appletContextOf(JApplet applet) {
    return appletContext(applet);
  }

  @RunsInEDT
  private static AppletContext appletContext(final JApplet applet) {
    return execute(new GuiQuery<AppletContext>() {
      protected AppletContext executeInEDT() {
        return applet.getAppletContext();
      }
    });
  }

  /**
   * Requests the given <code>{@link JApplet}</code> to be resized.
   * @param applet the given {@code JApplet}.
   * @param width the new width.
   * @param height the new height.
   */
  @RunsInEDT
  public void resize(JApplet applet, int width, int height) {
    doResize(applet, width, height);
  }

  @RunsInEDT
  private static void doResize(final JApplet applet, final int width, final int height) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        applet.resize(width, height);
      }
    });
  }

  /**
   * Returns the URL of the directory that contains the given <code>{@link JApplet}</code>.
   * @param applet the given {@code JApplet}.
   * @return the URL of the directory that contains the given {@code JApplet}.
   */
  @RunsInEDT
  public URL codeBaseOf(JApplet applet) {
    return codeBase(applet);
  }

  @RunsInEDT
  private static URL codeBase(final JApplet applet) {
    return execute(new GuiQuery<URL>() {
      protected URL executeInEDT() {
        return applet.getCodeBase();
      }
    });
  }

  /**
   * Returns the URL of the document the given <code>{@link JApplet}</code> is embedded.
   * @param applet the given {@code JApplet}.
   * @return the URL of the document the given {@code JApplet} is embedded.
   */
  @RunsInEDT
  public URL documentBaseOf(JApplet applet) {
    return documentBase(applet);
  }

  @RunsInEDT
  private static URL documentBase(final JApplet applet) {
    return execute(new GuiQuery<URL>() {
      protected URL executeInEDT() {
        return applet.getDocumentBase();
      }
    });
  }

  /**
   * Returns the value of the named parameter in the given <code>{@link JApplet}</code> in the HTML tag, or
   * <code>null</code> if not set.
   * @param applet the given {@code JApplet}.
   * @param parameterName a parameter name.
   * @return the value of the named parameter in the given {code JApplet} in the HTML tag, or <code>null</code> if not
   * set.
   */
  @RunsInEDT
  public String parameterValue(JApplet applet, String parameterName) {
    return parameter(applet, parameterName);
  }

  @RunsInEDT
  private static String parameter(final JApplet applet, final String parameterName) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        return applet.getParameter(parameterName);
      }
    });
  }
}
