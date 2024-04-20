package org.frc5274.montylib.devices.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class SparkIO extends EncodedMotor {
    
    private CANSparkMax motorController;

    public SparkIO(MotorConfig constants) {
        super(constants.position_constants, constants.velocity_constants);
        setGearRatio(constants.gear_ratio);

        motorController = new CANSparkMax(constants.id, MotorType.kBrushless);
        motorController.setInverted(constants.direction.isInverted());
        motorController.setIdleMode(constants.idle_behavior.toIdleMode());

        setPowerFunction(motorController::set);
        setDefaultPositionSupplier(motorController.getEncoder()::getPosition);
        setDefaultVelocitySupplier(motorController.getEncoder()::getVelocity);
    }
}
