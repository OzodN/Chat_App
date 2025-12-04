package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record Chat(String sender, String message, LocalTime time) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String toString() {
        return "%s,%s,%s".formatted(sender, message, time.format(FORMATTER));
    }
}
