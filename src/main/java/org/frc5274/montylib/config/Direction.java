package org.frc5274.montylib.config;

public class Direction {
    public enum MotorDirection {
        CLOCKWISE,
        COUNTER_CLOCKWISE;

        public boolean isInverted() {
            return this == COUNTER_CLOCKWISE;
        }
    }
}
