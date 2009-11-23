/*
 * Created on Feb 19, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; either version 2 of 
 * the License. You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 * the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.driver;
  
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingCheckBox;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.stage.*;

/** 
 * Script for testing <code>org.fest.javafx.desktop.driver.AbstractSwingButtonDriver</code>.
 *
 * @author Alex Ruiz 
 */
Stage {
  title: "AbstractSwingButtonDriverTest"
  width: 300
  scene: Scene {
    content: [
      HBox {
        content: [
          SwingButton {
            id: "button"
            text: "Click Me"
          }
          SwingCheckBox {
            id: "selectedCheckBox"
            text: "Uncheck Me"
            selected: true
          }
          SwingCheckBox {
            id: "unselectedCheckBox"
            text: "Check Me"
            selected: false
          }
        ]
      }
    ]
  }
}
  