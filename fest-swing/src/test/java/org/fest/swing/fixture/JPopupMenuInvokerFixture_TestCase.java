/*
 * Created on Nov 17, 2009
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
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JPopupMenu;

import org.fest.swing.driver.ComponentDriver;
import org.junit.Test;

/**
 * Understands test methods for implementations of {@link JPopupMenuInvokerFixture}.
 * 
 * @param <T> the type of component supported by the fixture to test.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class JPopupMenuInvokerFixture_TestCase<T extends Component> extends
ComponentFixture_Implementations_TestCase<T> {
  @Override
  abstract ComponentDriver driver();

  @Override
  abstract JPopupMenuInvokerFixture fixture();

  @Test
  public final void should_show_popup_menu() {
    final JPopupMenu popupMenu = newPopupMenu();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().invokePopupMenu(target())).andReturn(popupMenu);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture popupMenuFixture = fixture().showPopupMenu();
        assertThat(popupMenuFixture.robot).isSameAs(robot());
        assertThat(popupMenuFixture.target()).isSameAs(popupMenu);
      }
    }.run();
  }

  @Test
  public final void should_show_popup_menu_at_given_point() {
    final JPopupMenu popupMenu = newPopupMenu();
    final Point p = new Point();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().invokePopupMenu(target(), p)).andReturn(popupMenu);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture popupMenuFixture = fixture().showPopupMenuAt(p);
        assertThat(popupMenuFixture.robot).isSameAs(robot());
        assertThat(popupMenuFixture.target()).isSameAs(popupMenu);
      }
    }.run();
  }

  private static JPopupMenu newPopupMenu() {
    return createMock(JPopupMenu.class);
  }
}
