/*
 * Created on Apr 14, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import static java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager;

/**
 * Understands an action that gives focus to the next component when the "tab" key is pressed.
 *
 * @author Alex Ruiz
 */
class TabOutAction extends AbstractAction {

  private static final long serialVersionUID = 1L;
  
  public void actionPerformed(ActionEvent e) {
    getCurrentKeyboardFocusManager().focusNextComponent();
  }
}
