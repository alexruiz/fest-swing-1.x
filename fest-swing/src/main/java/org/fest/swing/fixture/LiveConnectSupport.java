package org.fest.swing.fixture;

import java.awt.Point;
import java.io.File;

import org.fest.swing.core.*;
import org.fest.swing.data.TableCell;

/**
 * Understands support methods/members required to interact through LiveConnect.
 * Certain functions require references to created java objects to work. Client
 * side JavaScript code can then use these references with requests back to the
 * applet.
 *
 * @author Mel Llaguno
 */
public interface LiveConnectSupport {

	/**
	 * A factory method for creating the currentKeyPressInfo;
	 * @param keyCode The ASCII KeyCode
	 * @param modifier The bit-mask modifier
	 * @return KeyPressInfo the object used to simulate a key press
	 */
	KeyPressInfo createKeyPressInfo(int keyCode, int modifier);

	/**
	 * A factory method for creating a Point
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return Point the object used to specify a location
	 */
	Point createPoint(int x, int y);

	/**
	 * A factory method for creating a MouseButton object
	 * @param buttonMask the integer representation of a button mask
	 * @return MouseButton the object used to simulate a mouse button
	 */
	MouseButton createMouseButton(int buttonMask);

	/**
	 * A factory method for creating a File object
	 * @param name the path name of the file
	 * @return File
	 */
	File createFile(String name);

	/**
	 * A factory method for creating a TableCell
	 * @param row the row of the cell
	 * @param column the column of the cell
	 * @return TableCell the object used to specify a cell location within a table
	 */
	TableCell createTableCell(int row, int column);

	/**
	 * A factory method for creating a TableCell Array
	 * @param objects the objects to be converted to individual TableCell objects
	 * @return TableCell[] the array of TableCells
	 */
	TableCell[] createTableCells(Object...objects);

	/**
	 * A factory method for create a String []
	 * @param objects
	 * @return String [] the reference to the String Array
	 */
	String [] createStringArray(Object...objects);
}
