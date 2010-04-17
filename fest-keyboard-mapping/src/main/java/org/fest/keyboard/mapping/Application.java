/*
 * Created on Apr 1, 2010
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

import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.UIManager.*;

/**
 * Understands the application's main class.
 *
 * @author Alex Ruiz
 */
public class Application {

  public static void main(String... args) {
    setUpForMac();
    invokeLater(new Runnable() {
      public void run() {
        setLaF();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.giveFocusToCharTextField();
      }

      private void setLaF() {
        try {
          setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
      }
    });
  }

  private static void setUpForMac() {
    String osName = System.getProperty("os.name");
    if (!osName.startsWith("Mac")) return;
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "FEST Keyboard Mapping Tool");
  }
}
