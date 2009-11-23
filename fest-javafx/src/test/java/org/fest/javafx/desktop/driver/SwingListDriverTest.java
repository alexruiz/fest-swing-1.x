/*
 * Created on Feb 22, 2009
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

import javax.swing.JFrame;
import javax.swing.JList;

import org.testng.annotations.*;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.BasicJavaFxRobot;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.desktop.launcher.JavaFxClassLauncher.launch;
import static org.fest.javafx.desktop.matcher.NodeByIdMatcher.nodeWithId;
import static org.fest.javafx.desktop.util.Nodes.componentInNode;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Tests for <code>{@link SwingListDriver}</code>.
 *
 * @author Alex Ruiz
 */
@Test public class SwingListDriverTest {

  private JavaFxRobot robot;
  private SwingListDriver driver;
  private FXNode list;
  
  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    robot = BasicJavaFxRobot.robotWithNewAwtHierarchy();
    driver = new SwingListDriver(robot); 
    JFrame frame = launch(SwingListDriverTestFrame.class);
    list = robot.finder().find(frame, nodeWithId("list"));
  }
  
  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  @Test(dataProvider = "itemValues")
  public void shouldSelectItemWithText(String value, int index) {
    driver.selectItem(list, value);
    assertThatListSelectionIs(index);
  }
  
  @Test(dataProvider = "itemIndices")
  public void shouldSelectItemWithIndex(int index) {
    driver.selectItem(list, index);
    assertThatListSelectionIs(index);
  }

  private void assertThatListSelectionIs(int index) {
    assertThat(selectedIndexIn(realList())).isEqualTo(index);
  }

  private JList realList() {
    return componentInNode(list, JList.class);
  }
  
  @DataProvider(name = "itemValues") public Object[][] itemValues() {
    return new Object[][] {
        { "One", 0 }, { "Six", 5 }, { "Eight", 7 }, /*{ "Twelve", 11 }, { "Sixteen", 15 },*/  
    };
  }
  
  @DataProvider(name = "itemIndices") public Object[][] itemIndices() {
    return new Object[][] {
        { 0 }, { 5 }, { 7 }, { 11 }, { 15 },  
    };
  }

  @RunsInEDT
  private static int selectedIndexIn(final JList list) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        return list.getSelectedIndex();
      }
    });
  }
}
