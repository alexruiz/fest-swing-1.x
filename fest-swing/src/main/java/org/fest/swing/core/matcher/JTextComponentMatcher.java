/*
 * Created on Jul 17, 2008
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

import java.util.regex.Pattern;

import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Matches <code>{@link JTextComponent}</code>s by type, name or text.
 *
 * @author Alex Ruiz
 */
public final class JTextComponentMatcher extends NamedComponentMatcherTemplate<JTextComponent> {

  private Object text;

  /**
   * Creates a new <code>{@link JTextComponentMatcher}</code> that matches a <code>{@link JTextComponent}</code> that:
   * <ol>
   * <li>has a matching name</li>
   * <li>(optionally) has matching text</li>
   * <li>(optionally) is showing on the screen</li>
   * <p>
   * The following code listing shows how to match a <code>{@link JTextComponent}</code> by name and text:
   * <pre>
   * JTextComponentMatcher m = {@link #withName(String) withName}("lastName").{@link #andText(String) andText}("Wang");
   * </pre>
   * </p>
   * <p>
   * The following code listing shows how to match a <code>{@link JTextComponent}</code>, that should be showing on the screen,
   * by name and text:
   * <pre>
   * JTextComponentMatcher m = {@link #withName(String) withName}("lastName").{@link #andText(String) andText}("Wang").{@link #andShowing() andShowing}();
   * </pre>
   * </p>
   * @param name the id to match.
   * @return the created matcher.
   */
  public static JTextComponentMatcher withName(String name) {
    return new JTextComponentMatcher(name, ANY);
  }

  /**
   * Creates a new <code>{@link JTextComponentMatcher}</code> that matches a <code>{@link JTextComponent}</code> by its
   * text.
   * <p>
   * The following code listing shows how to match a <code>{@link JTextComponent}</code> by text:
   * <pre>
   * JTextComponentMatcher m = {@link #withText(String) withText}("Wang");
   * </pre>
   * </p>
   * <p>
   * The following code listing shows how to match a <code>{@link JTextComponent}</code>, that should be showing on the
   * screen, by text:
   * <pre>
   * JTextComponentMatcher m = {@link #withText(String) withText}("Wang").{@link #andShowing() andShowing}();
   * </pre>
   * </p>
   * @param text the text to match. It can be a regular expression.
   * @return the created matcher.
   */
  public static JTextComponentMatcher withText(String text) {
    return new JTextComponentMatcher(ANY, text);
  }

  /**
   * Creates a new <code>{@link JTextComponentMatcher}</code> that matches a <code>{@link JTextComponent}</code> by its
   * text.
   * <p>
   * The following code listing shows how to match a <code>{@link JTextComponent}</code> by text, using a regular
   * expression pattern:
   * <pre>
   * JTextComponentMatcher m = {@link #withText(Pattern) withText}(Pattern.compile("W.*"));
   * </pre>
   * </p>
   * <p>
   * The following code listing shows how to match a <code>{@link JTextComponent}</code>, that should be showing on the
   * screen, by text, using a regular expression pattern:
   * <pre>
   * JTextComponentMatcher m = {@link #withText(Pattern) withText}(Pattern.compile("W.*")).{@link #andShowing() andShowing}();
   * </pre>
   * </p>
   * @param textPattern the text to match. It can be a regular expression.
   * @return the created matcher.
   */
  public static JTextComponentMatcher withText(Pattern textPattern) {
    return new JTextComponentMatcher(ANY, textPattern);
  }

  /**
   * Creates a new <code>{@link JTextComponentMatcher}</code> that matches any <code>{@link JTextComponent}</code>.
   * @return the created matcher.
   */
  public static JTextComponentMatcher any() {
    return new JTextComponentMatcher(ANY, ANY);
  }

  private JTextComponentMatcher(Object name, Object text) {
    super(JTextComponent.class, name);
    this.text = text;
  }

  /**
   * Specifies the text to match. If this matcher was created using <code>{@link #withText(String)}</code> or
   * <code>{@link #withText(Pattern)}</code>, this method will simply update the text to match.
   * @param newText the new text to match. It can be a regular expression.
   * @return this matcher.
   */
  public JTextComponentMatcher andText(String newText) {
    text = newText;
    return this;
  }

  /**
   * Specifies the text to match. If this matcher was created using <code>{@link #withText(String)}</code> or
   * <code>{@link #withText(Pattern)}</code>, this method will simply update the text to match.
   * @param textPattern the regular expression pattern to match.
   * @return this matcher.
   * @since 1.2
   */
  public JTextComponentMatcher andText(Pattern textPattern) {
    text = textPattern;
    return this;
  }

  /**
   * Indicates that the <code>{@link JTextComponent}</code> to match should be showing on the screen.
   * @return this matcher.
   */
  public JTextComponentMatcher andShowing() {
    requireShowing(true);
    return this;
  }

  /**
   * Indicates whether the text of the given <code>{@link JTextComponent}</code> is equal to the text in this matcher.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param button the <code>JTextComponent</code> to match.
   * @return {@code true} if the text in the <code>JTextComponent</code> is equal to the text in this matcher,
   * {@code false} otherwise.
   */
  @RunsInCurrentThread
  @Override protected boolean isMatching(JTextComponent button) {
    if (!isNameMatching(button.getName())) return false;
    return arePropertyValuesMatching(text, button.getText());
  }

  @Override public String toString() {
    return concat(
        getClass().getName(), "[",
        "name=", quotedName(), ", ",
        "text=", quoted(text), ", ",
        "requireShowing=", valueOf(requireShowing()),
        "]"
    );
  }
}
