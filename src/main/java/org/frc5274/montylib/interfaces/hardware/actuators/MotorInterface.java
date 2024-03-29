package org.frc5274.montylib.interfaces.hardware.actuators;

import java.util.function.Supplier;

import org.frc5274.montylib.util.PIDConstants;

import edu.wpi.first.util.sendable.SendableBuilder;

public interface MotorInterface {
    abstract double getActuatedPosition();
    abstract double getActuatedVelocity(); 

    abstract void setActuationFactor(double factor);

    abstract void setPositionPIDConstants(PIDConstants constants);
    abstract void setVelocityPIDConstants(PIDConstants constants);

    abstract void runToRawPosition(double position);
    abstract void runToActuatedPosition(double position);

    abstract void runToRawVelocity(double velocity);
    abstract void runToActuatedVelocity(double velocity);

    abstract boolean doesUseAlternatePositionSource();
    abstract void setAlternativePositionSource(Supplier<Double> alternate_source_supplier);

    abstract boolean doesUseAlternateVelocitySource();
    abstract void setAlternativeVelocitySource(Supplier<Double> alternate_source_supplier);
}
