package org.frc5274.montylib.devices;

import java.util.function.Supplier;

import org.frc5274.montylib.config.Direction.AngularDirection;

public class AbsoluteEncoder {
    public Supplier<Double> absolutePositionSupplier = null;
    public double positionOffset = 0.0;
    public boolean reversePosition = false;
    public double gearRatio = 1.0;

    public void setSensorPositionSupplier(Supplier<Double> position_supplier) {
        absolutePositionSupplier = position_supplier;
    }

    public void setSensorOffset(double offset) {
        positionOffset = offset;
    }

    public void setSensorDirection(AngularDirection direction) {
        reversePosition = direction.isInverted();
    }

    public void setSensorGearRatio(double gear_ratio) {
        gearRatio = gear_ratio;
    }

    public double getAbsolutePosition(boolean actuated) {
        double position = reversePosition ? -absolutePositionSupplier.get() : absolutePositionSupplier.get();
        return actuated ? position * gearRatio : position;
    }
}
