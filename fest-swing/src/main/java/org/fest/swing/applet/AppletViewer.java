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
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.SwingUtilities.isEventDispatchThread;
import static javax.swing.border.BevelBorder.LOWERED;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.jcip.annotations.GuardedBy;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;

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
public class AppletViewer {

  private static final long serialVersionUID = 1L;

  private static final String APPLET_VIEWER_TITLE = "Applet Viewer: ";
  private static final String APPLET_LOADED_MESSAGE = "Applet loaded";
  private static final Dimension DEFAULT_SIZE = new Dimension(100, 100);

  private final Viewer viewer;
  private final Applet applet;
  private final AppletStub stub;
  @GuardedBy("this") private boolean loaded;
 
  /**
   * Creates a new </code>{@link AppletViewer}</code>. This constructor creates new instances of
   * <code>{@link BasicAppletStub}</code> and <code>{@link BasicAppletContext}</code>.
   * @param applet the applet to view.
   * @throws NullPointerException if <code>applet</code> is <code>null</code>.
   */
  public AppletViewer(Applet applet) {
    this.applet = validateIsNotNull(applet);
    viewer = Viewer.createNew(applet);
    stub = new BasicAppletStub(viewer, new BasicAppletContext(viewer));
    applet.setStub(stub);
  }

  /**
   * Creates a new </code>{@link AppletViewer}</code>. This constructor creates new instances of
   * <code>{@link BasicAppletStub}</code> and <code>{@link BasicAppletContext}</code>.
   * @param applet the applet to view.
   * @param parameters the parameters included in an applet HTML tag.
   * @throws NullPointerException if <code>applet</code> is <code>null</code>.
   * @throws NullPointerException if <code>parameters</code> is <code>null</code>.
   */
  public AppletViewer(Applet applet, Map<String, String> parameters) {
    this.applet = validateIsNotNull(applet);
    viewer = Viewer.createNew(applet);
    stub = new BasicAppletStub(viewer, new BasicAppletContext(viewer), parameters);
    applet.setStub(stub);
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
  public AppletViewer(Applet applet, AppletStub stub) {
    this.applet = validateIsNotNull(applet);
    if (stub == null) throw new NullPointerException("The AppletStub should not be null");
    viewer = Viewer.createNew(applet);
    this.stub = stub;
    applet.setStub(stub);
  }
  
  private static Applet validateIsNotNull(Applet applet) {
    if (applet == null) throw new NullPointerException("The applet to load should not be null");
    return applet;
  }

  /**
   * Loads the applet and shows it in this viewer.
   */
  public void display() {
    reloadApplet();
    execute(new GuiTask() {
      protected void executeInEDT() {
        viewer.showStatus(APPLET_LOADED_MESSAGE);
        viewer.pack();
        viewer.setVisible(true);
      }
    });
  }
  
  public void dispose() {
    execute(new GuiTask() {
      protected void executeInEDT() {
        viewer.setVisible(false);
        viewer.dispose();
      }
    });
  }

  /**
   * Initializes and starts the applet in this viewer.
   */
  public synchronized void reloadApplet() {
    if (loaded) unloadApplet();
    applet.init();
    applet.start();
    loaded = true;
  }

  /**
   * Stops and destroys the applet loaded in this viewer. This method should be called before closing or disposing this
   * viewer.
   */
  public synchronized void unloadApplet() {
    applet.stop();
    applet.destroy();
    loaded = false;
  }
  
  /**
   * Indicates whether the applet in this viewer is loaded or not.
   * @return <code>true</code> if this applet is loaded, <code>false</code> otherwise.
   */
  public synchronized boolean appletLoaded() { return loaded; }
  
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
  
  /**
   * Returns the <code>{@link Frame}</code> used to display an applet.
   * @return the <code>{@link Frame}</code> used to display an applet.
   */
  public Frame window() {
    return viewer;
  }
  
  static class Viewer extends JFrame implements StatusDisplay {
    private static final long serialVersionUID = 1L;
    
    private final JLabel statusLabel = new JLabel();

    static Viewer createNew(final Applet applet) {
      return execute(new GuiQuery<Viewer>() {
        protected Viewer executeInEDT() {
          return new Viewer(applet);
        }
      });
    }
    
    private Viewer(Applet applet) {
      setTitle(concat(APPLET_VIEWER_TITLE, applet.getClass().getName()));
      setSize(DEFAULT_SIZE);
      setLayout(new BorderLayout());
      add(applet, CENTER);
      statusLabel.setBorder(createCompoundBorder(createBevelBorder(LOWERED), createEmptyBorder(2, 5, 2, 5)));
      statusLabel.setName("status");
      add(statusLabel, SOUTH);
    }

    public void showStatus(final String status) {
      if (isEventDispatchThread()) {
        setStatus(status);
        return;
      }
      invokeLater(new Runnable() {
        public void run() {
          setStatus(status);
        }
      });
    }

    void setStatus(String status) {
      statusLabel.setText(status);
    }
  }
}
