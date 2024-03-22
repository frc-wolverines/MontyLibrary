package org.frc5274.crescendo.config;

import org.frc5274.montylib.config.Direction.MotorDirection;

public class LauncherConfig {
    
    public final int top_launcher_id, bottom_launcher_id;
    public final int launcher_pivot_id;
    public final int primer_id;

    public final MotorDirection top_launcher_direction, bottom_launcher_direction;
    public final MotorDirection launcher_pivot_direction;
    public final MotorDirection primer_direction;

    public LauncherConfig (
        int top_launcher_id, int bottom_launcher_id, 
        int launcher_pivot_id, 
        int primer_id,
        MotorDirection top_launcher_direction, MotorDirection bottom_launcher_direction,
        MotorDirection launcher_pivot_direction,
        MotorDirection primer_direction
    ) {
        
        this.top_launcher_id = top_launcher_id;
        this.top_launcher_direction = top_launcher_direction;

        this.bottom_launcher_id = bottom_launcher_id;
        this.bottom_launcher_direction = bottom_launcher_direction;

        this.launcher_pivot_id = launcher_pivot_id;
        this.launcher_pivot_direction = launcher_pivot_direction;

        this.primer_id = primer_id;
        this.primer_direction = primer_direction;
    
    }
}
