/*
 * Created on Jul 30, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.finder;

import java.awt.Dialog;
import java.awt.Frame;

import org.fest.swing.core.GenericTypeMatcher;

/**
 * <p>
 * Understands lookup of <code>{@link Frame}</code>s and <code>{@link Dialog}</code>s. Lookups are performed till
 * the window of interest is found, or until the given time to perform the lookup is over. The default lookup time is 5
 * seconds.
 * </p>
 * <p>
 * <code>{@link WindowFinder}</code> is the &quot;entry point&quot; of a fluent interface to look up frames and
 * dialogs. This example illustrates finding a <code>{@link Frame}</code> by name, using the default lookup time (5
 * seconds):
 * 
 * <pre>
 * FrameFixture frame = WindowFinder.findFrame(&quot;someFrame&quot;).using(robot);
 * </pre>
 * 
 * </p>
 * <p>
 * Where <code>robot</code> is an instance of <code>{@link org.fest.swing.core.Robot}</code>.
 * </p>
 * <p>
 * This example shows how to find a <code>{@link Dialog}</code> by type using a lookup time of 10 seconds:
 * 
 * <pre>
 * DialogFixture dialog = WindowFinder.findDialog(MyDialog.class).withTimeout(10000).using(robot);
 * </pre>
 * 
 * We can also specify the time unit:
 * 
 * <pre>
 * DialogFixture dialog = WindowFinder.findDialog(MyDialog.class).withTimeout(10, {@link java.util.concurrent.TimeUnit#SECONDS SECONDS}).using(robot);
 * </pre>
 * 
 * </p>
 * <p>
 * This example shows how to use a <code>{@link GenericTypeMatcher}</code> to find a <code>{@link Frame}</code> with
 * title "Hello":
 * 
 * <pre>
 * GenericTypeMatcher&lt;JFrame&gt; matcher = new GenericTypeMatcher&lt;JFrame&gt;() {
 *   protected boolean isMatching(JFrame frame) {
 *     return &quot;hello&quot;.equals(frame.getTitle());
 *   }
 * };
 * FrameFixture frame = WindowFinder.findFrame(matcher).using(robot);
 * </pre>
 * 
 * </p>
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class WindowFinder {
 
  private WindowFinder() {}

  /**
   * Creates a new <code>{@link FrameFinder}</code> capable of looking up a <code>{@link Frame}</code> by name.
   * @param frameName the name of the frame to find.
   * @return the created finder.
   */
  public static FrameFinder findFrame(String frameName) {
    return new FrameFinder(frameName);
  }

  /**
   * Creates a new <code>{@link FrameFinder}</code> capable of looking up a <code>{@link Frame}</code> by type.
   * @param frameType the type of the frame to find.
   * @return the created finder.
   */
  public static FrameFinder findFrame(Class<? extends Frame> frameType) {
    return new FrameFinder(frameType);
  }

  /**
   * Creates a new <code>{@link FrameFinder}</code> capable of looking up a <code>{@link Frame}</code> using the 
   * provided matcher.
   * @param matcher the matcher to use to find a frame.
   * @return the created finder.
   */
  public static FrameFinder findFrame(GenericTypeMatcher<? extends Frame> matcher) {
    return new FrameFinder(matcher);
  }
  
  /**
   * Creates a new <code>{@link DialogFinder}</code> capable of looking up a <code>{@link Dialog}</code> by name.
   * @param dialogName the name of the dialog to find.
   * @return the created finder.
   */
  public static DialogFinder findDialog(String dialogName) {
    return new DialogFinder(dialogName);
  }

  /**
   * Creates a new <code>{@link DialogFinder}</code> capable of looking up a <code>{@link Dialog}</code> by type.
   * @param dialogType the type of the dialog to find.
   * @return the created finder.
   */
  public static DialogFinder findDialog(Class<? extends Dialog> dialogType) {
    return new DialogFinder(dialogType);
  }

  /**
   * Creates a new <code>{@link DialogFinder}</code> capable of looking up a <code>{@link Dialog}</code> using the 
   * provided matcher.
   * @param matcher the matcher to use to find a dialog.
   * @return the created finder.
   */
  public static DialogFinder findDialog(GenericTypeMatcher<? extends Dialog> matcher) {
    return new DialogFinder(matcher);
  }
}
