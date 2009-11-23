/*
 * Created on Feb 20, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.format;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.scenario.scenegraph.SGImage;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.test.builder.SwingButtons.button;

/**
 * Tests for <code>{@link Formatting}</code>.
 *
 * @author Yvonne Wang
 */
@Test public class FormattingTest {

  @BeforeClass public void setUp() {
    FailOnThreadViolationRepaintManager.install();
  }

  public void shouldReturnNullNodeMessageIfNodeIsNull() {
    String formatted = Formatting.format(null);
    assertThat(formatted).isEqualTo("Null Node");
  }

  public void shouldReturnNodeToStringIfNoFormatterFound() {
    FXNode node = new FXNode(new SGImage());
    String formatted = Formatting.format(node);
    assertThat(formatted).isEqualTo(node.toString());
  }

  public void shouldFormatSwingButton() {
    String formatted = Formatting.format(button().withId("myId").withText("Hello").enabled(false).createNew());
    assertThat(formatted).contains("JavaFX SwingButton")
                         .contains("id='myId', button=[javax.swing.JButton[text='Hello', enabled=false]]]");
  }

}
