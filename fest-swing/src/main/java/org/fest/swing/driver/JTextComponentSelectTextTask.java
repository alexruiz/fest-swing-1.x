/*
 * Created on Aug 11, 2008
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
package org.fest.swing.driver;

import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a task that selects text in a given <code>{@link JTextComponent}</code>.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * 
 * @author Alex Ruiz
 */
final class JTextComponentSelectTextTask {
  
  @RunsInCurrentThread
  static void selectTextInRange(JTextComponent textBox, int start, int end) {
    textBox.setCaretPosition(start);
    textBox.moveCaretPosition(end);
  }
  
  private JTextComponentSelectTextTask() {}
}