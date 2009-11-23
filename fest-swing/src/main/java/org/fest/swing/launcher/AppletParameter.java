/*
 * Created on Jul 14, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.launcher;

/**
 * Understands a fluent interface for creation of applet parameters.
 * <p>
 * For example, the following code listing:
 *
 * <pre>
 * // import static org.fest.swing.launcher.AppletParameter.name;
 *
 * AppletParameter = {@link #name(String) name}(&quot;bgcolor&quot;).{@link AppletParameterBuilder#value(String) value}(&quot;blue&quot;);
 * </pre>
 *
 * will create an applet parameter with name "bgcolor" and value "blue."
 * </p>
 *
 * @author Yvonne Wang
 */
public class AppletParameter {

  public final String name;
  public final String value;

  AppletParameter(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Starting point of the fluent interface for creation of <code>{@link AppletParameter}</code>s.
   * @param name the name of the applet parameter.
   * @return a builder of <code>AppletParameter</code>s.
   */
  public static AppletParameterBuilder name(String name) {
    return new AppletParameterBuilder(name);
  }

  /**
   * Understands creation of <code>{@link AppletParameter}</code>s.
   *
   * @author Yvonne Wang
   */
  public static class AppletParameterBuilder {

    private final String name;

    AppletParameterBuilder(String name) {
      this.name = name;
    }

    /**
     * Creates a new <code>{@link AppletParameter}</code> with the given name and value.
     * @param value the value for the <code>AppletParameter</code>.
     * @return the created <code>AppletParameter</code>.
     */
    public AppletParameter value(String value) {
      return new AppletParameter(name, value);
    }
  }
}
