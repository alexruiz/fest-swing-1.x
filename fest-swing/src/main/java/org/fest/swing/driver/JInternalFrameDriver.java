/*
 * Created on Feb 1, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.driver.ComponentStateValidator.validateIsShowing;
import static org.fest.swing.driver.JInternalFrameAction.*;
import static org.fest.swing.driver.JInternalFrameIconQuery.isIconified;
import static org.fest.swing.driver.JInternalFrameSetIconTask.setIcon;
import static org.fest.swing.driver.JInternalFrameSetMaximumTask.setMaximum;
import static org.fest.swing.driver.WindowLikeContainerLocations.*;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.concat;

import java.awt.*;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JInternalFrame.JDesktopIcon;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.UnexpectedException;
import org.fest.swing.util.Pair;
import org.fest.swing.util.Triple;
import org.fest.util.VisibleForTesting;

/**
 * Understands functional testing of <code>{@link JInternalFrame}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JInternalFrameDriver extends JComponentDriver {

  /**
   * Creates a new </code>{@link JInternalFrameDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JInternalFrameDriver(Robot robot) {
    super(robot);
  }

  /**
   * Brings the given <code>{@link JInternalFrame}</code> to the front.
   * @param internalFrame the target <code>JInternalFrame</code>.
   */
  @RunsInEDT
  public void moveToFront(JInternalFrame internalFrame) {
    toFront(internalFrame);
  }

  @RunsInEDT
  private static void toFront(final JInternalFrame internalFrame) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        // it seems that moving to front always works, regardless if the internal frame is invisible and/or disabled.
        internalFrame.toFront();
      }
    });
  }

  /**
   * Brings the given <code>{@link JInternalFrame}</code> to the back.
   * @param internalFrame the target <code>JInternalFrame</code>.
   */
  @RunsInEDT
  public void moveToBack(JInternalFrame internalFrame) {
    toBack(internalFrame);
  }

  @RunsInEDT
  private static void toBack(final JInternalFrame internalFrame) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        // it seems that moving to back always works, regardless if the internal frame is invisible and/or disabled.
        internalFrame.moveToBack();
      }
    });
  }

  /**
   * Maximizes the given <code>{@link JInternalFrame}</code>, deconifying it first if it is iconified.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not maximizable.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  @RunsInEDT
  public void maximize(JInternalFrame internalFrame) {
    Pair<Container, Point> maximizeLocation = validateAndFindMaximizeLocation(internalFrame);
    maximizeOrNormalize(internalFrame, MAXIMIZE, maximizeLocation);
  }

  @RunsInEDT
  private static Pair<Container, Point> validateAndFindMaximizeLocation(final JInternalFrame internalFrame) {
    return execute(new GuiQuery<Pair<Container, Point>>() {
      protected Pair<Container, Point> executeInEDT() {
        validateCanMaximize(internalFrame);
        return findMaximizeLocation(internalFrame);
      }
    });
  }

  @RunsInCurrentThread
  private static void validateCanMaximize(JInternalFrame internalFrame) {
    validateIsShowingOrIconified(internalFrame);
    if (!internalFrame.isMaximizable())
      throw new IllegalStateException(concat("The JInternalFrame <", format(internalFrame), "> is not maximizable"));
  }

  /**
   * Normalizes the given <code>{@link JInternalFrame}</code>, deconifying it first if it is iconified.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  @RunsInEDT
  public void normalize(JInternalFrame internalFrame) {
    Pair<Container, Point> normalizeLocation = validateAndFindNormalizeLocation(internalFrame);
    maximizeOrNormalize(internalFrame, NORMALIZE, normalizeLocation);
  }

  @RunsInEDT
  private static Pair<Container, Point> validateAndFindNormalizeLocation(final JInternalFrame internalFrame) {
    return execute(new GuiQuery<Pair<Container, Point>>() {
      protected Pair<Container, Point> executeInEDT() {
        validateIsShowingOrIconified(internalFrame);
        return findMaximizeLocation(internalFrame);
      }
    });
  }

  @RunsInCurrentThread
  private static void validateIsShowingOrIconified(JInternalFrame internalFrame) {
    if (!internalFrame.isIcon()) validateIsShowing(internalFrame);
  }

  @RunsInCurrentThread
  private static Pair<Container, Point> findMaximizeLocation(JInternalFrame internalFrame) {
    Container clickTarget = internalFrame.isIcon() ? internalFrame.getDesktopIcon() : internalFrame;
    Point location = maximizeLocationOf(clickTarget);
    return new Pair<Container, Point>(clickTarget, location);
  }

  @RunsInEDT
  private void maximizeOrNormalize(JInternalFrame internalFrame, JInternalFrameAction action,
      Pair<Container, Point> toMoveMouseTo) {
    moveMouseIgnoringAnyError(toMoveMouseTo.i, toMoveMouseTo.ii);
    setMaximumProperty(internalFrame, action);
  }

  @RunsInEDT
  private void setMaximumProperty(JInternalFrame internalFrame, JInternalFrameAction action) {
    try {
      setMaximum(internalFrame, action);
      robot.waitForIdle();
    } catch (UnexpectedException unexpected) {
      failIfVetoed(internalFrame, action, unexpected);
    }
  }

  /**
   * Iconifies the given <code>{@link JInternalFrame}</code>.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not iconifiable.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  @RunsInEDT
  public void iconify(JInternalFrame internalFrame) {
    Pair<Boolean, Point> iconifyInfo = validateAndfindIconifyInfo(internalFrame);
    if (iconifyInfo.i) return; // internal frame is already iconified
    moveMouseIgnoringAnyError(internalFrame, iconifyInfo.ii);
    setIconProperty(internalFrame, ICONIFY);
  }

  @RunsInEDT
  private static Pair<Boolean, Point> validateAndfindIconifyInfo(final JInternalFrame internalFrame) {
    return execute(new GuiQuery<Pair<Boolean, Point>>() {
      protected Pair<Boolean, Point> executeInEDT() throws Throwable {
        validateIsShowingOrIconified(internalFrame);
        if (!internalFrame.isIconifiable())
          throw new IllegalStateException(concat("The JInternalFrame <", format(internalFrame), "> is not iconifiable"));
        return iconifyInfo(internalFrame);
      }
    });
  }

  @RunsInCurrentThread
  private static Pair<Boolean, Point> iconifyInfo(JInternalFrame internalFrame) {
    boolean iconified = isIconified(internalFrame);
    if (iconified) return new Pair<Boolean, Point>(true, null);
    return new Pair<Boolean, Point>(iconified, findIconifyLocation(internalFrame));
  }

  /**
   * De-iconifies the given <code>{@link JInternalFrame}</code>.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  @RunsInEDT
  public void deiconify(JInternalFrame internalFrame) {
    Triple<Boolean, Container, Point> deiconifyInfo = validateAndfindDeiconifyInfo(internalFrame);
    if (deiconifyInfo.i) return; // internal frame is already de-iconified
    moveMouseIgnoringAnyError(deiconifyInfo.ii, deiconifyInfo.iii);
    setIconProperty(internalFrame, DEICONIFY);
  }

  @RunsInEDT
  private static Triple<Boolean, Container, Point> validateAndfindDeiconifyInfo(final JInternalFrame internalFrame) {
    return execute(new GuiQuery<Triple<Boolean, Container, Point>>() {
      protected Triple<Boolean, Container, Point> executeInEDT() throws Throwable {
        validateIsShowingOrIconified(internalFrame);
        return deiconifyInfo(internalFrame);
      }
    });
  }

  @RunsInCurrentThread
  private static Triple<Boolean, Container, Point> deiconifyInfo(JInternalFrame internalFrame) {
    boolean deiconified = !isIconified(internalFrame);
    if (deiconified) return new Triple<Boolean, Container, Point>(true, null, null);
    JDesktopIcon desktopIcon = internalFrame.getDesktopIcon();
    return new Triple<Boolean, Container, Point>(deiconified, desktopIcon, iconifyLocationOf(desktopIcon));
  }

  @RunsInCurrentThread
  private static Point findIconifyLocation(JInternalFrame internalFrame) {
    return iconifyLocationOf(internalFrame.getDesktopIcon());
  }

  @RunsInEDT
  private void setIconProperty(JInternalFrame internalFrame, JInternalFrameAction action) {
    try {
      setIcon(internalFrame, action);
      robot.waitForIdle();
    } catch (UnexpectedException unexpected) {
      failIfVetoed(internalFrame, action, unexpected);
    }
  }

  @VisibleForTesting
  void failIfVetoed(JInternalFrame internalFrame, JInternalFrameAction action, UnexpectedException unexpected) {
    PropertyVetoException vetoError = vetoFrom(unexpected);
    if (vetoError == null) return;
    throw actionFailure(concat(action.name, " of ", format(internalFrame), " was vetoed: <", vetoError.getMessage(), ">"));
  }

  private PropertyVetoException vetoFrom(UnexpectedException unexpected) {
    Throwable cause = unexpected.getCause();
    if (!(cause instanceof PropertyVetoException)) return null;
    return (PropertyVetoException)cause;
  }

  /**
   * Resizes the <code>{@link JInternalFrame}</code> horizontally.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @param width the width that the <code>JInternalFrame</code> should have after being resized.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not resizable by the user.
   */
  @RunsInEDT
  public void resizeWidthTo(JInternalFrame internalFrame, int width) {
    resizeWidth(internalFrame, width);
  }

  /**
   * Resizes the <code>{@link JInternalFrame}</code> vertically.
   * @param w the target <code>JInternalFrame</code>.
   * @param height the height that the <code>JInternalFrame</code> should have after being resized.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not resizable by the user.
   */
  @RunsInEDT
  public void resizeHeightTo(JInternalFrame w, int height) {
    resizeHeight(w, height);
  }

  /**
   * Resizes the <code>{@link JInternalFrame}</code> to the given size.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @param size the size to resize the <code>JInternalFrame</code> to.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not resizable by the user.
   */
  @RunsInEDT
  public void resizeTo(JInternalFrame internalFrame, Dimension size) {
    resize(internalFrame, size.width, size.height);
  }

  /**
   * Moves the <code>{@link JInternalFrame}</code> to the given location.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @param where the location to move the <code>JInternalFrame</code> to.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   */
  @RunsInEDT
  public void moveTo(JInternalFrame internalFrame, Point where) {
    move(internalFrame, where.x, where.y);
  }

  /**
   * Closes the given <code>{@link JInternalFrame}</code>.
   * @param internalFrame the target <code>JInternalFrame</code>.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JInternalFrame</code> is not closable.
   */
  @RunsInEDT
  public void close(JInternalFrame internalFrame) {
    Pair<Boolean, Point> closeInfo = validateAndFindCloseInfo(internalFrame);
    if (closeInfo.i) return; // internal frame is already closed
    moveMouseIgnoringAnyError(internalFrame, closeInfo.ii);
    JInternalFrameCloseTask.close(internalFrame);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static Pair<Boolean, Point> validateAndFindCloseInfo(final JInternalFrame internalFrame) {
    return execute(new GuiQuery<Pair<Boolean, Point>>() {
      protected Pair<Boolean, Point> executeInEDT() {
        validateCanClose(internalFrame);
        return closeInfo(internalFrame);
      }
    });
  }

  @RunsInCurrentThread
  private static void validateCanClose(JInternalFrame internalFrame) {
    validateIsShowing(internalFrame);
    if (!internalFrame.isClosable())
      throw new IllegalStateException(concat("The JInternalFrame <", format(internalFrame), "> is not closable"));
  }

  @RunsInCurrentThread
  private static Pair<Boolean, Point> closeInfo(JInternalFrame internalFrame) {
    if (internalFrame.isClosed()) return new Pair<Boolean, Point>(true, null);
    return new Pair<Boolean, Point>(false, closeLocationOf(internalFrame));
  }

}
