package org.frc5274.montylib.devices;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class VortexIO extends EncodedMotor {
    
    private CANSparkFlex motorController;

    public VortexIO(MotorConfig constants) {
        super(constants.position_constants, constants.velocity_constants);
        setGearRatio(constants.gear_ratio);

        motorController = new CANSparkFlex(constants.id, MotorType.kBrushless);
        motorController.setInverted(constants.direction.isInverted());
        motorController.setIdleMode(constants.idle_behavior.toIdleMode());

        setPowerFunction(motorController::set);
        setDefaultPositionSupplier(motorController.getEncoder()::getPosition);
        setDefaultVelocitySupplier(motorController.getEncoder()::getVelocity);
    }
}
