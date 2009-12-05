/*
 * Created on Dec 4, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.driver.JProgressBarValueQuery.valueOf;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.util.Strings.concat;

import javax.swing.JProgressBar;

import org.fest.assertions.Description;
import org.fest.swing.edt.GuiLazyLoadingDescription;
import org.fest.swing.timing.Condition;

/**
 * Understands an EDT-safe task that waits until the value of a <code>{@link JProgressBar}</code> is equal to the
 * given expected value.
 *
 * @author Alex Ruiz
 */
final class JProgressBarWaitUntilValueIsEqualToExpectedTask {

  static void waitUntilValueIsEqualToExpected(final JProgressBar progressBar, final int expected) {
    pause(new Condition(untilValueIsEqualTo(progressBar, expected)) {
      public boolean test() {
        return valueOf(progressBar) == expected;
      }
    });
  }

  private static Description untilValueIsEqualTo(final JProgressBar progressBar, final int expected) {
    return new GuiLazyLoadingDescription() {
      protected String loadDescription() {
        return concat("value of ", format(progressBar), " to be equal to ", expected);
      }
    };
  }

  private JProgressBarWaitUntilValueIsEqualToExpectedTask() {}
}
