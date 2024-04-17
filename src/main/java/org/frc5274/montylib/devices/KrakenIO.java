package org.frc5274.montylib.devices;

import com.ctre.phoenix6.hardware.TalonFX;

public class KrakenIO extends EncodedMotor {
    
    private TalonFX motorController;

    public KrakenIO(MotorConfig config) {
        super(config.position_constants, config.velocity_constants);
        setGearRatio(config.gear_ratio);

        motorController = new TalonFX(config.id);
        motorController.setInverted(config.direction.isInverted());
        motorController.setNeutralMode(config.idle_behavior.toNeutralMode());

        setPowerFunction(motorController::set);
        setDefaultPositionSupplier(motorController.getPosition()::getValueAsDouble);
        setDefaultVelocitySupplier(motorController.getVelocity()::getValueAsDouble);
    }
}
