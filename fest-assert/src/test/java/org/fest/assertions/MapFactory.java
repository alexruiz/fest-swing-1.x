/*
 * Created on Sep 29, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

import java.util.*;

import org.fest.assertions.MapAssert.Entry;

/**
 * Understands creation of <code>{@link Map}</code>s.
 *
 * @author Alex Ruiz
 */
final class MapFactory {

  static Map<Object, Object> map(Entry... entries) {
    Map<Object, Object> map = new LinkedHashMap<Object, Object>();
    for (Entry entry : entries)
      map.put(entry.key, entry.value);
    return map;
  }

  private MapFactory() {}
}
