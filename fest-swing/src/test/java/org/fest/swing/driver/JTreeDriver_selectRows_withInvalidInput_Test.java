/*
 * Created on Aug 9, 2009
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
package org.fest.swing.driver;

import static org.fest.swing.test.builder.JTrees.tree;
import static org.fest.swing.test.core.Mocks.mockRobot;

import javax.swing.JTree;

import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link JTreeDriver#selectRows(javax.swing.JTree, int[])}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeDriver_selectRows_withInvalidInput_Test extends EDTSafeTestCase {

  private JTree tree;
  private JTreeDriver driver;

  @Before
  public void onSetUp() {
    tree = tree().createNew();
    driver = new JTreeDriver(mockRobot());
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_array_is_null() {
    int[] rows = null;
    driver.selectRows(tree, rows);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_array_is_empty() {
    int[] rows = new int[0];
    driver.selectRows(tree, rows);
  }

}
