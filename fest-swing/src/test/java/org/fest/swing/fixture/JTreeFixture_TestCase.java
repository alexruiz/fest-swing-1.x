/*
 * Created on May 21, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.test.builder.JTrees.tree;

import javax.swing.JTree;

import org.fest.swing.driver.JTreeDriver;
import org.junit.BeforeClass;

/**
 * Base test case for {@link JTreeFixture}.
 * 
 * @author Keith Coughtrey
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTreeFixture_TestCase extends ComponentFixture_Implementations_TestCase<JTree> {
  private static JTree target;

  private JTreeDriver driver;
  private JTreeFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = tree().createNew();
  }

  @Override
  final void onSetUp() {
    driver = createMock(JTreeDriver.class);
    fixture = new JTreeFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  final JTreeDriver driver() {
    return driver;
  }

  @Override
  final JTree target() {
    return target;
  }

  @Override
  final JTreeFixture fixture() {
    return fixture;
  }
}