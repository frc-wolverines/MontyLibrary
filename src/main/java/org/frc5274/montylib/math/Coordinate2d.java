package org.frc5274.montylib.math;

public class Coordinate2d {
    public double x, y = 0.0;

    public double bounds_x, bounds_y = 0.0;

    public Coordinate2d() {};

    public Coordinate2d(double x_position, double y_position) {
        x = x_position;
        y = y_position;
        bounds_x = 1.0;
        bounds_y = 1.0;
    }

    public Coordinate2d(double x_position, double y_position, double x_bounds, double y_bounds) {
        x = x_position;
        y = y_position;
        bounds_x = x_bounds;
        bounds_y = y_bounds;
    }

    public Coordinate2d getCoordinatePercentage() {
        return new Coordinate2d(x / bounds_x, y / bounds_y);
    }
}
