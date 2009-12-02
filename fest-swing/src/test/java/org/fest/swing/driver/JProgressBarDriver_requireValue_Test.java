/*
 * Created on Dec 1, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JProgressBar;

import org.junit.Test;

/**
 * Tests for <code>{@link JProgressBarDriver#requireValue(JProgressBar, int)}</code>.
 *
 * @author Alex Ruiz
 */
public class JProgressBarDriver_requireValue_Test extends JProgressBarDriver_TestCase {

  @Test
  public void should_pass_if_value_is_equal_to_expected() {
    driver.requireValue(progressBar, 60);
  }

  @Test
  public void should_fail_if_value_is_not_equal_to_expected() {
    try {
      driver.requireValue(progressBar, 50);
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'value'")
                                .contains("expected:<50> but was:<60>");
    }
  }
}
