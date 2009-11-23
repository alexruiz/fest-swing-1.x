/*
 * Created on Jan 11, 2009
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
package org.fest.javafx.desktop.fixture;

import javax.swing.JFrame;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.javafx.desktop.core.BasicJavaFxRobot;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.scripts.calculator.Calculator;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;

import static org.fest.javafx.desktop.launcher.JavaFxClassLauncher.launch;
import static org.fest.javafx.desktop.matcher.SwingButtonNodeMatcher.buttonWithText;

/**
 * Tests that the JavaFX UI <code>org.fest.javafx.scripts.calculator.Calculator</code> works properly.
 *
 * @author Alex Ruiz
 */
@Test
public class CalculatorTest {

  private JavaFxRobot robot;
  private FrameFixture calculator;
  
  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    robot = BasicJavaFxRobot.robotWithNewAwtHierarchy();
    JFrame calculatorFrame = launch(Calculator.class);
    calculator = new FrameFixture(robot, calculatorFrame); 
  }
  
  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }
  
  public void shouldUpdateTextBoxWhenClickingButtonFoundById() {
    calculator.swingButton("button7").click();
    calculator.textBox().requireText("7");
  }

  public void shouldUpdateTextBoxWhenClickingButtonFoundByText() {
    calculator.swingButton(buttonWithText("6")).click();
    calculator.swingButton(buttonWithText("8")).click();
    calculator.textBox().requireText("68");
  }
}
