package org.frc5274.montylib.interfaces.hardware.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class BeamSensor extends DigitalInput {
    public BeamSensor(int channel) {
        super(channel);
    }
}
