package org.mite.jsurf;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A standard implementation of the Integral_Image interface. Creates the
 * integral image representation of supplied input image. Calculates pixel sums
 * over upright rectangular areas.
 * 
 * @author Alessandro Martini, Claudio Fantacci
 */
public class IntegralImage implements IIntegralImage {

    private float[][] integralValues;
    private BufferedImage img;

    /**
     * Constructor of IntegralImage. The constructor receives the image and
     * makes the conversion in its integral version. You allocate a matrix of
     * integralValues of the same dimension of the given image. The Integral
     * Image is full calculated as follows:
     * <ul>
     *
     * <li>At the first line, the direct sum of intensity (brightness) of pixels
     * in each point represents the right value of the integral;</li>
     *
     * <li>For each next line, the correct value is given by the sum of the
     * current row and of the cell in the matrix of the same coordinates of the
     * pixel immediately above the pixel being processed.</li>
     * </ul>
     */
    public IntegralImage(BufferedImage img, BufferedImage parent) {

        this.img = img;
        integralValues = new float[img.getHeight()][img.getWidth()];
        for (int i = 0; i < img.getHeight()-1; i++) {

            float sumOfTheCurrentRow = 0;
            for (int j = 0; j < img.getWidth()-1; j++) {
//parent.
                float[] cacheHsbValue = new float[3];
//                System.out.println(i+" "+j);
                int what = img.getRGB(j, i);
                Color.RGBtoHSB((what >> 16) & 0xff, (what >> 8) & 0xff,
                        what & 0xff, cacheHsbValue);

                sumOfTheCurrentRow += cacheHsbValue[2] * 255; //Mozno e da ne e vaka
                if (i > 0) {

                    integralValues[i][j] = integralValues[i - 1][j] + sumOfTheCurrentRow;

                } else {

                    integralValues[i][j] = sumOfTheCurrentRow;

                }
            }
        }
    }

    public float getIntegralValue(int x, int y) {

        if ((y < 0 || y >= img.getHeight()) || (x < 0 || x >= img.getWidth())) {

            return 0;

        } else {

            return integralValues[y][x];

        }

    }

    public float getIntegralSquare(int xA, int yA, int xD, int yD) {

        float A = getIntegralValue(xA, yA);
        float D = getIntegralValue(xD, yD);
        float B = getIntegralValue(xD, yA);
        float C = getIntegralValue(xA, yD);
        return A + D - (C + B);

    }
}
