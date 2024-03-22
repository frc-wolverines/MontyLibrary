package org.frc5274.crescendo.robotcode.systems.notego;

import org.frc5274.crescendo.config.NoteGOConfig;
import org.frc5274.crescendo.kinematics.NoteGOState;
import org.frc5274.crescendo.robotcode.systems.notego.subsystems.Indexer;
import org.frc5274.crescendo.robotcode.systems.notego.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**A class representing the Note Guiding Operators on SHATTER */
public class NoteGO extends SubsystemBase {
    
    private final Intake intake;
    private final Indexer indexer;

    public NoteGO(NoteGOConfig config) {

        intake = new Intake(config);
        indexer = new Indexer(config);

    }

    @Override
    public void periodic() {
        intake.handleFeedback();
        indexer.handleFeedback();
    }

    public void enableFeedback(boolean enabled) {
        intake.enableFeedback(enabled);
        indexer.enableFeedback(enabled);
    }

    public void setDesiredState(NoteGOState state) {
        intake.setDesiredState(state);
        indexer.setDesiredState(state);
    }

    public void stop() {
        intake.stop();
        indexer.stop();
    }
}
