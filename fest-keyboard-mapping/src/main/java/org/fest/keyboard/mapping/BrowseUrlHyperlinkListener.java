/*
 * Created on Apr 30, 2010
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

import static javax.swing.event.HyperlinkEvent.EventType.ACTIVATED;

import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.fest.util.VisibleForTesting;

/**
 * Understands a <code>{@link HyperlinkListener}</code> that opens a URL in the system's default browser when a user
 * clicks on a hyperlink.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class BrowseUrlHyperlinkListener implements HyperlinkListener {

  @VisibleForTesting
  final Platform platform;

  static void install(JEditorPane target, Platform platform) {
    if (!platform.isBrowsingSupported()) return;
    target.addHyperlinkListener(new BrowseUrlHyperlinkListener(platform));
  }

  @VisibleForTesting
  BrowseUrlHyperlinkListener(Platform platform) {
    this.platform = platform;
  }

  @Override
  public void hyperlinkUpdate(HyperlinkEvent e) {
    if (!e.getEventType().equals(ACTIVATED)) return;
    new Thread(new BrowseUrlRunnable(platform, e.getURL())).start();
  }

  private static class BrowseUrlRunnable implements Runnable {
    private final Platform platform;
    private final URL url;

    BrowseUrlRunnable(Platform platform, URL url) {
      this.platform = platform;
      this.url = url;
    }

    @Override public void run() {
      try {
        platform.browse(url);
      } catch (Exception ignored) {}
    }
  }
}
