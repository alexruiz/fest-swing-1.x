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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

/**
 * Understands:
 * <ul>
 * <li>simulation of keyboard focus</li>
 * <li>simulation of keyboard input</li>
 * <li>simulation of mouse input</li>
 * <li>verification of state</li>
 * </ul>
 * of a GUI component.
 *
 * @author Alex Ruiz
 */
public interface CommonComponentFixture extends FocusableComponentFixture, KeyboardInputSimulationFixture,
    MouseInputSimulationFixture, StateVerificationFixture {}