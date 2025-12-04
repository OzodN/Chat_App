package service;

import model.User;
import repository.FileUserRepository;
import repository.UserRepository;
import util.SingleLogger;
import util.Validators;

import java.time.LocalTime;
import java.util.Optional;
import java.util.logging.Logger;

public class UserService {
    private static final String USERS_FILE = "src/data/users.txt";
    private final UserRepository userRepo = new FileUserRepository(USERS_FILE);
    private final Logger logger = SingleLogger.getInstance();

    public UserService() {
        userRepo.findAll();
    }

    public boolean register(String email, String password) {
        if (!Validators.validEmail(email)) {
            logger.warning("Invalid e-mail.");
            return false;
        }
        if (!Validators.validPassword(password)) {
            logger.warning("password > 4");
            return false;
        }
        Optional<User> exists = userRepo.findByUsername(email);
        if (exists.isPresent()) {
            logger.warning("User already exists.");
            return false;
        }

        User user = new User(email, password, LocalTime.now());
        userRepo.save(user);
        logger.info("REGISTER: " + email + " at " + user.registrationTime().toString());
        return true;
    }

    public Optional<User> login(String email, String password) {
        Optional<User> u = userRepo.findByUsername(email);
        if (u.isPresent() && u.get().password().equals(password)) {
            logger.info("LOGIN: " + email + " at " + LocalTime.now());
            return u;
        } else {
            return Optional.empty();
        }
    }
}
