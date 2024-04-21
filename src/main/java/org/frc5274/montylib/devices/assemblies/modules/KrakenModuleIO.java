package org.frc5274.montylib.devices.assemblies.modules;

import org.frc5274.montylib.devices.motors.KrakenIO;
import org.frc5274.montylib.devices.sensors.CANCoderIO;

public class KrakenModuleIO extends ModuleIO {
    public KrakenModuleIO(ModuleConfig config) {
        super(
            new KrakenIO(config.drive_motor_config), 
            new KrakenIO(config.pivot_motor_config), 
            new CANCoderIO(config.encoder_config)
        );
    }
}
