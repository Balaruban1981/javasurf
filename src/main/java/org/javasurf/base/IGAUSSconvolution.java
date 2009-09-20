 
package org.javasurf.base;

/**
 * Generic interface for a class that calculates the convolution of an image
 * with a Gaussian Filter.
 * 
 * @author Alessandro Martini, Claudio Fantacci
 */

public interface IGAUSSconvolution {

	/**
	 * Computes the Gaussian second oder partial derivative in in x-direction
	 * and returns the value of the (x,y) point for a filter large as specified.
	 * 
	 * @param x
	 * @param y
	 * @param filtersize
	 * @return float Gauss Convolution value in the x-direction for the
	 *         specified Interest Point.
	 */
	public float makeConvolutionDxx(int x, int y, int filtersize);

	/**
	 * Computes the Gaussian second oder partial derivative in in xy-direction
	 * and returns the value of the (x,y) point for a filter large as specified.
	 * 
	 * @param x
	 * @param y
	 * @param filtersize
	 * @return float Gauss Convolution value in the xy-direction for the
	 *         specified Interest Point.
	 */
	public float makeConvolutionDxy(int x, int y, int filtersize);

	/**
	 * Computes the Gaussian second oder partial derivative in in y-direction
	 * and returns the value of the (x,y) point for a filter large as specified.
	 * 
	 * @param x
	 * @param y
	 * @param filtersize
	 * @return float Gauss Convolution value in the y-direction for the
	 *         specified Interest Point.
	 */
	public float makeConvolutionDyy(int x, int y, int filtersize);

}
