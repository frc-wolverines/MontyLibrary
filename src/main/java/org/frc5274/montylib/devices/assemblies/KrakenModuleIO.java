package org.frc5274.montylib.devices.assemblies;

import org.frc5274.montylib.devices.KrakenIO;

import com.ctre.phoenix6.hardware.CANcoder;

public class KrakenModuleIO {
    
    private KrakenIO driveKraken;
    private KrakenIO pivotKraken;
    private CANcoder moduleEncoder;
    private boolean moduleInverted;

    public KrakenModuleIO(ModuleConfig config) {
        
        driveKraken = new KrakenIO(config.drive_motor_config);
        pivotKraken = new KrakenIO(config.pivot_motor_config);
        moduleEncoder = new CANcoder(config.absolute_encoder_id);
        moduleInverted = config.absolute_encoder_reversed;

    }
}
