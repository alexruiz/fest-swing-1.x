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
package org.fest.javafx.desktop.core;

import java.awt.event.InputEvent;

import static java.awt.event.InputEvent.*;

import static org.fest.util.Strings.concat;

/**
 * Understands mouse buttons.
 *
 * @author Alex Ruiz
 */
public enum MouseButton {

  LEFT_BUTTON(BUTTON1_MASK), MIDDLE_BUTTON(BUTTON2_MASK), RIGHT_BUTTON(BUTTON3_MASK);
  
  /**The mouse button modifier. */
  public final int mask;
  
  final org.fest.swing.core.MouseButton internalButton;
  
  private MouseButton(int mask) {
    this.mask = mask;
    internalButton = org.fest.swing.core.MouseButton.lookup(mask);
  }

  /**
   * Returns the mouse button whose mask matches the given mask. Valid values are 
   * <code>{@link InputEvent#BUTTON1_MASK}</code>, <code>{@link InputEvent#BUTTON2_MASK}</code>, and
   * <code>{@link InputEvent#BUTTON3_MASK}</code> 
   * @param mask the mask of the button we are looking for.
   * @return the found button.
   * @throws IllegalArgumentException if the given mask is not a valid one.
   */
  public static MouseButton lookup(int mask) {
    for (MouseButton button : values())
      if (button.mask == mask) return button;
    throw new IllegalArgumentException(concat(String.valueOf(mask), " is not a valid button mask"));
  }
}
