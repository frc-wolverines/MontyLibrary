package org.frc5274.montylib.config;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase.IdleMode;

public class MotorBehavior {
    public enum ZeroPowerBehavior {
        BRAKE, 
        COAST;

        public IdleMode toIdleMode() {
            return this == BRAKE ? IdleMode.kBrake : IdleMode.kCoast;
        }

        public NeutralModeValue toNeutralMode() {
            return this == BRAKE ? NeutralModeValue.Brake : NeutralModeValue.Coast;
        }
    }
}
