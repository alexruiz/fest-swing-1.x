/*
 * Created on May 6, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.image;

import static org.fest.swing.core.FocusOwnerFinder.focusOwner;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.image.ImageFileExtensions.PNG;
import static org.fest.swing.query.ComponentLocationOnScreenQuery.locationOnScreen;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;
import static org.fest.util.Strings.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Locale;

import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.util.RobotFactory;
import org.fest.util.VisibleForTesting;

/**
 * Understands taking screenshots of the desktop and GUI components.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ScreenshotTaker {

  /**
   * Extension of the image files containing the screenshots taken by instances of this class (png).
   * @deprecated use <code>{@link ImageFileExtensions#PNG}</code> instead.
   */
  @Deprecated public static final String PNG_EXTENSION = "png";

  private final Robot robot;
  private final ImageFileWriter writer;

  /**
   * Creates a new <code>{@link ScreenshotTaker}</code>.
   * @throws ImageException if a AWT Robot (the responsible for taking screenshots) cannot be instantiated.
   */
  public ScreenshotTaker() {
    this(new ImageFileWriter(), new RobotFactory());
  }

  @VisibleForTesting
  ScreenshotTaker(ImageFileWriter writer, RobotFactory robotFactory) {
    this.writer = writer;
    try {
      robot = robotFactory.newRobotInPrimaryScreen();
    } catch (AWTException e) {
      throw new ImageException("Unable to create AWT Robot", e);
    }
  }

  /**
   * Takes a screenshot of the desktop and saves it as a PNG file.
   * @param imageFilePath the path of the file to save the screenshot to.
   * @throws ImageException if the given file path is <code>null</code> or empty.
   * @throws ImageException if the given file path does not end with ".png".
   * @throws ImageException if the given file path belongs to a non-empty directory.
   * @throws ImageException if an I/O error prevents the image from being saved as a file.
   */
  public void saveDesktopAsPng(String imageFilePath) {
    saveImage(takeDesktopScreenshot(), imageFilePath);
  }

  /**
   * Takes a screenshot of the desktop.
   * @return the screenshot of the desktop.
   * @throws SecurityException if <code>readDisplayPixels</code> permission is not granted.
   */
  public BufferedImage takeDesktopScreenshot() {
    Rectangle r = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    return takeScreenshot(r);
  }

  /**
   * Takes a screenshot of the given <code>{@link java.awt.Component}</code> and saves it as a PNG file.
   * @param c the given component.
   * @param imageFilePath the path of the file to save the screenshot to.
   * @throws ImageException if the given file path is <code>null</code> or empty.
   * @throws ImageException if the given file path does not end with ".png".
   * @throws ImageException if the given file path belongs to a non-empty directory.
   * @throws ImageException if an I/O error prevents the image from being saved as a file.
   */
  public void saveComponentAsPng(Component c, String imageFilePath) {
    saveImage(takeScreenshotOf(c), imageFilePath);
  }

  /**
   * Takes a screenshot of the given <code>{@link java.awt.Component}</code>.
   * @param c the given component.
   * @return a screenshot of the given component.
   * @throws SecurityException if <code>readDisplayPixels</code> permission is not granted.
   */
  public BufferedImage takeScreenshotOf(Component c) {
    Point locationOnScreen = locationOnScreen(c);
    Dimension size = sizeOf(c);
    Rectangle r = new Rectangle(locationOnScreen.x,  locationOnScreen.y, size.width, size.height);
    return takeScreenshot(r);
  }

  private BufferedImage takeScreenshot(Rectangle r) {
    JTextComponent textComponent = findFocusOwnerAndHideItsCaret();
    robot.waitForIdle();
    try {
      return takeScreenshot(robot, r);
    } finally {
      showCaretIfPossible(textComponent);
    }
  }

  @RunsInEDT
  private static JTextComponent findFocusOwnerAndHideItsCaret() {
    return execute(new GuiQuery<JTextComponent>() {
      protected JTextComponent executeInEDT() {
        Component focusOwner = focusOwner();
        if (!(focusOwner instanceof JTextComponent)) return null;
        JTextComponent textComponent = (JTextComponent)focusOwner;
        Caret caret = textComponent.getCaret();
        if (caret == null || !caret.isVisible()) return null;
        caret.setVisible(false);
        return textComponent;
      }
    });
  }

  private static BufferedImage takeScreenshot(final Robot robot, final Rectangle r) {
    return execute(new GuiQuery<BufferedImage>() {
      protected BufferedImage executeInEDT() {
        return robot.createScreenCapture(r);
      }
    });
  }

  private void showCaretIfPossible(JTextComponent textComponent) {
    if (textComponent == null) return;
    showCaretOf(textComponent);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void showCaretOf(final JTextComponent textComponent) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        Caret caret = textComponent.getCaret();
        if (caret != null) caret.setVisible(true);
      }
    });
  }

  /**
   * Save the given image as a PNG file.
   * @param image the image to save.
   * @param filePath the path of the file to save the image to.
   * @throws ImageException if the given file path is <code>null</code> or empty.
   * @throws ImageException if the given file path does not end with ".png".
   * @throws ImageException if the given file path belongs to a non-empty directory.
   * @throws ImageException if an I/O error prevents the image from being saved as a file.
   */
  public void saveImage(BufferedImage image, String filePath) {
    validate(filePath);
    try {
      writer.writeAsPng(image, filePath);
    } catch (Exception e) {
      throw new ImageException(concat("Unable to save image as ", quote(filePath)), e);
    }
  }

  private void validate(String imageFilePath) {
    if (isEmpty(imageFilePath)) throw new ImageException("The image path cannot be empty");
    if (!imageFilePath.endsWith(PNG))
      throw new ImageException(concat("The image file should be a ", PNG.toUpperCase(Locale.getDefault())));
  }
}
