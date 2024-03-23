package org.frc5274.crescendo.robotcode.commands.notego;

import java.util.function.Supplier;

import org.frc5274.crescendo.kinematics.NoteGOState;
import org.frc5274.crescendo.robotcode.systems.notego.NoteGO;

import edu.wpi.first.wpilibj2.command.Command;

public class DefaultNoteGOCMD extends Command {

  private final NoteGO noteGO;
  private final Supplier<Double> GOPower;

  public DefaultNoteGOCMD(NoteGO note_go, double input_power) {
    noteGO = note_go;
    GOPower = () -> input_power;
    addRequirements(note_go);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    noteGO.setDesiredState(new NoteGOState(GOPower.get()));
  }

  @Override
  public void end(boolean interrupted) {
    noteGO.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
