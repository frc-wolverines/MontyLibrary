package org.frc5274.crescendo.robotcode.systems.notego.subsystems;

import org.frc5274.crescendo.config.NoteGOConfig;
import org.frc5274.crescendo.kinematics.NoteGOState;
import org.frc5274.montylib.interfaces.hardware.actuators.NeoV1;
import org.frc5274.montylib.util.DynamicDataLog;

import com.revrobotics.CANSparkBase.IdleMode;

/**A class representing a Note Guiding Operator consisting of an intake system to pick up Notes */
public class Intake {
    
    private final NeoV1 frontWheelMotor, rearWheelMotor;

    private boolean feedbackRequested = false;

    public Intake(NoteGOConfig config) {

        frontWheelMotor = new NeoV1(config.front_intake_id);
        frontWheelMotor.setInverted(config.front_intake_direction.isInverted());
        frontWheelMotor.setIdleMode(IdleMode.kBrake);

        rearWheelMotor = new NeoV1(config.rear_intake_id);
        rearWheelMotor.setInverted(config.rear_intake_direction.isInverted());
        rearWheelMotor.setIdleMode(IdleMode.kBrake);

    }

    public void enableFeedback(boolean enabled) {
        feedbackRequested = enabled;
    }

    public void handleFeedback() {
        if(feedbackRequested) {
            DynamicDataLog.log("NOTEGO - Front Intake Velocity", frontWheelMotor.getEncoder().getVelocity());
            DynamicDataLog.log("NOTEGO - Rear Intake Velocity", rearWheelMotor.getEncoder().getVelocity());
        }
    }

    public void setDesiredState(NoteGOState state) {
        frontWheelMotor.set(state.getFrontIntakePower());
        rearWheelMotor.set(state.getRearIntakePower());
    }

    public void stop() {
        frontWheelMotor.stopMotor();
        rearWheelMotor.stopMotor();
    }
}
