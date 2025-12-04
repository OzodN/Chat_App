package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record User(String username, String password, LocalTime registrationTime) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String toString() {
        return "%s,%s,%s".formatted(username, password, registrationTime.format(FORMATTER));
    }
}
