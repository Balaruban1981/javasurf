Java implementation on speeded up robust features SURF.

SURF (Speeded Up Robust Features) is a robust image descriptor, first presented by Herbert Bay et al. in 2006, that can be used in computer vision tasks like object recognition or 3D reconstruction. It is partly inspired by the SIFT descriptor. The standard version of SURF is several times faster than SIFT and claimed by its authors to be more robust against different image transformations than SIFT. SURF is based on sums of 2D Haar wavelet responses and makes an efficient use of integral images. As basic image features it uses a Haar wavelet approximation of the determinant of Hessian blob detector.

More about surf on http://en.wikipedia.org/wiki/SURF

Currenty this project is refactored version of http://processingsurf.altervista.org/ where the dependency of Processing is removed.

The general idea is to create java SURF lib that can be easily integrated into excising projects also to give various example sub projects interesting usage of SURF for machine learning and computer vision projects