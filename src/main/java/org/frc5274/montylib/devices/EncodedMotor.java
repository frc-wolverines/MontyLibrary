package org.frc5274.montylib.devices;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.frc5274.montylib.config.PIDConstants;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;

public class EncodedMotor {
    public Consumer<Double> setPowerConsumer = null;
    public Supplier<Double> alternatePositionSupplier, alternateVelocitySupplier = null;
    public Supplier<Double> defaultPositionSupplier, defaultVelocitySupplier = null;
    public boolean doesUseAlternatePosition, doesUseAlternateVelocity = false;
    public PIDController positionController, velocityController = null;
    public Consumer<Double> resetConsumer = null;
    public double gearRatio = 0.0;

    public EncodedMotor(PIDConstants position_constants, PIDConstants velocity_constants) {
        positionController = position_constants.toController();
        velocityController = velocity_constants.toController();
    }

    public EncodedMotor(EncodedMotorConstants constants) {
        positionController = constants.positionConstants.toController();
        velocityController = constants.velocityConstants.toController();
        setGearRatio(constants.gearRatio);
    }

    protected void setPowerFunction(Consumer<Double> power_consumer) {
        setPowerConsumer = power_consumer;
    }

    protected void setResetFunction(Consumer<Double> reset_consumer) {
        resetConsumer = reset_consumer;
    }

    public void setAlternatePositionSupplier(Supplier<Double> position_supplier) {
        alternatePositionSupplier = position_supplier;
        doesUseAlternatePosition = true;
    }

    public void setAlternateVelocitySupplier(Supplier<Double> velocity_supplier) {
        alternateVelocitySupplier = velocity_supplier;
        doesUseAlternateVelocity = true;
    }

    protected void setDefaultPositionSupplier(Supplier<Double> position_supplier) {
        defaultPositionSupplier = position_supplier;
    }

    protected void setDefaultVelocitySupplier(Supplier<Double> velocity_supplier) {
        defaultVelocitySupplier = velocity_supplier;
    }

    public void setGearRatio(double gear_ratio) {
        gearRatio = gear_ratio;
    }

    public void runToPosition(double position, boolean actuated) {
        double currentPosition = doesUseAlternatePosition ? alternatePositionSupplier.get() : defaultPositionSupplier.get();
        double outputPower;

        if (actuated) {
            outputPower = positionController.calculate(Units.rotationsToRadians(currentPosition) * gearRatio, position);
        } else {
            outputPower = positionController.calculate(Units.rotationsToRadians(currentPosition), position);
        }

        setPowerConsumer.accept(outputPower);
    }

    public void runToVelocity(double velocity, boolean actuated) {
        double currentVelocity = doesUseAlternateVelocity ? alternateVelocitySupplier.get() : defaultVelocitySupplier.get();
        double outputPower;

        if (actuated) {
            outputPower = positionController.calculate(Units.rotationsToRadians(currentVelocity) * gearRatio, velocity);
        } else {
            outputPower = positionController.calculate(Units.rotationsToRadians(currentVelocity), velocity);
        }

        setPowerConsumer.accept(outputPower);
    }

    public class EncodedMotorConstants {
        private final PIDConstants positionConstants;
        private final PIDConstants velocityConstants;
        private final double gearRatio;

        public EncodedMotorConstants(PIDConstants position_constants, PIDConstants velocity_constants, double gear_ratio) {
            positionConstants = position_constants;
            velocityConstants = velocity_constants;
            gearRatio = gear_ratio;
        }
    }

    public double getPosition(boolean actuated) {
        return actuated ? 
            (doesUseAlternatePosition ? 
                alternatePositionSupplier.get() * gearRatio : defaultPositionSupplier.get() * gearRatio) : 
            (doesUseAlternatePosition ? 
                alternatePositionSupplier.get() : defaultPositionSupplier.get());
    }

    public double getVelocity(boolean actuated) {
        return actuated ? 
            (doesUseAlternateVelocity ? 
                alternateVelocitySupplier.get() * gearRatio : defaultVelocitySupplier.get() * gearRatio) : 
            (doesUseAlternatePosition ? 
                alternateVelocitySupplier.get() : defaultVelocitySupplier.get());
    }

    public void stopOutput() {
        setPowerConsumer.accept(0.0);
    }

    public void reset() {
        stopOutput();
        resetConsumer.accept(0.0);
    }

    public void reset(double position) {
        stopOutput();
        resetConsumer.accept(position);
    }
}
