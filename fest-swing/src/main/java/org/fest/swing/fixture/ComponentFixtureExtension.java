/*
 * Created on Aug 1, 2008
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

import java.awt.Component;
import java.awt.Container;

import org.fest.swing.core.Robot;

/**
 * Understands an "extension method" for implementations of <code>{@link ContainerFixture}</code>. This extension 
 * creates new <code>{@link ComponentFixture}</code>s managing components inside a 
 * <code>{@link ContainerFixture}</code>'s <code>{@link Container}</code>.
 * @param <C> the type of {@code Component} the <code>ComponentFixture</code> to create can handle.
 * @param <F> the type of <code>ComponentFixture</code> this extension can create.
 *
 * @author Alex Ruiz
 */
public abstract class ComponentFixtureExtension<C extends Component, F extends ComponentFixture<C>> {

  /**
   * Creates a new <code>{@link ComponentFixture}</code>.
   * @param robot the <code>Robot</code> to pass to the new fixture.
   * @param root the container where the component to handle by the created fixture is contained.
   * @return the created <code>ComponentFixture</code>.
   */
  public abstract F createFixture(Robot robot, Container root);
}
