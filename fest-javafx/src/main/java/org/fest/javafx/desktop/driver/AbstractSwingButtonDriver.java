/*
 * Created on Feb 19, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License. You may obtain a copy of the
 * License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.driver;

import javax.swing.AbstractButton;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.assertions.Description;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.driver.AbstractButtonDriver;
import org.fest.swing.edt.GuiQuery;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands simulation of user input on a Swing button. This class is intended for internal use only.
 * @param <T> the type of button this driver supports. 
 * 
 * @author Alex Ruiz
 */
public class AbstractSwingButtonDriver<T extends AbstractButton> extends SwingComponentDriver<T> {

  private final AbstractButtonDriver buttonDriver;

  /**
   * Creates a new </code>{@link AbstractSwingButtonDriver}</code>.
   * @param supportedType the type of <code>Component</code> this driver supports.
   * @param robot simulates user input.
   */
  public AbstractSwingButtonDriver(Class<T> supportedType, JavaFxRobot robot) {
    super(supportedType, robot);
    buttonDriver = new AbstractButtonDriver(robot.swingRobot());
  }

  /**
   * Asserts that the text in the given node's button is equal to the specified <code>String</code>.
   * @param node the target button node.
   * @param expected the text to match.
   * @throws IllegalArgumentException if the node does not contain a button.
   * @throws AssertionError if the text of the button is not equal to the given one.
   */
  @RunsInEDT
  public void requireText(FXNode node, String expected) {
    assertThat(textOf(node)).as(propertyName(node, "text")).isEqualTo(expected);
  }

  /**
   * Selects the button in the given node only it is not already selected.
   * @param node the target button node.
   * @throws IllegalArgumentException if the node does not contain a button.
   * @throws IllegalStateException if the button is disabled.
   * @throws IllegalStateException if the button is not showing on the screen.
   */
  @RunsInEDT
  public void select(FXNode node) {
    if (validateAndFindIsSelected(componentIn(node))) return;
    click(node);
  }

  /**
   * Unselects the button in the given node only if it is selected.
   * @param node the target button node.
   * @throws IllegalArgumentException if the node does not contain a button.
   * @throws IllegalStateException if the button is disabled.
   * @throws IllegalStateException if the button is not showing on the screen.
   */
  @RunsInEDT
  public void unselect(FXNode node) {
    if (!validateAndFindIsSelected(componentIn(node))) return;
    click(node);
  }

  @RunsInEDT
  private static boolean validateAndFindIsSelected(final AbstractButton button) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        validateIsEnabledAndShowing(button);
        return button.isSelected();
      }
    });
  }

  /**
   * Returns the text of the button in the given node.
   * @param node the target button node.
   * @throws IllegalArgumentException if the node does not contain a button.
   * @return the text of the given button.
   */
  @RunsInEDT
  public String textOf(FXNode node) {
    return buttonDriver.textOf(componentIn(node));
  }

  /**
   * Verifies that the button is selected.
   * @param node the target button node.
   * @throws AssertionError if the button is not selected.
   */
  @RunsInEDT
  public void requireSelected(FXNode node) {
    assertThatButtonIsSelected(node, true);
  }  

  /**
   * Verifies that the button is not selected.
   * @param node the target button node.
   * @throws AssertionError if the button is selected.
   */
  @RunsInEDT
  public void requireNotSelected(FXNode node) {
    assertThatButtonIsSelected(node, false);
  }  

  @RunsInEDT
  private void assertThatButtonIsSelected(FXNode node, boolean selected) {
    AbstractButton button = componentIn(node);
    assertThat(isSelected(button)).as(selectedProperty(node)).isEqualTo(selected);
  }

  @RunsInEDT
  private static boolean isSelected(final AbstractButton button) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return button.isSelected();
      }
    });
  }
  
  private static Description selectedProperty(FXNode node) {
    return propertyName(node, "selected");
  }
}
