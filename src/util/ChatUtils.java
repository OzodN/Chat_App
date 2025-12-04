package util;

import model.Chat;

import java.time.LocalTime;

public class ChatUtils {
    public static Chat fromJson(String line) {
        String[] p = line.split(",");
        return new Chat(
                p[0],
                p[1],
                LocalTime.parse(p[2])
        );
    }
}
