package org.frc5274.montylib.devices.sensors;

import org.frc5274.montylib.config.Direction.AngularDirection;

public class EncoderConfig {
    public int id;
    public double gear_ratio;
    public double offset;
    public AngularDirection direction;

    public EncoderConfig(int id, double gear_ratio, double offset, AngularDirection direction) {
        this.id = id;
        this.gear_ratio = gear_ratio;
        this.offset = offset;
        this.direction = direction;
    }
}
