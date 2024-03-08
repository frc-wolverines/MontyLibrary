package org.montylib.util;

import edu.wpi.first.math.controller.PIDController;

public class PIDConstants {
    
    public double kP, kI, kD = 0.0;

    public PIDConstants(double kp, double ki, double kd) {
        this.kP = kp;
        this.kI = ki;
        this.kD = kd;
    }

    public PIDController toController() {
        return new PIDController(kP, kI, kD);
    }
}
