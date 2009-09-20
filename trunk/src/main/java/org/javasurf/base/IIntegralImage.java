 

package org.javasurf.base;

/**
 * Generic interface for a class that converts an image to its integral version.
 * 
 * @author Alessandro Martini, Claudio Fantacci
 */

public interface IIntegralImage {

	/**
	 * Given the coordinates of a point of the image, returns its integral
	 * value, ie the intensity of the rectangle whose vertices are the origin
	 * and (x, y).
	 * 
	 * @param x
	 * 			x-coordinate
	 * @param y
	 * 			y-coordinate
	 * @return The integral value of the given point.
	 */
	public float getIntegralValue(int x, int y);

	/**
	 * The method needs only A and D, i.e. the upper left and bottom right vertex coordinates.
	 * 
	 * @param xA
	 *            x-coordinate of vertex A.
	 * @param yA
	 *            y-coordinate of vertex A.
	 * @param xD
	 *            x-coordinate of vertex D.
	 * @param yD
	 *            y-coordinate of vertex D.
	 * @return The integral value of the square.
	 */
	public float getIntegralSquare(int xA, int yA, int xD, int yD);

}
