/*
 * Created on Dec 1, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import javax.swing.JProgressBar;

import org.fest.swing.core.Robot;

/**
 * Understands functional testing of <code>{@link JProgressBar}</code>s including:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
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
