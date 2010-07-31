/*
 * Created on Apr 21, 2010
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
package org.fest.swing.core;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Collections.list;

import java.awt.Component;
import java.util.Collection;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.fest.swing.test.data.BooleanProvider;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link ComponentIsFocusableQuery#isFocusable(Component)}</code>.
 *
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class ComponentIsFocusableQuery_isFocusable_Test extends EDTSafeTestCase {

  private final boolean isFocusable;
  private Component component;

  @Parameters
  public static Collection<Object[]> isFocusable() {
    return list(BooleanProvider.booleans());
  }

  public ComponentIsFocusableQuery_isFocusable_Test(boolean isFocusable) {
    this.isFocusable = isFocusable;
  }

  @Before
  public void setUp() {
    component = createMock(Component.class);
  }

  @Test
  public void should_return_Component_is_focusable() {
    new EasyMockTemplate(component) {
      @Override protected void expectations() {
        expect(component.isFocusable()).andReturn(isFocusable);
      }

      @Override protected void codeToTest() {
        assertThat(ComponentIsFocusableQuery.isFocusable(component)).isEqualTo(isFocusable);
      }
    }.run();
  }
}
