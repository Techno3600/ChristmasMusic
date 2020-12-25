// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

import java.util.Arrays;

public class Interpolator
{
    public static double[] interpolateLinear(final double[] x, final double[] y, final double[] xi) throws IllegalArgumentException {
        if (x.length != y.length) {
            throw new IllegalArgumentException("X and Y must be the same length");
        }
        if (x.length == 1) {
            throw new IllegalArgumentException("X must contain more than one value");
        }
        final double[] dx = new double[x.length - 1];
        final double[] dy = new double[x.length - 1];
        final double[] slope = new double[x.length - 1];
        final double[] intercept = new double[x.length - 1];
        for (int i = 0; i < x.length - 1; ++i) {
            dx[i] = x[i + 1] - x[i];
            if (dx[i] == 0.0) {
                throw new IllegalArgumentException("X must be montotonic. A duplicate x-value was found");
            }
            if (dx[i] < 0.0) {
                throw new IllegalArgumentException("X must be sorted");
            }
            dy[i] = y[i + 1] - y[i];
            slope[i] = dy[i] / dx[i];
            intercept[i] = y[i] - x[i] * slope[i];
        }
        final double[] yi = new double[xi.length];
        for (int j = 0; j < xi.length; ++j) {
            if (xi[j] > x[x.length - 1] || xi[j] < x[0]) {
                yi[j] = Double.NaN;
            }
            else {
                int loc = Arrays.binarySearch(x, xi[j]);
                if (loc < -1) {
                    loc = -loc - 2;
                    yi[j] = slope[loc] * xi[j] + intercept[loc];
                }
                else {
                    yi[j] = y[loc];
                }
            }
        }
        return yi;
    }
    
    public static double interpolateLinear(final double[] xy, final double xx) {
        if (xy.length % 2 != 0) {
            throw new IllegalArgumentException("XY must be divisible by two.");
        }
        final double[] x = new double[xy.length / 2];
        final double[] y = new double[x.length];
        for (int i = 0; i < xy.length; ++i) {
            if (i % 2 == 0) {
                x[i / 2] = xy[i];
            }
            else {
                y[i / 2] = xy[i];
            }
        }
        return interpolateLinear(x, y, new double[] { xx })[0];
    }
}
