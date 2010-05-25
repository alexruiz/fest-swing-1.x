/*
 * Created on May 24, 2010
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
package org.fest.javafx.test.builder;

import static org.fest.javafx.threading.GuiActionRunner.execute;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiQuery;

import javafx.scene.control.Button;

/**
 * Understands a factory of <code>{@link Button}</code>s.
 *
 * @author Alex Ruiz
 */
public class Buttons {

  private Buttons() {}

  public static ButtonFactory button() {
    return new ButtonFactory();
  }

  public static class ButtonFactory {
    String id;

    public ButtonFactory withId(String newId) {
      id = newId;
      return this;
    }

    @RunsInUIThread
    public Button createNew() {
      return execute(new GuiQuery<Button>() {
        @Override protected Button executeInUIThread() {
          Button button = new Button();
          button.set$id(id);
          return button;
        }
      });
    }
  }
}
