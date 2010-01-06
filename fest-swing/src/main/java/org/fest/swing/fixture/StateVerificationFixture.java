/*
 * Created on Jul 8, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands state verification of a GUI component.
 *
 * @author Alex Ruiz
 */
public interface StateVerificationFixture {

  /**
   * Asserts that this fixture's GUI component is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's GUI component is enabled.
   */
  StateVerificationFixture requireDisabled();

  /**
   * Asserts that this fixture's GUI component is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's GUI component is disabled.
   */
  StateVerificationFixture requireEnabled();

  /**
   * Asserts that this fixture's GUI component is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's GUI component is never enabled.
   */
  StateVerificationFixture requireEnabled(Timeout timeout);

  /**
   * Asserts that this fixture's GUI component is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's GUI component is visible.
   */
  StateVerificationFixture requireNotVisible();

  /**
   * Asserts that this fixture's GUI component is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's GUI component is not visible.
   */
  StateVerificationFixture requireVisible();
}
