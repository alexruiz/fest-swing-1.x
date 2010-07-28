/*
 * Created on Jun 14, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.TestComponentFinders.newComponentFinderMock;
import static org.fest.swing.core.TestRobots.newRobotMock;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.builder.JTextFields.textField;

import java.awt.*;

import javax.swing.JTextField;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentFixture#ComponentFixture(Robot, Class)}</code>,
 * <code>{@link ComponentFixture#ComponentFixture(Robot, Component)}</code> and
 * <code>{@link ComponentFixture#ComponentFixture(Robot, String, Class)}</code>.
 *
 * @author Alex Ruiz
 */
public class ComponentFixture_constructor_Test extends EDTSafeTestCase {

  private Robot robot;
  private Settings settings;
  private Class<JTextField> type;
  private String name;
  private JTextField target;

  @Before public void setUp() {
    robot = newRobotMock();
    settings = new Settings();
    type = JTextField.class;
    name = "textBox";
    target = textField().createNew();
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_Robot_is_null_when_looking_up_Component_by_type() {
    new ConcreteComponentFixture(null, type);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_class_is_null_when_looking_up_Component_by_type() {
    new ConcreteComponentFixture(robot, (Class<? extends JTextField>)null);
  }

  @Test
  public void should_lookup_Component_by_type() {
    final ComponentFinder finder = newComponentFinderMock();
    new EasyMockTemplate(robot, finder) {
      protected void expectations() {
        expect(robot.settings()).andReturn(settings);
        expect(robot.finder()).andReturn(finder);
        expect(finder.findByType(type, requireShowing())).andReturn(target);
      }

      protected void codeToTest() {
        assertHasCorrectTarget(new ConcreteComponentFixture(robot, type));
      }
    }.run();
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_Robot_is_null_when_looking_up_Component_by_name() {
    new ConcreteComponentFixture(null, name, type);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_class_is_null_when_looking_up_Component_by_name() {
    new ConcreteComponentFixture(robot, name, null);
  }

  @Test
  public void should_lookup_Component_by_name() {
    final ComponentFinder finder = newComponentFinderMock();
    new EasyMockTemplate(robot, finder) {
      protected void expectations() {
        expect(robot.settings()).andReturn(settings);
        expect(robot.finder()).andReturn(finder);
        expect(finder.findByName(name, type, requireShowing())).andReturn(target);
      }

      protected void codeToTest() {
        assertHasCorrectTarget(new ConcreteComponentFixture(robot, name, type));
      }
    }.run();
  }

  private boolean requireShowing() {
    return settings.componentLookupScope().requireShowing();
  }

  private void assertHasCorrectTarget(ConcreteComponentFixture fixture) {
    assertThat(fixture.target).isSameAs(target);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_IfRobotIsNullWhenPassingTarget() {
    new ConcreteComponentFixture(null, target);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_target_Component_is_null() {
    new ConcreteComponentFixture(robot, (JTextField)null);
  }

  @Test
  public void should_return_FontFixture() {
    FontFixture fontFixture = fixture().font();
    assertThat(fontFixture.target()).isSameAs(fontOf(target));
    assertThat(fontFixture.description()).contains("javax.swing.JTextField")
                                         .contains("property:'font'");
  }

  @RunsInEDT
  private static Font fontOf(final Component component) {
    return execute(new GuiQuery<Font>() {
      protected Font executeInEDT() {
        return component.getFont();
      }
    });
  }

  @Test
  public void should_return_ColorFixture_for_Component_background() {
    ColorFixture colorFixture = fixture().background();
    Component component = target;
    assertThat(colorFixture.target()).isSameAs(backgroundOf(component));
    assertThat(colorFixture.description()).contains("javax.swing.JTextField")
                                          .contains("property:'background'");
  }

  @RunsInEDT
  private static Color backgroundOf(final Component component) {
    return execute(new GuiQuery<Color>() {
      protected Color executeInEDT() {
        return component.getBackground();
      }
    });
  }

  @Test
  public void should_return_ColorFixture_for_Component_foreground() {
    ColorFixture colorFixture = fixture().foreground();
    assertThat(colorFixture.target()).isSameAs(foregroundOf(target));
    assertThat(colorFixture.description()).contains("javax.swing.JTextField")
                                          .contains("property:'foreground'");
  }

  @RunsInEDT
  private static Color foregroundOf(final Component component) {
    return execute(new GuiQuery<Color>() {
      protected Color executeInEDT() {
        return component.getForeground();
      }
    });
  }

  @Test
  public void should_cast_target_Component() {
    JTextField castedTarget = fixture().targetCastedTo(JTextField.class);
    assertThat(castedTarget).isSameAs(target);
  }

  private ComponentFixture<JTextField> fixture() {
    return new ConcreteComponentFixture(robot, target);
  }
}

