/*
 * Created on Aug 27, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.util;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.util.OSIdentifierStub.windowsXP;

import java.awt.Toolkit;

import org.fest.swing.test.core.SequentialTestCase;

/**
 * Base test case for <code>{@link Platform}</code>
 *
 * @author Alex Ruiz
 */
public abstract class Platform_TestCase extends SequentialTestCase {

  ToolkitProviderStub toolkitProvider;

  @Override protected final void onSetUp() {
    toolkitProvider = new ToolkitProviderStub();
  }

  @Override protected final void onTearDown() {
    Platform.reload();
  }

  protected final Toolkit wireMockToolkit() {
    Platform.initialize(windowsXP(), toolkitProvider);
    return toolkitProvider.toolkit(createMock(Toolkit.class));
  }

  public static class ToolkitProviderStub extends ToolkitProvider {
    private Toolkit toolkit;

    @Override Toolkit toolkit() {
      return toolkit;
    }

    Toolkit toolkit(Toolkit newToolkit) {
      toolkit = newToolkit;
      return toolkit;
    }
  }
}
