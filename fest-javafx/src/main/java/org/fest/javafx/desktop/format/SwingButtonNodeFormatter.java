/*
 * Created on Feb 19, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.format;

import javax.swing.JButton;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.format.IntrospectionComponentFormatter;

import static org.fest.javafx.desktop.util.Nodes.componentInNode;
import static org.fest.util.Strings.*;

/**
 * Understands a formatter for <code>SwingButton</code>s.
 *
 * @author Yvonne Wang
 */
public class SwingButtonNodeFormatter implements NodeFormatter {

  private final IntrospectionComponentFormatter componentFormatter = new IntrospectionComponentFormatter(JButton.class,
      "text", "enabled");

  /**
   * Returns the <code>String</code> representation of the given <code>SwingButton</code>.
   * @param node the <code>SwingButton</code> node.
   * @return  the <code>String</code> representation of the given <code>SwingButton</code>.
   */
  public String format(FXNode node) {
    JButton button = componentInNode(node, JButton.class);
    return concat(
        "JavaFX SwingButton[",
        "id=", quote(node.getLeaf().getID()), ", ",
        "button=[", componentFormatter.format(button), "]",
        "]"
    );
  }

  /**
   * Indicates that this formatter supports <code>SwingButton</code>.
   * @return <code>JButton</code>.
   */
  public Class<?> supportedType() {
    return JButton.class;
  }
}
