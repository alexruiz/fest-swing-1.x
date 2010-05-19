/*
 * Created on Jan 21, 2008
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

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.String.valueOf;
import static javax.swing.text.DefaultEditorKit.deletePrevCharAction;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JTextComponentEditableQuery.isEditable;
import static org.fest.swing.driver.JTextComponentSelectAllTask.selectAllText;
import static org.fest.swing.driver.JTextComponentSelectTextTask.selectTextInRange;
import static org.fest.swing.driver.JTextComponentSetTextTask.setTextIn;
import static org.fest.swing.driver.PointAndParentForScrollingJTextFieldQuery.pointAndParentForScrolling;
import static org.fest.swing.driver.TextAssert.verifyThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.*;

import java.awt.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.util.Pair;

/**
 * Understands functional testing of <code>{@link JTextComponent}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 */
public class JTextComponentDriver extends JComponentDriver implements TextDisplayDriver<JTextComponent> {

  private static final String EDITABLE_PROPERTY = "editable";
  private static final String TEXT_PROPERTY = "text";

  /**
   * Creates a new </code>{@link JTextComponentDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JTextComponentDriver(Robot robot) {
    super(robot);
  }

  /**
   * Deletes the text of the <code>{@link JTextComponent}</code>.
   * @param textBox the target <code>JTextComponent</code>.
   * @throws IllegalStateException if the <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if the <code>JTextComponent</code> is not showing on the screen.
   */
  @RunsInEDT
  public void deleteText(JTextComponent textBox) {
    selectAll(textBox);
    invokeAction(textBox, deletePrevCharAction);
  }

  /**
   * Types the given text into the <code>{@link JTextComponent}</code>, replacing any existing text already there.
   * @param textBox the target <code>JTextComponent</code>.
   * @param text the text to enter.
   * @throws NullPointerException if the text to enter is <code>null</code>.
   * @throws IllegalArgumentException if the text to enter is empty.
   * @throws IllegalStateException if the <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if the <code>JTextComponent</code> is not showing on the screen.
   */
  @RunsInEDT
  public void replaceText(JTextComponent textBox, String text) {
    if (text == null) throw new NullPointerException("The text to enter should not be null");
    if (isEmpty(text)) throw new IllegalArgumentException("The text to enter should not be empty");
    selectAll(textBox);
    enterText(textBox, text);
  }

  /**
   * Selects the text in the <code>{@link JTextComponent}</code>.
   * @param textBox the target <code>JTextComponent</code>.
   * @throws IllegalStateException if the <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if the <code>JTextComponent</code> is not showing on the screen.
   */
  @RunsInEDT
  public void selectAll(JTextComponent textBox) {
    validateAndScrollToPosition(textBox, 0);
    selectAllText(textBox);
  }

  /**
   * Types the given text into the <code>{@link JTextComponent}</code>.
   * @param textBox the target <code>JTextComponent</code>.
   * @param text the text to enter.
   * @throws IllegalStateException if the <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if the <code>JTextComponent</code> is not showing on the screen.
   */
  @RunsInEDT
  public void enterText(JTextComponent textBox, String text) {
    focusAndWaitForFocusGain(textBox);
    robot.enterText(text);
  }

  /**
   * Sets the given text into the <code>{@link JTextComponent}</code>. Unlike
   * <code>{@link #enterText(JTextComponent, String)}</code>, this method bypasses the event system and allows immediate
   * updating on the underlying document model.
   * <p>
   * Primarily desired for speeding up tests when precise user event fidelity isn't necessary.
   * </p>
   * @param textBox the target <code>JTextComponent</code>.
   * @param text the text to enter.
   * @throws IllegalStateException if the <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if the <code>JTextComponent</code> is not showing on the screen.
   */
  @RunsInEDT
  public void setText(JTextComponent textBox, String text) {
    focusAndWaitForFocusGain(textBox);
    setTextIn(textBox, text);
    robot.waitForIdle();
  }

