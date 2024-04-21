package org.frc5274.montylib.config.mapping;

import org.frc5274.montylib.logging.MessageLog;
import org.frc5274.montylib.logging.MessageLog.MessageType;
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
            new MessageLog(name + ": expected type not found", MessageType.ERROR);
            return null; 
        }
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
