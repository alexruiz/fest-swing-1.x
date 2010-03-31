/*
 * Created on Feb 4, 2010
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

import static java.util.Collections.emptyList;
import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.core.Mocks.*;
import static org.fest.util.Collections.list;

import java.awt.Component;
import java.awt.Container;
import java.util.List;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentFoundCondition#test()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ComponentFoundCondition_test_withResettableComponentMatcher_Test extends EDTSafeTestCase {

  private ComponentFinder finder;
  private ResettableComponentMatcher matcher;
  private Container root;

  private ComponentFoundCondition condition;

  @Before
  public void setUp() {
    finder = mockComponentFinder();
    matcher = createMock(ResettableComponentMatcher.class);
    root = mockContainer();
    condition = new ComponentFoundCondition("",  finder, matcher, root);
  }

  @Test
  public void should_reset_matcher_when_match_not_found() {
    final List<Component> found = emptyList();
    new EasyMockTemplate(finder, matcher) {
      protected void expectations() {
        expect(finder.findAll(root, matcher)).andReturn(found);
        matcher.reset(false);
      }

      protected void codeToTest() {
        condition.test();
      }
    }.run();
  }

  @Test
  public void should_reset_matcher_when_match_found() {
    final List<Component> found = list(mockComponent());
    new EasyMockTemplate(finder, matcher) {
      protected void expectations() {
        expect(finder.findAll(root, matcher)).andReturn(found);
        matcher.reset(true);
      }

      protected void codeToTest() {
        condition.test();
      }
    }.run();
  }
}
