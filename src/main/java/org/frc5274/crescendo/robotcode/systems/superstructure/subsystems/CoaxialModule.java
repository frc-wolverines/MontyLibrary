package org.frc5274.crescendo.robotcode.systems.superstructure.subsystems;

import org.frc5274.crescendo.config.SwerveModuleConfig;
import org.frc5274.montylib.interfaces.hardware.actuators.NeoV1;
import org.frc5274.montylib.interfaces.hardware.vendors.SDSHardware.MK4i;
import org.frc5274.montylib.interfaces.software.AbstractCoaxialSwerveModule;
import org.frc5274.montylib.util.PIDConstants;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;

public class CoaxialModule extends AbstractCoaxialSwerveModule {

    private final NeoV1 propulsion, swivel;

    public static PIDConstants swivelPositionConstants = new PIDConstants(0, 0, 0);
    public static PIDConstants propulsionVelocityConstants = new PIDConstants(0, 0, 0);
    public static double trackLength = 0.0;
    public static double maxSpeedMetersPerSecond = 0.0;
    
    public CoaxialModule(SwerveModuleConfig config) {
        propulsion = new NeoV1(config.propulsion_id);
        propulsion.setInverted(config.propulsion_direction.isInverted());
        propulsion.setIdleMode(config.propulsion_idle_mode);
        propulsion.setActuationFactor(MK4i.drive_actuation_factors[0]);
        propulsion.setVelocityPIDConstants(propulsionVelocityConstants);

        swivel = new NeoV1(config.swivel_id);
        swivel.setInverted(config.propulsion_direction.isInverted());
        swivel.setIdleMode(config.propulsion_idle_mode);
        swivel.setActuationFactor(MK4i.steer_actuation_factor);
        swivel.setPositionPIDConstants(swivelPositionConstants);
    }

    @Override
    public void resetSwivelPosition(double new_position) {
        swivel.getEncoder().setPosition(new_position);
    }

    @Override
    public void resetTrackPosition(double new_position) {
        propulsion.getEncoder().setPosition(new_position);
    }

    @Override
    public double getSwivelPosition() {
        return Units.rotationsToRadians(swivel.getActuatedPosition());
    }

    @Override
    public Rotation2d getSwivelRotation2d() {
        return Rotation2d.fromRadians(getSwivelPosition());
    }

    @Override
    public double getSwivelVelocity() {
        return Units.rotationsToRadians(swivel.getActuatedVelocity()) / 60;
    }

    @Override
    public double getTrackPosition() {
        return propulsion.getActuatedPosition() * trackLength;
    }

    @Override
    public double getTrackVelocity() {
        return (propulsion.getActuatedVelocity() * trackLength) / 60;
    }

    @Override
    public SwerveModuleState getModuleState() {
        return new SwerveModuleState(getTrackVelocity(), getSwivelRotation2d());
    }

    @Override
    public SwerveModulePosition getModulePosition() {
        return new SwerveModulePosition(getTrackPosition(), getSwivelRotation2d());
    }

    @Override
    public void setDesiredModuleState(SwerveModuleState state) {
        if (state.speedMetersPerSecond < 0.001) {
            stopModule();
            return;
        }

        state = SwerveModuleState.optimize(state, getSwivelRotation2d());

        propulsion.runToActuatedVelocity(state.speedMetersPerSecond / trackLength);
        swivel.runToActuatedPosition(state.angle.getRotations());
    }

    @Override
    public void stopModule() {
        propulsion.stopMotor();
        swivel.stopMotor();
    }
}
