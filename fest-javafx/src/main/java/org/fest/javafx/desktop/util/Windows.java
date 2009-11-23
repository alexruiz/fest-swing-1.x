/*
 * Created on Jan 8, 2009
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
package org.fest.javafx.desktop.util;

import javafx.stage.Stage;

import javax.swing.JFrame;

import com.sun.javafx.stage.FrameStageDelegate;

/**
 * Understands utilities related to JavaFX's desktop profile.
 *
 * @author Alex Ruiz
 */
public final class Windows {

  /**
   * Returns the <code>{@link JFrame}</code> stored in the given JavaFX stage.
   * 
   * @param stage
   *            the given JavaFX stage.
   * @return the <code>JFrame</code> from given JavaFX stage.
   */
  public static JFrame frameFrom(Stage stage) {
    FrameStageDelegate frameDelegate = 
      (FrameStageDelegate) stage.get$impl_stageDelegate().get();
    return (JFrame) frameDelegate.get$window().get();
  }

  private Windows() {}
}
