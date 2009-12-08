/*
 * Created on Dec 7, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.launcher;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Frame;

import org.fest.javafx.scripts.dnd.ball.Main;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link JavaFxClassLauncher}</code>.
 *
 * @author Alex Ruiz
 */
public class JavaFxClassLauncher_Test {

  @Before
  public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @Test
  public void should_start_JavaFX_UI() {
    Frame frame = JavaFxClassLauncher.launch(Main.class);
    assertThat(frame).isNotNull();
  }

}
