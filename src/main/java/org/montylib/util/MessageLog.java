package org.montylib.util;

import edu.wpi.first.wpilibj.DriverStation;

public class MessageLog {
    
    public MessageLog(String message, MessageType type) {
        switch (type) {
            case ERROR: DriverStation.reportError(message, false);
            case INFORMATION: System.out.println(TerminalColors.ANSI_PURPLE + message);
            case SUCCESS: System.out.println(TerminalColors.ANSI_GREEN + message);
            case WARNING: DriverStation.reportWarning(message, false);
            default: break;
        }
    }

    public enum MessageType {
        INFORMATION,
        SUCCESS,
        WARNING,
        ERROR    
    }
}
