/*
 * Created on Dec 18, 2007
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
package org.fest.swing.fixture;


/**
 * Understands functional testing of frame-like components (not necessarily subclasses of
 * <code>{@link java.awt.Frame}</code>):
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public interface FrameLikeFixture extends WindowLikeContainerFixture {

  /**
   * Simulates a user iconifying this fixture's frame-like component.
   * @return this fixture.
   */
  FrameLikeFixture iconify();

  /**
   * Simulates a user deiconifying this fixture's frame-like component.
   * @return this fixture.
   */
  FrameLikeFixture deiconify();

  /**
   * Simulates a user maximizing this fixture's frame-like component.
   * @return this fixture.
   */
  FrameLikeFixture maximize();

  /**
   * Simulates a user normalizing this fixture's frame-like component.
   * @return this fixture.
   */
  FrameLikeFixture normalize();
}
