package org.frc5274.montylib.config.mapping;

import org.frc5274.montylib.config.PIDConstants;
import org.frc5274.montylib.config.Direction.AngularDirection;
import org.frc5274.montylib.config.MotorBehavior.IdleBehavior;
import org.frc5274.montylib.devices.motors.MotorConfig;
import org.frc5274.montylib.devices.sensors.EncoderConfig;
import org.frc5274.montylib.logging.MessageLog;
import org.frc5274.montylib.logging.MessageLog.MessageType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MappingReader {
    
    JSONObject source;

    public MappingReader(JSONObject source) {
        this.source = source;
    }

    public JSONObject getObject(String name, MappingType expected_type) {
        JSONObject object = (JSONObject) source.get(name);

        if(MappingType.fromKey((String) object.get("type")) == expected_type) {
            return object;
        } else {
            new MessageLog(name + ": expected mapping type not found", MessageType.ERROR);
            return null; 
        }
    }

    public MotorConfig toMotorConfig(String motor_name) {
        
        JSONObject mapping = getObject(motor_name, MappingType.MOTOR);

        int id = ((Long) mapping.get("id")).intValue();
        AngularDirection direction;
        IdleBehavior idleBehavior;
        
        if((String) mapping.get("direction") == "clockwise") {
            direction = AngularDirection.CLOCKWISE;
        } else if ((String) mapping.get("direction") == "counter_clockwise") {
            direction = AngularDirection.COUNTER_CLOCKWISE;
        } else {
            new MessageLog(motor_name + ": direction mapping invalid, default: COUNTER CLOCKWISE", MessageType.WARNING);
            direction = AngularDirection.COUNTER_CLOCKWISE;
        }

        if((String) mapping.get("behavior") == "brake") {
            idleBehavior = IdleBehavior.BRAKE;
        } else if ((String) mapping.get("behavior") == "coast") {
            idleBehavior = IdleBehavior.COAST;
        } else {
            new MessageLog(motor_name + ": behavior mapping invalid, default: COAST", MessageType.WARNING);
            idleBehavior = IdleBehavior.COAST;
        }

        JSONObject pidConstants = (JSONObject) mapping.get("pidconstants");

        JSONArray positionConstantObject = (JSONArray) pidConstants.get("position");
        JSONArray velocityConstantsObject = (JSONArray) pidConstants.get("velocity");

        PIDConstants positionConstants = new PIDConstants(
            (Double) positionConstantObject.get(0), 
            (Double) positionConstantObject.get(1), 
            (Double) positionConstantObject.get(2)
        );

        PIDConstants velocityConstants = new PIDConstants(
            (Double) velocityConstantsObject.get(0), 
            (Double) velocityConstantsObject.get(1), 
            (Double) velocityConstantsObject.get(2)
        );

        double gearRatio = (Double) mapping.get("ratio");

        return new MotorConfig(
            id, 
            idleBehavior, 
            direction, 
            positionConstants, 
            velocityConstants, 
            gearRatio
        );
    }

    public EncoderConfig toEncoderConfig(String encoder_name) {
        
        JSONObject mapping = getObject(encoder_name, MappingType.ENCODER);

        int id = ((Long) mapping.get("id")).intValue();

        AngularDirection direction;
        
        if((String) mapping.get("direction") == "clockwise") {
            direction = AngularDirection.CLOCKWISE;
        } else if ((String) mapping.get("direction") == "counter_clockwise") {
            direction = AngularDirection.COUNTER_CLOCKWISE;
        } else {
            new MessageLog(encoder_name + ": direction mapping invalid, default: COUNTER CLOCKWISE", MessageType.WARNING);
            direction = AngularDirection.COUNTER_CLOCKWISE;
        }

        double offset = (Double) mapping.get("offset");
        double ratio = (Double) mapping.get("ratio");

        return new EncoderConfig(
            id, 
            ratio, 
            offset, 
            direction
        );
    }

    public int toGenericId(String generic_device_name) {
        JSONObject mapping = getObject(generic_device_name, MappingType.GENERIC);
        return ((Long) mapping.get("id")).intValue();
    }

    public enum MappingType {
        MOTOR,
        ENCODER,
        CAMERA,
        GENERIC;

        public static MappingType fromKey(String key) {
            switch(key) {
                case "generic": return MappingType.GENERIC;
                case "motor": return MappingType.MOTOR;
                case "encoder": return MappingType.ENCODER;
                case "camera": return MappingType.CAMERA;
                default: return MappingType.GENERIC;
            }
        }
    }
}
