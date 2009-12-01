/*
 * Created on Apr 10, 2007
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

import java.awt.Component;

/**
 * Understands simulation of user events on GUI components that accept text input from the user.
 * 
 * @author Alex Ruiz
 */
public interface TextInputFixture extends TextDisplayFixture, EditableComponentFixture {

  /**
   * Simulates a user entering the given text in the <code>{@link Component}</code> managed by this fixture.
   * @param text the text to enter.
   * @return this fixture.
   */
  TextInputFixture enterText(String text);

  /**
   * Simulates a user deleting all the text in the <code>{@link Component}</code> managed by this fixture.
   * @return this fixture.
   */
  TextInputFixture deleteText();
  
  /**
   * Simulates a user selecting all the text contained in the <code>{@link Component}</code> managed by this fixture. 
   * @return this fixture.
   */
  TextInputFixture selectAll();

  /**
   * Simulates a user selecting a portion of the text contained in the <code>{@link Component}</code> managed by this
   * fixture.
   * @param start index where selection should start.
   * @param end index where selection should end.
   * @return this fixture.
   */
  TextInputFixture selectText(int start, int end);
  
  /**
   * Simulates a user selecting the given text contained in the <code>{@link Component}</code> managed by this fixture.
   * @param text the text to select.
   * @return this fixture.
   */
  TextInputFixture select(String text);
}
