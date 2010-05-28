/*
 * Created on Dec 7, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.launcher;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.javafx.util.Scenes.close;
import javafx.stage.Stage;

import org.fest.javafx.annotations.RunsInCurrentThread;
import org.fest.javafx.scripts.ButtonDemo;
import org.fest.javafx.test.core.SequentialTestCase;
import org.fest.javafx.threading.GuiQuery;
import org.fest.ui.testing.annotation.GuiTest;

import org.junit.Test;

/**
 * Tests for <code>{@link GuiLauncher#launch(Class)}</code>.
 *
 * @author Alex Ruiz
 */
@GuiTest
public class GuiLauncher_launch_Test extends SequentialTestCase {

  @Test
  public void should_start_JavaFX_UI() {
    Stage stage = null;
    try {
      stage = GuiLauncher.launch(ButtonDemo.class);
      boolean visible = isVisible(stage);
      assertThat(visible).isTrue();
    } finally {
      if (stage != null) close(stage.get$scene());
    }
  }

  @RunsInCurrentThread
  private boolean isVisible(final Stage stage) {
    boolean visible = execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInUIThread() throws Throwable {
        return stage.$visible;
      }
    });
    return visible;
  }
}
