package org.frc5274.montylib.vision;

import java.util.function.Supplier;

import org.frc5274.montylib.math.Angle2d;

/**A class that fixes an Apriltag's data from a fixed camera being physically offset from a target*/
public class ApriltagOffsetFixer {
    private Supplier<Double> apriltagDistanceFromCamera;
    private double apriltagOffset;

    public ApriltagOffsetFixer(Supplier<Double> apriltag_distance_supplier, double apriltag_offset) {
        this.apriltagDistanceFromCamera = apriltag_distance_supplier;
        this.apriltagOffset = apriltag_offset;
    }

    public Angle2d getAdjustedAngle(Angle2d apriltag_angle) {
        double distance_meters = apriltagDistanceFromCamera.get(); //Cosine
        double height_offset = apriltagOffset; //Added to initial Sine
        double apriltag_height = distance_meters / java.lang.Math.sin(apriltag_angle.angle_y);

        return new Angle2d(apriltag_angle.angle_x, 0.0);
    }
}
