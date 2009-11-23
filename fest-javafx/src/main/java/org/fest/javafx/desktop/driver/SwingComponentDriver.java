/*
 * Created on Feb 19, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; either version 2 of 
 * the License. You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 * the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.driver;

import java.awt.Component;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.assertions.Description;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.format.Formatting;
import org.fest.javafx.desktop.format.NodeFormatter;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiLazyLoadingDescription;
import org.fest.swing.edt.GuiTask;

import static org.fest.javafx.desktop.format.Formatting.format;
import static org.fest.javafx.desktop.util.Nodes.componentInNode;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.*;

/**
 * Understands simulation of user input on a <code>{@link Component}</code> attached to a JavaFX node. This class is
 * intended for internal use only.
 * @param <T> the type of <code>Component</code> this driver supports. 
 * 
 * @author Alex Ruiz
 */
public class SwingComponentDriver<T extends Component> {

  /** Understands how to simulate user input. **/
  protected final JavaFxRobot robot;
  
  /** The type of <code>{@link Component}</code> this driver supports. */
  protected final Class<T> supportedType;

  /**
   * Creates a new </code>{@link SwingComponentDriver}</code>.
   * @param supportedType the type of <code>Component</code> this driver supports.
   * @param robot simulates user input.
   */
  public SwingComponentDriver(Class<T> supportedType, JavaFxRobot robot) {
    this.supportedType = supportedType;
    this.robot = robot;
  }
  
  /**
   * Clicks the given node.
   * @param node the node to click.
   * @throws IllegalStateException if the node does not contain a <code>Component</code>. 
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  public void click(FXNode node) {
    assertIsEnabledAndShowing(node);
    robot.click(node);
  }

  /**
   * Validates that the <code>{@link Component}</code> attached to the given node is enabled and showing on the screen.
   * This method is executed in the event dispatch thread.
   * @param node the node containing the <code>Component</code> to check.
   * @throws IllegalStateException if the node does not contain a <code>Component</code>. 
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  protected static void assertIsEnabledAndShowing(final FXNode node) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        Component c = componentInNode(node, Component.class);
        if (c == null)
          throw new IllegalStateException("The given node does not contain a AWT/Swing component");
        validateIsEnabledAndShowing(c);
      }
    });
  }

  /**
   * Formats the name of a property of the given node by concatenating the value obtained from 
   * <code>{@link Formatting#format(FXNode)}</code> with the given property name.
   * @param node the given node.
   * @param propertyName the name of the property.
   * @return the description of a property belonging to a node.
   * @see NodeFormatter
   * @see Formatting#format(FXNode)
   */
  @RunsInEDT
  public static Description propertyName(final FXNode node, final String propertyName) {
    return new GuiLazyLoadingDescription() {
      protected String loadDescription() {
        return concat(format(node), " - property:", quote(propertyName));
      }
    };
  }

  /**
   * Returns the Swing component in the given node. The Swing component must be of the same type as the one supported
   * by this driver. 
   * @param node the given node.
   * @return the Swing component in the given node.
   * @throws IllegalArgumentException if the given node does not have a Swing component of the same type as the one
   * supported by this driver.
   */
  protected final T componentIn(FXNode node) {
    T component = componentInNode(node, supportedType);
    if (component == null)
      throw new IllegalArgumentException(concat("The given node does not contain a ", supportedType.getSimpleName()));
    return component;
  }
}
