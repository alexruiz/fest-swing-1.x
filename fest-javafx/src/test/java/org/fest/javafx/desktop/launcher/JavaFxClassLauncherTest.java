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
package org.fest.javafx.desktop.launcher;

import javax.swing.JFrame;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.fest.javafx.scripts.calculator.Calculator;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link JavaFxClassLauncher}</code>.
 *
 * @author Alex Ruiz
 */
@Test public class JavaFxClassLauncherTest {

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  public void shouldStartJavaFxUI() {
    JFrame frame = JavaFxClassLauncher.launch(Calculator.class);
    assertThat(frame.getTitle()).isEqualTo("Calculator");
  }
}