  /**
   * Select the given text range.
   * @param textBox the target <code>JTextComponent</code>.
   * @param text the text to select.
   * @throws IllegalStateException if the <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if the <code>JTextComponent</code> is not showing on the screen.
   * @throws IllegalArgumentException if the <code>JTextComponent</code> does not contain the given text to select.
   * @throws ActionFailedException if selecting the text fails.
   */
  @RunsInEDT
  public void selectText(JTextComponent textBox, String text) {
    int indexFound = indexOfText(textBox, text);
    if (indexFound == -1) throw new IllegalArgumentException(concat("The text ", quote(text), " was not found"));
    selectText(textBox, indexFound, indexFound + text.length());
  }

  @RunsInEDT
  private static int indexOfText(final JTextComponent textBox, final String text) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        validateIsEnabledAndShowing(textBox);
        String actualText = textBox.getText();
        if (isEmpty(actualText)) return -1;
        return actualText.indexOf(text);
      }
    });
  }

  /**
   * Select the given text range.
   * @param textBox the target <code>JTextComponent</code>.
   * @param start the starting index of the selection.
   * @param end the ending index of the selection.
   * @throws IllegalStateException if the <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if the <code>JTextComponent</code> is not showing on the screen.
   * @throws ActionFailedException if selecting the text in the given range fails.
   */
  @RunsInEDT
  public void selectText(JTextComponent textBox, int start, int end) {
    robot.moveMouse(textBox, validateAndScrollToPosition(textBox, start));
    robot.moveMouse(textBox, scrollToPosition(textBox, end));
    performAndValidateTextSelection(textBox, start, end);
  }

  @RunsInEDT
  private static Point validateAndScrollToPosition(final JTextComponent textBox, final int index) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        validateIsEnabledAndShowing(textBox);
        return scrollToVisible(textBox, index);
      }
    });
  }

  @RunsInEDT
  private static Point scrollToPosition(final JTextComponent textBox, final int index) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        return scrollToVisible(textBox, index);
      }
    });
  }

  /**
   * Move the pointer to the location of the given index. Takes care of auto-scrolling through text.
   * @param textBox the target <code>JTextComponent</code>.
   * @param index the given location.
   * @return the position of the pointer after being moved.
   * @throws ActionFailedException if it was not possible to scroll to the location of the given index.
   */
  @RunsInCurrentThread
  private static Point scrollToVisible(JTextComponent textBox, int index) {
    Rectangle indexLocation = locationOf(textBox, index);
    if (isRectangleVisible(textBox, indexLocation)) return centerOf(indexLocation);
    scrollToVisible(textBox, indexLocation);
    indexLocation = locationOf(textBox, index);
    if (isRectangleVisible(textBox, indexLocation)) return centerOf(indexLocation);
    throw actionFailure(concat(
        "Unable to make visible the location of the index '", valueOf(index),
        "' by scrolling the point (", formatOriginOf(indexLocation), ") on ", format(textBox)));
  }

  @RunsInCurrentThread
  private static Rectangle locationOf(JTextComponent textBox, int index) {
    Rectangle r = null;
    try {
      r = textBox.modelToView(index);
    } catch (BadLocationException e) {
      throw cannotGetLocation(textBox, index);
    }
    if (r != null) return r;
    throw cannotGetLocation(textBox, index);
  }

  private static ActionFailedException cannotGetLocation(JTextComponent textBox, int index) {
    throw actionFailure(concat("Unable to get location for index '", valueOf(index), "' in ", format(textBox)));
  }

  @RunsInCurrentThread
  private static boolean isRectangleVisible(JTextComponent textBox, Rectangle r) {
    Rectangle visible = textBox.getVisibleRect();
    return visible.contains(r.x, r.y);
  }

  private static String formatOriginOf(Rectangle r) {
    return concat(valueOf(r.x), ",", valueOf(r.y));
  }

  @RunsInCurrentThread
  private static void scrollToVisible(JTextComponent textBox, Rectangle r) {
    textBox.scrollRectToVisible(r);
    if (isVisible(textBox, r)) return;
    scrollToVisibleIfIsTextField(textBox, r);
  }

  @RunsInCurrentThread
  private static void scrollToVisibleIfIsTextField(JTextComponent textBox, Rectangle r) {
    if (!(textBox instanceof JTextField)) return;
    Pair<Point, Container> pointAndParent = pointAndParentForScrolling((JTextField)textBox);
    Container parent = pointAndParent.ii;
    if (parent == null || parent instanceof CellRendererPane || !(parent instanceof JComponent)) return;
    ((JComponent)parent).scrollRectToVisible(addPointToRectangle(pointAndParent.i, r));
  }

  private static Rectangle addPointToRectangle(Point p, Rectangle r) {
    Rectangle destination = new Rectangle(r);
    destination.x += p.x;
    destination.y += p.y;
    return destination;
  }

  private static Point centerOf(Rectangle r) {
    return new Point(r.x + r.width / 2, r.y + r.height / 2);
  }

  @RunsInEDT
  private static void performAndValidateTextSelection(final JTextComponent textBox, final int start, final int end) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        selectTextInRange(textBox, start, end);
        verifyTextWasSelected(textBox, start, end);
      }
    });
  }

  @RunsInCurrentThread
  private static void verifyTextWasSelected(JTextComponent textBox, int start, int end) {
    int actualStart = textBox.getSelectionStart();
    int actualEnd = textBox.getSelectionEnd();
    if (actualStart == min(start, end) && actualEnd == max(start, end)) return;
    throw actionFailure(concat(
        "Unable to select text using indices '", valueOf(start), "' and '", valueOf(end),
        ", current selection starts at '", valueOf(actualStart), "' and ends at '", valueOf(actualEnd), "'"));
  }

  /**
   * Asserts that the text in the given <code>{@link JTextComponent}</code> is equal to the specified value.
   * @param textBox the given <code>JTextComponent</code>.
   * @param expected the text to match. It can be a regular expression pattern.
   * @throws AssertionError if the text of the <code>JTextComponent</code> is not equal to the given one.
   */
  @RunsInEDT
  public void requireText(JTextComponent textBox, String expected) {
    verifyThat(textOf(textBox)).as(textProperty(textBox)).isEqualOrMatches(expected);
  }

  /**
   * Asserts that the text in the given <code>{@link JTextComponent}</code> matches the given regular expression
   * pattern.
   * @param textBox the given <code>JTextComponent</code>.
   * @param pattern the regular expression pattern to match.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the text of the <code>JTextComponent</code> is not equal to the given one.
   * @since 1.2
   */
  @RunsInEDT
  public void requireText(JTextComponent textBox, Pattern pattern) {
    verifyThat(textOf(textBox)).as(textProperty(textBox)).matches(pattern);
  }

  /**
   * Asserts that the given <code>{@link JTextComponent}</code> is empty.
   * @param textBox the given <code>JTextComponent</code>.
   * @throws AssertionError if the <code>JTextComponent</code> is not empty.
   */
  @RunsInEDT
  public void requireEmpty(JTextComponent textBox) {
    assertThat(textOf(textBox)).as(textProperty(textBox)).isEmpty();
  }

  @RunsInEDT
  private static Description textProperty(JTextComponent textBox) {
    return propertyName(textBox, TEXT_PROPERTY);
  }

  /**
   * Asserts that the given <code>{@link JTextComponent}</code> is editable.
   * @param textBox the given <code>JTextComponent</code>.
   * @throws AssertionError if the <code>JTextComponent</code> is not editable.
   */
  @RunsInEDT
  public void requireEditable(JTextComponent textBox) {
    assertEditable(textBox, true);
  }

  /**
   * Asserts that the given <code>{@link JTextComponent}</code> is not editable.
   * @param textBox the given <code>JTextComponent</code>.
   * @throws AssertionError if the <code>JTextComponent</code> is editable.
   */
  @RunsInEDT
  public void requireNotEditable(JTextComponent textBox) {
    assertEditable(textBox, false);
  }

  @RunsInEDT
  private void assertEditable(JTextComponent textBox, boolean editable) {
    assertThat(isEditable(textBox)).as(editableProperty(textBox)).isEqualTo(editable);
  }

  @RunsInEDT
  private static Description editableProperty(JTextComponent textBox) {
    return propertyName(textBox, EDITABLE_PROPERTY);
  }

  /**
   * Returns the text of the given <code>{@link JTextComponent}</code>.
   * @param textBox the given <code>JTextComponent</code>.
   * @return the text of the given <code>JTextComponent</code>.
   * @since 1.2
   */
  @RunsInEDT
  public String textOf(JTextComponent textBox) {
    return JTextComponentTextQuery.textOf(textBox);
  }

}
