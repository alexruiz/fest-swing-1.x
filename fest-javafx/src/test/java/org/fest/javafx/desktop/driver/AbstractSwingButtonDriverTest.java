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

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.BasicJavaFxRobot;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.test.recorder.ClickRecorder;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.desktop.core.MouseButton.LEFT_BUTTON;
import static org.fest.javafx.desktop.launcher.JavaFxClassLauncher.launch;
import static org.fest.javafx.desktop.matcher.SwingButtonNodeMatcher.buttonWithId;
import static org.fest.javafx.desktop.matcher.SwingCheckBoxNodeMatcher.checkBoxWithId;
import static org.fest.javafx.desktop.util.Nodes.componentInNode;
import static org.fest.javafx.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Tests for <code>{@link AbstractSwingButtonDriver}</code>.
 *
 * @author Alex Ruiz
 */
@Test
public class AbstractSwingButtonDriverTest {

  private JavaFxRobot robot;
  private AbstractSwingButtonDriver<AbstractButton> driver;
  private FXNode button;
  private FXNode selectedCheckBox;
  private FXNode unselectedCheckBox;
  
  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    robot = BasicJavaFxRobot.robotWithNewAwtHierarchy();
    driver = new AbstractSwingButtonDriver<AbstractButton>(AbstractButton.class, robot); 
    JFrame frame = launch(AbstractSwingButtonDriverTestFrame.class);
    button = robot.finder().find(frame, buttonWithId("button"));
    selectedCheckBox   = robot.finder().find(frame, checkBoxWithId("selectedCheckBox"));
    unselectedCheckBox = robot.finder().find(frame, checkBoxWithId("unselectedCheckBox"));
  }
  
  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  public void shouldClickButton() {
    ClickRecorder clickRecorder = ClickRecorder.attachTo(button); 
    driver.click(button);
    assertThat(clickRecorder).wasClickedWith(LEFT_BUTTON).timesClicked(1);
  }
  
  public void shouldFailIfButtonTextIsNotEqualToExpected() {
    try {
      driver.requireText(button, "Hello");
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'text'")
                                .contains("expected:<'Hello'> but was:<'Click Me'>");
    }
  }
  
  public void shouldPassIfButtonTextIsEqualToExpected() {
    driver.requireText(button, "Click Me");
  }

  public void shouldSelectButton() {
    driver.select(unselectedCheckBox);
    assertThat(isSelected(unselectedCheckBox)).isTrue();
  }
  
  public void shouldNotSelectButtonIfAlreadySelected() {
    driver.select(selectedCheckBox);
    assertThat(isSelected(selectedCheckBox)).isTrue();
  }

  public void shouldUnselectButton() {
    driver.unselect(selectedCheckBox);
    assertThat(isSelected(selectedCheckBox)).isFalse();
  }

  public void shouldNotUnselectButtonIfAlreadyUnselected() {
    driver.unselect(unselectedCheckBox);
    assertThat(isSelected(unselectedCheckBox)).isFalse();
  }
  
  public void shouldFailIfButtonIsNotSelectedAndExpectingSelected() {
    try {
      driver.requireSelected(unselectedCheckBox);
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'selected'")
                                .contains("expected:<true> but was:<false>");
    }
  }

  public void shouldPassIfButtonIsSelectedAsExpected() {
    driver.requireSelected(selectedCheckBox);
  }
  
  public void shouldFailIfButtonIsSelectedAndExpectingNotSelected() {
    try {
      driver.requireNotSelected(selectedCheckBox);
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'selected'")
                                .contains("expected:<false> but was:<true>");
    }
  }

  public void shouldPassIfButtonIsNotSelectedAsExpected() {
    driver.requireNotSelected(unselectedCheckBox);
  }

  @RunsInEDT
  private static boolean isSelected(FXNode node) {
    final AbstractButton button = componentInNode(node, AbstractButton.class);
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return button.isSelected();
      }
    });
  }
}
