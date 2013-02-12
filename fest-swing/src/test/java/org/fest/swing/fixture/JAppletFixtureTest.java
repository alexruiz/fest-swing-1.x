/*
 * Created on Nov. 25, 2009 Mel Llaguno http://www.aclaro.com ------------------------------------
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.data.TableCell.row;
import static org.fest.swing.test.builder.JApplets.applet;

import java.awt.Point;
import java.io.File;

import javax.swing.JApplet;

import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.MouseButton;
import org.fest.swing.data.TableCell;
import org.fest.swing.driver.ComponentDriver;
import org.fest.swing.driver.JAppletDriver;
import org.junit.Test;

public class JAppletFixtureTest extends ComponentFixture_Implementations_TestCase<JApplet> {
  private JAppletDriver driver;
  private JApplet target;
  private JAppletFixture fixture;

  @Override
  CommonComponentFixture fixture() {
    return fixture;
  }

  @Override
  ComponentDriver driver() {
    return driver;
  }

  @Override
  JApplet target() {
    return target;
  }

  @Override
  void onSetUp() {
    driver = createMock(JAppletDriver.class);
    target = applet().createNew();
    fixture = new JAppletFixture(robot(), target);
    fixture.driver(driver);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_driver_is_null() {
    fixture.driver(null);
  }

  /*
   * @Test public void should_create_fixture_with_given_component_name() { String name = "applet";
   * expectLookupByName(name, JApplet.class); verifyLookup(new JAppletFixture(robot, name)); }
   */

  @Test
  public void should_create_keyPressInfo() {
    int code = 0x41;
    int modifier = 0x0;
    KeyPressInfo info = fixture.createKeyPressInfo(code, modifier);

    assertThat(info.keyCode()).isEqualTo(code);
    assertThat(info.modifiers()[0]).isEqualTo(modifier);
  }

  @Test
  public void should_create_point() {
    int x = 10;
    int y = 20;
    Point point = new Point(x, y);

    assertThat(fixture.createPoint(x, y)).isEqualTo(point);
  }

  @Test
  public void should_create_mouseButton() {
    int buttonMask = 16;
    MouseButton button = MouseButton.lookup(buttonMask);

    assertThat(fixture.createMouseButton(buttonMask)).isEqualTo(button);
  }

  @Test
  public void should_create_file() {
    String name = "fileName";
    File file = new File(name);

    assertThat(fixture.createFile(name)).isEqualTo(file);
  }

  @Test
  public void should_create_table_cell() {
    int row = 1;
    int column = 2;
    TableCell cell = row(row).column(column);

    assertThat(fixture.createTableCell(row, column)).isEqualTo(cell);
  }

  @Test
  public void should_create_table_cells() {
    Object[] objects = new Object[] { "0,0", "1,1", "2,2" };
    TableCell[] array = new TableCell[] { row(0).column(0), row(1).column(1), row(2).column(2) };

    assertThat(fixture.createTableCells(objects)).isEqualTo(array);
    assertThat(fixture.createTableCells(objects).length).isEqualTo(3);
  }

  @Test
  public void should_create_string_array() {
    Object[] objects = new Object[] { "string1", "string2" };

    assertThat(fixture.createStringArray(objects)).isEqualTo(objects);
    assertThat(fixture.createStringArray(objects).length).isEqualTo(2);
  }
}
