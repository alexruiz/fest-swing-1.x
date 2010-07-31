/*
 * Created on Jul 31, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.finder;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;

/**
 * Understands a finder for <code>{@link Dialog}</code>s. This class cannot be used directly, please see
 * <code>{@link WindowFinder}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class DialogFinder extends WindowFinderTemplate<Dialog> {

  /**
   * Creates a new </code>{@link DialogFinder}</code>.
   * @param dialogName the name of the {@code Dialog} to look for.
   */
  protected DialogFinder(String dialogName) {
    super(dialogName, Dialog.class);
  }

  /**
   * Creates a new </code>{@link DialogFinder}</code>.
   * @param matcher specifies the search criteria to use when looking up a {@code Dialog}.
   */
  protected DialogFinder(GenericTypeMatcher<? extends Dialog> matcher) {
    super(matcher);
  }

  /**
   * Creates a new </code>{@link DialogFinder}</code>.
   * @param dialogType the type of {@code Dialog} to look for.
   */
  protected DialogFinder(Class<? extends Dialog> dialogType) {
    super(dialogType);
  }

  /**
   * Sets the timeout for this finder. The window to search should be found within the given time period.
   * @param timeout the number of milliseconds before stopping the search.
   * @return this finder.
   */
  @Override public DialogFinder withTimeout(long timeout) {
    super.withTimeout(timeout);
    return this;
  }

  /**
   * Sets the timeout for this finder. The window to search should be found within the given time period.
   * @param timeout the period of time the search should be performed.
   * @param unit the time unit for <code>timeout</code>.
   * @return this finder.
   */
  @Override public DialogFinder withTimeout(long timeout, TimeUnit unit) {
    super.withTimeout(timeout, unit);
    return this;
  }

  /**
   * Finds a <code>{@link Dialog}</code> by name or type.
   * @param robot contains the underlying finding to delegate the search to.
   * @return a <code>DialogFixture</code> managing the found <code>Dialog</code>.
   * @throws org.fest.swing.exception.WaitTimedOutError if a <code>Dialog</code> could not be found.
   */
  @Override public DialogFixture using(Robot robot) {
    return new DialogFixture(robot, findComponentWith(robot));
  }

  /**
   * Casts the given {@code Component} to <code>{@link Dialog}</code>.
   * @return the given {@code Component}, casted to {@code Dialog}.
   */
  @Override protected Dialog cast(Component c) { return (Dialog)c; }
}