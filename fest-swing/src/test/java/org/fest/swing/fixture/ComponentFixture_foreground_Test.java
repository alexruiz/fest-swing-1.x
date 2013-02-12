/*
 * Created on Jul 27, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Color;
import java.awt.Component;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.junit.Test;

/**
 * Tests for {@link AbstractComponentFixture#foreground()}.
 * 
 * @author Alex Ruiz
 */
public class ComponentFixture_foreground_Test extends ComponentFixture_TestCase {
  @Test
  public void should_return_ColorFixture_with_foreground_from_target_Component() {
    ColorFixture colorFixture = fixture.foreground();
    Component component = target;
    assertThat(colorFixture.target()).isSameAs(foregroundOf(component));
    assertThat(colorFixture.description()).contains("javax.swing.JTextField").contains("property:'foreground'");
  }

  @RunsInEDT
  private static Color foregroundOf(final Component component) {
    return execute(new GuiQuery<Color>() {
      @Override
      protected Color executeInEDT() {
        return component.getForeground();
      }
    });
  }
}
