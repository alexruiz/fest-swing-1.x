/*
 * Created on Jul 31, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.query.ContainerInsetsQuery.insetsOf;

import java.awt.Insets;

import org.junit.Test;

/**
 * Tests for <code>{@link WindowMetrics#topAndBottomInsets()}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowMetrics_topAndBottomInsets_Test extends WindowMetrics_TestCase {

  @Test
  public void should_add_horizontal_insets() {
    Insets insets = insetsOf(window);
    assertThat(metrics.topAndBottomInsets()).isEqualTo(insets.top + insets.bottom);
  }

}
