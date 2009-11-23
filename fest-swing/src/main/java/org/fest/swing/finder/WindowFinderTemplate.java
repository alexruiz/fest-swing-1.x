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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.finder;

import java.awt.Window;
import java.util.concurrent.TimeUnit;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.WindowFixture;

/**
 * Understands a template for <code>{@link Window}</code> finders.
 * @param <T> the type of window this finder can search.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
abstract class WindowFinderTemplate<T extends Window> extends ComponentFinderTemplate<T> {

  WindowFinderTemplate(String windowName, Class<? extends T> windowType) {
    super(windowName, windowType);
  }

  WindowFinderTemplate(GenericTypeMatcher<? extends T> matcher) {
    super(matcher);
  }

  WindowFinderTemplate(Class<? extends T> windowType) {
    super(windowType);
  }

  @Override WindowFinderTemplate<T> withTimeout(long timeout) {
    super.withTimeout(timeout);
    return this;
  }

  @Override WindowFinderTemplate<T> withTimeout(long timeout, TimeUnit unit) {
    super.withTimeout(timeout, unit);
    return this;
  }

  /**
   * Finds a window by name or type using the given robot.
   * @param robot contains the underlying finding to delegate the search to.
   * @return a fixture capable of managing the found window.
   * @throws org.fest.swing.exception.WaitTimedOutError if a window with the given name or of the given type could not 
   * be found.
   */
  public abstract WindowFixture<T> using(Robot robot);
}
