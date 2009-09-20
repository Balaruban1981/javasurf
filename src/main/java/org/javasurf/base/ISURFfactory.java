 

package org.javasurf.base;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Basic interface for a class that provides the right IDetector and IDescriptor
 * class.
 * 
 * @author Alessandro Martini, Claudio Fantacci
 */

public interface ISURFfactory {

	/**
	 * Create the correct IDetector.
	 * 
	 * @return A IDetector object.
	 */
	public IDetector createDetector();

	/**
	 * Create the correct IDescriptor.
	 * 
	 * @param interest_points
	 *            ArrayList of Interest Point on which the descriptor will
	 *            compute the descriptors.
	 * 
	 * @return A IDescriptor object.
	 */
	public IDescriptor createDescriptor(ArrayList<InterestPoint> interest_points);

	/**
	 * Getter for the Integral Image.
	 * 
	 * @return The Integral Image used by SURF.
	 */
	public IIntegralImage getIntegralImage();

	/**
	 * Setter for the Integral Image.
	 * 
	 * @param img
	 *            PImage that is converted into Integral Image before it's
	 *            assigned to SURF.
	 */
	public void setIntegralImage(BufferedImage img);

	/**
	 * Getter for the Balance Value.
	 * 
	 * @return The Balance Value. Its default value is 0.9.
	 */
	public float getBalanceValue();

	/**
	 * Setter for the Balance Value.
	 * 
	 * @param balanceValue
	 *            Value used to calculate the Laplacian of Gaussian response.
	 */
	public void setBalanceValue(float balanceValue);

	/**
	 * Getter for the threshold.
	 * 
	 * @return The threshold value.
	 */
	public float getThreshold();

	/**
	 * Setter for the threshold.
	 * 
	 * @param threshold
	 *            Value used to filter the Laplacian of Gaussian.
	 */
	public void setThreshold(float threshold);

	/**
	 * Getter for the octaves.
	 * 
	 * @return Number of octaves computed in SURF.
	 */
	public int getOctaves();

	/**
	 * Setter for the octaves.
	 * 
	 * @param octaves
	 *            Number of octaves that you want to compute.
	 */
	public void setOctaves(int octaves);

}
