package org.montylib.interfaces.hardware.sensors;

import org.montylib.math.Math;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;

public class NavX2 extends AHRS {
    public NavX2() {
        super(Port.kMXP);
    }

    public double headingRemainder() {
        return Math.remainder(getAngle(), 360);
    }
}
