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

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import javax.swing.JList;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.BasicJavaFxRobot;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.core.MouseButton;
import org.fest.swing.driver.JListDriver;
import org.fest.swing.exception.LocationUnavailableException;
import org.fest.swing.util.Range.From;
import org.fest.swing.util.Range.To;

/**
 * Understands simulation of user input on a <code>SwingList</code>. This class is intended for internal use only.
 *
 * @author Alex Ruiz
 */
public class SwingListDriver extends SwingComponentDriver<JList> {

  private final JListDriver listDriver;
  
  /**
   * Creates a new </code>{@link SwingListDriver}</code>.
   * @param robot
   */
  public SwingListDriver(JavaFxRobot robot) {
    super(JList.class, robot);
    listDriver = new JListDriver(robot.swingRobot());
  }

  /**
   * Returns an array of <code>String</code>s that represents the selection in the given <code>SwingList</code>,
   * using this driver's <code>{@link JListCellReader}</code>.
   * @param node the target <code>SwingList</code>.
   * @return an array of <code>String</code>s that represents the selection in the given <code>JList</code>.
   * @throws IllegalArgumentException if the given node is not a <code>SwingList</code>.
   * @see #cellReader(JListCellReader)
   */
  @RunsInEDT
  public String[] selectionOf(FXNode node) {
    return listDriver.selectionOf(componentIn(node));
  }

  /**
   * Selects the item under the given index.
   * @param node the target <code>SwingList</code>.
   * @param index the index of the item to click.
   * @throws IllegalArgumentException if the given node is not a <code>SwingList</code>.
   * @throws IllegalStateException if the <code>SwingList</code> is disabled.
   * @throws IllegalStateException if the <code>SwingList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>SwingList</code>.
   */
  @RunsInEDT
  public void selectItem(FXNode node, int index) {
    listDriver.selectItem(componentIn(node), index);
  }

  /**
   * Selects the item matching the given value.
   * @param node the target <code>SwingList</code>.
   * @param value the value to match.
   * @throws IllegalArgumentException if the given node is not a <code>SwingList</code>.
   * @throws IllegalStateException if the <code>SwingList</code> is disabled.
   * @throws IllegalStateException if the <code>SwingList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   */
  @RunsInEDT
  public void selectItem(FXNode node, String value) {
    Rectangle2D boundsInScene = node.getBoundsInScene();
    int originX = (int) boundsInScene.getX();
    int originY = (int) boundsInScene.getY();
    Point p = node.getPanel().getLocationOnScreen();
    p.translate(originX, originY);
    Point locationOnScreen = BasicJavaFxRobot.locationOnScreen(node, 0, 0);
    listDriver.selectItem(componentIn(node), value, locationOnScreen);
  }

  /**
   * @param list
   * @param index
   * @param button
   * @param times
   * @see org.fest.swing.driver.JListDriver#clickItem(javax.swing.JList, int, org.fest.swing.core.MouseButton, int)
   */
  public void clickItem(JList list, int index, MouseButton button, int times) {
    listDriver.clickItem(list, index, button, times);
  }

  /**
   * @param list
   * @param value
   * @param button
   * @param times
   * @see org.fest.swing.driver.JListDriver#clickItem(javax.swing.JList, java.lang.String, org.fest.swing.core.MouseButton, int)
   */
  public void clickItem(JList list, String value, MouseButton button, int times) {
    listDriver.clickItem(list, value, button, times);
  }

  /**
   * @param list
   * @return
   * @see org.fest.swing.driver.JListDriver#contentsOf(javax.swing.JList)
   */
  public String[] contentsOf(JList list) {
    return listDriver.contentsOf(list);
  }

  /**
   * @param list
   * @param value
   * @see org.fest.swing.driver.JListDriver#selectItem(javax.swing.JList, java.lang.String)
   */
  public void selectItem(JList list, String value) {
    listDriver.selectItem(list, value);
  }

  /**
   * @param list
   * @param from
   * @param to
   * @see org.fest.swing.driver.JListDriver#selectItems(javax.swing.JList, org.fest.swing.util.Range.From, org.fest.swing.util.Range.To)
   */
  public void selectItems(JList list, From from, To to) {
    listDriver.selectItems(list, from, to);
  }

  /**
   * @param list
   * @param start
   * @param end
   * @see org.fest.swing.driver.JListDriver#selectItems(javax.swing.JList, int, int)
   */
  public void selectItems(JList list, int start, int end) {
    listDriver.selectItems(list, start, end);
  }

  /**
   * @param list
   * @param indices
   * @see org.fest.swing.driver.JListDriver#selectItems(javax.swing.JList, int[])
   */
  public void selectItems(JList list, int[] indices) {
    listDriver.selectItems(list, indices);
  }

  /**
   * @param list
   * @param values
   * @see org.fest.swing.driver.JListDriver#selectItems(javax.swing.JList, java.lang.String[])
   */
  public void selectItems(JList list, String[] values) {
    listDriver.selectItems(list, values);
  }

  /**
   * @param list
   * @param index
   * @return
   * @see org.fest.swing.driver.JListDriver#value(javax.swing.JList, int)
   */
  public String value(JList list, int index) {
    return listDriver.value(list, index);
  }
  
  
}
