/*
 * Created on Feb 20, 2009
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

import javax.swing.JRadioButton;

import org.fest.javafx.desktop.core.ComponentNodeMatcher;
import org.fest.swing.annotation.RunsInCurrentThread;

import static org.fest.util.Strings.concat;

/**
 * Understands matching a <code>SwingRadioButton</code> by:
 * <ul>
 * <li>by id</li>
 * <li>by text</li>
 * <li>by "selected" state</li>
 * <li>any combination of the above</li>
 * </ul>
 * <p>
 * Matching a <code>SwingRadioButton</code> by id:
 * <pre>
 * NodeMatcher m = {@link #radioButtonWithId(String) radioButtonWithId}("login");
 * </pre> 
 * </p>
 * <p>
 * Matching a <code>SwingRadioButton</code> by id, text, and "selected" state:
 * <pre>
 * NodeMatcher m = {@link #radioButtonWithId(String) radioButtonWithId}("login").{@link #andText(String) andText}("Login").{@link #andSelected(boolean) andSelected}(true);
 * </pre> 
 * </p>
 * <p>
 * Matching a <code>SwingRadioButton</code> by text:
 * <pre>
 * NodeMatcher m = {@link #radioButtonWithText(String) radioButtonWithText}("Login");
 * </pre> 
 * </p>
 * <p>
 * Matching any <code>SwingRadioButton</code>:
 * <pre>
 * NodeMatcher m = {@link #any() any}();
 * </pre> 
 * </p>
 *
 * @author Alex Ruiz
 */
public final class SwingRadioButtonNodeMatcher extends ComponentNodeMatcher<JRadioButton> {

  private Object text;
  private Object selected;
  
  /**
   * Creates a new <code>{@link SwingRadioButtonNodeMatcher}</code> that matches a <code>SwingRadioButton</code> that:
   * <ol>
   * <li>has a matching id</li>
   * <li>(optionally) has a matching text</li>
   * <p>
   * The following code listing shows how to match a <code>SwingRadioButton</code> by id and text:
   * <pre>
   * NodeMatcher m = {@link #radioButtonWithId(String) radioButtonWithId}("ok").{@link #andText(String) andText}("OK");
   * </pre> 
   * </p>
   * @param id the id to match.
   * @return the created matcher.
   */
  public static SwingRadioButtonNodeMatcher radioButtonWithId(String id) {
    return new SwingRadioButtonNodeMatcher(id, ANY);
  }
  
  /**
   * Creates a new <code>{@link SwingRadioButtonNodeMatcher}</code> that matches a <code>SwingRadioButton</code> by text. 
   * @param text the text to match.
   * @return the created matcher.
   */
  public static SwingRadioButtonNodeMatcher radioButtonWithText(String text) {
    return new SwingRadioButtonNodeMatcher(ANY, text);
  }
  
  /**
   * Creates a new <code>{@link SwingRadioButtonNodeMatcher}</code> that matches any <code>SwingRadioButton</code>. 
   * @return the created matcher.
   */
  public static SwingRadioButtonNodeMatcher any() {
    return new SwingRadioButtonNodeMatcher(ANY, ANY);
  }
  
  private SwingRadioButtonNodeMatcher(Object id, Object text) {
    super(id, JRadioButton.class);
    this.text = text;
    selected = ANY;
  }
  
  /** 
   * Indicates the given <code>{@link JRadioButton}</code> matches the search criteria in this matcher.
   * @param button the <code>JRadioButton</code> to verify.
   * @return <code>true</code> if the given <code>JRadioButton</code> matches the search criteria in this matcher, 
   * <code>false</code> otherwise. This method returns <code>true</code> if this matcher does not have search criteria 
   * specified.
   */
  @RunsInCurrentThread
  protected boolean isMatching(JRadioButton button) {
    if (!arePropertyValuesMatching(text, button.getText())) return false;
    return arePropertyValuesMatching(selected, button.isSelected());
  }
  
  /**
   * Specifies the text to match. If this matcher was created using <code>{@link #radioButtonWithText(String)}</code>, this method
   * will simply update the text to match.
   * @param newText the new text to match.
   * @return this matcher.
   */
  public SwingRadioButtonNodeMatcher andText(String newText) {
    text = newText;
    return this;
  }

  /**
   * Specifies whether the <code>SwingRadioButton</code> should be selected or not.
   * @param isSelected indicates whether the <code>SwingRadioButton</code> should be selected or not.
   * @return this matcher.
   */
  public SwingRadioButtonNodeMatcher andSelected(boolean isSelected) {
    selected = isSelected;
    return this;
  }

  @Override public String toString() {
    return concat(
        getClass().getName(), 
        "[", "id=", quotedId(), ",", 
        "text=", quoted(text), ",",
        "selected=", selected, 
        "]");
  }
}
