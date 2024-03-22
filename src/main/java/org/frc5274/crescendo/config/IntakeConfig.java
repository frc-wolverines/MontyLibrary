package org.frc5274.crescendo.config;

import org.frc5274.montylib.config.Direction.MotorDirection;

public class IntakeConfig {
    
    public final int front_wheel_id;
    public final int rear_wheel_id;

    public final MotorDirection front_wheel_direction;
    public final MotorDirection rear_wheel_direction;

    public IntakeConfig(
        int front_wheel_id, 
        int rear_wheel_id, 
        MotorDirection front_wheel_direction, 
        MotorDirection rear_wheel_direction
    ) {

        this.front_wheel_id = front_wheel_id;
        this.front_wheel_direction = front_wheel_direction;

        this.rear_wheel_id = rear_wheel_id;
        this.rear_wheel_direction = rear_wheel_direction;

    }
}
