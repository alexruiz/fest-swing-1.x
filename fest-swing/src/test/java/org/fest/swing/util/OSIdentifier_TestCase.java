/*
 * Created on Jul 30, 2009
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
package org.fest.swing.util;

import org.junit.Before;

/**
 * Base test case for {@link OSIdentifier}.
 * 
 * @author Alex Ruiz
 */
public abstract class OSIdentifier_TestCase {
  SystemPropertyReader propertyReader;

  @Before
  public void setUp() {
    propertyReader = createMock(SystemPropertyReader.class);
  }

  final void expectOSName(String osName) {
    expect(propertyReader.systemProperty("os.name")).andReturn(osName);
  }

  final void expectSomeMRJVersion() {
    expectMRJVersion("6");
  }

  final void expectNoMRJVersion() {
    expectMRJVersion(null);
  }

  final void expectMRJVersion(String mrjVersion) {
    expect(propertyReader.systemProperty("mrj.version")).andReturn(mrjVersion);
  }
}
