/*
 * Created on Sep 11, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.test.swing;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.task.FrameShowTask.packAndShow;
import static org.fest.swing.test.task.FrameShowTask.waitForShowing;
import static org.fest.swing.test.task.WindowDestroyTask.hideAndDispose;

import java.awt.*;

import javax.swing.JFrame;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;

/**
 * Understands the base window for all GUI tests.
 *
 * @author Alex Ruiz
 */
public class TestWindow extends JFrame {

  private static final long serialVersionUID = 1L;

  static final Point DEFAULT_WINDOW_LOCATION = new Point(100, 100);

  /**
   * Creates a new <code>{@link TestWindow}</code> and displays it on the screen. This method is executed in the event
   * dispatch thread.
   * @param testClass the class of the test where the window to create will be used. The simple name of the given class
   * will be used as the title of the created window.
   * @return the created window.
   */
  @RunsInEDT
  public static TestWindow createAndShowNewWindow(final Class<?> testClass) {
    TestWindow w = execute(new GuiQuery<TestWindow>() {
      protected TestWindow executeInEDT() {
        TestWindow window = createInCurrentThread(testClass);
        TestWindow.display(window);
        return window;
      }
    });
    waitForShowing(w);
    return w;
  }

  /**
   * Creates a new <code>{@link TestWindow}</code>. This method is executed in the event dispatch thread.
   * @param testClass the class of the test where the window to create will be used. The simple name of the given class
   * will be used as the title of the created window.
   * @return the created window.
   */
  @RunsInEDT
  public static TestWindow createNewWindow(final Class<?> testClass) {
    return execute(new GuiQuery<TestWindow>() {
      protected TestWindow executeInEDT() {
        return createInCurrentThread(testClass);
      }
    });
  }

  private static TestWindow createInCurrentThread(Class<?> testClass) {
    return new TestWindow(testClass);
  }

  /**
   * Creates a new </code>{@link TestWindow}</code>.
   * @param testClass the class of the test where the window to create will be used. The simple name of the given class
   * will be used as the title of the created window.
   */
  @RunsInCurrentThread
  protected TestWindow(Class<?> testClass) {
    setTitle(testClass.getSimpleName());
    setLayout(new FlowLayout());
    chooseLookAndFeel();
  }

  /**
   * Adds the given GUI components to this window. This method is <b>not</b> executed in the event dispatch thread.
   * @param components the components to add.
   */
  @RunsInCurrentThread
  public void addComponents(Component...components) {
    for (Component c : components) add(c);
  }

  /**
   * Displays this window on the screen. This method is executed in the event dispatch thread.
   */
  @RunsInEDT
  public void display() {
    execute(new GuiTask() {
      protected void executeInEDT() {
        display(TestWindow.this);
      }
    });
    waitForShowing(TestWindow.this);
  }

  /**
   * Displays the given window on the screen. This method is executed in the current thread where it is called.
   * @param <T> the type of window to display.
   * @param w the window to display on the screen.
   * @return the displayed window.
   */
  @RunsInCurrentThread
  protected static <T extends TestWindow> T display(T w) {
    w.setLocation(DEFAULT_WINDOW_LOCATION);
    packAndShow(w);
    return w;
  }

  /**
   * Displays this window on the screen using the given dimension as its preferred size. This method is executed in the
   * event dispatch thread.
   * @param preferredSize the preferred size to set to this window before displaying it on the screen.
   */
  @RunsInEDT
  public void display(final Dimension preferredSize) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        display(TestWindow.this, preferredSize);
      }
    });
  }

  /**
   * Displays the given window on the screen using the given dimension as its preferred size. This method is executed in
   * the current thread where it is called.
   * @param window the window to display on the screen.
   * @param preferredSize the preferred size to set to the given window before displaying it on the screen.
   */
  @RunsInCurrentThread
  protected static void display(TestWindow window, Dimension preferredSize) {
    window.setLocation(DEFAULT_WINDOW_LOCATION);
    packAndShow(window, preferredSize);
  }

  /**
   * Chooses the look and feel.
   */
  @RunsInCurrentThread
  protected void chooseLookAndFeel() {}

  /**
   * Hides and disposes this window. This method is executed in the event dispatch thread.
   */
  @RunsInEDT
  public void destroy() {
    execute(new GuiTask() {
      protected void executeInEDT() {
        destroy(TestWindow.this);
      }
    });
  }

  /**
   * Hides and disposes the given window. This method is executed in the current thread where it is called.
   * @param window the window to destroy.
   */
  @RunsInCurrentThread
  protected static void destroy(TestWindow window) {
    hideAndDispose(window);
  }
}
