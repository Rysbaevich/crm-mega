package util;

import java.time.LocalDateTime;

public class Log {
    public static void info(String message, String className, String targetName) {
        System.out.printf("%s [INFO] ----- %s ----- %s ----- %s%n", LocalDateTime.now(), className, targetName, message);
    }

    public static void error(String message, String className, String targetName) {
        System.err.printf("%s [ERROR] ----- %s ----- %s ----- %s%n", LocalDateTime.now(), className, targetName, message);
    }
}
