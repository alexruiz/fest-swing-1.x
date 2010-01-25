/*
 * Created on Jan 23, 2010
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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.maven.CommonAssertions.failWhenExpectingUnexpectedError;

import org.apache.maven.plugin.MojoExecutionException;
import org.fest.mocks.EasyMockTemplate;
import org.fest.mocks.UnexpectedError;
import org.junit.*;

/**
 * Tests for <code>{@link JavaFXHome#verify(String)}</code>.
 *
 * @author Alex Ruiz
 */
public class JavaFXHome_verify_Test {

  private Environment environment;
  private JavaFXHome javaFXHome;

  @Before
  public void setUp() {
    environment = createMock(Environment.class);
    javaFXHome = new JavaFXHome(environment);
  }

  @Test
  public void should_return_given_JavaFX_home_if_it_is_not_empty() throws MojoExecutionException {
    assertThat(javaFXHome.verify("c:\\javafx")).isEqualTo("c:\\javafx");
  }

  @Test
  public void should_return_environment_variable_JAVAFXHOME_if_given_value_is_empty() {
    new EasyMockTemplate(environment) {
      protected void expectations() {
        expect(environment.javaFXHome()).andReturn("c:\\javafx");
      }

      protected void codeToTest() throws MojoExecutionException {
        assertThat(javaFXHome.verify(null)).isEqualTo("c:\\javafx");
      }
    }.run();
  }

  @Test
  public void should_throw_error_if_JavaFX_home_cannot_be_obtained() {
    try {
      new EasyMockTemplate(environment) {
        protected void expectations() {
          expect(environment.javaFXHome()).andReturn(null);
        }

        protected void codeToTest() throws MojoExecutionException {
          javaFXHome.verify(null);
        }
      }.run();
      failWhenExpectingUnexpectedError();
    } catch (UnexpectedError e) {
      Throwable cause = e.getCause();
      assertThat(cause).isInstanceOf(MojoExecutionException.class);
    }
  }
}
