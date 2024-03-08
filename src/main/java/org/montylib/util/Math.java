package org.montylib.util;

public class Math {
    
    /**
     * Outputs a value that is locked to be in between a min and max value
     * @param value the number which will be modified to fit into given limits
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @return a value that is limited to be in between a min and max value
     */
    public static double clip(double value, double min, double max) {
        return java.lang.Math.max(min, java.lang.Math.min(max, value));
    }

    /**
     * Copies the functionality of Math.IEEEremainder but makes it more understandable
     * @param x1 one value to compute
     * @param x2 a second value to compute
     * @return the difference between the two values in IEEE 754 Format
     */
    public static double remainder(double x1, double x2) {
        return java.lang.Math.IEEEremainder(x1, x2);
    }
    
    /**
     * Discards a value if it is within a certain threshold
     * @param value the number that will be affected
     * @param threshold the value in which a -threshold to threshold deadzone will be incurred using
     * @return a value, outside of a given threshold, otherwise it is 0.0
     */
    public static double threshold(double value, double threshold) {
        return java.lang.Math.abs(value) >= threshold ? value : 0.0;
    }
}
