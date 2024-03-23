package org.frc5274.crescendo.kinematics;

import org.frc5274.montylib.kinematics.MechanismState;

public class NoteGOState {
    private MechanismState state;

    public NoteGOState(double front_intake_power, double rear_intake_power, double indexer_power) {
        state = new MechanismState(front_intake_power, rear_intake_power, indexer_power);
    }

    public NoteGOState(double unified_power) {
        state = new MechanismState(unified_power, unified_power, unified_power);
    }

    public double getFrontIntakePower() {
        return state.getState(0);
    }

    public double getRearIntakePower() {
        return state.getState(1);
    }

    public double getIndexerPower() {
        return state.getState(2);
    }
}
