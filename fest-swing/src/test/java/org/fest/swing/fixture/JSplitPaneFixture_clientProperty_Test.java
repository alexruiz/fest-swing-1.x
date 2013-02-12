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

import static org.fest.swing.test.builder.JSplitPanes.splitPane;

import javax.swing.JSplitPane;

import org.fest.swing.driver.JSplitPaneDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link JSplitPaneFixture} that are inherited from {@link ClientPropertyStorageFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JSplitPaneFixture_clientProperty_Test extends ClientPropertyStorageFixture_TestCase<JSplitPane> {
  private static JSplitPane target;

  private JSplitPaneDriver driver;
  private JSplitPaneFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = splitPane().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(JSplitPaneDriver.class);
    fixture = new JSplitPaneFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  JSplitPaneDriver driver() {
    return driver;
  }

  @Override
  JSplitPane target() {
    return target;
  }

  @Override
  JSplitPaneFixture fixture() {
    return fixture;
  }
}
