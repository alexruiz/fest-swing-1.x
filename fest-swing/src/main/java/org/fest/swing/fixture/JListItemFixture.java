/*
 * Created on Dec 8, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseButton.RIGHT_BUTTON;

import javax.swing.JList;

import org.fest.swing.cell.JListCellReader;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands functional testing of single rows in <code>{@link JList}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JListItemFixture implements ItemFixture {

  final JListFixture list;
  final int index;

  /**
   * Creates a new </code>{@link JListItemFixture}</code>.
   * @param list manages the <code>JList</code> containing the list item to be managed by this fixture.
   * @param index index of the list item to be managed by this fixture.
   * @throws NullPointerException if <code>list</code> is {@code null}.
   */
  public JListItemFixture(JListFixture list, int index) {
    if (list == null) throw new NullPointerException("The given JListFixture should not be null");
    this.list = list;
    this.index = index;
  }

  /**
   * Simulates a user selecting this fixture's list item.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public final JListItemFixture select() {
    list.selectItem(index);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's list item.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public final JListItemFixture click() {
    list.clickItem(index);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's list item.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public final JListItemFixture click(MouseButton button) {
    list.clickItem(index, button, 1);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's list item.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public final JListItemFixture click(MouseClickInfo mouseClickInfo) {
    list.clickItem(index, mouseClickInfo.button(), mouseClickInfo.times());
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's list item.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public final JListItemFixture doubleClick() {
    list.clickItem(index, LEFT_BUTTON, 2);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's list item.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public final JListItemFixture rightClick() {
    list.clickItem(index, RIGHT_BUTTON, 1);
    return this;
  }

  /**
   * Shows a pop-up menu using this fixture's list item as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public final JPopupMenuFixture showPopupMenu() {
    return list.showPopupMenuAt(index);
  }

  /**
   * Returns the <code>String</code> representation of the value of this fixture's list item, using the
   * <code>{@link JListCellReader}</code> from the <code>{@link JListFixture}</code> that created this
   * <code>{@link JListItemFixture}</code>.
   * @return the <code>String</code> representation of the value of this fixture's list item.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   * @see JListFixture#cellReader(JListCellReader)
   */
  public final String value() {
    return list.valueAt(index);
  }

  /**
   * Simulates a user dragging this fixture's list item.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public final JListItemFixture drag() {
    list.drag(index);
    return this;
  }

  /**
   * Simulates a user dropping into this fixture's list item.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if this item's index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public final JListItemFixture drop() {
    list.drop(index);
    return this;
  }

  /**
   * Returns the index of this fixture's list item.
   * @return the index of this fixture's list item.
   */
  public final int index() { return index; }
}
