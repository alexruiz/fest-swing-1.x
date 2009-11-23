/*
 * Created on Jul 23, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.swing.junit;

import org.junit.Test;

import org.fest.swing.annotation.GUITest;

/**
 * Understands a JUnit test that fails to be used to test FEST's JUnit extensions.
 * 
 * @author Alex Ruiz
 */
@GUITest public class FirstFailingJUnitTest {

  @Test public void shouldFail() {
    throw new AssertionError("Failing on purpose");
  }
}
