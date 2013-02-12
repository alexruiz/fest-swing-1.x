/*
 * Created on Aug 2, 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.timing.Timeout;

/**
 * Understands an implementation of {@link AbstractWindowFixture} for testing.
 * 
 * @author Alex Ruiz
 */
class ConcreteWindowFixture extends AbstractWindowFixture<JFrame> {
  public ConcreteWindowFixture(Class<? extends JFrame> type) {
    super(type);
  }

  public ConcreteWindowFixture(Robot robot, Class<? extends JFrame> type) {
    super(robot, type);
  }

  public ConcreteWindowFixture(String name, Class<? extends JFrame> type) {
    super(name, type);
  }

  public ConcreteWindowFixture(Robot robot, String name, Class<? extends JFrame> type) {
    super(robot, name, type);
  }

  public ConcreteWindowFixture(JFrame target) {
    super(target);
  }

  public ConcreteWindowFixture(Robot robot, JFrame target) {
    super(robot, target);
  }

  @Override
  public ConcreteWindowFixture show() {
    return this;
  }

  @Override
  public ConcreteWindowFixture show(Dimension size) {
    return this;
  }

  public void close() {}

  public ConcreteWindowFixture focus() {
    return this;
  }

  public ConcreteWindowFixture requireFocused() {
    return this;
  }

  public ConcreteWindowFixture pressKey(int keyCode) {
    return this;
  }

  public ConcreteWindowFixture releaseKey(int keyCode) {
    return this;
  }

  public ConcreteWindowFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    return this;
  }

  public ConcreteWindowFixture pressAndReleaseKeys(int... keyCodes) {
    return this;
  }

  public ConcreteWindowFixture click() {
    return this;
  }

  public ConcreteWindowFixture click(MouseButton button) {
    return this;
  }

  public ConcreteWindowFixture click(MouseClickInfo mouseClickInfo) {
    return this;
  }

  public ConcreteWindowFixture rightClick() {
    return this;
  }

  public ConcreteWindowFixture doubleClick() {
    return this;
  }

  public ConcreteWindowFixture requireEnabled() {
    return this;
  }

  public ConcreteWindowFixture requireEnabled(Timeout timeout) {
    return this;
  }

  public ConcreteWindowFixture requireDisabled() {
    return this;
  }

  public ConcreteWindowFixture requireVisible() {
    return this;
  }

  public ConcreteWindowFixture requireNotVisible() {
    return this;
  }

  public ConcreteWindowFixture moveTo(Point p) {
    return this;
  }

  public ConcreteWindowFixture moveToBack() {
    return this;
  }

  public ConcreteWindowFixture moveToFront() {
    return this;
  }

  public ConcreteWindowFixture requireSize(Dimension size) {
    return this;
  }

  public ConcreteWindowFixture resizeTo(Dimension size) {
    return this;
  }

  public ConcreteWindowFixture resizeHeightTo(int height) {
    return this;
  }

  public ConcreteWindowFixture resizeWidthTo(int width) {
    return this;
  }

  public JPopupMenuFixture showPopupMenu() {
    return null;
  }

  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return null;
  }
}