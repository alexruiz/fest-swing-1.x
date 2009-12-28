/*
 * Created on Dec 26, 2009
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
package org.fest.swing.fixture;

import javax.swing.JTree;

import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands functional testing of single nodes, referenced by their row indices, in <code>{@link JTree}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class JTreeRowFixture implements JTreeNodeFixture {

  private final int index;
  private final JTreeFixture tree;

  /**
   * Creates a new </code>{@link JTreeRowFixture}</code>.
   * @param tree handles the <code>JTree</code> containing the node with the given row index.
   * @param index the given row index.
   */
  protected JTreeRowFixture(JTreeFixture tree, int index) {
    this.tree = tree;
    this.index = index;
  }

  /**
   * Simulates a user expanding this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   * @throws ActionFailedException if this method fails to expand the row.
   */
  public JTreeRowFixture expand() {
    tree.expandRow(index);
    return this;
  }

  /**
   * Simulates a user collapsing this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   * @throws ActionFailedException if this method fails to collapse the row.
   */
  public JTreeRowFixture collapse() {
    tree.collapseRow(index);
    return this;
  }

  /**
   * Selects the this fixture's tree node, expanding parent nodes if necessary. This method will not click the node if
   * it is already selected.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTree</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTree</code> is not showing on the screen.
   */
  public JTreeRowFixture select() {
    tree.selectRow(index);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   */
  public JTreeRowFixture click() {
    tree.clickRow(index);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's tree node.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given button is <code>null</code>.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   */
  public JTreeRowFixture click(MouseButton button) {
    tree.clickRow(index, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's tree node.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   */
  public JTreeRowFixture click(MouseClickInfo mouseClickInfo) {
    tree.clickRow(index, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   */
  public JTreeRowFixture doubleClick() {
    tree.doubleClickRow(index);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   */
  public JTreeRowFixture rightClick() {
    tree.rightClickRow(index);
    return this;
  }

  /**
   * Simulates a user dragging this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   */
  public JTreeRowFixture drag() {
    tree.drag(index);
    return this;
  }

  /**
   * Simulates a user dropping relative to this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public JTreeRowFixture drop() {
    tree.drop(index);
    return this;
  }

  /**
   * Shows a pop-up menu using this fixture's tree node as the invoker of the pop-up menu.
   * @return a fixture that handles functional testing of the displayed pop-up menu.
   * @throws IllegalStateException if the <code>JTree</code> is disabled.
   * @throws IllegalStateException if the <code>JTree</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return tree.showPopupMenuAt(index);
  }

  /**
   * Returns the <code>String</code> representation of this fixture's tree node.
   * @return the <code>String</code> representation of this fixture's tree node.
   */
  public String value() {
    return tree.valueAt(index);
  }

  /**
   * Returns the row index of the node.
   * @return the row index of the node.
   */
  public int index() {
    return index;
  }
}
