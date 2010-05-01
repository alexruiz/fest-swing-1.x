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

import static com.apple.mrj.MRJApplicationUtils.registerAboutHandler;

import org.fest.util.VisibleForTesting;

import com.apple.mrj.MRJAboutHandler;

/**
 * Understands support for Mac OS X-specific features.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
class MacSupport {

  private final SystemProperties systemProperties;

  void setUpForMacOS() {
    if (!isMacOS()) return;
    systemProperties.set("apple.laf.useScreenMenuBar", "true");
    systemProperties.set("com.apple.mrj.application.apple.menu.about.name", "FEST Keyboard Mapping Tool");
  }

  boolean isMacOS() {
    return systemProperties.get("os.name").startsWith("Mac") || systemProperties.get("mrj.version") != null;
  }

  void register(MRJAboutHandler h) {
    registerAboutHandler(h);
  }

  static MacSupport instance() {
    return LazyLoadedSingleton.INSTANCE;
  }

  private static class LazyLoadedSingleton {
    static final MacSupport INSTANCE = new MacSupport();
  }

  @VisibleForTesting
  MacSupport() {
    this(new SystemProperties());
  }

  @VisibleForTesting
  MacSupport(SystemProperties systemProperties) {
    this.systemProperties = systemProperties;
  }
}
