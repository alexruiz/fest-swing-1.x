/*
 * Created on Dec 27, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;

/**
 * Base test case for {@link JTreeRowFixture}.
 * 
 * @author Alex Ruiz
 */
public class JTreeRowFixture_withMockTree_TestCase extends EDTSafeTestCase {
  JTreeFixture tree;
  int row;
  JTreeRowFixture fixture;

  @Before
  public final void setUp() {
    tree = createMock(JTreeFixture.class);
    row = 6;
    fixture = new JTreeRowFixture(tree, row);
  }

  final void assertThatReturnsSelf(JTreeRowFixture result) {
    assertThat(result).isSameAs(fixture);
  }
}