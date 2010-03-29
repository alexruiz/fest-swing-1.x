/*
 * Created on May 16, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.util;

import static java.util.Locale.ENGLISH;
import static org.fest.swing.util.OSFamily.*;
import static org.fest.swing.util.OSFamily.LINUX;
import static org.fest.swing.util.OSFamily.UNIX;

import org.fest.util.VisibleForTesting;

/**
 * Understands identification of the current Operating System.
 *
 * @author Alex Ruiz
 */
class OSIdentifier {

  private final boolean isWindows;
  private final boolean isWindows9x;
  private final boolean isWindowsXP;
  private final boolean isMacintosh;
  private final boolean isOSX;
  private final boolean isX11;
  private final boolean isSolaris;
  private final boolean isHPUX;
  private final boolean isLinux;
  private final OSFamily osFamily;

  OSIdentifier() {
    this(new SystemPropertyReader());
  }

  @VisibleForTesting
  OSIdentifier(SystemPropertyReader r) {
    String osName = r.systemProperty("os.name").toLowerCase(ENGLISH);
    String mrjVersion = r.systemProperty("mrj.version");
    isWindows = osName.startsWith("windows");
    isWindows9x = isWindows && containsAny(osName, "95", "98", "me");
    isWindowsXP = isWindows && osName.contains("xp");
    isMacintosh = mrjVersion != null;
    isOSX = isMacintosh && osName.contains("os x");
    isX11 = !isOSX && !isWindows;
    isSolaris = osName.startsWith("sunos") || osName.startsWith("solaris");
    isHPUX = osName.equals("hp-ux");
    isLinux = osName.equals("linux");
    osFamily = findOSFamily();
  }

  private static boolean containsAny(String s, String... subs) {
    for (String sub : subs) if (s.contains(sub)) return true;
    return false;
  }

  private OSFamily findOSFamily() {
    if (isWindows()) return WINDOWS;
    if (isMacintosh() || isOSX()) return MAC;
    if (isLinux()) return LINUX;
    return UNIX;
  }

  boolean isWindows() { return isWindows; }
  boolean isWindows9x() { return isWindows9x; }
  boolean isWindowsXP() { return isWindowsXP; }
  boolean isMacintosh() { return isMacintosh; }
  boolean isOSX() { return isOSX; }
  boolean isX11() { return isX11; }
  boolean isSolaris() { return isSolaris; }
  boolean isHPUX() { return isHPUX; }
  boolean isLinux() { return isLinux; }

  /** @since 1.2 */
  OSFamily osFamily() { return osFamily; }
}
