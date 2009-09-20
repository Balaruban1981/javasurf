package org.javasurf.base;

import java.awt.Image;

import java.util.ArrayList;

/**
 * A standard implementation of the IDetector interface. FastHessian builds an
 * Hessian Matrix for each different scale. Then it thresholds the results and
 * performs a non-maximal suppression in order to localize the Interest Points
 * in the scale space.
 *
 * @author Alessandro Martini, Claudio Fantacci , Mite Mitreski
 */
public class FastHessian implements IDetector {

    private IIntegralImage integralImage;
    private int imgHeight;
    private int imgWidth;
    private float balanceValue;
    private float threshold;
    private int octaves;
    private IGAUSSconvolution gaussConvolution;
    private ArrayList<ArrayList<float[][]>> hessianDeterminantsByOctaves;
    private ArrayList<InterestPoint> interestPoints;
    private Image parent;

    /**
     * Constructor of FastHessian.
     *
     * @param integralImage
     * @param imgWidth
     * @param imgHeight
     * @param balanceValue
     *            Value used to calculate the Laplacian of Gaussian response.
     * @param threshold
     *            Value used to filter the Laplacian of Gaussian.
     * @param octaves
     *            Number of octaves that you want to compute.
     * @param parent
     *            PApplet where you display your video output.
     */
    public FastHessian(IIntegralImage integralImage, int imgWidth,
            int imgHeight, float balanceValue, float threshold, int octaves,
            Image parent) {
        this.integralImage = integralImage;
        this.threshold = threshold;
        this.octaves = octaves;
        this.gaussConvolution = new GAUSSconvolution(integralImage);
        interestPoints = new ArrayList<InterestPoint>();
        hessianDeterminantsByOctaves = new ArrayList<ArrayList<float[][]>>(octaves);
        this.parent = parent;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }

    /**
     * After building all the determinants matrix at each octaves, at each
     * scale, FastHessian thresholds the results and then apply a
     * non-maximal suppression in order to detect the Interest Points.
     */
    public ArrayList<InterestPoint> generateInterestPoints() {
        buildDet();
        threshold();
        nonMaximalSuppression();
        return interestPoints;
    }

    private void buildDet() {
        float Dxx;
        float Dyy;
        float Dxy;
        float balancaValueSquared=balanceValue * balanceValue;
        int center;
        int filterArea;
        int filterIncrease = 3;
        int firstFiltersizeOfTheOctave = 9;
        for (int k = 0; k < octaves; k++) {
            ArrayList<float[][]> hessianDeterminantsOfAScale = new ArrayList<float[][]>();
            filterIncrease = 2 * filterIncrease;

            for (int filtersize = firstFiltersizeOfTheOctave;
                    filtersize < (firstFiltersizeOfTheOctave +
                    (3 * filterIncrease) + 1); filtersize += filterIncrease) {
                /*
                 * The overflow from the edges is not managed.
                 */
                center = (filtersize - 1) / 2;
                filterArea = filtersize * filtersize;
                float[][] hessianDeterminants = new float[imgHeight][imgWidth];
                int centerHeight = (imgHeight - center);
                int centerWidth = (imgWidth - center);
                for (int i = center; i < centerHeight; i++) {
                    for (int j = center; j < centerWidth; j++) {
                        Dxx = gaussConvolution.makeConvolutionDxx(j, i, filtersize) / filterArea;
                        Dyy = gaussConvolution.makeConvolutionDyy(j, i, filtersize) / filterArea;
                        Dxy = gaussConvolution.makeConvolutionDxy(j, i, filtersize) / filterArea;
                        float value = (float) ((Dxx * Dyy) - ( balancaValueSquared* Dxy * Dxy));
                        hessianDeterminants[i][j] = ((value < 0) ? 0 : value);
                    }
                }
                hessianDeterminantsOfAScale.add(hessianDeterminants);
            }
            firstFiltersizeOfTheOctave += filterIncrease;
            hessianDeterminantsByOctaves.add(hessianDeterminantsOfAScale);
        }
    }

