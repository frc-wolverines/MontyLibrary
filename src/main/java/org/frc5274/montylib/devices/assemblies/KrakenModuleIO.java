package org.frc5274.montylib.devices.assemblies;

import org.frc5274.montylib.devices.CANCoderIO;
import org.frc5274.montylib.devices.KrakenIO;

public class KrakenModuleIO extends ModuleIO {
    public KrakenModuleIO(ModuleConfig config) {
        super(
            new KrakenIO(config.drive_motor_config), 
            new KrakenIO(config.pivot_motor_config), 
            new CANCoderIO(config.encoder_config)
        );
    }
}
