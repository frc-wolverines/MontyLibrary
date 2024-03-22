package org.frc5274.crescendo.robotcode.systems.notego.subsystems;

import org.frc5274.crescendo.config.NoteGOConfig;
import org.frc5274.crescendo.kinematics.NoteGOState;
import org.frc5274.montylib.interfaces.hardware.actuators.Falcon500;
import org.frc5274.montylib.util.DynamicDataLog;

import com.ctre.phoenix6.signals.NeutralModeValue;

/**A class representing a Note Guiding Operator consisting of four rollers and eight belts */
public class Indexer {
    
    private final Falcon500 indexerMotor;

    private boolean feedbackRequested = false;

    public Indexer(NoteGOConfig config) {

        indexerMotor = new Falcon500(config.indexer_id);
        indexerMotor.setInverted(config.indexer_direction.isInverted());
        indexerMotor.setNeutralMode(NeutralModeValue.Brake);

    }

    public void enableFeedback(boolean enabled) {
        feedbackRequested = true;
    }

    public void handleFeedback() {
        if(feedbackRequested) {
            DynamicDataLog.log("NOTEGO - Indexer Velocity", indexerMotor.getVelocity().getValueAsDouble());
            DynamicDataLog.log("NOTEGO - Indexer Motor", indexerMotor);
        }
    }

    public void setDesiredState(NoteGOState state) {
        indexerMotor.set(state.getIndexerPower());
    }

    public void stop() {
        indexerMotor.stopMotor();
    }
}
