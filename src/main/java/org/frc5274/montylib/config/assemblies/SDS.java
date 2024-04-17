package org.frc5274.montylib.config.assemblies;

import edu.wpi.first.math.util.Units;

public class SDS {
    public class MK4iHardwareConstants {

        public static final double[] DRIVE_GEAR_RATIOS = {
            1 / 8.14,
            1 / 6.75,
            1 / 6.12
        };

        public static final double STEERING_GEAR_RATIO = 1 / 21.4285714286;
        public static final double TRACK_LENGTH = Units.inchesToMeters(4) * Math.PI;
    }
}
