package org.frc5274.crescendo.robotcode.systems.launcher;

import org.frc5274.crescendo.config.LauncherConfig;
import org.frc5274.crescendo.kinematics.LauncherState;
import org.frc5274.crescendo.robotcode.systems.launcher.LauncherGoal.LauncherMode;
import org.frc5274.montylib.interfaces.hardware.actuators.Falcon500;
import org.frc5274.montylib.interfaces.hardware.actuators.NeoV1;
import org.frc5274.montylib.interfaces.hardware.sensors.BeamSensor;
import org.frc5274.montylib.util.DynamicDataLog;
import org.frc5274.montylib.util.PIDConstants;
import org.frc5274.montylib.vision.LimelightHandler;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**A class representing the Launcher Pod consisting of the Launching flywheels, Primer wheels, and Pivoting rack & pinion*/
public class Launcher extends SubsystemBase {

    //Constants
    public final static PIDConstants launcherPivotConstants = new PIDConstants(0.0, 0.0, 0.0);
    
    private final Falcon500 topLauncherMotor, bottomLauncherMotor;
    private final Falcon500 launcherPivotMotor;
    private final NeoV1 primerMotor;

    private final BeamSensor noteStatusDetector;

    private boolean feedbackRequested = false;
    private LauncherMode currentMode;

    public Launcher(LauncherConfig config) {

        topLauncherMotor = new Falcon500(config.top_launcher_id);
        topLauncherMotor.setInverted(config.top_launcher_direction.isInverted());
        topLauncherMotor.setNeutralMode(NeutralModeValue.Coast);
        topLauncherMotor.setActuationFactor(1);

        bottomLauncherMotor = new Falcon500(config.bottom_launcher_id);
        bottomLauncherMotor.setInverted(config.bottom_launcher_direction.isInverted());
        bottomLauncherMotor.setNeutralMode(NeutralModeValue.Coast);
        bottomLauncherMotor.setActuationFactor(1);

        launcherPivotMotor = new Falcon500(config.launcher_pivot_id);
        launcherPivotMotor.setInverted(config.launcher_pivot_direction.isInverted());
        launcherPivotMotor.setNeutralMode(NeutralModeValue.Brake);
        launcherPivotMotor.setActuationFactor(1);
        launcherPivotMotor.setPositionPIDConstants(launcherPivotConstants);


        primerMotor = new NeoV1(config.primer_id);
        primerMotor.setInverted(config.primer_direction.isInverted());
        primerMotor.setIdleMode(IdleMode.kBrake);

        noteStatusDetector = new BeamSensor(0);
    }

    @Override
    public void periodic() {
        handleFeedback();
    }

    public void enableFeedback(boolean enabled) {
        feedbackRequested = enabled;
    }

    public void handleFeedback() {
        if (feedbackRequested) {
            DynamicDataLog.log("LAUNCHER - Top Launcher Velocity", topLauncherMotor.getActuatedVelocity());
            DynamicDataLog.log("LAUNCHER - Top Launcher Motor", topLauncherMotor);
            DynamicDataLog.log("LAUNCHER - Bottom Launcher Velocity", bottomLauncherMotor.getActuatedVelocity());
            DynamicDataLog.log("LAUNCHER - Bottom Launcher Motor", bottomLauncherMotor);
            DynamicDataLog.log("LAUNCHER - Launcher Angle", launcherPivotMotor.getActuatedPosition());
            DynamicDataLog.log("LAUNCHER - Launcher Pivot Motor", launcherPivotMotor);
            DynamicDataLog.log("LAUNCHER - Primer Power", primerMotor.get());
            DynamicDataLog.log("LAUNCHER - Note Primed", isNotePrimed());
            DynamicDataLog.log("LAUNCHER - Has Target", hasTarget());
            DynamicDataLog.log("LAUNCHER - Is Launch Good", isLaunchGood());
        }
    }

    public boolean isNotePrimed() {
        return !noteStatusDetector.get();
    }

    public boolean hasTarget() {
        return LimelightHandler.getTV("");
    }

    public boolean hasInput() {
        if (topLauncherMotor.get() != 0 && bottomLauncherMotor.get() != 0 && launcherPivotMotor.get() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLaunchGood() {
        return (getLauncherVelocity()[0] >= currentMode.getGoodVelocity()) && (getLauncherVelocity()[1] >= currentMode.getGoodVelocity());
    }

    public double getLauncherAngle() {
        return launcherPivotMotor.getActuatedPosition();
    }

    public double[] getLauncherVelocity() {
        return new double[] {
            topLauncherMotor.getActuatedVelocity(),
            bottomLauncherMotor.getActuatedVelocity()
        };
    }

    public void setDesiredState(LauncherState state) {

        topLauncherMotor.set(state.getLauncherPower());
        bottomLauncherMotor.set(state.getLauncherPower());
        primerMotor.set(state.getPrimerPower());

        launcherPivotMotor.runToActuatedPosition(state.getLauncherAngle());

    }

    public void setDesiredGoal(LauncherMode mode, double launcher_input, double primer_input) {

        currentMode = mode;

        topLauncherMotor.set(mode.getOutputPower(launcher_input));
        bottomLauncherMotor.set(mode.getOutputPower(launcher_input));
        primerMotor.set(primer_input);

        launcherPivotMotor.runToActuatedPosition(mode.getOutputAngle());

    }

    public void stop() {
        topLauncherMotor.stopMotor();
        bottomLauncherMotor.stopMotor();
        launcherPivotMotor.stopMotor();
        primerMotor.stopMotor();
    }
}
