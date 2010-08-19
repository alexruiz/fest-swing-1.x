/*
 * Created on Jul 10, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.applet;

import java.applet.*;
import java.awt.Window;
import java.net.URL;
import java.util.*;

/**
 * Basic (and limited) implementation of <code>{@link AppletStub}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicAppletStub implements AppletStub {

  private final Window viewer;
  private final AppletContext context;
  private final Map<String, String> parameters = new HashMap<String, String>();

  /**
   * Creates a new </code>{@link BasicAppletStub}</code>.
   * @param viewer the window to host the applet.
   * @param context the applet context.
   * @param parameters the parameters included in an applet HTML tag.
   * @throws NullPointerException if {@code viewer} is {@code null}.
   * @throws NullPointerException if {@code context} is {@code null}.
   * @throws NullPointerException if {@code parameters} is {@code null}.
   */
  public BasicAppletStub(Window viewer, AppletContext context, Map<String, String> parameters) {
    this(viewer, context);
    if (parameters == null) throw new NullPointerException("The map of parameters should not be null");
    this.parameters.putAll(parameters);
  }

  /**
   * Creates a new </code>{@link BasicAppletStub}</code>.
   * @param viewer the window to host the applet.
   * @param context the applet context.
   * @throws NullPointerException if {@code viewer} is {@code null}.
   * @throws NullPointerException if {@code context} is {@code null}.
   */
  public BasicAppletStub(Window viewer, AppletContext context) {
    if (viewer == null) throw new NullPointerException("The apple viewer should not be null");
    if (context == null) throw new NullPointerException("The AppletContext should not be null");
    this.viewer = viewer;
    this.context = context;
  }

  /**
   * Not implemented. Returns {@code true}.
   * @see AppletStub#isActive()
   */
  public boolean isActive() {
    return true;
  }

  /**
   * Resizes this stub's viewer when the applet wants to be resized.
   * @param width the new requested width for the applet.
   * @param height the new requested height for the applet.
   */
  public void appletResize(int width, int height) {
    viewer.setSize(width, height);
  }

  /**
   * Returns the applet's context.
   * @return the applet's context.
   */
  public AppletContext getAppletContext() {
    return context;
  }

  /**
   * Not implemented. Returns {@code getClass().getResource(".")}.
   * @see AppletStub#getCodeBase()
   */
  public URL getCodeBase() {
    return getDocumentBase();
  }

  /**
   * Not implemented. Returns {@code getClass().getResource(".")}.
   * @see AppletStub#getDocumentBase()
   */
  public URL getDocumentBase() {
    return Thread.currentThread().getContextClassLoader().getResource(".");
  }

  /**
   * Returns the value of the named parameter in the HTML tag.
   * @param name a parameter name.
   * @return the value of the named parameter, or {@code null} if not set.
   */
  public String getParameter(String name) {
    return parameters.get(name);
  }
}
