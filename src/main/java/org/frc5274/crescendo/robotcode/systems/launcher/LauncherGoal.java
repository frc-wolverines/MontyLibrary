package org.frc5274.crescendo.robotcode.systems.launcher;

public class LauncherGoal {
    public final static double IDLE_ANGLE = 0.0;
    public final static double SPEAKER_ANGLE = 0.0;

    public final static double IDLE_POWER = 0.0;
    public final static double SPEAKER_POWER = 1.0;
    public final static double AMP_POWER = 0.20;
    public final static double SOURCE_POWER = -0.35;

    public final static double IDLE_GOOD_VELOCITY = 0.0;
    public final static double SPEAKER_GOOD_VELOCITY = 0.0;
    public final static double AMP_GOOD_VELOCITY = 0.0;
    public final static double SOURCE_GOOD_VELOCITY = 0.0;

    public final static double MAX_ANGLE_POSITION = 0.0;
    public final static double MIN_ANGLE_POSITION = 0.0;

    public enum LauncherMode {
        IDLE,
        AMP,
        SOURCE,
        SPEAKER,
        MANUAL,
        DYNAMIC;

        public double getOutputPower(double input) {
            switch (this) {
                case AMP:
                    return AMP_POWER * input;
                case IDLE:
                    return IDLE_POWER;
                case SOURCE:
                    return SOURCE_POWER;
                case SPEAKER:
                    return SPEAKER_POWER * input;
                case MANUAL:
                    return input;
                case DYNAMIC:
                    return input;
                default:
                    return IDLE_POWER;
            }
        }

        public double getOutputAngle() {
            switch(this) {
                case AMP:
                    return MAX_ANGLE_POSITION;
                case IDLE:
                    return IDLE_ANGLE;
                case SOURCE:
                    return MAX_ANGLE_POSITION;
                case SPEAKER:
                    return SPEAKER_ANGLE;
                case MANUAL:
                    return 0.0;
                case DYNAMIC:
                    return 0.0;
                default:
                    return IDLE_ANGLE;
            }
        }

        public double getOutputAngle(double current_angle, double angle_input) {
            switch(this) {
                case AMP:
                    return MAX_ANGLE_POSITION;
                case IDLE:
                    return IDLE_ANGLE;
                case SOURCE:
                    return MAX_ANGLE_POSITION;
                case SPEAKER:
                    return SPEAKER_ANGLE;
                case MANUAL:
                    return current_angle + (angle_input * 0.1);
                case DYNAMIC:
                    return 0.0;
                default:
                    return IDLE_ANGLE;
            }
        }

        public double getGoodVelocity() {
            switch(this) {
                case AMP:
                    return AMP_GOOD_VELOCITY;
                case IDLE:
                    return IDLE_ANGLE;
                case SOURCE:
                    return SOURCE_GOOD_VELOCITY;
                case SPEAKER:
                    return SPEAKER_GOOD_VELOCITY;
                case MANUAL:
                    return SPEAKER_GOOD_VELOCITY;
                case DYNAMIC:
                    return SPEAKER_GOOD_VELOCITY;
                default:
                    return IDLE_GOOD_VELOCITY;
            }
        }
    }
}
