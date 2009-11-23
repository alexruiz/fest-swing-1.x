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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Understands simulation of user events on a window-like container (not necessarily a subclass of 
 * <code>{@link java.awt.Window}</code>) and verification of the state of such window-like container.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public interface WindowLikeContainerFixture {

  /**
   * Simulates a user closing this fixture's window-like container.
   */
  void close();
  
  /**
   * Simulates a user resizing horizontally this fixture's window-like container.
   * @param width the width that this fixture's window-like container should have after being resized.
   * @return this fixture.
   */
  WindowLikeContainerFixture resizeWidthTo(int width);

  /**
   * Simulates a user resizing vertically this fixture's window-like container.
   * @param height the height that this fixture's window-like container should have after being resized.
   * @return this fixture.
   */
  WindowLikeContainerFixture resizeHeightTo(int height);

  /**
   * Simulates a user resizing this fixture's window-like container.
   * @param size the size that the target window should have after being resized.
   * @return this fixture.
   */
  WindowLikeContainerFixture resizeTo(Dimension size);

  /**
   * Asserts that the size of this fixture's window-like container is equal to given one. 
   * @param size the given size to match.
   * @return this fixture.
   * @throws AssertionError if the size of this fixture's window-like container is not equal to the given size. 
   */
  WindowLikeContainerFixture requireSize(Dimension size);
  
  /**
   * Simulates a user moving this fixture's window-like container to the given point.
   * @param p the point to move this fixture's window-like container to.
   * @return this fixture.
   */
  WindowLikeContainerFixture moveTo(Point p);


  /**
   * Brings this fixture's window-like component to the front.
   * @return this fixture.
   */
  WindowLikeContainerFixture moveToFront();
  
  /**
   * Sends this fixture's window-like component to the back.
   * @return this fixture.
   */
  WindowLikeContainerFixture moveToBack();
}
