package org.montylib.kinematics;

public class MechanismState {
    private double[] mechanism_states;

    public MechanismState(double... states) {
        mechanism_states = states;
    }

    public double getState(int state_index) {
        if (state_index > mechanism_states.length - 1) {
            System.out.println(getClass().getName() + ": index outside of state array");
            return 0.0;
        } else {
            return mechanism_states[state_index];
        }
    }
}
