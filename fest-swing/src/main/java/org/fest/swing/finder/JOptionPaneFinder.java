/*
 * Created on Oct 29, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.finder;

import java.awt.Component;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.JOptionPaneFixture;

/**
 * Understands a finder for <code>{@link JOptionPane}</code>s. Lookups are performed till a file chooser is found, or
 * until the given time to perform the lookup is over. The default lookup time is 5 seconds.
 * </p>
 * <p>
 * This example illustrates finding a <code>{@link JOptionPane}</code> by name, using the default lookup time (5
 * seconds):
 * 
 * <pre>
 * JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().using(robot);
 * </pre>
 * 
 * </p>
 * <p>
 * Where <code>robot</code> is an instance of <code>{@link org.fest.swing.core.Robot}</code>.
 * </p>
 * <p>
 * This example shows how to find a <code>{@link JOptionPane}</code> by type using a lookup time of 10 seconds:
 * 
 * <pre>
 * JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().withTimeout(10000).using(robot);
 * </pre>
 * 
 * We can also specify the time unit:
 * 
 * <pre>
 * JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().withTimeout(10, {@link TimeUnit#SECONDS SECONDS}).using(robot);
 * </pre>
 * 
 * </p>
 * <p>
 * This example shows how to find a <code>{@link JOptionPane}</code> using a <code>{@link GenericTypeMatcher}</code>:
 * 
 * <pre>
 * GenericTypeMatcher&lt;JOptionPane&gt; matcher = new GenericTypeMatcher&lt;JOptionPane&gt;() {
 *   protected boolean isMatching(JOptionPane optionPane) {
 *     return &quot;A message&quot;.equals(optionPane.getMessage());
 *   }
 * };
 * JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane(matcher).using(robot);
 * </pre>
 * 
 * </p>
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class JOptionPaneFinder extends ComponentFinderTemplate<JOptionPane> {

  JOptionPaneFinder() {
    super(JOptionPane.class);
  }

  JOptionPaneFinder(GenericTypeMatcher<? extends JOptionPane> matcher) {
    super(matcher);
  }
  
  /**
   * Creates a new <code>{@link JOptionPaneFinder}</code> capable of looking up a <code>{@link JOptionPane}</code>.
   * @return the created finder.
   */
  public static JOptionPaneFinder findOptionPane() {
    return new JOptionPaneFinder();
  }

  /**
   * Creates a new <code>{@link JOptionPaneFinder}</code> capable of looking up a <code>{@link JOptionPane}</code>
   * using the given matcher.
   * @param matcher the given matcher.
   * @return the created finder.
   */
  public static JOptionPaneFinder findOptionPane(GenericTypeMatcher<? extends JOptionPane> matcher) {
    return new JOptionPaneFinder(matcher);
  }
  
  /**
   * Finds a <code>{@link JOptionPaneFinder}</code> by name or type.
   * @param robot contains the underlying finding to delegate the search to.
   * @return a <code>JOptionPaneFixture</code> managing the found <code>JOptionPane</code>.
   * @throws org.fest.swing.exception.WaitTimedOutError if a <code>JOptionPane</code> could not be found.
   */
  public JOptionPaneFixture using(Robot robot) {
    return new JOptionPaneFixture(robot, findComponentWith(robot));
  }

  /**
   * Sets the timeout for this finder. The window to search should be found within the given time period.
   * @param timeout the number of milliseconds before stopping the search.
   * @return this finder.
   */
  @Override public JOptionPaneFinder withTimeout(long timeout) {
    super.withTimeout(timeout);
    return this;
  }

  /**
   * Sets the timeout for this finder. The window to search should be found within the given time period.
   * @param timeout the period of time the search should be performed.
   * @param unit the time unit for <code>timeout</code>.
   * @return this finder.
   */
  @Override public JOptionPaneFinder withTimeout(long timeout, TimeUnit unit) {
    super.withTimeout(timeout, unit);
    return this;
  }

  JOptionPane cast(Component c) { return (JOptionPane) c; }
}