package org.frc5274.crescendo.robotcode.commands.launcher;

import java.util.function.Supplier;

import org.frc5274.crescendo.robotcode.systems.launcher.Launcher;
import org.frc5274.crescendo.robotcode.systems.launcher.LauncherGoal.LauncherMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class DefaultLauncherCMD extends Command {

  private final Launcher launcher;
  private final LauncherMode launcherPreset;

  private final Supplier<Double> launcherInput, primerInput;

  private final Timer idleTime;

  public DefaultLauncherCMD(Launcher launcher_subsystem, LauncherMode mode, CommandXboxController controller) {

    launcher = launcher_subsystem;
    launcherPreset = mode;

    launcherInput = () -> controller.getRightTriggerAxis() - controller.getLeftTriggerAxis();
    primerInput = () -> controller.getHID().getRightBumper() ? 1.0 : controller.getHID().getLeftBumper() ? -1.0 : 0.0;

    idleTime = new Timer();

    addRequirements(launcher_subsystem);
  }

  public DefaultLauncherCMD(Launcher launcher_subsystem, LauncherMode preset, CommandXboxController controller, double primer_power) {

    launcher = launcher_subsystem;
    launcherPreset = preset;

    launcherInput = () -> controller.getRightTriggerAxis() - controller.getLeftTriggerAxis();
    primerInput = () -> primer_power;

    idleTime = new Timer();

    addRequirements(launcher_subsystem);
  }

  @Override
  public void initialize() {
    idleTime.restart();
  }

  @Override
  public void execute() {
    if (launcher.hasInput()) {idleTime.restart();}
    
    if(idleTime.hasElapsed(3)) {
      launcher.setDesiredGoal(LauncherMode.IDLE, 0, 0);
    } else {
      launcher.setDesiredGoal(launcherPreset, launcherInput.get(), primerInput.get());
    }
  }

  @Override
  public void end(boolean interrupted) {
    launcher.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
