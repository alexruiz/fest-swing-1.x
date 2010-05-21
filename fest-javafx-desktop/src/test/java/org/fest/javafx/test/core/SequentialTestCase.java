/*
 * Created on May 21, 2010
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
package org.fest.javafx.test.core;

import org.junit.*;

import org.fest.ui.testing.lock.ScreenLock;

/**
 * Base class for test classes that need to be serialized when executed.
 *
 * @author Alex Ruiz
 */
public abstract class SequentialTestCase {
  
  @Before
  public final void setUp() throws Throwable {
    ScreenLock.instance().acquire(this);
    onSetUp();
  }
  
  protected void onSetUp() throws Throwable {}

  @After
  public final void tearDown() throws Throwable {
    try {
      onTearDown();
    } finally {
      ScreenLock.instance().release(this);
    }
  }

  protected void onTearDown() {}
}
