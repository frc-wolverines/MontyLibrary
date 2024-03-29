package org.frc5274.montylib.interfaces.hardware.actuators;

import java.util.function.Supplier;

import org.frc5274.montylib.util.PIDConstants;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;

public class NeoV1 extends CANSparkMax implements MotorInterface {

    private PIDController positionController = new PIDController(0.0, 0.0, 0.0);
    private PIDController velocityController = new PIDController(0.0, 0.0, 0.0);

    private Supplier<Double> alternatePositionSource;
    private Supplier<Double> alternateVelocitySource;

    private double actuationFactor = 0.0;

    @Override
    public double getActuatedPosition() {
        return getEncoder().getPosition() * actuationFactor;
    }

    @Override
    public double getActuatedVelocity() {
        return getEncoder().getVelocity() * actuationFactor;
    }

    @Override
    public void setActuationFactor(double factor) {
        actuationFactor = factor;
    }

    @Override
    public void setPositionPIDConstants(PIDConstants constants) {
        positionController = constants.toController();
    }

    @Override
    public void setVelocityPIDConstants(PIDConstants constants) {
        velocityController = constants.toController();
    }

    @Override
    public void runToRawPosition(double position) {
        if (doesUseAlternatePositionSource()) {
            set(positionController.calculate(alternatePositionSource.get(), position));
        } else {
            set(positionController.calculate(getEncoder().getPosition(), position));
        }
    }

    @Override
    public void runToActuatedPosition(double position) {
        set(positionController.calculate(getActuatedPosition(), position));
    }

    @Override
    public void runToRawVelocity(double velocity) {
        if (doesUseAlternateVelocitySource()) {
            set(velocityController.calculate(alternateVelocitySource.get(), velocity));
        } else {
            set(velocityController.calculate(getEncoder().getVelocity(), velocity));
        }
    }

    @Override
    public void runToActuatedVelocity(double velocity) {
        set(velocityController.calculate(getActuatedVelocity(), velocity));
    }

    @Override
    public boolean doesUseAlternatePositionSource() {
        return alternatePositionSource != null ? false : true;
    }

    @Override
    public void setAlternativePositionSource(Supplier<Double> alternate_source_supplier) {
        alternatePositionSource = alternate_source_supplier;
    }

    @Override
    public boolean doesUseAlternateVelocitySource() {
        return alternateVelocitySource != null ? false : true;
    }

    @Override
    public void setAlternativeVelocitySource(Supplier<Double> alternate_source_supplier) {
        alternateVelocitySource = alternate_source_supplier;
    }

    public NeoV1(int deviceId) {
        super(deviceId, MotorType.kBrushless);
    }
}
