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

import java.awt.Frame;

import org.fest.javafx.desktop.core.JavaFxRobot;

/**
 * Understands simulation of user events on a <code>{@link Frame}</code> and verification of the state of such
 * <code>{@link Frame}</code>.
 *
 * @author Alex Ruiz
 */
public class FrameFixture extends ContainerFixture {

  private final Frame frame;

  /**
   * Creates a new </code>{@link FrameFixture}</code>.
   * @param robot simulates user input.
   * @param frame the <code>Frame</code> to handle.
   */
  public FrameFixture(JavaFxRobot robot, Frame frame) {
    super(robot, frame);
    this.frame = frame;
  }
  
  /**
   * Returns this fixture's <code>{@link Frame}</code>.
   * @return this fixture's <code>Frame</code>.
   */
  public Frame frame() { return frame; }
}
