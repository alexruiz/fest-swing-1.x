/*
 * Created on Mar 30, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.test.core;

import static org.easymock.classextension.EasyMock.createMock;

import java.awt.*;
import java.awt.event.AWTEventListener;

import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.Robot;
import org.fest.swing.hierarchy.ComponentHierarchy;
import org.fest.swing.image.ImageFileWriter;
import org.fest.swing.keystroke.KeyStrokeMappingProvider;
import org.fest.swing.util.RobotFactory;

/**
 * Understands common mock objects.
 *
 * @author Alex Ruiz
 */
public final class Mocks {

  public static AWTEventListener mockAWTEventListener() { return mock(AWTEventListener.class); }

  public static Component mockComponent() { return mock(Component.class); }

  public static ComponentFinder mockComponentFinder() { return mock(ComponentFinder.class); }

  public static ComponentHierarchy mockComponentHierarchy() { return mock(ComponentHierarchy.class); }

  public static ImageFileWriter mockImageFileWriter() { return mock(ImageFileWriter.class); }

  public static KeyStrokeMappingProvider mockKeyStrokeMappingProvider() { return mock(KeyStrokeMappingProvider.class); }

  public static Robot mockRobot() { return mock(Robot.class); }

  public static RobotFactory mockRobotFactory() { return mock(RobotFactory.class); }

  private static <T> T mock(Class<T> typeToMock) {
    return createMock(typeToMock);
  }

  private Mocks() {}
}
