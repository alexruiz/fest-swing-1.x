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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.applet;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.BorderFactory.*;
import static javax.swing.border.BevelBorder.LOWERED;
import static org.fest.util.Strings.concat;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a window that displays an <code>{@link Applet}</code>.
 * <p>
 * Typical usage:
 * <pre>
 * AppletViewer viewer = new AppletViewer(new MyApplet());
 * 
 * // test the applet, viewer can be wrapped with a FrameFixture.
 * FrameFixture viewerFixture = new FrameFixture(viewer);
 * 
 * viewer.unloadApplet() // stops and destroys the applet
 * viewerFixture.cleanUp();
 * </pre>
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class AppletViewer extends JFrame implements StatusDisplay {

  private static final long serialVersionUID = 1L;

  private static final String APPLET_VIEWER_TITLE = "Applet Viewer: ";
  private static final String APPLET_LOADED_MESSAGE = "Applet loaded";
  private static final Dimension DEFAULT_SIZE = new Dimension(100, 100);

  private final JLabel statusLabel = new JLabel();

  private Applet applet;
  private transient AppletStub stub;
  private boolean loaded;
 
  /**
   * Creates a new </code>{@link AppletViewer}</code>. This constructor creates new instances of
   * <code>{@link BasicAppletStub}</code> and <code>{@link BasicAppletContext}</code>.
   * <p>
   * <b>Note:</b> This constructor is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible 
   * for invoking this constructor in the EDT.
   * </p>
   * @param applet the applet to view.
   * @throws NullPointerException if <code>applet</code> is <code>null</code>.
   */
  @RunsInCurrentThread
  public AppletViewer(Applet applet) {
    load(applet, new BasicAppletStub(this, new BasicAppletContext(this)));
  }

  /**
   * Creates a new </code>{@link AppletViewer}</code>. This constructor creates new instances of
   * <code>{@link BasicAppletStub}</code> and <code>{@link BasicAppletContext}</code>.
   * <p>
   * <b>Note:</b> This constructor is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible 
   * for invoking this constructor in the EDT.
   * </p>
   * @param applet the applet to view.
   * @param parameters the parameters included in an applet HTML tag.
   * @throws NullPointerException if <code>applet</code> is <code>null</code>.
   * @throws NullPointerException if <code>parameters</code> is <code>null</code>.
   */
  @RunsInCurrentThread
  public AppletViewer(Applet applet, Map<String, String> parameters) {
    load(applet, new BasicAppletStub(this, new BasicAppletContext(this), parameters));
  }

  /**
   * Creates a new </code>{@link AppletViewer}</code>.
   * <p>
   * <b>Note:</b> This constructor is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible 
   * for invoking this constructor in the EDT.
   * </p>
   * @param applet the applet to view.
   * @param stub the applet's stub.
   * @throws NullPointerException if <code>applet</code> is <code>null</code>.
   * @throws NullPointerException if <code>stub</code> is <code>null</code>.
   */
  @RunsInCurrentThread
  public AppletViewer(Applet applet, AppletStub stub) {
    load(applet, stub);
  }

  final void load(Applet newApplet, AppletStub newStub) {
    applet = validated(newApplet);
    stub = validated(newStub);
    setUpFrame();
    addContent();
    setUpApplet();
  }

  private static Applet validated(Applet applet) {
    if (applet == null) throw new NullPointerException("The applet to load should not be null");
    return applet;
  }

  private static AppletStub validated(AppletStub stub) {
    if (stub == null) throw new NullPointerException("The AppletStub should not be null");
    return stub;
  }

  private void setUpFrame() {
    setTitle(concat(APPLET_VIEWER_TITLE, applet.getClass().getName()));
    setSize(DEFAULT_SIZE);
    setLayout(new BorderLayout());
  }

  private void addContent() {
    add(applet, CENTER);
    statusLabel.setBorder(createCompoundBorder(createBevelBorder(LOWERED), createEmptyBorder(2, 5, 2, 5)));
    statusLabel.setName("status");
    add(statusLabel, SOUTH);
  }

  private void setUpApplet() {
    applet.setStub(stub);
    reloadApplet();
    showStatus(APPLET_LOADED_MESSAGE);
  }

  /**
   * Initializes and starts the applet in this viewer.
   */
  public void reloadApplet() {
    if (loaded) unloadApplet();
    applet.init();
    applet.start();
    loaded = true;
  }

  /**
   * Stops and destroys the applet loaded in this viewer. This method should be called before closing or disposing this
   * viewer.
   */
  public void unloadApplet() {
    applet.stop();
    applet.destroy();
    loaded = false;
  }
  
  /**
   * Indicates whether the applet in this viewer is loaded or not.
   * @return <code>true</code> if this applet is loaded, <code>false</code> otherwise.
   */
  public boolean appletLoaded() { return loaded; }
  
  /**
   * Displays the given status message.
   * @param status the status to display.
   */
  public void showStatus(String status) {
    statusLabel.setText(status);
  }

  /**
   * Returns the applet displayed in this viewer.
   * @return the applet displayed in this viewer.
   */
  public Applet applet() {
    return applet;
  }

  /**
   * Returns the <code>{@link AppletStub}</code> in this viewer.
   * @return the <code>AppletStub</code> in this viewer.
   */
  public AppletStub stub() {
    return stub;
  }
}
