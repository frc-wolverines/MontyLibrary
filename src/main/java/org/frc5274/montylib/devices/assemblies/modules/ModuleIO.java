package org.frc5274.montylib.devices.assemblies.modules;

import org.frc5274.montylib.devices.motors.EncodedMotor;
import org.frc5274.montylib.devices.sensors.AbsoluteEncoder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class ModuleIO {
    
    public EncodedMotor driveMotor;
    public EncodedMotor pivotMotor;
    public AbsoluteEncoder encoder;

    private static double wheelCircumference = 1;

    public ModuleIO(EncodedMotor driveMotor, EncodedMotor pivotMotor, AbsoluteEncoder moduleEncoder) {
        this.driveMotor = driveMotor;
        this.pivotMotor = pivotMotor;
        this.encoder = moduleEncoder;
    }

    public double getTrackPosition() {
        return driveMotor.getPosition(true) * wheelCircumference;
    }

    public double getTrackVelocity() {
        return driveMotor.getVelocity(true) * wheelCircumference;
    }

    public double getAngle() {
        return pivotMotor.getPosition(true) * Math.PI * 2;
    }

    public double getAngularVelocity() {
        return pivotMotor.getVelocity(true) * Math.PI * 2;
    }

    public double getAbsoluteAngle() {
        return encoder.getAbsolutePosition(true);
    }

    public Rotation2d getRotation2d() {
        return Rotation2d.fromRadians(getAngle());
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(getTrackPosition(), getRotation2d());
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getTrackVelocity(), getRotation2d());
    }

    public void setDesiredState(SwerveModuleState desiredState) {

        if (desiredState.speedMetersPerSecond < 0.001) {
            return;
        }

        driveMotor.runToVelocity(desiredState.speedMetersPerSecond / wheelCircumference, true);
        pivotMotor.runToPosition(desiredState.angle.getRotations(), true);
    }

    public void stop() {
        driveMotor.stopOutput();
        pivotMotor.stopOutput();
    }

    public void zero() {
        pivotMotor.reset(getAbsoluteAngle());
    }

    public static void setWheelCircumference(double circumferenceMeters) {
        wheelCircumference = circumferenceMeters;
    }
}
