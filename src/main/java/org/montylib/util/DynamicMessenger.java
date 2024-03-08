package org.montylib.util;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DynamicMessenger {
    public static void log(String key, double value) {
        SmartDashboard.putNumber(key, value);
    }

    public static void  log(String key, double[] values) {
        SmartDashboard.putNumberArray(key, values);
    }

    public static void log(String key, String value) {
        SmartDashboard.putString(key, value);
    }

    public static void log(String key, String[] values) {
        SmartDashboard.putStringArray(key, values);
    }

    public static void log(String key, Boolean value) {
        SmartDashboard.putBoolean(key, value);
    }

    public static void log(String key, Boolean[] values) {
        SmartDashboard.putBooleanArray(key, values);
    }

    public static void log(String key, Sendable value) {
        SmartDashboard.putData(key, value);
    }
}
