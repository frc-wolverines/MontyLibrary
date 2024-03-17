package org.montylib.vision;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class ChassisTargetFollower {
    public ChassisSpeeds fromTargetFacing(double x_velocity, double y_velocity, Rotation2d robot_heading) {
        return ChassisSpeeds.fromFieldRelativeSpeeds(x_velocity, y_velocity, LimelightHandler.getTX(""), robot_heading);
    }
}
