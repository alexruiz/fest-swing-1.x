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
import static org.fest.swing.core.ComponentFinders.newComponentFinderMock;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.builder.JTextFields.textField;
import static org.fest.swing.test.core.Mocks.mockRobot;

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
 * Tests for <code>{@link ComponentFixture}</code>.
 *
 * @author Alex Ruiz
 */
public class ComponentFixtureTest extends EDTSafeTestCase {

  // TODO Reorganize into smaller units

  private Robot robot;
  private Settings settings;
  private Class<JTextField> type;
  private String name;
  private JTextField target;

  @Before public void setUp() {
    robot = mockRobot();
    settings = new Settings();
    type = JTextField.class;
    name = "textBox";
    target = textField().createNew();
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfRobotIsNullWhenLookingUpComponentByType() {
    new ConcreteComponentFixture(null, type);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfTypeIsNullWhenLookingUpComponentByType() {
    new ConcreteComponentFixture(robot, (Class<? extends JTextField>)null);
  }

  @Test
  public void shouldLookupComponentByType() {
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
  public void shouldThrowErrorIfRobotIsNullWhenLookingUpComponentByName() {
    new ConcreteComponentFixture(null, name, type);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfTypeIsNullWhenLookingUpComponentByName() {
    new ConcreteComponentFixture(robot, name, null);
  }

  @Test
  public void shouldLookupComponentByName() {
    final ComponentFinder finder = ComponentFinders.newComponentFinderMock();
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
  public void shouldThrowErrorIfRobotIsNullWhenPassingTarget() {
    new ConcreteComponentFixture(null, target);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfTargetIsNullWhenPassingTarget() {
    new ConcreteComponentFixture(robot, (JTextField)null);
  }

  @Test
  public void shouldReturnFontFixtureWithFontFromTarget() {
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
  public void shouldReturnColorFixtureWithBackgroundFromTarget() {
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
  public void shouldReturnColorFixtureWithForegroundFromTarget() {
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
  public void shouldCastTarget() {
    JTextField castedTarget = fixture().targetCastedTo(JTextField.class);
    assertThat(castedTarget).isSameAs(target);
  }

  private ComponentFixture<JTextField> fixture() {
    return new ConcreteComponentFixture(robot, target);
  }

  private static class ConcreteComponentFixture extends ComponentFixture<JTextField> {
    public ConcreteComponentFixture(Robot robot, Class<? extends JTextField> type) {
      super(robot, type);
    }

    public ConcreteComponentFixture(Robot robot, JTextField target) {
      super(robot, target);
    }

    public ConcreteComponentFixture(Robot robot, String name, Class<? extends JTextField> type) {
      super(robot, name, type);
    }
  }
}

