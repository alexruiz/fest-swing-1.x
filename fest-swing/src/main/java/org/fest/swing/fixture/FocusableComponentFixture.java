/*
 * Created on Jul 13, 2008
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

/**
 * Understands simulation of input focus on a GUI component.
 *
 * @author Alex Ruiz
 */
public interface FocusableComponentFixture {

  /**
   * Gives input focus to this fixture's GUI component.
   * @return this fixture.
   * @throws IllegalStateException if the component is disabled.
   * @throws IllegalStateException if the component is not showing on the screen.
   */
  FocusableComponentFixture focus();

  /**
   * Asserts that this fixture's GUI component has input focus.
   * @return this fixture.
   * @throws AssertionError if this component does not have input focus.
   */
  FocusableComponentFixture requireFocused();
}
