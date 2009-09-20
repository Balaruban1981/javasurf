 

package org.javasurf.base;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
 public class SURF implements ISURFfactory {

	private static SURF instance;
	private IIntegralImage integralImage;
	private int imgWidth;
	private int imgHeight;
	private float balanceValue;
	private float threshold;
	private int octaves;
	private BufferedImage parent;

	/**
	 * Application of the Singleton Pattern. Returns an instance of the class
	 * making sure that it is the only one. The method is STATIC so that any
	 * subclass that extends Singleton retains ownership of uniqueness of the
	 * instance.
	 * 
	 * @param img
	 *            PImage that is converted into Integral Image before it's
	 *            assigned to SURF.
	 * @param balanceValue
	 *            Value used to calculate the Laplacian of Gaussian response.
	 * @param threshold
	 *            Value used to filter the Laplacian of Gaussian.
	 * @param octaves
	 *            Number of octaves that you want to compute.
	 * @param parent
	 *            PApplet where you display your video output.
	 * @return The single instance of SURF.
	 */
	public static SURF createInstance(BufferedImage img, float balanceValue,
			float threshold, int octaves, BufferedImage parent) {

		if (instance == null) {

			instance = new SURF(img, balanceValue, threshold, octaves, parent);

		} else {

			instance.setIntegralImage(img);
			instance.setBalanceValue(balanceValue);
			instance.setOctaves(octaves);
			instance.setThreshold(threshold);

		}

		return instance;

	}

	/**
	 * Application of the Singleton Pattern. Returns an instance of the class
	 * making sure that it is the only one. The method is STATIC so that any
	 * subclass that extends Singleton retains ownership of uniqueness of the
	 * instance. Here, the Balance Value has a standard value of 0.9.
	 * 
	 * @param img
	 *            PImage that is converted into Integral Image before it's
	 *            assigned to SURF.
	 * @param threshold
	 *            Value used to filter the Laplacian of Gaussian.
	 * @param octaves
	 *            Number of octaves that you want to compute.
	 * @param parent
	 *            PApplet where you display your video output.
	 * @return The single instance of SURF.
	 */
	public static SURF createInstance(BufferedImage img, float threshold, int octaves,
			BufferedImage parent) {
		if (instance == null) {
			instance = new SURF(img, threshold, octaves, parent);
		} else {
			instance.setIntegralImage(img);
			instance.setOctaves(octaves);
			instance.setThreshold(threshold);
		}
		return instance;
	}

	/**
	 * Constructor of SURF. The constructor is private to prevent any
	 * possibility to create an instance without the Singleton method.
	 */
	private SURF(BufferedImage img, float balanceValue, float threshold, int steps,
			BufferedImage parent) {
		this.integralImage = new IntegralImage(img );
		this.imgHeight = img.getHeight();
		this.imgWidth = img.getWidth();
		this.balanceValue = balanceValue;
		this.threshold = threshold;
		this.octaves = steps;
		this.parent = parent;
	}

	/*
	 * Constructor of SURF with a standard Balance Value (0.9). The constructor
	 * is private to prevent any possibility to create an instance without the
	 * Singleton method.
	 */
	private SURF(BufferedImage img, float threshold, int steps, BufferedImage parent) {
		this.integralImage = new IntegralImage(img);
		this.imgHeight = img.getHeight();
		this.imgWidth = img.getWidth();
		this.balanceValue = 0.9f;
		this.threshold = threshold;
		this.octaves = steps;
		this.parent = parent;
	}

	public IDetector createDetector() {
		return new FastHessian(integralImage, imgWidth, imgHeight,
				balanceValue, threshold, octaves, parent);
	}

	public IDescriptor createDescriptor(ArrayList<InterestPoint> interest_points) {
		return new SURFdescriptor(interest_points, integralImage);
	}

	public IIntegralImage getIntegralImage() {
		return integralImage;
	}

	public void setIntegralImage(BufferedImage img) {
		this.integralImage = new IntegralImage(img);
	}

	public float getBalanceValue() {
		return balanceValue;
	}

	public void setBalanceValue(float balanceValue) {
		this.balanceValue = balanceValue;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public int getOctaves() {
		return octaves;
	}

	public void setOctaves(int octaves) {
		this.octaves = octaves;
	}

}
