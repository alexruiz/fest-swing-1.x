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
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Understands a basic (and limited) implementation of <code>{@link AppletContext}</code>.
 *
 * @author Alex Ruiz 
 * @author Yvonne Wang
 */
public class BasicAppletContext implements AppletContext {

  private static final Enumeration<Applet> NO_APPLETS = new EmptyAppletEnumeration();
  
  private static final class EmptyAppletEnumeration implements Enumeration<Applet> {
    EmptyAppletEnumeration() {}
    
    public boolean hasMoreElements() {
      return false;
    }

    public Applet nextElement() {
      return null;
    }
  }

  private final Map<String, InputStream> streamMap = new HashMap<String, InputStream>();
  private final StatusDisplay statusDisplay;

  /**
   * Creates a new </code>{@link BasicAppletContext}</code>.
   * @param statusDisplay where the application can inform users of its current state.
   * @throws NullPointerException if <code>statusDisplay</code> is <code>null</code>.
   */
  public BasicAppletContext(StatusDisplay statusDisplay) {
    if (statusDisplay == null) throw new NullPointerException("Instance of StatusDisplay should not be null");
    this.statusDisplay = statusDisplay;
  }
  
  /**
   * Not implemented. Returns <code>null</code>.
   * @see AppletContext#getApplet(String)
   */
  public Applet getApplet(String name) { return null; }

  /**
   * Not implemented. Returns an empty <code>{@link Enumeration}</code>.
   * @see AppletContext#getApplets()
   */
  public Enumeration<Applet> getApplets() { return NO_APPLETS; }

  /**
   * Not implemented. Returns <code>null</code>.
   * @see AppletContext#getAudioClip(URL)
   */
  public AudioClip getAudioClip(URL url) { return null; }

  /**
   * Not implemented. Returns <code>null</code>.
   * @see AppletContext#getImage(URL)
   */
  public Image getImage(URL url) { return null; }

  /**
   * Returns the stream to which specified key is associated within this applet context. Returns <code>null</code> if
   * the applet context contains no stream for this key.
   * @return the stream to which this applet context maps the key.
   * @param key key whose associated stream is to be returned.
   */
  public InputStream getStream(String key) {
    return streamMap.get(key);
  }

  /**
   * Finds all the keys of the streams in this applet context.
   * @return an iterator of all the names of the streams in this applet context.
   */
  public Iterator<String> getStreamKeys() {
    return streamMap.keySet().iterator();
  }

  /**
   * Associates the specified stream with the specified key in this applet context.
   * @param key key with which the specified value is to be associated.
   * @param stream stream to be associated with the specified key. If this parameter is <code>null</code>, the specified
   *        key is removed in this applet context.
   */
  public void setStream(String key, InputStream stream) {
    if (stream == null) {
      streamMap.remove(key);
      return;
    }
    streamMap.put(key, stream);
  }

  /**
   * Not implemented.
   * @see AppletContext#showDocument(URL)
   */
  public void showDocument(URL url) {}

  /**
   * Not implemented.
   * @see AppletContext#showDocument(URL, String)
   */
  public void showDocument(URL url, String target) {}

  /**
   * Requests that the given status be displayed in this context's <code>{@link StatusDisplay}</code>.
   * @param status a message to display.
   */
  public void showStatus(String status) {
    statusDisplay.showStatus(status);
  }
}
