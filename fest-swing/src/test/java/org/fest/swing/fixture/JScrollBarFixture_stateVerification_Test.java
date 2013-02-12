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

import static org.fest.swing.test.builder.JScrollBars.scrollBar;

import javax.swing.JScrollBar;

import org.fest.swing.driver.JScrollBarDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link JScrollBarFixture} that are inherited from {@link StateVerificationFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JScrollBarFixture_stateVerification_Test extends StateVerificationFixture_TestCase<JScrollBar> {
  private static JScrollBar target;

  private JScrollBarDriver driver;
  private JScrollBarFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = scrollBar().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(JScrollBarDriver.class);
    fixture = new JScrollBarFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  JScrollBarDriver driver() {
    return driver;
  }

  @Override
  JScrollBar target() {
    return target;
  }

  @Override
  JScrollBarFixture fixture() {
    return fixture;
  }
}
