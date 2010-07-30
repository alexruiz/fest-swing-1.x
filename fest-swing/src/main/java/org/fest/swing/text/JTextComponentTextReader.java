/*
 * Created on Jul 29, 2010
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
package org.fest.swing.text;

import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands reading the text displayed in a <code>{@link JTextComponent}</code>.
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class JTextComponentTextReader extends TextReader<JTextComponent> {

  /**
   * Indicates that this reader supports {@code JTextComponent}s.
   * @return {@code JTextComponent.class}.
   */
  public Class<JTextComponent> supportedComponent() {
    return JTextComponent.class;
  }

  /**
   * Indicates whether the given {@code JTextComponent} displays the given text.
   * @param textComponent the given {@code JTextComponent}.
   * @param text the given text.
   * @return {@code true} if the given {@code JTextComponent} displays the given text; {@code false} otherwise.
   */
  @RunsInCurrentThread
  @Override protected boolean checkContainsText(JTextComponent textComponent, String text) {
    String labelText = textComponent.getText();
    if (labelText == null) return false;
    return labelText.contains(text);
  }
}
