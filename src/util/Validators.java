package util;

import java.util.regex.Pattern;

public final class Validators {
    private Validators() {}

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PASSWORD =
            Pattern.compile("^.{4,}$"); // минимум 4 символа

    public static boolean validEmail(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    public static boolean validPassword(String password) {
        return password != null && PASSWORD.matcher(password).matches();
    }
}
