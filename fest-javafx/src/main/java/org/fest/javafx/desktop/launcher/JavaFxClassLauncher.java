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
package org.fest.javafx.desktop.launcher;

import javafx.stage.Stage;

import javax.swing.JFrame;

import com.sun.javafx.runtime.TypeInfo;
import com.sun.javafx.runtime.sequence.Sequence;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

import static org.fest.javafx.desktop.util.Windows.frameFrom;
import static org.fest.reflect.core.Reflection.staticMethod;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands how to start a compile JavaFX UI.
 *
 * @author Alex Ruiz
 */
public final class JavaFxClassLauncher {

  /**
   * Launches the given compiled JavaFX UI and returns the <code>{@link JFrame}</code> hosting the JavaFX UI.
   * @param javaFxClass the class containing the JavaFX UI to launch.
   * @return the <code>JFrame</code> hosting the JavaFX UI once it's started.
   */
  @RunsInEDT
  public static JFrame launch(final Class<?> javaFxClass) {
    Stage stage = execute(new GuiQuery<Stage>() {
      protected Stage executeInEDT() throws Throwable {
        return (Stage) staticMethod("javafx$run$").withReturnType(Object.class)
                                                  .withParameterTypes(Sequence.class)
                                                  .in(javaFxClass)
                                                  .invoke(TypeInfo.String.emptySequence);
      }
    });
    return frameFrom(stage);
  }
  
  private JavaFxClassLauncher() {}

}
