/*
 * Created on Jul 16, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.core.matcher;

import static java.lang.String.valueOf;
import static org.fest.util.Strings.concat;

import java.awt.Frame;
import java.util.regex.Pattern;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands matching a <code>{@link Frame}</code>  by type, name or title.
 *
 * @author Alex Ruiz
 */
public final class FrameMatcher extends NamedComponentMatcherTemplate<Frame> {

  private Object title;

  /**
   * Creates a new <code>{@link FrameMatcher}</code> that matches a <code>{@link Frame}</code> that:
   * <ol>
   * <li>has a matching name</li>
   * <li>(optionally) has matching title</li>
   * <li>(optionally) is showing on the screen</li>
   * <p>
   * The following code listing shows how to match a <code>{@link Frame}</code> by name and title:
   * <pre>
   * FrameMatcher m = {@link #withName(String) withName}("myApp").{@link #andTitle(String) andTitle}("My App");
   * </pre>
   * </p>
   * <p>
   * The following code listing shows how to match a <code>{@link Frame}</code>, that should be showing on the screen,
   * by name and title:
   * <pre>
   * FrameMatcher m = {@link #withName(String) withName}("myApp").{@link #andTitle(String) andTitle}("My App").{@link #andShowing() andShowing}();
   * </pre>
   * </p>
   * @param name the id to match.
   * @return the created matcher.
   */
  public static FrameMatcher withName(String name) {
    return new FrameMatcher(name, ANY);
  }

  /**
   * Creates a new <code>{@link FrameMatcher}</code> that matches a <code>{@link Frame}</code> by its title.
   * <p>
   * The following code listing shows how to match a <code>{@link Frame}</code> by title:
   * <pre>
   * FrameMatcher m = {@link #withTitle(String) withTitle}("My App");
   * </pre>
   * </p>
   * <p>
   * The following code listing shows how to match a <code>{@link Frame}</code>, that should be showing on the screen,
   * by title:
   * <pre>
   * FrameMatcher m = {@link #withTitle(String) withTitle}("My App").{@link #andShowing() andShowing}();
   * </pre>
   * </p>
   * @param title the title to match. It can be a regular expression.
   * @return the created matcher.
   */
  public static FrameMatcher withTitle(String title) {
    return new FrameMatcher(ANY, title);
  }

  /**
   * Creates a new <code>{@link FrameMatcher}</code> that matches a <code>{@link Frame}</code> by its title.
   * <p>
   * The following code listing shows how to match a <code>{@link Frame}</code> by title, using a regular expression
   * matcher:
   * <pre>
   * FrameMatcher m = {@link #withTitle(Pattern) withTitle}(Pattern.compile("My.*"));
   * </pre>
   * </p>
   * <p>
   * The following code listing shows how to match a <code>{@link Frame}</code>, that should be showing on the screen,
   * by title:
   * <pre>
   * FrameMatcher m = {@link #withTitle(Pattern) withTitle}(Pattern.compile("My.*")).{@link #andShowing() andShowing}();
   * </pre>
   * </p>
   * @param titlePattern the title to match.
   * @return the created matcher.
   * @since 1.2
   */
  public static FrameMatcher withTitle(Pattern titlePattern) {
    return new FrameMatcher(ANY, titlePattern);
  }

  /**
   * Creates a new <code>{@link FrameMatcher}</code> that matches any <code>{@link Frame}</code>.
   * @return the created matcher.
   */
  public static FrameMatcher any() {
    return new FrameMatcher(ANY, ANY);
  }

  private FrameMatcher(Object name, Object title) {
    super(Frame.class, name);
    this.title = title;
  }

  /**
   * Specifies the title to match. If this matcher was created using <code>{@link #withTitle(String)}</code> or
   * <code>{@link FrameMatcher#withTitle(Pattern)}</code>, this method will simply update the title to match.
   * @param newTitle the new title to match. It can be a regular expression.
   * @return this matcher.
   */
  public FrameMatcher andTitle(String newTitle) {
    title = newTitle;
    return this;
  }

  /**
   * Specifies the title to match. If this matcher was created using <code>{@link #withTitle(String)}</code> or
   * <code>{@link FrameMatcher#withTitle(Pattern)}</code>, this method will simply update the title to match.
   * @param titlePattern the regular expression pattern to match.
   * @return this matcher.
   * @since 1.2
   */
  public FrameMatcher andTitle(Pattern titlePattern) {
    title = titlePattern;
    return this;
  }

  /**
   * Indicates that the <code>{@link Frame}</code> to match should be showing on the screen.
   * @return this matcher.
   */
  public FrameMatcher andShowing() {
    requireShowing(true);
    return this;
  }

  /**
   * Indicates whether the title of the given <code>{@link Frame}</code> is equal to the title in this matcher.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for
   * invoking this method in the EDT.
   * </p>
   * @param frame the <code>Frame</code> to match.
   * @return <code>true</code> if the title in the <code>Frame</code> is equal to the title in this matcher,
   * <code>false</code> otherwise.
   */
  @RunsInCurrentThread
  protected boolean isMatching(Frame frame) {
    if (!isNameMatching(frame.getName())) return false;
    return arePropertyValuesMatching(title, frame.getTitle());
  }

  @Override public String toString() {
    return concat(
        getClass().getName(), "[",
        "name=", quotedName(), ", ",
        "title=", quoted(title), ", ",
        "requireShowing=", valueOf(requireShowing()),
        "]"
    );
  }
}
