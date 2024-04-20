package org.frc5274.montylib.devices.assemblies;

import org.frc5274.montylib.devices.KrakenIO;

import com.ctre.phoenix6.hardware.CANcoder;

public class KrakenModuleIO extends ModuleIO {

    public KrakenModuleIO(ModuleConfig config) {
        super(
            new KrakenIO(config.drive_motor_config), 
            new KrakenIO(config.pivot_motor_config), 
            new CANcoder(config.absolute_encoder_id)
        );
    }
}
