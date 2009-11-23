/*
 * Created on Feb 19, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; either version 2 of 
 * the License. You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 * the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.fixture;

import javax.swing.JButton;

import org.testng.annotations.Test;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.driver.AbstractSwingButtonDriver;
import org.fest.mocks.EasyMockTemplate;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.desktop.util.Nodes.componentInNode;
import static org.fest.javafx.test.builder.SwingButtons.button;

/**
 * Tests for <code>{@link SwingButtonFixture}</code>.
 *
 * @author Alex Ruiz
 */
@Test
public class SwingButtonFixtureTest extends SwingComponentNodeTestCase {

  private SwingButtonFixture fixture;
  private FXNode node;
  private AbstractSwingButtonDriver<JButton> driver;

  @SuppressWarnings("unchecked")
  void setUpFixture() {
    node = button().withId("id").createNew();
    driver = createMock(AbstractSwingButtonDriver.class);
    fixture = new SwingButtonFixture(robot(), node);
    fixture.updateDriver(driver);
  }

  public void shouldRequireText() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.requireText(node, "Hello");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireText("Hello"));
      }
    }.run();
  }
  
  public void shouldClickNode() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.click(node);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.click());
      }
    }.run();
  }
  
  public void shouldReturnNodeComponent() {
    assertThat(fixture.component()).isSameAs(componentInNode(node, JButton.class));
  }
  
  FXNode target() { return node; }
  NodeFixture fixture() { return fixture; }
}
