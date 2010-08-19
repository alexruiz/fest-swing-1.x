/*
 * Created on Feb 26, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.core.matcher.JButtonMatcher.withText;
import static org.fest.swing.driver.JFileChooserApproveButtonTextQuery.approveButtonTextFrom;
import static org.fest.swing.driver.JFileChooserCancelButtonTextQuery.cancelButtonText;
import static org.fest.swing.driver.JFileChooserSelectFileTask.*;
import static org.fest.swing.driver.JFileChooserSetCurrentDirectoryTask.validateAndSetCurrentDirectory;
import static org.fest.util.Arrays.isEmpty;
import static org.fest.util.Strings.*;

import java.io.File;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.ComponentLookupException;

/**
 * User input simulation, state verification and property value query on a <code>{@link JFileChooser}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JFileChooserDriver extends JComponentDriver {

  private static final String APPROVE_BUTTON = "Approve";
  private static final String CANCEL_BUTTON = "Cancel";

  /**
   * Creates a new </code>{@link JFileChooserDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JFileChooserDriver(Robot robot) {
    super(robot);
  }

  /**
   * Selects the given file in the <code>{@link JFileChooser}</code>.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @param file the file to select.
   * @throws NullPointerException if the given file is {@code null}.
   * @throws IllegalStateException if the <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if the <code>JFileChooser</code> is not showing on the screen.
   * @throws IllegalArgumentException if the <code>JFileChooser</code> can select directories only and the file to
   * select is not a directory.
   * @throws IllegalArgumentException if the <code>JFileChooser</code> cannot select directories and the file to select
   * is a directory.
   */
  @RunsInEDT
  public void selectFile(JFileChooser fileChooser, File file) {
    if (file == null) throw new NullPointerException("The file to select should not be null");
    validateAndSelectFile(fileChooser, file);
  }

  /**
   * Selects the given file in the <code>{@link JFileChooser}</code>.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @param files the files to select.
   * @throws NullPointerException if the given array of files is {@code null}.
   * @throws IllegalArgumentException if the given array of files is empty.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> does not support multiple selection and
   * there is more than one file to select.
   * @throws IllegalArgumentException if this fixture's <code>JFileChooser</code> can select directories only and any of
   * the files to select is not a directory.
   * @throws IllegalArgumentException if this fixture's <code>JFileChooser</code> cannot select directories and any of
   * the files to select is a directory.
   */
  public void selectFiles(JFileChooser fileChooser, File[] files) {
    if (files == null) throw new NullPointerException("The files to select should not be null");
    if (isEmpty(files)) throw new IllegalArgumentException("The array of files to select should not be empty");
    for (File file : files)
      if (file == null) throw new NullPointerException("The array of files to select should not contain null elements");
    validateAndSelectFiles(fileChooser, files);
  }

  /**
   * Sets the current directory in the <code>{@link JFileChooser}</code> to the given one.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @param dir the directory to set as current.
   * @throws IllegalStateException if the <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if the <code>JFileChooser</code> is not showing on the screen.
   */
  @RunsInEDT
  public void setCurrentDirectory(JFileChooser fileChooser, File dir) {
    validateAndSetCurrentDirectory(fileChooser, dir);
  }

  /**
   * Returns the text field where the user can enter the name of the file to select.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @return the found text field.
   * @throws ComponentLookupException if a matching text field could not be found.
   */
  @RunsInEDT
  public JTextField fileNameTextBox(JFileChooser fileChooser) {
    return robot.finder().findByType(fileChooser, JTextField.class);
  }

  /**
   * Finds and clicks the "Cancel" button in the given <code>{@link JFileChooser}</code>.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @throws ComponentLookupException if the "Cancel" button cannot be found.
   * @throws IllegalStateException if the "Cancel" button is disabled.
   * @throws IllegalStateException if the "Cancel" button is not showing on the screen.
   */
  @RunsInEDT
  public void clickCancelButton(JFileChooser fileChooser) {
    click(cancelButton(fileChooser));
  }

  /**
   * Finds the "Cancel" button in the given <code>{@link JFileChooser}</code>.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @return the found "Cancel" button.
   * @throws ComponentLookupException if the "Cancel" button cannot be found.
   */
  @RunsInEDT
  public JButton cancelButton(JFileChooser fileChooser) {
    return findButton(fileChooser, CANCEL_BUTTON, cancelButtonText());
  }

  /**
   * Finds and clicks the "Approve" button in the given <code>{@link JFileChooser}</code>.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @throws ComponentLookupException if the "Approve" button cannot be found.
   * @throws IllegalStateException if the "Approve" button is disabled.
   * @throws IllegalStateException if the "Approve" button is not showing on the screen.
   */
  @RunsInEDT
  public void clickApproveButton(JFileChooser fileChooser) {
    click(approveButton(fileChooser));
  }

  /**
   * Finds the "Approve" button in the given <code>{@link JFileChooser}</code>.
   * @param fileChooser the target <code>JFileChooser</code>.
   * @return the found "Approve" button.
   * @throws ComponentLookupException if the "Approve" button cannot be found.
   */
  @RunsInEDT
  public JButton approveButton(JFileChooser fileChooser) {
    return findButton(fileChooser, APPROVE_BUTTON, approveButtonTextFrom(fileChooser));
  }

  @RunsInEDT
  private JButton findButton(JFileChooser fileChooser, String logicalName, String text) {
    JButton button = robot.finder().find(fileChooser, withText(text).andShowing());
    if (button == null) throw cannotFindButton(logicalName, text);
    return button;
  }

  private ComponentLookupException cannotFindButton(String name, String text) {
    throw new ComponentLookupException(concat(
        "Unable to find ", quote(name), " button with text ", quote(text)));
  }
}
