package org.frc5274.montylib.kinematics;

/**A class that represents the current state of a mechanism */
public class MechanismState {
    private double[] mechanism_states;

    /**
     * Constructs a new instance of MechanismState
     * @param states a variable number of values representing the state of a mechanism
     */
    public MechanismState(double... states) {
        mechanism_states = states;
    }

    /**
     * Retrieves a value from the stored variable mechanism states
     * @param state_index the integer representing the position of the mechanism state in the array 
     * @return the value stored in the array at the position: state_index
     */
    public double getState(int state_index) {
        if (state_index > mechanism_states.length - 1) {
            System.out.println(getClass().getName() + ": index outside of state array");
            return 0.0;
        } else {
            return mechanism_states[state_index];
        }
    }
}
