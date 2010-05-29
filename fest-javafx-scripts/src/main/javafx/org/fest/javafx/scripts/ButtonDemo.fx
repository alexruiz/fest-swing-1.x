/*
 * Created on May 5, 2010
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
package org.fest.javafx.scripts;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import java.util.HashMap;

/**
 * Understands a simple UI with a button.
 *
 * @author Alex Ruiz
 */
def rectangle = Rectangle {
  id: "rectangle"
  width: 20
  height: 20
  fill: Color.BLUE
};

def colorMapping = new HashMap();
colorMapping.put(MouseButton.PRIMARY, Color.RED);
colorMapping.put(MouseButton.MIDDLE, Color.GREEN);
colorMapping.put(MouseButton.SECONDARY, Color.YELLOW);
colorMapping.put(MouseButton.NONE, Color.PURPLE);

Stage {
  title: "ButtonDemo.fx"
  scene: Scene {
    width: 250
    height: 80
    content: [
      VBox {
        spacing: 10
        content: [
          Button {
            id: "button"
            text: "Click Me"
            onMousePressed: function(evt: MouseEvent) {
              rectangle.fill = (colorMapping.get(evt.button) as Color);
            }
          }
          rectangle
        ]
      }
    ]
  }
}
