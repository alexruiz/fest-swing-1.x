/*
 * Created on Aug 30, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.util;

import static java.lang.String.valueOf;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.util.Modifiers.keysFor;
import static org.fest.util.Strings.concat;

import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import org.fest.util.VisibleForTesting;

/**
 * Understands platform-specific functionality.
 *
 * @author Alex Ruiz
 */
public final class Platform {

  private static OSIdentifier osIdentifier;
  private static ToolkitProvider toolkitProvider;

  static {
    reload();
  }

  @VisibleForTesting
  static void reload() {
    initialize(new OSIdentifier(), new ToolkitProvider());
  }

  @VisibleForTesting
  static void initialize(OSIdentifier newOSIdentifier, ToolkitProvider newToolkitProvider) {
    osIdentifier = newOSIdentifier;
    toolkitProvider = newToolkitProvider;
  }

  /**
   * Return the modifier key for the appropriate accelerator key for menu shortcuts:
   * <code>{@link KeyEvent#VK_CONTROL}</code> (default) or <code>{@link KeyEvent#VK_META}</code> (MacOS.)
   * @return the modifier key for the appropriate accelerator key for menu shortcuts.
   * @throws AssertionError if unable to find the appropriate key.
   * @throws HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>.
   */
  public static int controlOrCommandKey() {
    int menuShortcutKeyMask = controlOrCommandMask();
    int[] keys = keysFor(menuShortcutKeyMask);
    assertThat(keys).as(concat("Key code for mask ", valueOf(menuShortcutKeyMask))).isNotNull().hasSize(1);
    return keys[0];
  }

  /**
   * Return the modifier mask for the appropriate accelerator key for menu shortcuts:
   * <code>{@link Event#CTRL_MASK}</code> (default) or <code>{@link Event#META_MASK}</code> (MacOS.)
   * @return the modifier mask for the appropriate accelerator key for menu shortcuts.
   * @throws HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>.
   */
  public static int controlOrCommandMask() {
    return toolkitProvider.toolkit().getMenuShortcutKeyMask();
  }

  /**
   * Indicates whether it is possible to resize windows that are not an instance of <code>{@link java.awt.Frame}</code>
   * or <code>{@link java.awt.Dialog}</code>. Most X11 window managers will allow this, but stock Macintosh and Windows
   * do not.
   * @return <code>true</code> if it is possible to resize windows other than <code>Frame</code>s or
   * <code>Dialog</code>s, <code>false</code> otherwise.
   */
  public static boolean canResizeWindows() {
    return !isWindows() && !isMacintosh();
  }

  /**
   * Indicates whether it is possible to move windows that are not an instance of <code>{@link java.awt.Frame}</code> or
   * <code>{@link java.awt.Dialog}</code>. Most X11 window managers will allow this, but stock Macintosh and Windows do
   * not.
   * @return <code>true</code> if it is possible to move windows other than <code>Frame</code>s or <code>Dialog</code>s,
   * <code>false</code> otherwise.
   */
  public static boolean canMoveWindows() {
    return !isWindows() && !isMacintosh();
  }

  /**
   * Indicates whether the operating system is Windows.
   * @return <code>true</code> if the operation system is Windows, <code>false</code> otherwise.
   */
  public static boolean isWindows() {
    return osIdentifier.isWindows();
  }

  /**
   * Indicates whether the operating system is Windows 9x (95, 98 or ME.)
   * @return <code>true</code> if the operating system is Windows 9x (95, 98 or ME,) <code>false</code> otherwise.
   */
  public static boolean isWindows9x() {
    return osIdentifier.isWindows9x();
  }

  /**
   * Indicates whether the operating system is Windows XP.
   * @return <code>true</code> if the operating system is Windows XP, <code>false</code> otherwise.
   */
  public static boolean isWindowsXP() {
    return osIdentifier.isWindowsXP();
  }

  /**
   * Indicates whether the operating system is a Macintosh OS.
   * @return <code>true</code> is the operating system is a Macintosh OS, <code>false</code> otherwise.
   */
  public static boolean isMacintosh() {
    return osIdentifier.isMacintosh();
  }

  /**
   * Indicates whether the operating system is Mac OS X.
   * @return <code>true</code> if the operating system is Mac OS X, <code>false</code> otherwise.
   */
  public static boolean isOSX() {
    return osIdentifier.isOSX();
  }

  /**
   * Indicates whether the operating system is using the X11 Windowing system.
   * @return <code>true</code> if the operating system is using the X11 Windowing system, <code>false</code> otherwise.
   */
  public static boolean isX11() {
    return osIdentifier.isX11();
  }

  /**
   * Indicates whether the operating system is Solaris.
   * @return <code>true</code> if the operating system is Solaris, <code>false</code> otherwise.
   */
  public static boolean isSolaris() {
    return osIdentifier.isSolaris();
  }

  /**
   * Indicates whether the operating system is HP-UX.
   * @return <code>true</code> if the operating system is HP-UX, <code>false</code> otherwise.
   */
  public static boolean isHPUX() {
    return osIdentifier.isHPUX();
  }

  /**
   * Indicates whether the operating system is Linux.
   * @return <code>true</code> if the operating system is Linux, <code>false</code> otherwise.
   */
  public static boolean isLinux() {
    return osIdentifier.isLinux();
  }

  /**
   * Returns the current operating system family.
   * @return the current operating system family.
   * @since 1.2
   */
  public static OSFamily osFamily() {
    return osIdentifier.osFamily();
  }

  private Platform() {}
}
