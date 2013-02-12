/*
 * Created on Nov 17, 2009
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

import static org.fest.swing.test.builder.JFrames.frame;
import static org.fest.swing.test.task.WindowDestroyTask.hideAndDisposeInEDT;

import java.awt.Frame;

import org.fest.swing.driver.FrameDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Base test class for {@link FrameFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class FrameFixture_TestCase extends ComponentFixture_Implementations_TestCase<Frame> {
  private static Frame target;

  private FrameDriver driver;
  private FrameFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = frame().createNew();
  }

  @AfterClass
  public static void disposeTarget() {
    hideAndDisposeInEDT(target);
  }

  @Override
  final void onSetUp() {
    driver = createMock(FrameDriver.class);
    fixture = new FrameFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  final FrameDriver driver() {
    return driver;
  }

  @Override
  final Frame target() {
    return target;
  }

  @Override
  final FrameFixture fixture() {
    return fixture;
  }
}
