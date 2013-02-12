/*
 * Created on Nov 18, 2009
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

import static org.fest.swing.core.TestRobots.singletonRobotMock;

import javax.swing.JScrollBar;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link JScrollBarFixture#driver(org.fest.swing.driver.JScrollBarDriver)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JScrollBarFixture_driver_Test {
  private JScrollBarFixture fixture;
  private JScrollBar target;

  @Before
  public void setUp() {
    target = createMock(JScrollBar.class);
    fixture = new JScrollBarFixture(singletonRobotMock(), target);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_driver_is_null() {
    fixture.driver(null);
  }
}
