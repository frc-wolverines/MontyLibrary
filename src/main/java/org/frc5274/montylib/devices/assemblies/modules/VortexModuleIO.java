package org.frc5274.montylib.devices.assemblies.modules;

import org.frc5274.montylib.devices.motors.VortexIO;
import org.frc5274.montylib.devices.sensors.CANCoderIO;

public class VortexModuleIO extends ModuleIO {
    public VortexModuleIO(ModuleConfig config) {
        super(
            new VortexIO(config.drive_motor_config), 
            new VortexIO(config.pivot_motor_config), 
            new CANCoderIO(config.encoder_config)
        );
    }
}
