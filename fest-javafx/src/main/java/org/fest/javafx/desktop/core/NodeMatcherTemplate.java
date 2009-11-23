/*
 * Created on Jan 13, 2009
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
package org.fest.javafx.desktop.core;

import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.quote;

/**
 * Understands a template for matching nodes by id. Subclasses are free to add other properties to use as search 
 * criteria.
 *
 * @author Alex Ruiz
 */
public abstract class NodeMatcherTemplate implements NodeMatcher {

  /**
   * Indicates that a property value to use as search criteria has not been set.
   */
  protected static final Object ANY = new Object() {
    @Override public String toString() { return "<Any>"; }
  };

  /** The node id to match. **/
  protected final Object id;

  /**
   * Creates a new </code>{@link NodeMatcherTemplate}</code>.
   * @param id the node id to match.
   */
  protected NodeMatcherTemplate(Object id) {
    this.id = id;
  }

  /**
   * Returns the node id to match surrounded by double quotes. If the node id has not been set, it will
   * return <code>{@link #ANY}</code>. This method is commonly used in implementations of <code>toString</code>.
   * @return the node id to match surrounded by double quotes, or <code>{@link #ANY}</code> if the node id
   * has not been set.
   */
  protected final Object quotedId() {
    return quoted(id);
  }
  
  /**
   * Returns the given property value to match surrounded by double quotes. If the property has not been set, it will
   * return <code>{@link #ANY}</code>. This method is commonly used in implementations of <code>toString</code>.
   * @param propertyValue the given property value.
   * @return the given property value to match surrounded by double quotes, or <code>{@link #ANY}</code> if the property
   * value has not been set.
   */
  protected final Object quoted(Object propertyValue) {
    if (ANY.equals(propertyValue)) return ANY;
    return quote(propertyValue);
  }

  /**
   * Indicates whether the given value matches the id in this matcher. It always returns <code>true</code> if this
   * matcher's id is <code>{@link #ANY}</code>.
   * @param actual the actual node id.
   * @return <code>true</code> if this matcher's id is <code>ANY</code> or if both the actual id is equal to the one
   * in this matcher. Otherwise <code>false</code>.
   */
  protected final boolean isIdMatching(String actual) {
    return arePropertyValuesMatching(id, actual);
  }
  
  /**
   * Indicates whether the given value matches the expected value in this matcher. It always returns <code>true</code> 
   * if this matcher's expected value is <code>{@link #ANY}</code>.
   * @param expected the expected value in this matcher.
   * @param actual the actual property value.
   * @return <code>true</code> if this matcher's expected value is <code>ANY</code> or if both the actual value is equal 
   * to the one in this matcher. Otherwise <code>false</code>.
   */
  protected final boolean arePropertyValuesMatching(Object expected, Object actual) {
    if (ANY.equals(expected)) return true;
    return areEqual(expected, actual);
  }
}
