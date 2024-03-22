package org.frc5274.crescendo.robotcode.systems.superstructure;

import org.frc5274.montylib.vision.LimelightHandler;
import org.frc5274.montylib.vision.TargetCamera;

public class Chassis {
    public final TargetCamera apriltagLimelight = new TargetCamera(
        () -> LimelightHandler.getTX(""), 
        () -> LimelightHandler.getTY(""), 
        false
    );
}
