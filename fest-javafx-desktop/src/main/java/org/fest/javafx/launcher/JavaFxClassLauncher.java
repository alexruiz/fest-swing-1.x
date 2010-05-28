/*
 * Created on Dec 7, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.launcher;

import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.reflect.core.Reflection.staticMethod;
import javafx.stage.Stage;

import org.fest.javafx.threading.GuiQuery;

import com.sun.javafx.runtime.TypeInfo;
import com.sun.javafx.runtime.sequence.Sequence;

/**
 * Understands how to launch a JavaFX UI.
 *
 * @author Alex Ruiz
 */
public final class JavaFxClassLauncher {

  /**
   * Launches a JavaFX UI from the given type.
   * @param guiSource the class defining the JavaFX UI to launch.
   * @return the {@code Stage} of the launched UI.
   */
  public static Stage launch(final Class<?> guiSource) {
    Stage stage = execute(new GuiQuery<Stage>() {
      @Override protected Stage executeInUIThread() {
        return (Stage) staticMethod("javafx$run$").withReturnType(Object.class)
                                                  .withParameterTypes(Sequence.class)
                                                  .in(guiSource)
                                                  .invoke(TypeInfo.String.emptySequence);
      }
    });
    return stage;
  }

  private JavaFxClassLauncher() {}
}
