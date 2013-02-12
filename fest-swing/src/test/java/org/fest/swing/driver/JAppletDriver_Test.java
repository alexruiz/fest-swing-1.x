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
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;

import java.applet.Applet;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.JApplet;

import org.fest.swing.core.Robot;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

public class JAppletDriver_Test extends EDTSafeTestCase {
  private JAppletDriver driver;
  private JApplet target;
  private Robot robot;

  @Before
  public void setUp() {
    robot = createMock(Robot.class);
    target = createMock(JApplet.class);
    driver = new JAppletDriver(robot, target);
  }

  @Test
  public void should_get_applet_context() {
    final AppletContext context = createMock(AppletContext.class);

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        expect(target.getAppletContext()).andReturn(context);
      }

      @Override
      protected void codeToTest() {
        assertThat(driver.getAppletContext()).isSameAs(context);
      }
    }.run();
  }

  @Test
  public void should_applet_resize() {
    final int width = 10;
    final int height = 10;

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        target.resize(10, 10);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        driver.appletResize(width, height);
      }
    }.run();
  }

  @Test
  public void should_get_code_base() throws MalformedURLException {
    final URL url = new URL("http://localhost");

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        expect(target.getCodeBase()).andReturn(url);
      }

      @Override
      protected void codeToTest() {
        assertThat(driver.getCodeBase()).isSameAs(url);
      }
    }.run();
  }

  @Test
  public void should_get_document_base() throws MalformedURLException {
    final URL url = new URL("http://localhost");

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        expect(target.getDocumentBase()).andReturn(url);
      }

      @Override
      protected void codeToTest() {
        assertThat(driver.getDocumentBase()).isSameAs(url);
      }
    }.run();
  }

  @Test
  public void should_get_parameter() {
    final String parameter = "parameter";
    final String name = "name";

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        expect(target.getParameter(name)).andReturn(parameter);
      }

      @Override
      protected void codeToTest() {
        assertThat(driver.getParameter(name)).isSameAs(parameter);
      }
    }.run();
  }

  @Test
  public void is_active() {
    final boolean active = true;

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        expect(target.isActive()).andReturn(active);
      }

      @Override
      protected void codeToTest() {
        assertThat(driver.isActive());
      }
    }.run();
  }

  @Test
  public void should_get_applet() {
    final AppletContext context = createMock(AppletContext.class);
    final Applet applet = createMock(Applet.class);
    final String name = "applet";

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        expect(target.getAppletContext()).andReturn(context);
        expect(context.getApplet(name)).andReturn(applet);
      }

      @Override
      protected void codeToTest() {
        replay(context);
        replay(applet);
        assertThat(driver.getApplet(name)).isSameAs(applet);
      }
    }.run();
  }

  @Test
  public void should_get_applets() {
    final AppletContext context = createMock(AppletContext.class);
    final Enumeration<Applet> applets = createMock(Enumeration.class);

    new EasyMockTemplate(target()) {
      @Override
      protected void expectations() {
        expect(target.getAppletContext()).andReturn(context);
        expect(context.getApplets()).andReturn(applets);
      }

      @Override
      protected void codeToTest() {
        replay(context);
        replay(applets);
        assertThat(driver.getApplets()).isSameAs(applets);

      }
    }.run();
  }

  JApplet target() {
    return target;
  }
}
