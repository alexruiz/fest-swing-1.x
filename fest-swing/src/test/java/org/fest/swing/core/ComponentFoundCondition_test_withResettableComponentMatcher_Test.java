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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.awt.Containers.singletonContainerMock;
import static org.fest.swing.test.core.Mocks.mockComponent;
import static org.fest.swing.test.core.Mocks.mockComponentFinder;
import java.awt.Container;
import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;

/**
 * Tests for <code>{@link ComponentFoundCondition#test()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ComponentFoundCondition_test_withResettableComponentMatcher_Test extends EDTSafeTestCase {

  private ComponentFinder finder;
  private ResettableComponentMatcher matcher;
  private static Container root;

  private ComponentFoundCondition condition;

  @BeforeClass
  public static void setUpOnce() {
    root = singletonContainerMock();
  }

  @Before
  public void setUp() {
    finder = mockComponentFinder();
    matcher = createMock(ResettableComponentMatcher.class);
    condition = new ComponentFoundCondition("",  finder, matcher, root);
  }

  @Test
  public void should_reset_matcher_when_match_not_found() {
    new EasyMockTemplate(finder, matcher) {
      protected void expectations() {
        expect(finder.find(root, matcher)).andThrow(new ComponentLookupException("Thrown on purpose"));
        matcher.reset(false);
      }

      protected void codeToTest() {
        condition.test();
      }
    }.run();
  }

  @Test
  public void should_reset_matcher_when_match_found() {
    new EasyMockTemplate(finder, matcher) {
      protected void expectations() {
        expect(finder.find(root, matcher)).andReturn(mockComponent());
        matcher.reset(true);
      }

      protected void codeToTest() {
        condition.test();
      }
    }.run();
  }
}