    private void threshold() {
        /*
         * The method scrolls the matrices of the determinants and assigns
         * 0 to all values below of the threshold value.
         */
        for (int i = 0; i < hessianDeterminantsByOctaves.size(); i++) {
            ArrayList<float[][]> currentScale = hessianDeterminantsByOctaves.get(i);

            for (float[][] matrix : currentScale) {
                for (int k = 0; k < imgHeight; k++) {
                    for (int j = 0; j < imgWidth; j++) {
                        if (matrix[k][j] < threshold) {
                            matrix[k][j] = 0;
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void nonMaximalSuppression() {
        for (int i = 0; i < hessianDeterminantsByOctaves.size(); i++) {
            ArrayList<float[][]> currentScale = hessianDeterminantsByOctaves.get(i);
            for (int t = 0; t < currentScale.size(); t++) {
                float[][] matrix = (float[][]) currentScale.get(t);
                for (int k = 0; k < imgHeight; k++) {
                    for (int j = 0; j < imgWidth; j++) {
                        if (matrix[k][j] != 0) {
                            // TO-DO curent number of sacles is 4
                            if (isLocalMaxima(i, t, j, k, 4)) {
                                interestPoints.add(new InterestPoint(j, k,
                                        (float) (((((9 + (6 * (2 ^ i))) - 6 +
                                        (t * 6 * 2)) ^ i) * 1.2) / 9), parent));
                            } else {
                            }
                        } else {
                        }
                    }
                }
            }
        }
    }

    /*Check if the selected pixel is a local maxima*/
    private boolean isLocalMaxima(int octaves, int numberOfFilter, int x, int y, int numberOfScales) {

        ArrayList<float[][]> currentScale = hessianDeterminantsByOctaves.get(octaves);
        float pixelToCheck = ((float[][]) currentScale.get(numberOfFilter))[y][x];
        boolean isTheGreatest = true;
        ArrayList<Float> localValues = new ArrayList<Float>();
        float[][] upperMatrix;
        float[][] currentMatrix;
        float[][] lowerMatrix;

        if (numberOfFilter == 0) {
            upperMatrix = (float[][]) currentScale.get(numberOfFilter + 1);
            currentMatrix = (float[][]) currentScale.get(numberOfFilter);

            for (int k = x - 1; k < (x + 2); k++) {
                for (int t = y - 1; t < (y + 2); t++) {
                    localValues.add(new Float(upperMatrix[t][k]));
                    localValues.add(new Float(currentMatrix[t][k]));
                }
            }
        } else if (numberOfFilter == (numberOfScales - 1)) {
            currentMatrix = (float[][]) currentScale.get(numberOfFilter);
            lowerMatrix = (float[][]) currentScale.get(numberOfFilter - 1);

            for (int k = x - 1; k < (x + 2); k++) {
                for (int t = y - 1; t < (y + 2); t++) {
                    localValues.add(new Float(currentMatrix[t][k]));
                    localValues.add(new Float(lowerMatrix[t][k]));
                }
            }
        } else {
            /* Caso standard */
            upperMatrix = (float[][]) currentScale.get(numberOfFilter + 1);
            currentMatrix = (float[][]) currentScale.get(numberOfFilter);
            lowerMatrix = (float[][]) currentScale.get(numberOfFilter - 1);
            for (int k = x - 1; k < (x + 2); k++) {
                for (int t = y - 1; t < (y + 2); t++) {
                    localValues.add(new Float(upperMatrix[t][k]));
                    localValues.add(new Float(currentMatrix[t][k]));
                    localValues.add(new Float(lowerMatrix[t][k]));
                }
            }
        }

        int n = 0;
        float pixelToExamineNow;
        while (isTheGreatest && (n < localValues.size())) {
            pixelToExamineNow = (Float) localValues.get(n);
            if ((pixelToExamineNow != 0) && (pixelToCheck < pixelToExamineNow)) {
                isTheGreatest = false;
            }
            n++;
        }

        return isTheGreatest;
    }
}
