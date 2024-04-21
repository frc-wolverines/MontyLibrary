package org.frc5274.montylib.devices.assemblies.modules;

import org.frc5274.montylib.devices.motors.SparkIO;
import org.frc5274.montylib.devices.sensors.CANCoderIO;

public class SparkModuleIO extends ModuleIO {
    public SparkModuleIO(ModuleConfig config) {
        super(
            new SparkIO(config.drive_motor_config), 
            new SparkIO(config.pivot_motor_config), 
            new CANCoderIO(config.encoder_config)
        );
    }
}
