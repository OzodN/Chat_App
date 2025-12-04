package controller;

import model.Chat;
import model.User;
import repository.*;
import service.*;
import util.SingleLogger;

import java.util.Objects;
import java.util.Scanner;
import java.util.logging.*;
import java.util.List;
import java.util.Optional;

public class LaunchChat {
    private static final UserService userService = new UserService();
    private static final ChatService chatService = new ChatService();
    private static final Scanner sc = new Scanner(System.in);
    public static final Logger logger = SingleLogger.getInstance();
    private static Optional<User> current = Optional.empty();


    public void launch() {
        authMenu();
    }

    private void authMenu() {
        while (true) {
            System.out.print("""
                    
                    1. Registration
                    2. Login
                    Choose:\
                    """);
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "1" -> {
                    current = register();
                    menu();
                }
                case "2" -> {
                    current = login();
                    menu();
                }
                default -> {
                    logger.warning("Invalid input");
                    return;
                }
            }
        }
    }

    private static void menu() {
        logger.info("Добро пожаловать, " + current.get().username());
        while (true) {
            System.out.print("""
                    
                    1. Show chat
                    2. Send Message
                    3. Exit
                    Выберите:\
                    """);
            String cmd = sc.nextLine().trim();
            switch (cmd) {
                case "1" -> displayChat();
                case "2" -> sendMessage();
                case "3" -> {
                    logger.info("Пока!");
                    return;
                }
                default -> {
                    logger.warning("Неизвестная команда.");
                    return;
                }
            }
        }
    }

    private static void sendMessage() {
        System.out.print("Message: ");
        String text = sc.nextLine();
        current.ifPresent(user -> chatService.send(user.username(), text));
    }

    private static void displayChat() {
        List<Chat> all = chatService.getAll();
        if (all.isEmpty()) {
            logger.warning("Empty chat.");
        } else {
            all.forEach(m -> System.out.println("[" + m.time() + "] " + m.sender() + ": " + m.message()));
        }
    }

    private static Optional<User> login() {
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Password: ");
        String pass = sc.nextLine().trim();
        current.ifPresent(user -> userService.login(email, pass));
        return current;
    }

    private Optional<User> register() {
        System.out.print("Email: ");
        String email = Objects.requireNonNull(sc.nextLine().trim());
        System.out.print("Password: ");
        String pass = Objects.requireNonNull(sc.nextLine().trim());
        current = userService.register(email, pass) ? userService.login(email, pass) : Optional.empty();
        if (current.isEmpty()) logger.warning("Invalid credentials");
        return current;
    }
}

