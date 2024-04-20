package org.frc5274.montylib.devices;

import org.frc5274.montylib.config.PIDConstants;
import org.frc5274.montylib.config.Direction.AngularDirection;
import org.frc5274.montylib.config.MotorBehavior.IdleBehavior;

public class MotorConfig {
    public final int id;
    public final IdleBehavior idle_behavior;
    public final AngularDirection direction;
    public final PIDConstants position_constants;
    public final PIDConstants velocity_constants;
    public final double gear_ratio;

    public MotorConfig (
        int id,
        IdleBehavior idle_behavior,
        AngularDirection direction,
        PIDConstants position_constants,
        PIDConstants velocity_constants,
        double gear_ratio
    ) {

        this.id = id;
        this.idle_behavior = idle_behavior;
        this.direction = direction;
        this.position_constants = position_constants;
        this.velocity_constants = velocity_constants;
        this.gear_ratio = gear_ratio;

    }
}
