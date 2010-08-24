/*
 * Created on Aug 23, 2010
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
package org.fest.swing.gestures;

import static java.awt.event.KeyEvent.VK_UNDEFINED;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.gestures.Actions.findActionKey;
import static org.fest.swing.gestures.KeyStrokes.findKeyStrokesForAction;
import static org.fest.util.Strings.*;

import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.ActionFailedException;

/**
 * Simulates user input on a <code>{@link JComponent}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 2.0
 */
public class JComponentGestures extends ContainerGestures {

  /**
   * Creates a new </code>{@link JComponentGestures}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JComponentGestures(Robot robot) {
    super(robot);
  }

  /**
   * Invoke <code>{@link JComponent#scrollRectToVisible(Rectangle)}</code> on the given <code>{@link JComponent}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param c the given {@code JComponent}.
   * @param r the visible {@code Rectangle}.
   */
  @RunsInCurrentThread
  protected final void scrollToVisible(JComponent c, Rectangle r) {
    // From Abbot:
    // Ideally, we'd use scrollBar commands to effect the scrolling, but that gets really complicated for no real gain
    // in function. Fortunately, Swing's Scrollable makes for a simple solution.
    // NOTE: absolutely MUST wait for idle in order for the scroll to finish, and the UI to update so that the next
    // action goes to the proper location within the scrolled component.
    c.scrollRectToVisible(r);
  }

  /**
   * Invoke an <code>{@link javax.swing.Action}</code> from the <code>{@link JComponent}</code>'s
   * <code>{@link javax.swing.ActionMap}</code>.
   * @param c the given {@code JComponent}.
   * @param name the name of the <code>Action</code> to invoke.
   * @throws ActionFailedException if an <code>Action</code> cannot be found under the given name.
   * @throws ActionFailedException if a <code>KeyStroke</code> cannot be found for the <code>Action</code> under the
   * given name.
   * @throws ActionFailedException if it is not possible to type any of the found <code>KeyStroke</code>s.
   */
  @RunsInEDT
  protected final void invokeAction(JComponent c, String name) {
    robot.focusAndWaitForFocusGain(c);
    for (KeyStroke keyStroke : keyStrokesForAction(c, name)) {
      try {
        type(keyStroke);
        robot.waitForIdle();
        return;
      } catch (IllegalArgumentException e) { /* try the next one, if any */ }
    }
    throw actionFailure(concat("Unable to type any key for the action with key ", quote(name)));
  }

  @RunsInCurrentThread
  private static KeyStroke[] keyStrokesForAction(JComponent component, String actionName) {
    Object key = findActionKey(actionName, component.getActionMap());
    return findKeyStrokesForAction(actionName, key, component.getInputMap());
  }

  private void type(KeyStroke keyStroke) {
    if (keyStroke.getKeyCode() == VK_UNDEFINED) {
      robot.type(keyStroke.getKeyChar());
      return;
    }
    robot.pressAndReleaseKey(keyStroke.getKeyCode(), keyStroke.getModifiers());
  }

}
