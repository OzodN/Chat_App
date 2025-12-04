package util;

import model.User;

import java.time.LocalTime;

public class UserUtils {
    public static User fromJson(String line) {
        String[] p = line.split(",");
        return new User(
                p[0],
                p[1],
                LocalTime.parse(p[2])
        );
    }
}
