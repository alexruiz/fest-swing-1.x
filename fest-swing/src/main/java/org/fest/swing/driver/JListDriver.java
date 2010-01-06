/*
 * Created on Jan 19, 2008
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
package org.fest.swing.driver;

import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.util.Arrays.sort;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.fest.swing.awt.AWT.visibleCenterOf;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.driver.CommonValidations.validateCellReader;
import static org.fest.swing.driver.JListContentQuery.contents;
import static org.fest.swing.driver.JListItemCountQuery.itemCountIn;
import static org.fest.swing.driver.JListItemIndexValidator.validateIndices;
import static org.fest.swing.driver.JListItemValueQuery.itemValue;
import static org.fest.swing.driver.JListMatchingItemQuery.centerOfMatchingItemCell;
import static org.fest.swing.driver.JListMatchingItemQuery.matchingItemIndex;
import static org.fest.swing.driver.JListMatchingItemQuery.matchingItemIndices;
import static org.fest.swing.driver.JListMatchingItemQuery.matchingItemValues;
import static org.fest.swing.driver.JListScrollToItemTask.ITEM_NOT_FOUND;
import static org.fest.swing.driver.JListScrollToItemTask.scrollToItem;
import static org.fest.swing.driver.JListScrollToItemTask.scrollToItemIfNotSelectedYet;
import static org.fest.swing.driver.JListSelectedIndexQuery.selectedIndexOf;
import static org.fest.swing.driver.JListSelectionIndicesQuery.selectedIndices;
import static org.fest.swing.driver.JListSelectionValueQuery.NO_SELECTION_VALUE;
import static org.fest.swing.driver.JListSelectionValueQuery.singleSelectionValue;
import static org.fest.swing.driver.JListSelectionValuesQuery.selectionValues;
import static org.fest.swing.driver.TextAssert.verifyThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.util.Arrays.isEmptyIntArray;
import static org.fest.util.Arrays.format;
import static org.fest.util.Strings.concat;

import java.awt.Point;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JList;
import javax.swing.JPopupMenu;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.LocationUnavailableException;
import org.fest.swing.util.Pair;
import org.fest.swing.util.PatternTextMatcher;
import org.fest.swing.util.StringTextMatcher;
import org.fest.swing.util.TextMatcher;
import org.fest.swing.util.Range.From;
import org.fest.swing.util.Range.To;

/**
 * Understands functional testing of <code>{@link JList}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListDriver extends JComponentDriver {

  private static final String SELECTED_INDICES_PROPERTY = "selectedIndices";
  private static final String SELECTED_INDEX_PROPERTY = "selectedIndex";

  private JListCellReader cellReader;

  /**
   * Creates a new </code>{@link JListDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JListDriver(Robot robot) {
    super(robot);
    cellReader(new BasicJListCellReader());
  }

  /**
   * Returns an array of <code>String</code>s that represents the contents of the given <code>{@link JList}</code>,
   * using this driver's <code>{@link JListCellReader}</code>.
   * @param list the target <code>JList</code>.
   * @return an array of <code>String</code>s that represents the contents of the given <code>JList</code>.
   * @see #cellReader(JListCellReader)
   */
  @RunsInEDT
  public String[] contentsOf(JList list) {
    return contents(list, cellReader);
  }

  /**
   * Selects the items matching the given values.
   * @param list the target <code>JList</code>.
   * @param values the values to match. Each <code>String</code> can be a regular expression.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the any of the given values cannot be found.
   */
  @RunsInEDT
  public void selectItems(JList list, String[] values) {
    selectItems(list, new StringTextMatcher(values));
  }

  /**
   * Selects the items matching the given regular expression patterns.
   * @param list the target <code>JList</code>.
   * @param patterns the regular expression patterns to match.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws NullPointerException if any of the regular expression patterns is <code>null</code>.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the any of the given regular expression patterns cannot
   * be found.
   * @since 1.2
   */
  @RunsInEDT
  public void selectItems(JList list, Pattern[] patterns) {
    selectItems(list, new PatternTextMatcher(patterns));
  }

  @RunsInEDT
  private void selectItems(final JList list, final TextMatcher matcher) {
    final List<Integer> indices = matchingItemIndices(list, matcher, cellReader);
    if (indices.isEmpty()) throw failMatchingNotFound(list, matcher);
    clearSelection(list);
    new MultipleSelectionTemplate(robot) {
      int elementCount() { return indices.size(); }
      void selectElement(int index) { selectItem(list, indices.get(index)); }
    }.multiSelect();
  }

  /**
   * Selects the item in the given <code>{@link JList}</code> whose value matches the given one.
   * @param list the target <code>JList</code>.
   * @param value the value to match.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   */
  @RunsInEDT
  public void selectItem(JList list, String value) {
    selectItem(list, new StringTextMatcher(value));
  }

  /**
   * Selects the item in the given <code>{@link JList}</code> whose value matches the given regular expression pattern.
   * @param list the target <code>JList</code>.
   * @param pattern the regular expression to match.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @since 1.2
   */
  @RunsInEDT
  public void selectItem(JList list, Pattern pattern) {
    selectItem(list, new PatternTextMatcher(pattern));
  }

  @RunsInEDT
  private void selectItem(JList list, TextMatcher matcher) {
    Pair<Integer, Point> scrollInfo = scrollToItemIfNotSelectedYet(list, matcher, cellReader);
    robot.waitForIdle();
    verify(list, scrollInfo, matcher);
    if (scrollInfo.ii == null) return; // already selected cell.
    robot.click(list, cellCenterIn(scrollInfo));
  }

  /**
   * Clicks the first item matching the given value, using the specified mouse button, the given number times.
   * @param list the target <code>JList</code>.
   * @param value the value to match.
   * @param button the button to use.
   * @param times the number of times to click.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   */
  public void clickItem(JList list, String value, MouseButton button, int times) {
    clickItem(list, new StringTextMatcher(value), button, times);
  }

  /**
   * Clicks the first item matching the given regular expression pattern, using the specified mouse button, the given
   * number times.
   * @param list the target <code>JList</code>.
   * @param pattern the regular expression pattern to match.
   * @param button the button to use.
   * @param times the number of times to click.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws LocationUnavailableException if an element matching the given regular expression pattern cannot be found.
   * @since 1.2
   */
  public void clickItem(JList list, Pattern pattern, MouseButton button, int times) {
    clickItem(list, new PatternTextMatcher(pattern), button, times);
  }

  private void clickItem(JList list, TextMatcher matcher, MouseButton button, int times) {
    Pair<Integer, Point> scrollInfo = scrollToItem(list, matcher, cellReader);
    robot.waitForIdle();
    verify(list, scrollInfo, matcher);
    robot.click(list, cellCenterIn(scrollInfo), button, times);
  }

  /**
   * Selects the items under the given indices.
   * @param list the target <code>JList</code>.
   * @param indices the indices of the items to select.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices is negative or greater than the index of the last item in
   * the <code>JList</code>.
   */
  public void selectItems(final JList list, final int[] indices) {
    validateArrayOfIndices(indices);
    clearSelection(list);
    new MultipleSelectionTemplate(robot) {
      int elementCount() { return indices.length; }
      void selectElement(int index) { selectItem(list, indices[index]); }
    }.multiSelect();
  }

  /**
   * Clears the selection in the given <code>{@link JList}</code>. Since this method does not simulate user input, it
   * does not verifies that the <code>JList</code> is enabled and showing.
   * @param list the target <code>JList</code>.
   * @since 1.2
   */
  public void clearSelection(JList list) {
    clearSelectionOf(list);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void clearSelectionOf(final JList list) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        list.clearSelection();
      }
    });
  }

  /**
   * Selects the items in the specified range.
   * @param list the target <code>JList</code>.
   * @param from the starting point of the selection.
   * @param to the last item to select.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the any index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  @RunsInEDT
  public void selectItems(JList list, From from, To to) {
    selectItems(list, from.value, to.value);
  }

  /**
   * Selects the items in the specified range.
   * @param list the target <code>JList</code>.
   * @param start the starting point of the selection.
   * @param end the last item to select (inclusive.)
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the any index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  @RunsInEDT
  public void selectItems(JList list, int start, int end) {
    validateIndicesAndClearSelection(list, start, end);
    selectItem(list, start);
    robot.pressKey(VK_SHIFT);
    try {
      clickItem(list, end, LEFT_BUTTON, 1);
    } finally {
      robot.releaseKey(VK_SHIFT);
    }
  }

  @RunsInEDT
  private static void validateIndicesAndClearSelection(final JList list, final int...indices) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        validateIndices(list, indices);
        list.clearSelection();
      }
    });
  }

  /**
   * Selects the item under the given index using left mouse button once.
   * @param list the target <code>JList</code>.
   * @param index the index of the item to click.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  @RunsInEDT
  public void selectItem(JList list, int index) {
    Point cellCenter = scrollToItemIfNotSelectedYet(list, index);
    robot.waitForIdle();
    if (cellCenter == null) return; // cell already selected
    robot.click(list, cellCenter);
  }

  /**
   * Clicks the item under the given index, using the specified mouse button, the given number times.
   * @param list the target <code>JList</code>.
   * @param index the index of the item to click.
   * @param button the button to use.
   * @param times the number of times to click.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  @RunsInEDT
  public void clickItem(JList list, int index, MouseButton button, int times) {
    Point cellCenter = scrollToItem(list, index);
    robot.waitForIdle();
    robot.click(list, cellCenter, button, times);
  }

  /**
   * Verifies that the selected item in the <code>{@link JList}</code> matches the given value.
   * @param list the target <code>JList</code>.
   * @param value the value to match. It can be a regular expression pattern.
   * @throws AssertionError if the selected item does not match the value.
   * @see #cellReader(JListCellReader)
   */
  @RunsInEDT
  public void requireSelection(final JList list, String value) {
    String selection = requiredSelection(list);
    verifyThat(selection).as(selectedIndexProperty(list)).isEqualOrMatches(value);
  }

  /**
   * Verifies that the selected item in the <code>{@link JList}</code> matches the given regular expression pattern.
   * @param list the target <code>JList</code>.
   * @param pattern the regular expression pattern to match.
   * @throws AssertionError if the selected item does not match the given regular expression pattern.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  @RunsInEDT
  public void requireSelection(JList list, Pattern pattern) {
    String selection = requiredSelection(list);
    verifyThat(selection).as(selectedIndexProperty(list)).matches(pattern);
  }

  private String requiredSelection(final JList list) {
    Object selection = singleSelectionValue(list, cellReader);
    if (NO_SELECTION_VALUE == selection) failNoSelection(list);
    return (String)selection;
  }

  /**
   * Verifies that the selected index in the <code>{@link JList}</code> matches the given value.
   * @param list the target <code>JList</code>.
   * @param index the selection index to match.
   * @throws AssertionError if the selected index does not match the value.
   * @since 1.2
   */
  @RunsInEDT
  public void requireSelection(final JList list, int index) {
    int selectedIndex = selectedIndexOf(list);
    if (selectedIndex == -1) failNoSelection(list);
    assertThat(selectedIndex).as(selectedIndexProperty(list)).isEqualTo(index);
  }

  /**
   * Returns an array of <code>String</code>s that represents the selection in the given <code>{@link JList}</code>,
   * using this driver's <code>{@link JListCellReader}</code>.
   * @param list the target <code>JList</code>.
   * @return an array of <code>String</code>s that represents the selection in the given <code>JList</code>.
   * @see #cellReader(JListCellReader)
   */
  @RunsInEDT
  public String[] selectionOf(JList list) {
    List<String> selection = selectionValues(list, cellReader);
    return selection.toArray(new String[selection.size()]);
  }

  /**
   * Verifies that the selected items in the <code>{@link JList}</code> match the given values.
   * @param list the target <code>JList</code>.
   * @param items the values to match. Each value can be a regular expression pattern.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws AssertionError if the selected items do not match the given values.
   */
  @RunsInEDT
  public void requireSelectedItems(JList list, String... items) {
    requireSelectedItems(list, new StringTextMatcher(items));
  }

  /**
   * Verifies that the selected items in the <code>{@link JList}</code> match the given regular expression patterns.
   * @param list the target <code>JList</code>.
   * @param patterns the regular expression patterns to match.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws NullPointerException if any of the patterns in the array is <code>null</code>.
   * @throws AssertionError if the selected items do not match the given values.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  @RunsInEDT
  public void requireSelectedItems(JList list, Pattern... patterns) {
    requireSelectedItems(list, new PatternTextMatcher(patterns));
  }

  @RunsInEDT
  private void requireSelectedItems(JList list, TextMatcher matcher) {
    List<String> matchingValues = matchingItemValues(list, matcher, cellReader);
    assertThat(selectionValues(list, cellReader)).as(propertyName(list, SELECTED_INDICES_PROPERTY)).isEqualTo(matchingValues);
  }

  /**
   * Verifies that the given item indices are selected in the <code>{@link JList}</code>.
   * @param list the target <code>JList</code>.
   * @param indices the expected indices of the selected items.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws AssertionError if the selection in the <code>JList</code> does not match the given one.
   */
  @RunsInEDT
  public void requireSelectedItems(JList list, int... indices) {
    validateArrayOfIndices(indices);
    sort(indices);
    assertThat(selectedIndices(list)).as(propertyName(list, SELECTED_INDICES_PROPERTY)).isEqualTo(indices);
  }

  private void validateArrayOfIndices(int[] indices) {
    if (indices == null) throw new NullPointerException("The array of indices should not be null");
    if (isEmptyIntArray(indices)) throw new IllegalArgumentException("The array of indices should not be empty");
  }

  /**
   * Verifies that the <code>{@link JList}</code> does not have a selection.
   * @param list the target <code>JList</code>.
   * @throws AssertionError if the <code>JList</code> has a selection.
   */
  @RunsInEDT
  public void requireNoSelection(JList list) {
    assertThat(selectedIndexOf(list)).as(selectedIndexProperty(list)).isEqualTo(-1);
  }

  @RunsInEDT
  private void failNoSelection(JList list) {
    fail(concat("[", selectedIndexProperty(list).value(), "] No selection"));
  }

  @RunsInEDT
  private Description selectedIndexProperty(JList list) {
    return propertyName(list, SELECTED_INDEX_PROPERTY);
  }

  /**
   * Starts a drag operation at the location of the first item matching the given value.
   * @param list the target <code>JList</code>.
   * @param value the value to match. It can be a regular expression.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @see #cellReader(JListCellReader)
   */
  @RunsInEDT
  public void drag(JList list, String value) {
    drag(list, new StringTextMatcher(value));
  }

  /**
   * Starts a drag operation at the location of the first item matching the given regular expression pattern.
   * @param list the target <code>JList</code>.
   * @param pattern the regular expression pattern to match.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws NullPointerException if the regular expression pattern is <code>null</code>.
   * @throws LocationUnavailableException if an element matching the given regular expression pattern cannot be found.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  @RunsInEDT
  public void drag(JList list, Pattern pattern) {
    drag(list, new PatternTextMatcher(pattern));
  }

  private void drag(JList list, TextMatcher matcher) {
    Pair<Integer, Point> scrollInfo = scrollToItem(list, matcher, cellReader);
    robot.waitForIdle();
    verify(list, scrollInfo, matcher);
    super.drag(list, cellCenterIn(scrollInfo));
  }

  /**
   * Ends a drag operation at the location of the first item matching the given value.
   * @param list the target <code>JList</code>.
   * @param value the value to match. It can be a regular expression.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  @RunsInEDT
  public void drop(JList list, String value) {
    drop(list, new StringTextMatcher(value));
  }

  /**
   * Ends a drag operation at the location of the first item matching the given regular expression pattern.
   * @param list the target <code>JList</code>.
   * @param pattern the regular expression pattern to match.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @throws ActionFailedException if there is no drag action in effect.
   * @since 1.2
   */
  public void drop(JList list, Pattern pattern) {
    drop(list, new PatternTextMatcher(pattern));
  }

  private void drop(JList list, TextMatcher matcher) {
    Pair<Integer, Point> scrollInfo = scrollToItem(list, matcher, cellReader);
    robot.waitForIdle();
    verify(list, scrollInfo, matcher);
    super.drop(list, cellCenterIn(scrollInfo));
  }

  private void verify(JList list, Pair<Integer, Point> scrollInfo, TextMatcher matcher) {
    if (ITEM_NOT_FOUND.equals(scrollInfo)) throw failMatchingNotFound(list, matcher);
  }

  /**
   * Starts a drag operation at the location of the given index.
   * @param list the target <code>JList</code>.
   * @param index the given index.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  @RunsInEDT
  public void drag(JList list, int index) {
    Point cellCenter = scrollToItem(list, index);
    robot.waitForIdle();
    super.drag(list, cellCenter);
  }

  /**
   * Ends a drag operation at the location of the given index.
   * @param list the target <code>JList</code>.
   * @param index the given index.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  @RunsInEDT
  public void drop(JList list, int index) {
    Point cellCenter = scrollToItem(list, index);
    robot.waitForIdle();
    super.drop(list, cellCenter);
  }


  /**
   * Ends a drag operation at the center of the <code>{@link JList}</code>.
   * @param list the target <code>JList</code>.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  @RunsInEDT
  public void drop(JList list) {
    assertIsEnabledAndShowing(list);
    super.drop(list, visibleCenterOf(list));
  }

  /**
   * Shows a pop-up menu at the location of the specified item in the <code>{@link JList}</code>.
   * @param list the target <code>JList</code>.
   * @param value the value to match. It can be a regular expression pattern.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   */
  @RunsInEDT
  public JPopupMenu showPopupMenu(JList list, String value) {
    return showPopupMenu(list, new StringTextMatcher(value));
  }

  /**
   * Shows a pop-up menu at the location of the specified item in the <code>{@link JList}</code>.
   * @param list the target <code>JList</code>.
   * @param pattern the regular expression pattern to match.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws NullPointerException if the regular expression pattern is <code>null</code>.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @since 1.2
   */
  @RunsInEDT
  public JPopupMenu showPopupMenu(JList list, Pattern pattern) {
    return showPopupMenu(list, new PatternTextMatcher(pattern));
  }

  @RunsInEDT
  private JPopupMenu showPopupMenu(JList list, TextMatcher matcher) {
    Pair<Integer, Point> scrollInfo = scrollToItem(list, matcher, cellReader);
    robot.waitForIdle();
    verify(list, scrollInfo, matcher);
    return robot.showPopupMenu(list, cellCenterIn(scrollInfo));
  }

  private Point cellCenterIn(Pair<Integer, Point> scrollInfo) {
    return scrollInfo.ii;
  }

  /**
   * Shows a pop-up menu at the location of the specified item in the <code>{@link JList}</code>.
   * @param list the target <code>JList</code>.
   * @param index the index of the item.
   * @return a driver that manages the displayed pop-up menu.
   * @throws IllegalStateException if the <code>JList</code> is disabled.
   * @throws IllegalStateException if the <code>JList</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  @RunsInEDT
  public JPopupMenu showPopupMenu(JList list, int index) {
    Point cellCenter = scrollToItem(list, index);
    robot.waitForIdle();
    return robot.showPopupMenu(list, cellCenter);
  }

  /**
   * Returns the coordinates of the first item matching the given value.
   * @param list the target <code>JList</code>.
   * @param value the value to match.
   * @return the coordinates of the item at the given item.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   */
  @RunsInEDT
  public Point pointAt(JList list, String value) {
    return centerOfMatchingItemCell(list, value, cellReader);
  }

  /**
   * Returns the index of the first item matching the given value.
   * @param list the target <code>JList</code>
   * @param value the value to match. It can be a regular expression.
   * @return the index of the first item matching the given value.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   */
  @RunsInEDT
  public int indexOf(JList list, String value) {
    return indexOf(list, new StringTextMatcher(value));
  }

  /**
   * Returns the index of the first item matching the given regular expression pattern.
   * @param list the target <code>JList</code>.
   * @param pattern the regular expression pattern to match.
   * @return the index of the first item matching the given regular expression pattern.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @since 1.2
   */
  @RunsInEDT
  public int indexOf(JList list, Pattern pattern) {
    return indexOf(list, new PatternTextMatcher(pattern));
  }

  @RunsInEDT
  private int indexOf(JList list, TextMatcher matcher) {
    int index = itemIndex(list, matcher, cellReader);
    if (index >= 0) return index;
    throw failMatchingNotFound(list, matcher);
  }

  @RunsInEDT
  private static int itemIndex(final JList list, final TextMatcher matcher, final JListCellReader cellReader) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        return matchingItemIndex(list, matcher, cellReader);
      }
    });
  }

  private LocationUnavailableException failMatchingNotFound(JList list, TextMatcher matcher) {
    throw new LocationUnavailableException(concat(
        "Unable to find item matching the ", matcher.description(), " ", matcher.formattedValues(),
        " among the JList contents ", format(contents(list, cellReader))));
  }

  /**
   * Returns the <code>String</code> representation of the element under the given index, using this driver's
   * <code>{@link JListCellReader}</code>.
   * @param list the target <code>JList</code>.
   * @param index the given index.
   * @return the value of the element under the given index.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   * @see #cellReader(JListCellReader)
   */
  @RunsInEDT
  public String value(JList list, int index) {
    return itemValue(list, index, cellReader);
  }

  /**
   * Updates the implementation of <code>{@link JListCellReader}</code> to use when comparing internal values of a
   * <code>{@link JList}</code> and the values expected in a test.
   * @param newCellReader the new <code>JListCellValueReader</code> to use.
   * @throws NullPointerException if <code>newCellReader</code> is <code>null</code>.
   */
  public void cellReader(JListCellReader newCellReader) {
    validateCellReader(newCellReader);
    cellReader = newCellReader;
  }

  /**
   * Verifies that number of items in the given <code>{@link JList}</code> is equal to the expected one.
   * @param list the target <code>JList</code>.
   * @param expected the expected number of items.
   * @throws AssertionError if the number of items in the given <code>{@link JList}</code> is not equal to the expected
   * one.
   * @since 1.2
   */
  @RunsInEDT
  public void requireItemCount(JList list, int expected) {
    int actual = itemCountIn(list);
    assertThat(actual).as(propertyName(list, "itemCount")).isEqualTo(expected);
  }
}
