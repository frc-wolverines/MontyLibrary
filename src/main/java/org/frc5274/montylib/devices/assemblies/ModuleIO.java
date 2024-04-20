package org.frc5274.montylib.devices.assemblies;

import org.frc5274.montylib.devices.EncodedMotor;

import com.ctre.phoenix6.hardware.CANcoder;

public class ModuleIO {
    
    public EncodedMotor driveMotor;
    public EncodedMotor pivotMotor;
    public CANcoder moduleEncoder;

    public ModuleIO(EncodedMotor drive_motor_object, EncodedMotor pivot_motor_object, CANcoder module_encoder) {
        driveMotor = drive_motor_object;
        pivotMotor = pivot_motor_object;
        moduleEncoder = module_encoder;
    }
}
