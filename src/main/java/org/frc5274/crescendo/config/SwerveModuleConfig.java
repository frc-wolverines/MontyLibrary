package org.frc5274.crescendo.config;

import org.frc5274.montylib.config.Direction.MotorDirection;
import org.frc5274.montylib.config.MotorBehavior.ZeroPowerBehavior;
import org.frc5274.montylib.interfaces.hardware.actuators.NeoV1;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase.IdleMode;

public class SwerveModuleConfig {
    public final int propulsion_id, swivel_id;
    public final MotorDirection propulsion_direction, swivel_direction;
    public final IdleMode propulsion_idle_mode, swivel_idle_mode;
    public final NeutralModeValue propulsion_neutral_mode, swivel_neutral_mode;

    public SwerveModuleConfig(
        int propulsion_id, 
        int swivel_id,
        MotorDirection propulsion_direction, 
        MotorDirection swivel_direction,
        ZeroPowerBehavior propulsion_idle_behavior,
        ZeroPowerBehavior swivel_idle_behavior
    ) {
        this.propulsion_id = propulsion_id;
        this.propulsion_direction = propulsion_direction;
        this.propulsion_idle_mode = propulsion_idle_behavior.toIdleMode();
        this.propulsion_neutral_mode = propulsion_idle_behavior.toNeutralMode();

        this.swivel_id = swivel_id;
        this.swivel_direction = swivel_direction;
        this.swivel_idle_mode = swivel_idle_behavior.toIdleMode();
        this.swivel_neutral_mode = swivel_idle_behavior.toNeutralMode();
    }
}
