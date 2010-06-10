/*
 * Created on May 17, 2010
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

import org.apache.maven.plugin.logging.Log;
import org.fest.mocks.EasyMockTemplate;
import org.junit.*;

import java.lang.reflect.Method;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;

/**
 * Tests for <code>{@link JavaFxcMojo#execute()}</code>.
 *
 * @author Alex Ruiz
 */
public class TestJavaFxcMojo_execute_Test {

  private TestJavaFxcMojo mojo;

  @Before
  public void setUp() throws Exception {
    Method compileMethod = AbstractJavaFxcMojo.class.getDeclaredMethod("compile");
    Method isJavaProjectMethod = AbstractJavaFxcMojo.class.getDeclaredMethod("isJavaProject");
    mojo = createMock(TestJavaFxcMojo.class, compileMethod, isJavaProjectMethod);
  }

  @Test
  public void should_compile_sources() {
    new EasyMockTemplate(mojo) {
      @Override protected void expectations() throws Exception {
        expect( mojo.isJavaProject() ).andReturn( true );
        mojo.compile();
        expectLastCall();
      }

      @Override protected void codeToTest() throws Exception {
        mojo.execute();
      }
    }.run();
  }

  @Test
  public void should_not_compile_sources_on_non_java_project() {
    new EasyMockTemplate(mojo) {
      @Override protected void expectations() throws Exception {
        expect( mojo.isJavaProject() ).andReturn( false );
        expectLastCall();
      }

      @Override protected void codeToTest() throws Exception {
        mojo.execute();
      }
    }.run();
  }

  @Test
  public void should_not_compile_sources_if_skip_is_true() {
    final Log log = setMojoLog();
    mojo.skip = true;
    new EasyMockTemplate(mojo, log) {
      @Override protected void expectations() {
        expect( mojo.isJavaProject() ).andReturn( true );
        log.info("Not compiling test sources");
        expectLastCall();
      }

      @Override protected void codeToTest() throws Exception {
        mojo.execute();
      }
    }.run();
  }

  private Log setMojoLog() {
    Log log = createMock(Log.class);
    mojo.setLog(log);
    return log;
  }
}
