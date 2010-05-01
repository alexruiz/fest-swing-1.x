/*
 * Created on Apr 17, 2010
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
import org.fest.util.VisibleForTesting;

import com.apple.mrj.MRJAboutHandler;

/**
 * Understands a Mac-only "About" handler.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
class MacAboutHandler implements MRJAboutHandler {

  private final MainFrame frame;

  static void installMacAboutHandler(MainFrame frame, MacSupport macSupport) {
    if (!macSupport.isMacOS()) return;
    macSupport.register(new MacAboutHandler(frame));
  }

  @VisibleForTesting
  MacAboutHandler(MainFrame frame) {
    this.frame = frame;
  }

  public void handleAbout() {
    invokeLater(new Runnable() {
      public void run() {
        frame.showAboutWindow();
      }
    });
  }
}
