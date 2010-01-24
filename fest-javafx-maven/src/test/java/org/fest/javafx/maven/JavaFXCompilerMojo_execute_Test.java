/*
 * Created on Jan 24, 2010
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
package org.fest.javafx.maven;

import static org.easymock.classextension.EasyMock.createMock;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link JavaFXCompilerMojo#execute()}</code>.
 *
 * @author Alex Ruiz
 */
public class JavaFXCompilerMojo_execute_Test {

  private JavaFXHome javaFXHome;
  private JavaFXCompilerFactory compilerFactory;
  private JavaFXCompilerSetup compilerSetup;
  private AntTaskExecutor compilerExecutor;
  private JavaFXCompilerMojo mojo;

  @Before
  public void setUp() {
    javaFXHome = createMock(JavaFXHome.class);
    compilerFactory = createMock(JavaFXCompilerFactory.class);
    compilerSetup = createMock(JavaFXCompilerSetup.class);
    compilerExecutor = createMock(AntTaskExecutor.class);
    mojo = new JavaFXCompilerMojo();
    mojo.javaFXHome(javaFXHome);
    mojo.compilerFactory(compilerFactory);
    mojo.compilerSetup(compilerSetup);
    mojo.compilerExecutor(compilerExecutor);
  }

  @Test
  public void should_create_and_execute_JavaFX_compiler_Ant_task() {
  }
}
