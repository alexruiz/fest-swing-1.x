/*
 * Created on Jan 11, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; either version 2 of 
 * the License. You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 * the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.matcher;

import javax.swing.JButton;

import org.fest.javafx.desktop.core.ComponentNodeMatcher;
import org.fest.swing.annotation.RunsInCurrentThread;

import static org.fest.util.Strings.concat;

/**
 * Understands matching a <code>SwingButton</code> by:
 * <ul>
 * <li>by id</li>
 * <li>by text</li>
 * <li>any combination of the above</li>
 * </ul>
 * <p>
 * Matching a <code>SwingButton</code> by id:
 * <pre>
 * NodeMatcher m = {@link #buttonWithId(String) buttonWithId}("login");
 * </pre> 
 * </p>
 * <p>
 * Matching a <code>SwingButton</code> by id and text:
 * <pre>
 * NodeMatcher m = {@link #buttonWithId(String) buttonWithId}("login").{@link #andText(String) andText}("Login");
 * </pre> 
 * </p>
 * <p>
 * Matching a <code>SwingButton</code> by text:
 * <pre>
 * NodeMatcher m = {@link #buttonWithText(String) buttonWithText}("Login");
 * </pre> 
 * </p>
 * <p>
 * Matching any <code>SwingButton</code>:
 * <pre>
 * NodeMatcher m = {@link #any() any}();
 * </pre> 
 * </p>
 *
 * @author Alex Ruiz
 */
public final class SwingButtonNodeMatcher extends ComponentNodeMatcher<JButton> {

  private Object text;
  
  /**
   * Creates a new <code>{@link SwingButtonNodeMatcher}</code> that matches a <code>SwingButton</code> that:
   * <ol>
   * <li>has a matching id</li>
   * <li>(optionally) has a matching text</li>
   * <p>
   * The following code listing shows how to match a <code>SwingButton</code> by id and text:
   * <pre>
   * NodeMatcher m = {@link #buttonWithId(String) buttonWithId}("ok").{@link #andText(String) andText}("OK");
   * </pre> 
   * </p>
   * @param id the id to match.
   * @return the created matcher.
   */
  public static SwingButtonNodeMatcher buttonWithId(String id) {
    return new SwingButtonNodeMatcher(id, ANY);
  }
  
  /**
   * Creates a new <code>{@link SwingButtonNodeMatcher}</code> that matches a <code>SwingButton</code> by text. 
   * @param text the text to match.
   * @return the created matcher.
   */
  public static SwingButtonNodeMatcher buttonWithText(String text) {
    return new SwingButtonNodeMatcher(ANY, text);
  }
  
  /**
   * Creates a new <code>{@link SwingButtonNodeMatcher}</code> that matches any <code>SwingButton</code>. 
   * @return the created matcher.
   */
  public static SwingButtonNodeMatcher any() {
    return new SwingButtonNodeMatcher(ANY, ANY);
  }
  
  private SwingButtonNodeMatcher(Object id, Object text) {
    super(id, JButton.class);
    this.text = text;
  }
  
  /** 
   * Indicates the given <code>{@link JButton}</code> matches the search criteria in this matcher.
   * @param button the <code>JButton</code> to verify.
   * @return <code>true</code> if the given <code>JButton</code> matches the search criteria in this matcher, 
   * <code>false</code> otherwise. This method returns <code>true</code> if this matcher does not have search criteria 
   * specified.
   */
  @RunsInCurrentThread
  protected boolean isMatching(JButton button) {
    return arePropertyValuesMatching(text, button.getText());
  }
  
  /**
   * Specifies the text to match. If this matcher was created using <code>{@link #buttonWithText(String)}</code>, this method
   * will simply update the text to match.
   * @param newText the new text to match.
   * @return this matcher.
   */
  public SwingButtonNodeMatcher andText(String newText) {
    text = newText;
    return this;
  }

  @Override public String toString() {
    return concat(getClass().getName(), "[", "id=", quotedId(), ",text=", quoted(text), "]");
  }
}
