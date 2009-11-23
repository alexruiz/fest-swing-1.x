/*
 * Created on Jan 8, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; either version 2 of 
 * the License. You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 * the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.util;

import java.awt.Component;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import com.sun.scenario.scenegraph.SGComponent;
import com.sun.scenario.scenegraph.SGImage;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.SGText;
import com.sun.scenario.scenegraph.fx.FXNode;

/**
 * Understands utility methods related to JavaFX nodes.
 *
 * @author Alex Ruiz
 */
public final class Nodes {

  /**
   * Returns the coordinates of the center of the given node.
   * @param node the given node.
   * @return the coordinates of the center of the given node, or <code>null</code> if the node is not showing on the 
   * screen.
   */
  public static Point centerOf(FXNode node) {
    Rectangle2D bounds = node.getBounds();
    int x = (int)(bounds.getWidth() / 2);
    int y = (int)(bounds.getHeight() / 2);
    return new Point(x, y);
  }
  
  /**
   * Returns the AWT/Swing <code>{@link Component}</code> attached to the given node (if any.)
   * @param <T> the generic type of the given class to match.
   * @param node the given node.
   * @param type the type of component to return.
   * @return the AWT/Swing component attached to the given node, or <code>null</code> if the node does not have any 
   * component attached to it.
   */
  public static <T extends Component> T componentInNode(FXNode node, Class<T> type) {
    SGComponent leaf = leafWithComponent(node);
    if (leaf == null) return null;
    return componentInNode(leaf, type);
  }
  
  /**
   * Returns the leaf of the given node only if such leaf is has an attached AWT/Swing component.
   * @param node the target node.
   * @return the leaf of the given node only if such leaf is has an attached AWT/Swing component, otherwise 
   * <code>null</code>.
   */
  public static SGComponent leafWithComponent(FXNode node) {
    SGNode leaf = node.getLeaf();
    if (!(leaf instanceof SGComponent)) return null;
    return (SGComponent)leaf;
  }
  
  /**
   * Returns the AWT/Swing component, of the given type, attached to the given node.
   * @param <T> the generic type of the given component type to match.
   * @param node the given node.
   * @param type the type of component to match and return.
   * @return the AWT/Swing component, of the given type, attached to the given node, or <code>null</code> if the node 
   * does not have any component attached to it.
   */
  public static <T extends Component> T componentInNode(SGComponent node, Class<T> type) {
    Component c = node.getComponent();
    if (type.isInstance(c)) return type.cast(c);
    if (c instanceof JScrollPane) return componentIn((JScrollPane)c, type);
    return null;
  }
  
  private static <T extends Component> T componentIn(JScrollPane scrollPane, Class<T> type) {
    JViewport viewport = scrollPane.getViewport();
    if (viewport == null) return null;
    Component c = viewport.getComponent(0);
    if (type.isInstance(c)) return type.cast(c);
    return null;
  }
  
  /**
   * Returns the text node attached to the given JavaFX node.
   * @param node the given node.
   * @return the text node attached to the given node, or <code>null</code> if the node does not have any.
   */
  public static SGText textBoxIn(FXNode node) {
    SGNode leaf = node.getLeaf();
    if (!(leaf instanceof SGText)) return null;
    return (SGText)leaf;
  }
  
  /**
   * Returns the image node attached to the given JavaFX node.
   * @param node the given node.
   * @return the image node attached to the given node, or <code>null</code> if the node does not have any.
   */
  public static SGImage imageIn(FXNode node) {
    SGNode leaf = node.getLeaf();
    if (!(leaf instanceof SGImage)) return null;
    return (SGImage)leaf;
  }

  private Nodes() {}
}
