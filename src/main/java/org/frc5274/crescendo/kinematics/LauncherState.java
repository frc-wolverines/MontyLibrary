package org.frc5274.crescendo.kinematics;

import org.frc5274.montylib.kinematics.MechanismState;

/**A class that represents the state of the Launcher at any instance */
public class LauncherState {
    private MechanismState state;

    /**
     * Creates a new instance of LauncherState
     * @param primer_power the analog output of the primer motor
     * @param launcher_power the analog output of the launcher motors
     * @param launcher_angle the target position of the launcher pivot motor
     */
    public LauncherState(double primer_power, double launcher_power, double launcher_angle) {
        state = new MechanismState(primer_power, launcher_power, launcher_angle);
    }

    /**
     * Retrieves the target power of the launcher motors
     * @return an analog value representing the output of the launcher motors
     */
    public double getLauncherPower() {
        return state.getState(1);
    }

    /**
     * Retrieves the target position of the launcher pivot motor
     * @return a target angle of the shooter
     */
    public double getLauncherAngle() {
        return state.getState(3);
    }

    /**
     * Retrieves the target power of the primer motor
     * @return an analog value representing the output of the primer motor
     */
    public double getPrimerPower() {
        return state.getState(0);
    }
}
