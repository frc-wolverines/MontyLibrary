package org.frc5274.montylib.config;

public class Direction {
    public enum AngularDirection {
        CLOCKWISE,
        COUNTER_CLOCKWISE;

        public boolean isInverted() {
            return this == CLOCKWISE;
        }
    }
}
