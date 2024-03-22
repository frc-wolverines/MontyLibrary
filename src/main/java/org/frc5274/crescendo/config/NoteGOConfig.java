package org.frc5274.crescendo.config;

import org.frc5274.montylib.config.Direction.MotorDirection;

public class NoteGOConfig {
    public final int front_intake_id;
    public final int rear_intake_id;
    public final int indexer_id;

    public final MotorDirection front_intake_direction;
    public final MotorDirection rear_intake_direction;
    public final MotorDirection indexer_direction;

    public NoteGOConfig(
        int front_intake_id, 
        int rear_intake_id, 
        int indexer_id,
        MotorDirection front_intake_direction,
        MotorDirection rear_intake_direction,
        MotorDirection indexer_direction
    ) {

        this.front_intake_id = front_intake_id;
        this.front_intake_direction = front_intake_direction;

        this.rear_intake_id = rear_intake_id;
        this.rear_intake_direction = rear_intake_direction;

        this.indexer_id = indexer_id;
        this.indexer_direction = indexer_direction;

    }
}
