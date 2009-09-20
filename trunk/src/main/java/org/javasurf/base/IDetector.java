 

package org.javasurf.base;

import java.util.ArrayList;

/**
 * Generic interface for a IDetector class.
 * 
 * @author Alessandro Martini, Claudio Fantacci
 */

public interface IDetector {

	/**
	 * Detects all the Interest Points in a given image and then returns them as
	 * an ArrayList.
	 * 
	 * @return An ArrayList of Interest_Point
	 */
	public ArrayList<InterestPoint> generateInterestPoints();

}
