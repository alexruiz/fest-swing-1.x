/*
 * Created on Jan 17, 2009
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
package org.fest.javafx.desktop.fixture;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.sun.scenario.scenegraph.SGImage;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.assertions.ImageAssert;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.core.NodeMatcher;
import org.fest.javafx.desktop.exception.NodeLookupException;

import static org.fest.javafx.desktop.matcher.ImageNodeMatcher.withId;
import static org.fest.javafx.desktop.util.Nodes.*;

/**
 * Understands how to simulate user input on a JavaFX image node and verifies the state of such node.
 *
 * @author Alex Ruiz
 */
public class ImageFixture extends NodeFixture {

  private final SGImage image;

  /**
   * Creates a new </code>{@link ImageFixture}</code>.
   * @param robot simulates user input.
   * @param id the id of the node to find.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public ImageFixture(JavaFxRobot robot, String id) {
    this(robot, withId(id));
  }

  /**
   * Creates a new </code>{@link ImageFixture}</code>.
   * @param robot simulates user input.
   * @param matcher contains the search criteria to look up the node to be used in this fixture.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public ImageFixture(JavaFxRobot robot, NodeMatcher matcher) {
    super(robot, matcher);
    image = imageInNode();
  }

  /**
   * Creates a new </code>{@link ImageFixture}</code>.
   * @param robot simulates user input.
   * @param node the node that contains an attached JavaFX image node.
   */
  public ImageFixture(JavaFxRobot robot, FXNode node) {
    super(robot, node);
    image = imageIn(node);
  }

  private SGImage imageInNode() {
    SGImage i = imageIn(node());
    if (i == null) throw new NodeLookupException("The node in this fixture does not have an attached image node");
    return i;
  }
  
  /**
   * Returns the image in this fixture's image node as a <code>{@link BufferedImage}</code>. You can use FEST-Assert's
   * <code>{@link ImageAssert}</code> to verify basic properties of the returned image.
   * @return the image in this fixture's image node, or <code>null</code> if the image node does not have an image or
   * if its image is not a <code>BufferedImage</code>.
   */
  public BufferedImage image() {
    Image i = image.getImage();
    if (i instanceof BufferedImage) return (BufferedImage)i;
    return null;
  }
  
  /**
   * Drags the image node in this fixture and drops it at the given coordinates, relative to the panel containing the
   * JavaFX UI. After the drag and drop operation is complete, the <b>center</b> of this fixture's image node will be at 
   * the given coordinates.
   * @param p the coordinates, relative to the panel containing the JavaFX UI, where to drop the image node in this
   * fixture.
   * @return this fixture.
   */
  public ImageFixture dragAndDropTo(Point p) {
    FXNode node = node();
    dragAndDrop().drag(node, centerOf(node));
    dragAndDrop().drop(node.getPanel(), p);
    return this;
  }
}
