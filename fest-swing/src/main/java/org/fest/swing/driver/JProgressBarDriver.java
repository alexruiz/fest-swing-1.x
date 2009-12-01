/*
 * Created on Dec 1, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import javax.swing.JProgressBar;

import org.fest.swing.core.Robot;

/**
 * Understands:
 * <ul>
 * <li>simulation of user input on a <code>{@link JProgressBar}</code> (if applicable)</li>
 * <li>state verification of a <code>{@link JProgressBar}</code></li>
 * </ul>
 * This class is intended for internal use only.
 *
 * @author Alex Ruiz
 */
public class JProgressBarDriver extends JComponentDriver {

  /**
   * Creates a new </code>{@link JProgressBarDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JProgressBarDriver(Robot robot) {
    super(robot);
  }

}
