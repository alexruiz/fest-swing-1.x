/*
 * Created on Dec 19, 2009
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

import static org.fest.swing.test.builder.JProgressBars.progressBar;

import javax.swing.JProgressBar;

import org.fest.swing.driver.JProgressBarDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link JProgressBarFixture} that are inherited from {@link StateVerificationFixture}.
 * 
 * @author Alex Ruiz
 */
public class JProgressBarFixture_stateVerification_Test extends StateVerificationFixture_TestCase<JProgressBar> {
  private static JProgressBar target;

  private JProgressBarDriver driver;
  private JProgressBarFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = progressBar().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(JProgressBarDriver.class);
    fixture = new JProgressBarFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  JProgressBarDriver driver() {
    return driver;
  }

  @Override
  JProgressBar target() {
    return target;
  }

  @Override
  JProgressBarFixture fixture() {
    return fixture;
  }
}
