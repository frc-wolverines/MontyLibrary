package org.frc5274.montylib.devices.assemblies;

import org.frc5274.montylib.devices.KrakenIO;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class KrakenModuleIO {
    
    private KrakenIO driveKraken;
    private KrakenIO pivotKraken;
    private CANcoder moduleEncoder;
    private boolean moduleInverted;

    public KrakenModuleIO(ModuleConfig config) {
        
        driveKraken = new KrakenIO(config.drive_motor_config);
        pivotKraken = new KrakenIO(config.pivot_motor_config);
        moduleEncoder = new CANcoder(config.absolute_encoder_id);
        moduleInverted = config.absolute_encoder_reversed;

    }

    public double getTrackPosition() {
        return driveKraken.getPosition(true);
    }

    public double getTrackVelocity() {
        return driveKraken.getVelocity(true);
    }

    public double getModuleAngle() {
        return pivotKraken.getPosition(true);
    }

    public double getModuleAngularVelocity() {
        return pivotKraken.getVelocity(true);
    }

    public Rotation2d getModuleRotation2d() {
        return Rotation2d.fromRadians(getModuleAngle());
    }

    public double getAbsoluteModuleAngle() {
        return moduleInverted ? -moduleEncoder.getAbsolutePosition().getValueAsDouble() : moduleEncoder.getAbsolutePosition().getValueAsDouble();
    }

    public SwerveModuleState getModuleState() {
        return new SwerveModuleState(getAbsoluteModuleAngle(), getModuleRotation2d());
    }
}
