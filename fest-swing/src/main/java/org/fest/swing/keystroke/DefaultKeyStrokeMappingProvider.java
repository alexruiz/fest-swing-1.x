/*
 * Created on Mar 26, 2008
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
package org.fest.swing.keystroke;

import static java.awt.event.KeyEvent.*;
import static java.util.Collections.unmodifiableList;
import static org.fest.swing.keystroke.KeyStrokeMapping.mapping;
import static org.fest.swing.util.Platform.isWindows;

import java.util.*;

import javax.swing.KeyStroke;

/**
 * Understands a default mapping of characters and <code>{@link KeyStroke}</code>s.
 *
 * @author Alex Ruiz
 */
public class DefaultKeyStrokeMappingProvider implements KeyStrokeMappingProvider {

  private final List<KeyStrokeMapping> mappings = new ArrayList<KeyStrokeMapping>();

  DefaultKeyStrokeMappingProvider(List<KeyStrokeMapping> mappings) {
    this();
    mappings.addAll(mappings);
  }

  /**
   * Creates a new </code>{@link DefaultKeyStrokeMappingProvider}</code>.
   */
  public DefaultKeyStrokeMappingProvider() {
    populateDefaultMappings();
  }

  private void populateDefaultMappings() {
    mappings.add(mapping('\b', VK_BACK_SPACE, NO_MASK));
    mappings.add(mapping('', VK_DELETE, NO_MASK));
    mappings.add(mapping('\n', VK_ENTER, NO_MASK));
    if (isWindows()) mappings.add(mapping('\r', VK_ENTER, NO_MASK));
    mappings.add(mapping('', VK_ESCAPE, NO_MASK));
    mappings.add(mapping('\t', VK_TAB, NO_MASK));
  }

  /**
   * Returns the default mapping of characters and <code>{@link KeyStroke}</code>s. This provider will only return
   * the mappings for following keys:
   * <ul>
   * <li>Backspace</li>
   * <li>Delete</li>
   * <li>Enter</li>
   * <li>Escape</li>
   * <li>Tab</li>
   * </ul>
   * @return the default mapping of characters and <code>KeyStroke</code>s
   */
  public Collection<KeyStrokeMapping> keyStrokeMappings() {
    return unmodifiableList(mappings);
  }
}
