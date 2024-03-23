package org.frc5274.montylib.interfaces.software;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class AbstractHolonomicDrive extends SubsystemBase {
    public abstract void resetHeading();
    public abstract void resetEncoders();
    public abstract void resetOdometry();
    public abstract void resetPose2d(Pose2d new_pose);

    public abstract double getHeading();
    public abstract Rotation2d getRotation2d();
    public abstract Pose2d getPose2d();
    public abstract ChassisSpeeds getChassisSpeeds();

    public abstract void setDesiredChassisSpeeds(ChassisSpeeds speeds);
    public abstract void driveRobotRelative(ChassisSpeeds relative_speeds);
    public abstract void stopDrive();
}
