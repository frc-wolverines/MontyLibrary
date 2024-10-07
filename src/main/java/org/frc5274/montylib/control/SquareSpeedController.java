package org.frc5274.montylib.control;

import java.util.function.Supplier;

public class SquareSpeedController {
    
    public double lowSpeed;
    public double normalSpeed;
    public double highSpeed;

    public Supplier<Boolean> lowSpeedEnabled;
    public Supplier<Boolean> highSpeedEnabled;

    /**
     * Constructs a new instance of SquareSpeedController, where values do not bridge and are instantly changed.
     * @param low_speed the lowest achievable speed desired
     * @param normal_speed the normal achieved speed desired
     * @param high_speed the highest achievable speed desired
     * @param low_speed_enabled a boolean supplier describing whether the lowest speed should be outputed 
     * @param high_speed_enabled a boolean supplier describing whether the highest speed should be outputed 
     */
    public SquareSpeedController(
        double low_speed, 
        double normal_speed, 
        double high_speed, 
        Supplier<Boolean> low_speed_enabled, 
        Supplier<Boolean> high_speed_enabled) {

        lowSpeed = low_speed;
        normalSpeed = normal_speed;
        highSpeed = high_speed;
        lowSpeedEnabled = low_speed_enabled;
        highSpeedEnabled = high_speed_enabled;

    }   

    /**
     * Performs logic checks to determin the output speed
     * @return the desired speed given the values of constructor parameters
     */
    public double get() {
        return lowSpeedEnabled.get() ? lowSpeed : (highSpeedEnabled.get() ? highSpeed : normalSpeed);
    }
}
