/*
 * Created on Jan 25, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Strings.quote;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Understands utility methods related to maps.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Maps {

  /**
   * Returns <code>true</code> if the given map is <code>null</code> or empty.
   * @param map the map to check.
   * @return <code>true</code> if the given map is <code>null</code> or empty, otherwise <code>false</code>.
   */
  public static boolean isEmpty(Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  /**
   * Returns the <code>String</code> representation of the given map, or <code>null</code> if the given map is
   * <code>null</code>.
   * @param map the map to format.
   * @return the <code>String</code> representation of the given map.
   */
  public static String format(Map<?, ?> map) {
    if (map == null) return null;
    Iterator<?> i = map.entrySet().iterator();
    if (!i.hasNext()) return "{}";
    StringBuilder b = new StringBuilder();
    b.append("{");
    for (;;) {
      Entry<?, ?> e = (Entry<?, ?>)i.next();
      b.append(format(map, e.getKey()));
      b.append('=');
      b.append(format(map, e.getValue()));
      if (!i.hasNext()) return b.append("}").toString();
      b.append(", ");
    }
  }

  private static Object format(Map<?, ?> map, Object o) {
    return o == map ? "(this Map)" : quote(o);
  }

  private Maps() {}
}
