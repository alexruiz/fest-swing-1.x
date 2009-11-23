/*
 * Created on Jan 15, 2009
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

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.javafx.desktop.core.BasicJavaFxRobot;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.scripts.dnd.ball.Main;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.desktop.launcher.JavaFxClassLauncher.launch;
import static org.fest.javafx.desktop.util.Nodes.centerOf;

/**
 * Tests that the JavaFX UI <code>org.fest.javafx.scripts.dnd.ball.Main</code> works properly.
 *
 * @author Alex Ruiz
 */
@Test public class DragAndDropBallTest {

  private JavaFxRobot robot;
  private FrameFixture frame;
  
  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeMethod public void setUp() {
    robot = BasicJavaFxRobot.robotWithNewAwtHierarchy();
    JFrame ballFrame = launch(Main.class);
    frame = new FrameFixture(robot, ballFrame);
  }
  
  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }
  
  public void shouldDragAndDropBall() {
    Point target = new Point(100, 100);
    ImageFixture image = frame.image("ball");
    image.dragAndDropTo(target);
    Rectangle2D boundsInScene = image.node().getBoundsInScene();
    Point center = centerOf(image.node());
    assertThat(boundsInScene.getX()).isEqualTo(target.x - center.x);
    assertThat(boundsInScene.getY()).isEqualTo(target.y - center.y);
  }
}
