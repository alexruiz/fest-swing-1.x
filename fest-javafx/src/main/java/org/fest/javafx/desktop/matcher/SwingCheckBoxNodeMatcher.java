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

import javax.swing.JCheckBox;

import org.fest.javafx.desktop.core.ComponentNodeMatcher;
import org.fest.swing.annotation.RunsInCurrentThread;

import static org.fest.util.Strings.concat;

/**
 * Understands matching a <code>SwingCheckBox</code> by:
 * <ul>
 * <li>by id</li>
 * <li>by text</li>
 * <li>by "selected" state</li>
 * <li>any combination of the above</li>
 * </ul>
 * <p>
 * Matching a <code>SwingCheckBox</code> by id:
 * <pre>
 * NodeMatcher m = {@link #checkBoxWithId(String) checkBoxWithId}("login");
 * </pre> 
 * </p>
 * <p>
 * Matching a <code>SwingCheckBox</code> by id, text, and "selected" state:
 * <pre>
 * NodeMatcher m = {@link #checkBoxWithId(String) checkBoxWithId}("login").{@link #andText(String) andText}("Login").{@link #andSelected(boolean) andSelected}(true);
 * </pre> 
 * </p>
 * <p>
 * Matching a <code>SwingCheckBox</code> by text:
 * <pre>
 * NodeMatcher m = {@link #checkBoxWithText(String) checkBoxWithText}("Login");
 * </pre> 
 * </p>
 * <p>
 * Matching any <code>SwingCheckBox</code>:
 * <pre>
 * NodeMatcher m = {@link #any() any}();
 * </pre> 
 * </p>
 *
 * @author Alex Ruiz
 */
public final class SwingCheckBoxNodeMatcher extends ComponentNodeMatcher<JCheckBox> {

  private Object text;
  private Object selected;
  
  /**
   * Creates a new <code>{@link SwingCheckBoxNodeMatcher}</code> that matches a <code>SwingCheckBox</code> that:
   * <ol>
   * <li>has a matching id</li>
   * <li>(optionally) has a matching text</li>
   * <p>
   * The following code listing shows how to match a <code>SwingCheckBox</code> by id and text:
   * <pre>
   * NodeMatcher m = {@link #checkBoxWithId(String) checkBoxWithId}("ok").{@link #andText(String) andText}("OK");
   * </pre> 
   * </p>
   * @param id the id to match.
   * @return the created matcher.
   */
  public static SwingCheckBoxNodeMatcher checkBoxWithId(String id) {
    return new SwingCheckBoxNodeMatcher(id, ANY);
  }
  
  /**
   * Creates a new <code>{@link SwingCheckBoxNodeMatcher}</code> that matches a <code>SwingCheckBox</code> by text. 
   * @param text the text to match.
   * @return the created matcher.
   */
  public static SwingCheckBoxNodeMatcher checkBoxWithText(String text) {
    return new SwingCheckBoxNodeMatcher(ANY, text);
  }
  
  /**
   * Creates a new <code>{@link SwingCheckBoxNodeMatcher}</code> that matches any <code>SwingCheckBox</code>. 
   * @return the created matcher.
   */
  public static SwingCheckBoxNodeMatcher any() {
    return new SwingCheckBoxNodeMatcher(ANY, ANY);
  }
  
  private SwingCheckBoxNodeMatcher(Object id, Object text) {
    super(id, JCheckBox.class);
    this.text = text;
    selected = ANY;
  }
  
  /** 
   * Indicates the given <code>{@link JCheckBox}</code> matches the search criteria in this matcher.
   * @param button the <code>JCheckBox</code> to verify.
   * @return <code>true</code> if the given <code>JCheckBox</code> matches the search criteria in this matcher, 
   * <code>false</code> otherwise. This method returns <code>true</code> if this matcher does not have search criteria 
   * specified.
   */
  @RunsInCurrentThread
  protected boolean isMatching(JCheckBox button) {
    if (!arePropertyValuesMatching(text, button.getText())) return false;
    return arePropertyValuesMatching(selected, button.isSelected());
  }
  
  /**
   * Specifies the text to match. If this matcher was created using <code>{@link #checkBoxWithText(String)}</code>, this method
   * will simply update the text to match.
   * @param newText the new text to match.
   * @return this matcher.
   */
  public SwingCheckBoxNodeMatcher andText(String newText) {
    text = newText;
    return this;
  }

  /**
   * Specifies whether the <code>SwingCheckBox</code> should be selected or not.
   * @param isSelected indicates whether the <code>SwingCheckBox</code> should be selected or not.
   * @return this matcher.
   */
  public SwingCheckBoxNodeMatcher andSelected(boolean isSelected) {
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
