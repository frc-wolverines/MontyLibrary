package org.frc5274.montylib.devices.assemblies;

import org.frc5274.montylib.devices.EncoderConfig;
import org.frc5274.montylib.devices.MotorConfig;

public class ModuleConfig {
    public final MotorConfig drive_motor_config;
    public final MotorConfig pivot_motor_config;

    public final EncoderConfig encoder_config;

    public ModuleConfig(
        MotorConfig drive_config,
        MotorConfig pivot_config,
        EncoderConfig encoder_config
    ) {
        drive_motor_config = drive_config;
        pivot_motor_config = pivot_config;
        this.encoder_config = encoder_config;
    }
}
