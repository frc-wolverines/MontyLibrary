package org.frc5274.montylib.devices;

import com.ctre.phoenix6.hardware.CANcoder;

public class CANCoderIO extends AbsoluteEncoder {
    private CANcoder sensor;

    public CANCoderIO(EncoderConfig config) {
        sensor = new CANcoder(config.id);

        setSensorPositionSupplier(sensor.getAbsolutePosition()::getValueAsDouble);
        setSensorOffset(config.offset);
        setSensorGearRatio(config.gear_ratio);
        setSensorDirection(config.direction);
    }
}
