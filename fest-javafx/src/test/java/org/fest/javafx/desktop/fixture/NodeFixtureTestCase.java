/*
 * Created on Feb 20, 2009
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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.core.NodeFinder;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;

import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Understands test methods for implementations of <code>{@link NodeFixture}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class NodeFixtureTestCase {

  private JavaFxRobot robot;
  private NodeFinder finder;

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public final void setUp() {
    robot = createMock(JavaFxRobot.class);
    finder = createMock(NodeFinder.class);
    onSetUp();
  }

  abstract void onSetUp();
  
  abstract NodeFixture fixture();

  JavaFxRobot robot() { return robot; }
  NodeFinder finder() { return finder; }
  
  final void assertThatReturnsThis(NodeFixture result) {
    assertThat(result).isSameAs(fixture());
  }

  abstract FXNode target();
}
