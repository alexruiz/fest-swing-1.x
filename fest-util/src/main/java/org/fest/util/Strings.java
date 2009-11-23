/*
 * Created on Jun 2, 2006
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
 * Copyright @2006 the original author or authors.
 */
package org.fest.util;

/**
 * Understands utility methods related to <code>String</code>s.
 * 
 * @author Alex Ruiz
 */
public final class Strings {

  /**
   * Returns <code>true</code> if the given <code>String</code> is <code>null</code> or empty.
   * @param s the <code>String</code> to check.
   * @return <code>true</code> if the given <code>String</code> is <code>null</code> or empty, otherwise 
   *          <code>false</code>. 
   */
  public static boolean isEmpty(String s) {
    return s == null || s.length() == 0;
  }

  /**
   * Returns the given <code>String</code> surrounded by single quotes, or <code>null</code> if the given 
   * <code>String</code> is <code>null</code>.
   * @param s the given <code>String</code>.
   * @return the given <code>String</code> surrounded by single quotes, or <code>null</code> if the given 
   * <code>String</code> is <code>null</code>.
   */
  public static String quote(String s) {
    return s != null ? concat("'", s, "'") : null;
  }

  /**
   * Returns the given object surrounded by single quotes, only if the object is a <code>String</code>.
   * @param o the given object.
   * @return the given object surrounded by single quotes, only if the object is a <code>String</code>.
   * @see #quote(String)
   */
  public static Object quote(Object o) {
    return o instanceof String ? quote(o.toString()) : o;
  }

  /**
   * Concatenates the given objects into a single <code>String</code>. This method is more efficient than concatenating
   * using "+", since only one <code>{@link StringBuilder}</code> is created.
   * @param objects the objects to concatenate.
   * @return a <code>String</code> containing the given objects.
   */
  public static String concat(Object... objects) {
    if (Arrays.isEmpty(objects)) return null;
    StringBuilder b = new StringBuilder();
    for (Object o : objects) b.append(o);
    return b.toString();
  }
  
  /**
   * Joins the given <code>String</code>s using a given delimiter. The following example illustrates proper usage of 
   * this method:
   * <pre>
   * Strings.join("a", "b", "c").with("|")
   * </pre>
   * which will result in the <code>String</code> <code>"a|b|c"</code>.
   * @param strings the <code>String</code>s to join.
   * @return an intermediate object that takes a given delimiter and understands how to join the given 
   * <code>String</code>s.
   * @see StringsToJoin#with(String)
   */
  public static StringsToJoin join(String...strings) {
    return new StringsToJoin(strings);
  }
  
  /**
   * Understands how to join <code>String</code>s using a given delimiter.
   * @see Strings#join(String[])
   */
  public static class StringsToJoin {
    
    /** The <code>String</code>s to join. */
    private final String[] strings;

    /**
     * Creates a new <code>{@link StringsToJoin}</code>.
     * @param strings the <code>String</code>s to join.
     */
    StringsToJoin(String...strings) {
      this.strings = strings;
    }
    
    /**
     * Specifies the delimeter to use to join <code>String</code>s.
     * @param delimeter the delimeter to use.
     * @return the <code>String</code>s joined using the given delimeter.
     */
    public String with(String delimeter) {
      if (delimeter == null) throw new IllegalArgumentException("Delimiter should not be null");
      if (Arrays.isEmpty(strings)) return "";
      StringBuilder b = new StringBuilder();
      int stringCount = strings.length;
      for (int i = 0; i < stringCount; i++) {
        String s = strings[i];
        b.append(s != null ? s : "");
        if (i < stringCount - 1) b.append(delimeter);
      }
      return b.toString();
    }
  }

  /**
   * Appends a given <code>String</code> to the given target, only if the target does not end with the given 
   * <code>String</code> to append. The following example illustrates proper usage of 
   * this method:
   * <pre>
   * Strings.append("c").to("ab");
   * Strings.append("c").to("abc");
   * </pre>
   * which will result in the <code>String</code> <code>"abc"</code> for both cases.
   * @param toAppend the <code>String</code> to append.
   * @return an intermediate object that takes the target <code>String</code> and knows to append the given 
   * <code>String</code>.
   * @see StringToAppend#to(String)
   */
  public static StringToAppend append(String toAppend) {
    return new StringToAppend(toAppend);
  }
  
  /**
   * Understands how to append a given <code>String</code> to the given target, only if the target does not end with the 
   * given <code>String</code> to append.
   */
  public static class StringToAppend {
    
    /** The <code>String</code> to append. */
    private final String toAppend;

    /**
     * Creates a new <code>{@link StringToAppend}</code>.
     * @param toAppend the <code>String</code> to append.
     */
    StringToAppend(String toAppend) {
      this.toAppend = toAppend;
    }
    
    /**
     * Appends the <code>String</code> specified in the constructor to the <code>String</code> passed as argument.
     * @param s the target <code>String</code>.
     * @return a <code>String</code> containing the target <code>String</code> with the given <code>String</code>
     * to append added to the end.
     */
    public String to(String s) {
      if (!s.endsWith(toAppend)) return concat(s, toAppend);
      return s;
    }
  }
  
  private Strings() {}
}
