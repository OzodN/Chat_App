package util;

import service.UserService;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SingleLogger {
    static {
        try {
            String file = Objects.requireNonNull(
                            UserService.class
                                    .getClassLoader().getResource("logging.properties"),
                            "logging.properties not found")
                    .getFile();
            System.setProperty("java.util.logging.config.file", file);
            LogManager.getLogManager().readConfiguration();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SingleLogger() {
    }

    private static class Holder {
        private static final Logger logger = Logger.getLogger("Chat_App");
    }

    public static Logger getInstance() {
        return Holder.logger;
    }
}
