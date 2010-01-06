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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.launcher;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.launcher.NewAppletViewerQuery.showAppletViewerWith;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.isEmpty;

import java.applet.Applet;
import java.applet.AppletStub;
import java.util.HashMap;
import java.util.Map;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.applet.AppletViewer;
import org.fest.swing.applet.BasicAppletContext;
import org.fest.swing.applet.BasicAppletStub;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.UnexpectedException;
import org.fest.swing.launcher.AppletParameter.AppletParameterBuilder;

/**
 * Understands a fluent interface for launching and testing <code>{@link Applet}</code>s.
 * <p>
 * An applet can be launched by passing its type as <code>String</code>, the actual type, or an instance of the
 * applet to launch:
 *
 * <pre>
 * {@link AppletViewer} viewer = AppletLauncher.{@link #applet(String) applet}(&quot;org.fest.swing.applet.MyApplet&quot;).{@link #start() start}();
 *
 * // or
 *
 *
 * {@link AppletViewer} viewer = AppletLauncher.{@link #applet(Class) applet}(MyApplet.class).{@link #start() start}();
 *
 * // or
 *
 * {@link AppletViewer} viewer = AppletLauncher.{@link #applet(Applet) applet}(new MyApplet()).{@link #start() start}();
 * </pre>
 *
 * </p>
 * <p>
 * In addition, we can pass parameters to the applet to launch. The parameters to pass are the same that are specified
 * in the <a href="http://java.sun.com/docs/books/tutorial/deployment/applet/html.html"
 * target="_blank">HTML "param" tag</a>:
 *
 * <pre>
 * {@link AppletViewer} viewer = AppletLauncher.{@link #applet(Applet) applet}(new MyApplet())
 *                                     .{@link #withParameters(Map) withParameters}(
 *                                         {@link AppletParameter#name(String) name}(&quot;bgcolor&quot;).{@link AppletParameterBuilder#value(String) value}(&quot;blue&quot;),
 *                                         {@link AppletParameter#name(String) name}(&quot;color&quot;).{@link AppletParameterBuilder#value(String) value}(&quot;red&quot;),
 *                                         {@link AppletParameter#name(String) name}(&quot;pause&quot;).{@link AppletParameterBuilder#value(String) value}(&quot;200&quot;)
 *                                      )
 *                                     .{@link #start() start}();
 *
 * // or
 *
 * Map&lt;String, String&gt; parameters = new HashMap&lt;String, String&gt;();
 * parameters.put(&quot;bgcolor&quot;, &quot;blue&quot;);
 * parameters.put(&quot;color&quot;, &quot;red&quot;);
 * parameters.put(&quot;pause&quot;, &quot;200&quot;);
 *
 * {@link AppletViewer} viewer = AppletLauncher.{@link #applet(Applet) applet}(new MyApplet()).{@link #withParameters(Map) withParameters}(parameters).{@link #start() start}();
 *
 *
 * </pre>
 *
 * </p>
 *
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class AppletLauncher {

  private final Applet applet;
  private final Map<String, String> parameters = new HashMap<String, String>();

  /**
   * Creates a new applet launcher. The applet to launch is a new instance of the given type. It is assumed that the
   * given type has a default constructor.
   * @param appletType the type of applet to instantiate.
   * @return the created applet launcher.
   * @throws NullPointerException if the given type name is <code>null</code>.
   * @throws IllegalArgumentException if the given type name is empty.
   * @throws IllegalArgumentException if the given type is not a subclass of <code>java.applet.Applet</code>.
   * @throws UnexpectedException if the given type cannot be loaded.
   * @throws UnexpectedException if a new instance of the given type cannot be instantiated.
   */
  @RunsInEDT
  public static AppletLauncher applet(String appletType) {
    if (appletType == null) throw new NullPointerException("The name of the applet type should not be null");
    if (isEmpty(appletType)) throw new IllegalArgumentException("The name of the applet type should not be empty");
    Class<?> type = load(appletType);
    if (!(Applet.class.isAssignableFrom(type)))
      throw new IllegalArgumentException(concat("The given type is not a subclass of ", Applet.class.getName()));
    return instantiate(type);
  }

  @RunsInEDT
  private static Class<?> load(String typeName) {
    try {
      return Class.forName(typeName);
    } catch (ClassNotFoundException e) {
      throw cannotLoadType(typeName, e);
    } catch (Exception e) {
      throw cannotLoadType(typeName, e);
    }
  }

  private static UnexpectedException cannotLoadType(String typeName, Exception e) {
    throw new UnexpectedException(concat("Unable to load class ", typeName), e);
  }
  
  /**
   * Creates a new applet launcher. The applet to launch is a new instance of the given type. It is assumed that the
   * given type has a default constructor.
   * @param appletType the type of applet to instantiate.
   * @return the created applet launcher.
   * @throws NullPointerException if the given type is <code>null</code>.
   * @throws UnexpectedException if a new instance of the given type cannot be instantiated.
   */
  @RunsInEDT
  public static AppletLauncher applet(Class<? extends Applet> appletType) {
    if (appletType == null) throw new NullPointerException("The applet type should not be null");
    return instantiate(appletType);
  }

  private static AppletLauncher instantiate(final Class<?> appletType) {
    try {
      Object applet = execute(new GuiQuery<Object>() {
        protected Object executeInEDT() throws Exception {
          return appletType.newInstance();
        }
      });
      return applet((Applet)applet);
    } catch (Exception e) {
      throw cannotInstantiateApplet(appletType.getName(), e);
    }
  }
  
  private static UnexpectedException cannotInstantiateApplet(String appletType, Exception cause) {
    throw new UnexpectedException(concat("Unable to create a new instance of ", appletType), cause);
  }

  /**
   * Creates a new applet launcher.
   * @param applet the applet to launch.
   * @return the created applet launcher.
   * @throws NullPointerException if the given applet is <code>null</code>.
   */
  public static AppletLauncher applet(Applet applet) {
    return new AppletLauncher(applet);
  }

  private AppletLauncher(Applet applet) {
    if (applet == null) throw new NullPointerException("The applet to launch should not be null");
    this.applet = applet;
  }

  /**
   * Sets the parameters for the applet to launch, as an alternative to
   * <code>{@link #withParameters(AppletParameter...)}</code>.
   * @param newParameters the parameters for the applet to launch.
   * @return this launcher.
   * @throws NullPointerException if <code>newParameters</code> is <code>null</code>.
   */
  public AppletLauncher withParameters(Map<String, String> newParameters) {
    if (newParameters == null) throw new NullPointerException("The map of parameters should not be null");
    parameters.clear();
    parameters.putAll(newParameters);
    return this;
  }

  /**
   * Sets the parameters for the applet to launch, as an alternative to <code>{@link #withParameters(Map)}</code>.
   * @param newParameters the parameters for the applet to launch.
   * @return this launcher.
   * @throws NullPointerException if <code>newParameters</code> is <code>null</code>.
   * @throws NullPointerException if any parameter is <code>null</code>.
   */
  public AppletLauncher withParameters(AppletParameter... newParameters) {
    if (newParameters == null) throw new NullPointerException("The array of parameters should not be null");
    parameters.clear();
    for (AppletParameter parameter : newParameters) add(parameter);
    return this;
  }

  private void add(AppletParameter parameter) {
    if (parameter == null) throw new NullPointerException("Found a null parameter");
    parameters.put(parameter.name, parameter.value);
  }

  /**
   * Launches the applet in a <code>{@link AppletViewer}</code> (using implementations of
   * <code>{@link BasicAppletStub}</code> and <code>{@link BasicAppletContext}</code>. To provide your own
   * <code>{@link AppletStub}</code> create a new <code>{@link AppletViewer}</code> directly.
   * The <code>AppletViewer</code> is created and launched in the event dispatch thread.
   * @return the created <code>AppletViewer</code>.
   */
  public AppletViewer start() {
    return showAppletViewerWith(applet, parameters);
  }
}
