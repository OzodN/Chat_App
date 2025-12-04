package repository;

import model.Chat;
import model.User;
import util.ChatUtils;
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

public class FileChatRepository implements ChatRepository {
    private final Path file;

    public FileChatRepository(String filePath) {
        this.file = Paths.get(filePath);
        try {
            if (!Files.exists(file)) Files.createFile(file);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file", e);
        }
    }

    @Override
    public synchronized List<Chat> findAll() {
        try (BufferedReader br = Files.newBufferedReader(file)) {
            return br.lines()
                    .filter(line -> !line.isBlank())
                    .map(ChatUtils::fromJson)
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public synchronized void save(Chat chatMassage) {
        try(BufferedWriter bw = Files.newBufferedWriter(file, StandardOpenOption.APPEND)){
            bw.write(chatMassage.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
