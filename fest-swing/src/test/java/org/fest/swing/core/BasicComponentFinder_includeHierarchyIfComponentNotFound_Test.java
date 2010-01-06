/*
 * Created on Jul 24, 2009
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
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JLabel;

import org.fest.swing.exception.ComponentLookupException;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicComponentFinder#includeHierarchyIfComponentNotFound(boolean)}</code>
 * and <code>{@link BasicComponentFinder#includeHierarchyIfComponentNotFound()}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Price
 */
public class BasicComponentFinder_includeHierarchyIfComponentNotFound_Test extends BasicComponentFinder_TestCase {

  @Test
  public void should_throw_error_without_ComponentHierarchy_as_configured() {
    finder.includeHierarchyIfComponentNotFound(false);
    assertThat(finder.includeHierarchyIfComponentNotFound()).isFalse();
    try {
      finder.findByName(window, "button", JLabel.class);
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      System.out.println(e.getMessage());
      assertThat(e.getMessage().contains("Component hierarchy:")).isFalse();
    }
  }

}
