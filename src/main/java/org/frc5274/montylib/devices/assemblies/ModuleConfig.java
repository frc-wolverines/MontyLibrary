package org.frc5274.montylib.devices.assemblies;

import org.frc5274.montylib.config.MotorBehavior.IdleBehavior;
import org.frc5274.montylib.devices.MotorConfig;

public class ModuleConfig {
    public final MotorConfig drive_motor_config;
    public final MotorConfig pivot_motor_config;

    public final int absolute_encoder_id;
    public final boolean absolute_encoder_reversed;

    public ModuleConfig(
        MotorConfig drive_config,
        MotorConfig pivot_config,
        int encoder_id,
        boolean encoder_reversed
    ) {
        drive_motor_config = drive_config;
        pivot_motor_config = pivot_config;

        absolute_encoder_id = encoder_id;
        absolute_encoder_reversed = encoder_reversed;
    }
}
