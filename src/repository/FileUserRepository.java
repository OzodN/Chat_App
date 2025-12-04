package repository;

import model.User;
import util.UserUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

public class FileUserRepository implements UserRepository {
    private final Path file;

    public FileUserRepository(String filePath) {
        this.file = Paths.get(filePath);
        try {
            if (!Files.exists(file)) Files.createFile(file);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file", e);
        }
    }

    @Override
    public synchronized List<User> findAll() {
        try (BufferedReader br = Files.newBufferedReader(file)) {
            return br.lines()
                    .filter(line -> !line.isBlank())
                    .map(UserUtils::fromJson)
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public synchronized Optional<User> findByUsername(String username) {
        return findAll().stream()
                .filter(u -> u.username().equals(username))
                .findFirst();
    }

    @Override
    public synchronized void save(User user) {
        try(BufferedWriter bw = Files.newBufferedWriter(file, StandardOpenOption.APPEND)){
            bw.write(user.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
