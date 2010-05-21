/*
 * Created on Dec 7, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.launcher;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.threading.GuiActionRunner.execute;
import javafx.stage.Stage;

import org.fest.javafx.scripts.ButtonDemo;
import org.fest.javafx.test.core.SequentialTestCase;
import org.fest.javafx.threading.GuiQuery;
import org.fest.ui.testing.annotation.GuiTest;

import org.junit.Test;

/**
 * Tests for <code>{@link JavaFxClassLauncher#launch(Class)}</code>.
 *
 * @author Alex Ruiz
 */
@GuiTest
public class JavaFxClassLauncher_launch_Test extends SequentialTestCase {

  @Test
  public void should_start_JavaFX_UI() {
    final Stage stage = JavaFxClassLauncher.launch(ButtonDemo.class);
    boolean visible = execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInUIThread() throws Throwable {
        return stage.$visible;
      }
    });
    assertThat(visible).isTrue();
  }
}
