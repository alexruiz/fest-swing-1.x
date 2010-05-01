/*
 * Created on May 1, 2010
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

import static java.awt.Desktop.Action.BROWSE;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.fest.util.VisibleForTesting;

/**
 * Understands utility methods related to the underlying platform.
 *
 * @author Alex Ruiz
 */
class Platform {

  private final DesktopProvider desktopProvider;

  boolean isBrowsingSupported() {
    return desktopProvider.supportsDesktop() && desktopProvider.desktop().isSupported(BROWSE);
  }

  void browse(URL url) throws IOException, URISyntaxException {
    desktopProvider.desktop().browse(url.toURI());
  }

  static Platform instance() {
    return LazyLoadedSingleton.INSTANCE;
  }

  private static class LazyLoadedSingleton {
    static final Platform INSTANCE = new Platform();
  }

  @VisibleForTesting
  Platform() {
    this(new DesktopProvider());
  }

  @VisibleForTesting
  Platform(DesktopProvider desktopProvider) {
    this.desktopProvider = desktopProvider;
  }
}
