package org.frc5274.montylib.vision;

import java.util.function.Supplier;

import org.frc5274.montylib.math.Coordinate2d;

/**A class representing a Camera that tracks the position of an AprilTag target */
public class TargetCamera {
    private Supplier<Double> target_x, target_y;
    private boolean flip_camera;

    /**
     * Creates a new instance of TargetCamera
     * @param target_x_axis the x-axis of the target where left is positive
     * @param target_y_axis the y-axis of the target where up is positive
     * @param flip_camera whether the camera is upsidown and thus the axes are flipped
     */
    public TargetCamera(Supplier<Double> target_x_axis, Supplier<Double> target_y_axis, boolean flip_camera) {
        this.target_x = target_x_axis;
        this.target_y = target_y_axis;
        this.flip_camera = flip_camera;
    }

    /**
     * Gives a coordinate point of the target relative to the camera sensor's plane 
     * @return a Coordinate2d representing the target's position
     */
    public Coordinate2d getTargetCoordinates() {
        double x = flip_camera ? -target_x.get() : target_x.get();
        double y = flip_camera ? -target_y.get() : target_y.get();

        return new Coordinate2d(x, y);
    }
}
