/*
 * Created on Feb 22, 2009
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

import javafx.ext.swing.SwingList;
import javafx.ext.swing.SwingListItem;
import javafx.scene.*;
import javafx.stage.*;

/** 
 * Script for testing <code>org.fest.javafx.desktop.driver.SwingListDriver</code>.
 *
 * @author Alex Ruiz 
 */
Stage {
  title: "SwingListDriverTest"
  width: 300
  scene: Scene {
    content: [
      SwingList {
        id: "list"
        width: 200
        height: 100
        items: [
          SwingListItem {
            text: "One"
          }
          SwingListItem {
            text: "Two"
            selected: true
          }
          SwingListItem {
            text: "Three"
          }
          SwingListItem {
            text: "Four"
          }
          SwingListItem {
            text: "Five"
          }
          SwingListItem {
            text: "Six"
          }
          SwingListItem {
            text: "Seven"
          }
          SwingListItem {
            text: "Eight"
          }
          SwingListItem {
            text: "Nine"
          }
          SwingListItem {
            text: "Ten"
          }
          SwingListItem {
            text: "Eleven"
          }
          SwingListItem {
            text: "Twelve"
          }
          SwingListItem {
            text: "Thirteen"
          }
          SwingListItem {
            text: "Fourteen"
          }
          SwingListItem {
            text: "Fifteen"
          }
          SwingListItem {
            text: "Sixteen"
          }
        ]
      }
    ]
  }
}
