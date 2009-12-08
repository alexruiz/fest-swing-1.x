/*
 * Created on Dec 7, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.launcher;

import static org.fest.reflect.core.Reflection.staticMethod;

import java.awt.Frame;
import javafx.stage.Stage;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;

import com.sun.javafx.runtime.TypeInfo;
import com.sun.javafx.runtime.sequence.Sequence;
import com.sun.javafx.tk.TKStage;
import com.sun.javafx.tk.swing.FrameStage;

/**
 * Understands SOMETHING DUMMY.
 *
 * @author Alex Ruiz
 */
public final class JavaFxClassLauncher {

  public static Frame launch(final Class<?> javaFxClass) {
    Stage stage = GuiActionRunner.execute(new GuiQuery<Stage>() {
      protected Stage executeInEDT() throws Throwable {
        return (Stage) staticMethod("javafx$run$").withReturnType(Object.class)
                                                  .withParameterTypes(Sequence.class)
                                                  .in(javaFxClass)
                                                  .invoke(TypeInfo.String.emptySequence);
      }
    });
    return frameFrom(stage);
  }

  private static Frame frameFrom(Stage stage) {
    TKStage peer = stage.get$javafx$stage$Stage$impl_peer();
    FrameStage frameStage = (FrameStage)peer;
    return (Frame)frameStage.window;
  }

  private JavaFxClassLauncher() {}
}
