 
package org.javasurf.base;

/**
 * Generic interface for a class that calculates the convolution of an image
 * with an Haar Filter.
 * 
 * @author Alessandro Martini, Claudio Fantacci
 */

public interface IHAARconvolution {

	/**
	 * Computes the response in x-direction and returns the value of the (x,y)
	 * point for a filter proportional to the interest point detected scale.
	 * 
	 * @param x
	 * @param y
	 * @param _scale
	 *            Detected Interest Point scale.
	 * @return float Haar Convolution value in the x-direction for the specified
	 *         Interest Point.
	 */
	public float makeConvolutionDx(int x, int y, float _scale);

	/**
	 * Computes the response in y-direction and returns the value of the (x,y)
	 * point for a filter proportional to the interest point detected scale.
	 * 
	 * @param x
	 * @param y
	 * @param _scale
	 *            Detected Interest Point scale.
	 * @return float Haar Convolution value in the y-direction for the specified
	 *         Interest Point.
	 */
	public float makeConvolutionDy(int x, int y, float _scale);

}
