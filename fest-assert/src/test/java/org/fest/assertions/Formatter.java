/*
 * Created on Jun 9, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.String.valueOf;
import static org.fest.util.Strings.quote;

import java.util.*;

import org.fest.util.*;
import org.fest.util.Arrays;
import org.fest.util.Collections;

/**
 * Understands how to create the {@code String} representation of an object.
 *
 * @author Alex Ruiz
 */
final class Formatter {

  static String format(Object o) {
    if (o == null) return "null";
    if (o.getClass().isArray()) return Arrays.format(o);
    if (o instanceof Collection<?>) return Collections.format((Collection<?>)o);
    if (o instanceof Map<?, ?>) return Maps.format((Map<?, ?>)o);
    if (o instanceof String) return quote((String)o);
    return valueOf(o);
  }

  private Formatter() {}
}
