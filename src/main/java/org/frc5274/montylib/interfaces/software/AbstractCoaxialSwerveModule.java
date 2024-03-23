package org.frc5274.montylib.interfaces.software;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public abstract class AbstractCoaxialSwerveModule {
    public abstract void resetSwivelPosition(double new_position);
    public abstract void resetTrackPosition(double new_position);

    public abstract double getSwivelPosition();
    public abstract Rotation2d getSwivelRotation2d();
    public abstract double getSwivelVelocity();
    public abstract double getTrackPosition();
    public abstract double getTrackVelocity();

    public abstract SwerveModuleState getModuleState();
    public abstract SwerveModulePosition getModulePosition();

    public abstract void setDesiredModuleState(SwerveModuleState state);
    public abstract void stopModule();
}
